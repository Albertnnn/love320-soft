package cms.web.html;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.entity.account.Arctype;
import cms.pageList.entity.PageContent;
import cms.service.account.ArchivesTypeManager;
import cms.service.account.ServicesManager;
import cms.web.CrudActionSupport;

import com.opensymphony.xwork2.ActionSupport;

@Results( { @Result(name = CrudActionSupport.RELOAD, location = "role.action", type = "redirect"),
			@Result(name = "outlink", location = "${entity.typedir}" , type = "redirect")})
public class ListAction extends ActionSupport {
	
	/**
	 * MyEclipse自动生成的标记
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id ;//栏目ID
	private ServicesManager sm;//服务层工厂
	private ArchivesTypeManager atm;//工厂类
	private Arctype entity;//栏目单体
	private int pageNo; //列表页分每号
	
	private PageContent pagecontent = new PageContent();
	
	@Override
	public String execute() throws Exception {
		//获取栏目单体
		entity = atm.getArctypeManager().getArctype(id);
		//IF单页面
		if(entity.getIspart() == 2){
			//页面跳转
			HttpServletResponse response = Struts2Utils.getResponse();
			response.sendRedirect(entity.getTypedir()); 
			return null;
			//return "outlink";
		}else{
			pageAction();
			return entity.getTemplistname();
		}
	}
	
	//进行页面处理
	private void pageAction(){
		
		//以栏目ID为索引查出模板文件
		if(pageNo <= 0){pageNo = 1;}
		if(entity == null) entity = atm.getArctypeManager().getArctype(id);
		//指定第pageNo页
//		if(pageNo > 0){love320Core.getTemplate().setPageNo(pageNo);}
//		
//		love320Core.getTemplate().setTypeid(id.intValue());
//		love320Core.getTemplate().setTypeidT(id.intValue());

		//模板加入核心中-//模板解析

			//加入环境变量说明
			pagecontent.getPfs().add(new PropertyFilter("EQI_typeId",""+id.intValue()+""));//栏目
			pagecontent.getPfs().add(new PropertyFilter("EQI_pageNo",""+pageNo+""));//分页号
			pagecontent.setTheTypeId(new Long(entity.getTopid()));//当前栏目ID
			
			try {
				pagecontent = sm.getTemplate().analyticalTmp(entity.getTemplist(),entity.getTemplistname()+".ftl",pagecontent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//位置导航
			sm.getPageList().position(id.intValue(), 1,pagecontent);

	}
	
	@Autowired
	public void setAtm(ArchivesTypeManager atm) {
		this.atm = atm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPageNo(int pageNo) {
		//System.out.println("得到"+pageNo);
		this.pageNo = pageNo;
	}

	public Arctype getEntity() {
		return entity;
	}

	public PageContent getPagecontent() {
		return pagecontent;
	}

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}

	
	
	
	
}
