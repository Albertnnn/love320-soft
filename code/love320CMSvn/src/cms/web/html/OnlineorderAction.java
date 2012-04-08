package cms.web.html;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cms.entity.account.OnlineOrder;
import cms.service.account.OrderManager;
import cms.service.account.ServicesManager;
import cms.web.CrudActionSupport;

@Results( { @Result(name = "RELOAD", location = "index.html", type = "redirect")})
public class OnlineorderAction extends CrudActionSupport<OnlineOrder> {
	
	private OnlineOrder entity;
	private ServicesManager sm;
	private OrderManager om;

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(entity == null){
			entity = new OnlineOrder();
		}		
	}

	@Override
	public String save() throws Exception {
		om = sm.getOrderManager();// 服务层生产订单管理对象
		if(entity != null){
			om.save(entity);
		}else{
			System.out.println("空");
		}
		return "RELOAD";
	}

	public OnlineOrder getModel() {		
		return entity;
	}

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}
	
	

}
