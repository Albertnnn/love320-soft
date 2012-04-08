package cms.web.html;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import cms.entity.account.Arctype;
import cms.entity.account.Sgpage;
import cms.pageList.entity.PageContent;
import cms.service.account.ArchivesTypeManager;
import cms.service.account.PageList;
import cms.service.account.ServicesManager;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class SgpageAction extends ActionSupport implements ModelDriven<Sgpage>, Preparable{
	
	/**
	 * MyEclipse自动生成的标记
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 首页显示的内容
	 * */
	private ServicesManager sm;//服务层工厂
	private ArchivesTypeManager atm;//工厂类
	private Long id;//文章ID
	
	private Sgpage entity;
	
	private PageContent pagecontent = new PageContent();
	
	
	public Sgpage getModel() {
		return entity;
	}

	public void prepare() throws Exception {
		if(id != null){
			if(entity != null){
				//System.out.println("不为空");
			}else{
				//System.out.println("空");
				entity = atm.getSgpageManager().getSgpage(id);
			}
			//System.out.println("action"+entity.getTitle());
		}else{
			entity = new Sgpage();
		}
	}
	
	@Override
	public String execute() throws Exception {
		return pageAction();
	}
	
	//进行页面处理
	private String pageAction(){
		//以文章ID为索引查出模板文件
		Sgpage entity = atm.getSgpageManager().getSgpage(id);
		//文章的栏目ID
//		love320Core.getTemplate().setTypeidT(entity.getTypeid());
//		//文章栏目的同级栏目
//		Arctype arctype = atm.getArctypeManager().getArctype(new Long(entity.getTypeid()));
//		if(arctype.getReid() != 0){
//			love320Core.getTemplate().setTypeidT(arctype.getReid());
//		}else{
//			love320Core.getTemplate().setTypeidT(entity.getTypeid());
//		}
		
		//System.out.println("html:"+entity.getTitle());

		//模板解析
		
			//加入环境变量说明
			pagecontent.getPfs().add(new PropertyFilter("EQI_typeId",""+entity.getTypeid()));//栏目
			pagecontent.setTheTypeId(new Long(atm.getArctypeManager().getArctype(new Long(entity.getTypeid())).getTopid()));
						
			try {
				pagecontent = sm.getTemplate().analyticalTmp(entity.getTemplate(),entity.getFileName()+".ftl",pagecontent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//设置导航位置
			sm.getPageList().position(entity.getTypeid(),1,pagecontent);
			
		return entity.getFileName();
		
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Autowired
	public void setAtm(ArchivesTypeManager atm) {
		this.atm = atm;
	}

	public PageContent getPagecontent() {
		return pagecontent;
	}

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}
	
	


	
	
	
		
}
