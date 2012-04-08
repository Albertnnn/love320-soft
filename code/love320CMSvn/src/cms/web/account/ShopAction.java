package cms.web.account;

import java.io.PrintWriter;
import java.util.Date;
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
import cms.entity.account.Shop;
import cms.service.ServiceException;
import cms.service.account.ArcattManager;
import cms.service.account.ArchivesManager;
import cms.service.account.ArctypeManager;
import cms.service.account.ServicesManager;
import cms.service.account.ShopManager;
import cms.service.account.Typeunit;
import cms.web.CrudActionSupport;

@Results({
	@Result(name=CrudActionSupport.RELOAD, location="shop.action" , type="redirect")
})
public class ShopAction extends CrudActionSupport<Shop> {
	
	private Long id ;//
	private Long[] ids;//批量修改用的ids
	private Shop entity;//商品对象
	private Page page = new Page<Shop>(20);//
	private ShopManager shopM;//商品管理对象
	private ServicesManager sm;//服务层工厂
	private List<Typeunit> checkedarctypes;//栏目
	private List<Arcatt> acratts;
	private List<Long> checkedAcattIds;//页面中钩选的文档属性id列表

	@Override
	public String delete() throws Exception {
		shopM = sm.getShopManager();//生产商品服务对象
		shopM.delete(id);//删除商品对象

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "shop","forward"));
		
		return null;
	}
	
	public String deleteIds() throws Exception {
		String delmsg = "成功删除文档:";//记录成功删除信息
		shopM = sm.getShopManager();//生产商品服务对象
		//开始批量删除
		for(Long delId : ids){
			try {
				shopM.delete(delId);
				delmsg += delId + ",";
			} catch (ServiceException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject(delmsg, "shop","forward"));
		
		return null;
	}

	@Override
	public String input() throws Exception {
		
		ArchivesManager am = sm.getArchivesManager();//生产商品服务对象
		ArcattManager acatM = sm.getArcattManager();//文档属性管理对象
		
		checkedarctypes = am.getTypeunit();
		checkedarctypes.remove(0);//删除顶级栏目
		
		acratts = acatM.getAll();
		checkedAcattIds = entity.getflagIds();
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		
		//搜索时选择顶级栏目，则remove些PropertyFilter对象
		for(PropertyFilter pf : filters){
			if(pf.getPropertyName().equals("typeid")){
				if(pf.getMatchValue().toString().trim().equals("0")){
					//System.out.println(pf.getMatchValue());
					filters.remove(pf);//删除栏目条件(因 是0,)
					break;//跳出,因为处理了filters对象,破坏了FOT的循环次数的条件
				}
			}
		}
		
		page = PageRIA.RiaToPage(page);//富客户端的page转为系统page的变量
		
		//设置默认排序
		if(!page.isOrderBySetted()){
			page.orderBy("id");
			page.order(page.DESC);
		}
		
		shopM = sm.getShopManager();//生产商品服务对象
		
		page = shopM.search(page,filters);
		
		checkedarctypes = sm.getArchivesManager().getTypeunit();
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			shopM = sm.getShopManager();//生产商品服务对象
			entity = shopM.getEntity(id);//生产商品对象
		}else{
			entity = new Shop();
		}
	}

	@Override
	public String save() throws Exception {
		
		//根据页面上的checkbox 整合Arcatt的Arcatt Set.
		HibernateUtils.mergeByCheckedIds(entity.getFlagList(),checkedAcattIds, Arcatt.class);
		
		ArctypeManager arctype = sm.getArctypeManager();//栏目管理对象
		
		entity.setArctype(arctype.getArctype(new Long(entity.getTypeid())));//关联栏目对象
		
		Date newDate = new Date();//初始化时间对象
		if(entity.getId() != null) entity.setSortrank(newDate);
		entity.setSenddate(newDate);//设置文章对象的时间
		
		shopM = sm.getShopManager();//生产商品服务对象
		shopM.save(entity);//保存对象

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "shop","closeCurrent"));

		return null;
		
	}

	public Shop getModel() {
		return entity;
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

	public List<Long> getCheckedAcattIds() {
		return checkedAcattIds;
	}

	public void setCheckedAcattIds(List<Long> checkedAcattIds) {
		this.checkedAcattIds = checkedAcattIds;
	}

	public List<Typeunit> getCheckedarctypes() {
		return checkedarctypes;
	}

	public List<Arcatt> getAcratts() {
		return acratts;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	
	
	

}
