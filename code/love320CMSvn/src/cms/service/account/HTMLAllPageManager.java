package cms.service.account;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cms.entity.account.Archives;
import cms.entity.account.Arctype;
import cms.entity.account.Sgpage;
import cms.entity.account.Shop;

//页面静态化管理类
@Component
public class HTMLAllPageManager {

	private HTMLGenerator hg; // 生成静态页面类

	private ServicesManager smf;//服务层工厂
	private SgpageManager sm;// 单页面工厂
	private ConfigManager cm;// 系统配置文件
	
	private String htmlFileMsg;//生成文件信息
	

	// 网址:
	private String wwwUrl = org.apache.struts2.ServletActionContext
			.getRequest().getRemoteHost();
	// 端口:
	private int wwwPort = org.apache.struts2.ServletActionContext.getRequest()
			.getLocalPort();
	// 路径:
	private String wwwPath = org.apache.struts2.ServletActionContext
			.getRequest().getContextPath();

	// 静态页面存放目录
	private String webAppHostPath;

	HTMLAllPageManager() {
		wwwUrl = org.apache.struts2.ServletActionContext.getRequest().getServerName();
		// 端口:
		wwwPort = org.apache.struts2.ServletActionContext.getRequest().getServerPort();
		// 路径:
		wwwPath = org.apache.struts2.ServletActionContext.getRequest()
				.getContextPath();
		// 静态页面存放目录
		webAppHostPath = "http://" + wwwUrl + ":" + wwwPort + wwwPath + "/";
		
		//System.out.println("网址完整-URL"+ webAppHostPath);
	}
	
	
	//JS跳转到网页
	private void saveJs(String outLinkStr ,String htmlName){
		htmlFileMsg += "|"+htmlName;//加入生成信息
		//System.out.println("外链接:"+outLinkStr +" 名:"+htmlName);
		String src = "<script language=\"javascript\" type=\"text/javascript\"> "
			+ "window.location.href='"
			+ outLinkStr
			+ "'</script>";

	File file = new File(this.getClass().getResource("/").getPath()+"../../html/"+ htmlName);
	//System.out.println(file.getPath());
	try {
		FileUtils.writeStringToFile(file, src, "UTF-8");
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}
	
	//生成静态页面
	private int saveHtml(String urlstr , String htmlName){
		htmlFileMsg += "|"+htmlName;//加入生成信息
		/*
		 //获取原始的PrintWriter对象,以便输出响应结果,而不用跳转到某个试图    
        HttpServletResponse response = ServletActionContext.getResponse();    
        
        try {
			PrintWriter out =  response.getWriter();
			out.println(">>> "+htmlName+"<br>");//Ajax生成信息装载体
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		*/
		return hg.save(urlstr, htmlName);
	}
	
	

	// 生成首页
	public String htmlHomePage() {
		
		//获取系统运行模式
		String sysRunMode = cm.getConfigFile().getMode();
		
		//伪静态或开发模式
		if(sysRunMode.equals("1")||sysRunMode.equals("3")){
			saveJs("index/" ,"../index.html");
			saveJs("index/" ,"index.html");
		}else{
			saveHtml(webAppHostPath + "html/homepage.action", "../index.html");// 放在网站根目下
			saveHtml(webAppHostPath + "html/homepage.action", "index.html");// 放在网站根目下的html目中	
		}
		
		//System.out.println("网址完整URL"+ webAppHostPath);
		return htmlFileMsg;
		
	}

	// 生成栏目
	public String htmlType() {
		
		
		//获取系统运行模式
		String sysRunMode = cm.getConfigFile().getMode();
		
		ArctypeManager atm = smf.getArctypeManager();//栏目服务对象
		// 获取栏目List
		List<Arctype> typeids = atm.getAllArctype();

		// 生成栏目
		for (Arctype arctype : typeids) {
			
			//System.out.println("文档数:"+arctype.getArchivesList().size());
			int arctypeListarcs = arctype.getArchivesList().size();//栏目文档数
			
			if (arctype.getIspart() != 2) {

				//伪静态或开发模式
				if(sysRunMode.equals("1")||sysRunMode.equals("3")){
					saveJs("list-" + arctype.getId() + "/" ,"list-" + arctype.getId() + ".html");
				}else{
					saveHtml(webAppHostPath + "html/list.action?id="
							+ arctype.getId(), "list-" + arctype.getId() + ".html");
					// System.out.println("生成栏目文件名:"+"list-"+arctype.getId()+".html");
				}
				
				
						
				int pageNo = 1;// 生成栏目分页
				int thepageNo = 1;// 当前页面长度
				int beforepageNo = 1;// 前页面长度
				while (0 < thepageNo) {
					
					
					thepageNo = saveHtml(webAppHostPath + "html/list.action?id="
							+ arctype.getId() + "&pageNo=" + pageNo, "list-"
							+ arctype.getId() + "-" + pageNo + ".html");
					// System.out.println("生成栏目分页文件名:"+"list-"+arctype.getId()+"-"+pageNo+".html");
					
					//伪静态或开发模式
					if(sysRunMode.equals("1")||sysRunMode.equals("3")){
						saveJs("list-"
								+ arctype.getId() + "-" + pageNo + "/","list-"
								+ arctype.getId() + "-" + pageNo + ".html");
					}
					
					//System.out.println("输入:"+thepageNo+">>"+arctype.getTypename()+"-"+pageNo);
					
					pageNo++;
					
					if(Integer.parseInt(cm.getConfigFile().getHtmlgeneration()) < pageNo) break;
					
					if ((beforepageNo == thepageNo)||(pageNo > arctypeListarcs)) break;//值相同时，则退出生成分页生成
					
					beforepageNo = thepageNo;

				}

			}

			if (arctype.getIspart() == 2) {
				// System.out.println("外连接为:"+arctype.getTypedir());

				// 检验URL正确性
				Pattern pattern = Pattern
						.compile("(http://|https://){1}[\\w\\.\\-/:]+");
				Matcher matcher = pattern.matcher(arctype.getTypedir());
				if (matcher.find()) {
					//System.out.println("外链接网址:" + arctype.getTypedir());// 跳转到网页-用js跳转
					//System.out.println("栏目ID:" + arctype.getId());
					saveJs(arctype.getTypedir(),"list-"
							+ arctype.getId() + ".html");
					
				} else {

					// 检验单页面
					pattern = Pattern.compile("sgpage");
					matcher = pattern.matcher(arctype.getTypedir());
					if (matcher.find()) {	
						
						//System.out.println("单页面："+arctype.getTypedir());
						
						//伪静态或开发模式
						if(sysRunMode.equals("1")||sysRunMode.equals("3")){
							saveJs("list-"
									+ arctype.getId() + "/","list-"
									+ arctype.getId() + ".html");
						}else{
							saveHtml(webAppHostPath + "html/"
									+ arctype.getTypedir(), "list-"
									+ arctype.getId() + ".html");	
						}
						
						
					} else {
						// 其它外链接
						// <script language="javascript" type="text/javascript">
						// window.location.href="login.jsp?backurl="+window.location.href;
						// </script>
						saveJs(arctype.getTypedir(),"list-"
								+ arctype.getId() + ".html");
						

					}

				}

			}

		}
		
		return htmlFileMsg;

	}


	// 生成文档
	public void htmlArticle() {
		//获取系统运行模式
		String sysRunMode = cm.getConfigFile().getMode();
		
		ArchivesManager am = smf.getArchivesManager();//文章服务对象
		// 获取所有文档
		List<Archives> arcicleses = am.getAllArchives();

		// 生成文档
		for (Archives archives : arcicleses) {
			
			//伪静态或开发模式
			if(sysRunMode.equals("1")||sysRunMode.equals("3")){
				/*
				 * saveJs("article-" + archives.getId()
						+ "/" ,"article-" + archives.getId()
						+ ".html");
				*/
			}else{
				saveHtml(webAppHostPath + "html/article.action?id="
						+ archives.getId(), "article-" + archives.getId()
						+ ".html");
		// System.out.println("生成文档文件名:"+"list-"+archives.getId()+".html");	
			}
			
			
		}

	}
	
	// 生成商品
	public void htmlShop() {
		//获取系统运行模式
		String sysRunMode = cm.getConfigFile().getMode();
		
		ShopManager shopM = smf.getShopManager();//文章服务对象
		// 获取所有文档
		List<Shop> shops = shopM.getAll();

		// 生成文档
		for (Shop shop: shops) {
			
			//伪静态或开发模式
			if(sysRunMode.equals("1")||sysRunMode.equals("3")){
				/*
				saveJs("shop-" + shop.getId()
						+ "/" ,"shop-" + shop.getId()
						+ ".html");
				*/
			}else{
				saveHtml(webAppHostPath + "html/shop.action?id="
						+ shop.getId(), "shop-" + shop.getId()
						+ ".html");
		// System.out.println("生成文档文件名:"+"list-"+archives.getId()+".html");	
			}
			
			
		}

	}
	
	//生成指定文档
	public void htmlOneArticle(Long id){
		
		//获取系统运行模式
		String sysRunMode = cm.getConfigFile().getMode();
		
		//伪静态或开发模式
		if(sysRunMode.equals("1")||sysRunMode.equals("3")){
			/*
			saveJs("article-" + id
					+ "/" ,"article-" + id
					+ ".html");
			*/
		}else{
			saveHtml(webAppHostPath + "html/article.action?id="
					+ id, "article-" + id
					+ ".html");
			// System.out.println("生成文档文件名:"+"list-"+id+".html");	
		}
	}

	// 生成单页面
	public void htmlSgpage() {
		
		//获取系统运行模式
		String sysRunMode = cm.getConfigFile().getMode();
		
		// 获取所有单页面
		List<Sgpage> sgpages = sm.getAllSgpage();

		// 生成单页面（系统运行模式）
		/*
		for (Sgpage sgpage : sgpages) {
			
			if(sysRunMode.equals("1")||sysRunMode.equals("3")){
				saveJs("sgpage-" + sgpage.getId() + "/","sgpage-" + sgpage.getId() + ".html");
			}else{
				saveHtml(webAppHostPath + "html/sgpage.action?id=" + sgpage.getId(),
						"sgpage-" + sgpage.getId() + ".html");
				// System.out.println("生成单页面文件名:"+"sgpage-"+sgpage.getId()+".html");
			}
			
		}
		*/
		
		//生成静态单页面
		for (Sgpage sgpage : sgpages) {
				saveHtml(webAppHostPath + "html/sgpage.action?id=" + sgpage.getId(),
						"sgpage-" + sgpage.getId() + ".html");		
		}

	}
	
	//生成指定单页面
	public void htmlOneSgpage(Long id){

		//获取系统运行模式
		String sysRunMode = cm.getConfigFile().getMode();
		
		//伪静态或开发模式
		if(sysRunMode.equals("1")||sysRunMode.equals("3")){
			saveJs("sgpage-" + id
					+ "/" ,"sgpage-" + id
					+ ".html");
		}else{
			saveHtml(webAppHostPath + "html/sgpage.action?id=" + id,
					"sgpage-" + id + ".html");		
		}
	}

	@Autowired
	public void setHg(HTMLGenerator hg) {
		this.hg = hg;
	}

	
	@Autowired
	public void setSmf(ServicesManager smf) {
		this.smf = smf;
	}

	@Autowired
	public void setCm(ConfigManager cm) {
		this.cm = cm;
	}

	@Autowired
	public void setSm(SgpageManager sm) {
		this.sm = sm;
	}


	public String getHtmlFileMsg() {
		return htmlFileMsg;
	}


	public void setHtmlFileMsg(String htmlFileMsg) {
		this.htmlFileMsg = htmlFileMsg;
	}

	
	

}
