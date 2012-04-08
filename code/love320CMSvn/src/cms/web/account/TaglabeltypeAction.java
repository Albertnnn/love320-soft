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
import cms.entity.account.TagLabelType;
import cms.service.account.ServicesManager;
import cms.service.account.TagLabelTypeManager;
import cms.web.CrudActionSupport;

@Results({
	@Result(name = CrudActionSupport.RELOAD, location  = "taglabeltype.action", type = "redirect") 
})
public class TaglabeltypeAction extends CrudActionSupport<TagLabelType> {
	
	private Long id ;
	private TagLabelType entity;//标签类型对象
	private ServicesManager sm;//服务层管理对象（提供服务对象进行服务）
	
	private Page page = new Page<TagLabelType>(10);
	

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		
		page = PageRIA.RiaToPage(page);//富客户端的page转为系统page的变量
		
		//设置默认排序方式
		if(!page.isOrderBySetted()){
			page.setOrder(page.DESC);
			page.setOrderBy("id");
		}
		
		TagLabelTypeManager tltm = sm.getTagLabelTypeManager();
		page = tltm.searchArchives(page, filters);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			entity = sm.getTagLabelTypeManager().getEntity(id);
		}else{
			entity = new TagLabelType();
		}
	}

	@Override
	public String save() throws Exception {
		sm.getTagLabelTypeManager().save(entity);

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "taglabeltype","closeCurrent"));

		return null;
		
	}

	public TagLabelType getModel() {
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}

	public Page getPage() {
		return page;
	}

	
	

}
