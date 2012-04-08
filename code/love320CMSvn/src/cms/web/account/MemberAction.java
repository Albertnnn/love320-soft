package cms.web.account;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.PageRIA;
import cms.bean.RiaJsonObject;
import cms.dao.HibernateUtils;
import cms.entity.account.Arcatt;
import cms.entity.account.Member;
import cms.entity.account.MemberClass;
import cms.entity.account.MemberType;
import cms.service.account.MemberClassManager;
import cms.service.account.MemberManager;
import cms.service.account.MemberTypeManager;
import cms.service.account.UcenterClientManager;
import cms.web.CrudActionSupport;

public class MemberAction extends CrudActionSupport<Member> {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long[] ids;//批量处理ids 
	
	private Page page = new Page<Member>(10);
	
	private Member entity;
	
	private MemberManager mm;
	private MemberTypeManager mtm;
	private MemberClassManager mcm;

	private List<MemberClass> mcList;
	private Long mcId;
	
	private List<MemberType> memberTypes;
	private Long mtmId;
	
	private List<Long> checkedMemberClassIds;//页面中钩选的班级属性id列表
	
	@Override
	public String delete() throws Exception {
		
		mm.deleteId(id);
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "member","forward"));
		return null;
	}

	@Override
	public String input() throws Exception {
		checkedMemberClassIds = entity.getMemberClassListsId();
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		
		page = PageRIA.RiaToPage(page);//富客户端的page转为系统page的变量
		
		page = mm.searchArchives(page,filters);
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			entity = mm.getEntity(id);
		}else{
			entity = new Member();
		}
		
		memberTypes = mtm.getAll();//会员类型列表
		mcList = mcm.getAll();//班级列表
	}

	@Override
	public String save() throws Exception {
		
		//根据页面上的checkedMemberClassIds.
		HibernateUtils.mergeByCheckedIds(entity.getMemberClassLists(),checkedMemberClassIds, MemberClass.class);
		
		entity.setMtype(mtm.getEntity(mtmId));//设置会员类型
		entity.setMemberclassstudent(mcm.getEntity(mcId));//设置班级
		mm.save(entity);
			
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "member","closeCurrent"));
		return null;
	}

	public Member getModel() {
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public Page getPage() {
		return page;
	}

	@Autowired
	public void setMm(MemberManager mm) {
		this.mm = mm;
	}

	@Autowired
	public void setMtm(MemberTypeManager mtm) {
		this.mtm = mtm;
	}

	public List<MemberType> getMemberTypes() {
		return memberTypes;
	}

	public void setMtmId(Long mtmId) {
		this.mtmId = mtmId;
	}

	@Autowired
	public void setMcm(MemberClassManager mcm) {
		this.mcm = mcm;
	}

	public Long getMcId() {
		return mcId;
	}

	public void setMcId(Long mcId) {
		this.mcId = mcId;
	}

	public List<MemberClass> getMcList() {
		return mcList;
	}

	public List<Long> getCheckedMemberClassIds() {
		return checkedMemberClassIds;
	}

	public void setCheckedMemberClassIds(List<Long> checkedMemberClassIds) {
		this.checkedMemberClassIds = checkedMemberClassIds;
	}


	
	
	

}
