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
import cms.entity.account.Advertising;
import cms.service.ServiceException;
import cms.service.account.AdvertisingManager;
import cms.service.account.ServicesManager;
import cms.web.CrudActionSupport;


@Results( { @Result(name = CrudActionSupport.RELOAD, location = "advertising.action", type = "redirect") })
public class AdvertisingAction extends CrudActionSupport<Advertising> {

	
	private Page page = new Page<Advertising>(10);//
	private ServicesManager sm;//服务层工厂
	private AdvertisingManager adm;//广告管理对象
	private Advertising entity;//广告对象
	private Long id;//
	private Long[] ids;//批量修改用的ids
	
	@Override
	public String delete() throws Exception {
		adm = sm.getAdvertising();//工厂生成广告管理对象
		adm.delete(id);

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "advertising","forward"));
		
		return null;
	}
	
	public String deleteIds() throws Exception {
		adm = sm.getAdvertising();//工厂生成广告管理对象
		String delmsg = "成功删除广告:";//记录成功删除信息
		//开始批量删除
		for(Long delId : ids){	
			adm.delete(delId);
			delmsg += delId + ",";
		}
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject(delmsg, "advertising","forward"));
		
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {

		//获取表单提交的参数
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		
		page = PageRIA.RiaToPage(page);//富客户端的page转为系统page的变量
		
		//设置page对象的默认排序
		if(!page.isOrderBySetted()){
			page.setOrderBy("id");
			page.setOrder(page.DESC);
		}
		
		//工厂生成广告管理对象
		adm = sm.getAdvertising();
		
		page = adm.search(page,filters);//搜索结果
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		
		if(id != null){
			adm = sm.getAdvertising();//工厂生成广告管理对象
			entity = adm.getEntity(id);
		}else{
			entity = new Advertising();
		}
	}

	@Override
	public String save() throws Exception {
		adm = sm.getAdvertising();//工厂生成广告管理对象
		adm.save(entity);//广告保存对象 

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "advertising","closeCurrent"));

		return null;
	}

	public Advertising getModel() {
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page getPage() {
		return page;
	}

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	
	

}
