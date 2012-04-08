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
import cms.entity.account.Arcatt;
import cms.service.account.ArcattManager;
import cms.web.CrudActionSupport;

@Results( { @Result(name = CrudActionSupport.RELOAD, location = "arcatt.action", type = "redirect") })
public class ArcattAction extends CrudActionSupport<Arcatt> {
	
	private Long id;
	
	private Page page = new Page<Arcatt>(10);
	
	private ArcattManager am;
	private Arcatt entity;
	
	@Override
	public String delete() throws Exception {
		am.delete(id);
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "arcatt","forward"));
		
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(Struts2Utils.getRequest());
		
		page = PageRIA.RiaToPage(page);//富客户端的page转为系统page的变量
		
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = am.searchArchives(page, filters);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			entity = am.getArcatt(id);
		}else{
			entity = new Arcatt();
		}
	}

	@Override
	public String save() throws Exception {	
		am.save(entity);

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "arcatt","closeCurrent"));

		return null;
	}

	public Arcatt getModel() {
		return entity;
	}

	public Page getPage() {
		return page;
	}
	
	@Autowired
	public void setAm(ArcattManager am) {
		this.am = am;
	}

	public Arcatt getEntity() {
		return entity;
	}

	public void setEntity(Arcatt entity) {
		this.entity = entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
