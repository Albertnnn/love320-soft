package cms.web.account;

import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.PageRIA;
import cms.bean.RiaJsonObject;
import cms.dao.HibernateUtils;
import cms.entity.account.Arcatt;
import cms.entity.account.TagLabel;
import cms.entity.account.TagLabelType;
import cms.service.account.ArcattManager;
import cms.service.account.ServicesManager;
import cms.service.account.TagLabelManager;
import cms.service.account.Typeunit;
import cms.web.CrudActionSupport;
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "taglabel.action", type = "redirect") })
public class TaglabelAction extends CrudActionSupport<TagLabel> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id ;
	private TagLabel entity;
	private Page<TagLabel> page = new Page<TagLabel>(10); 
	
	private List<Typeunit> typeList;//栏目	
	private List<Arcatt> acratts;//文档属性列表
	private List<Long> checkedAcattIds;//页面中钩选的文档属性id列表
	
	private Long tltId ;//标签类型id
	private List<TagLabelType> tagLabelTypeList;
	private ServicesManager sm;
	
	private TagLabelManager tagLabelManager ;
	
	//文档属性管理manager
	private ArcattManager arcattManager;

	
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		page = PageRIA.RiaToPage(page);//富客户端的page转为系统page的变量
		
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = tagLabelManager.searchArchives(page, filters);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			entity = tagLabelManager.getTagbel(id);
			tltId = entity.getTagLabelType().getId();//设置标签类型ID
		}else{
			entity = new TagLabel();
		}
		
	}
	
	@Override
	public String delete() throws Exception {
		tagLabelManager.delete(id);
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "taglabel","forward"));
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String input() throws Exception {

		
		tagLabelTypeList = sm.getTagLabelTypeManager().getAll();
		
		typeList = tagLabelManager.getListTypeunit();
		acratts = arcattManager.getAll();
		checkedAcattIds = entity.getflagIds();
		
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		//根据页面上的checkbox 整合Arcatt的Arcatt Set.
		HibernateUtils.mergeByCheckedIds(entity.getFlagList(),checkedAcattIds, Arcatt.class);
		//System.out.println("tltId:"+tltId);
		entity.setTagLabelType(sm.getTagLabelTypeManager().getEntity(tltId));//设置关联标签类型
		
		tagLabelManager.Save(entity);
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "taglabel","closeCurrent"));

		return null;
	}

	public TagLabel getModel() {
		return entity;
	}

	public Page<TagLabel> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	public void setTagLabelManager(TagLabelManager tagLabelManager) {
		this.tagLabelManager = tagLabelManager;
	}

	public List<Typeunit> getTypeList() {
		return typeList;
	}

	public List<Long> getCheckedAcattIds() {
		return checkedAcattIds;
	}

	public void setCheckedAcattIds(List<Long> checkedAcattIds) {
		this.checkedAcattIds = checkedAcattIds;
	}

	public List<Arcatt> getAcratts() {
		return acratts;
	}

	@Autowired
	public void setArcattManager(ArcattManager arcattManager) {
		this.arcattManager = arcattManager;
	}

	public Long getTltId() {
		return tltId;
	}

	public void setTltId(Long tltId) {
		this.tltId = tltId;
	}

	public List<TagLabelType> getTagLabelTypeList() {
		return tagLabelTypeList;
	}

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}
	

	
	


}
