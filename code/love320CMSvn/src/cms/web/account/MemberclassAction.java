package cms.web.account;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.PageRIA;
import cms.bean.RiaJsonObject;
import cms.entity.account.MemberClass;
import cms.service.account.MemberClassManager;
import cms.web.CrudActionSupport;

public class MemberclassAction extends CrudActionSupport<MemberClass> {

	private MemberClassManager mcm;
	private MemberClass entity;
	private Long id;
	private Long ids[];
	private Page page = new Page<MemberClass>(10);
	
	@Override
	public String delete() throws Exception {
		mcm.deleteId(id);
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "memberclass","forward"));
		return null;
	}

	@Override
	public String input() throws Exception {
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
		
		page = mcm.searchList(page,filters);
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			entity = mcm.getEntity(id);
		}else{
			entity = new MemberClass();
		}
	}

	@Override
	public String save() throws Exception {
		mcm.save(entity);
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "memberclass","closeCurrent"));
		return null;
	}

	public MemberClass getModel() {
		return entity;
	}

	@Autowired
	public void setMcm(MemberClassManager mcm) {
		this.mcm = mcm;
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

	
	
	
}
