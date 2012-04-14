<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>Love320!CMS 管理系统</title>

<link href="${base}/skin/system/themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/skin/system/themes/css/core.css" rel="stylesheet" type="text/css" />
<link href="${base}/skin/system/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->

<script src="${base}/skin/system/js/speedup.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/jquery-1.4.4.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/jquery.validate.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${base}/skin/system/xheditor/xheditor-1.1.9-zh-cn.min.js" type="text/javascript"></script>
<script src="${base}/skin/system/uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="${base}/skin/system/uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>

<script src="${base}/skin/system/js/dwz.core.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.drag.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.tree.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.ui.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.theme.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.tab.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.resize.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.stable.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.database.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.effects.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.panel.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.history.js" type="text/javascript"></script>
<script src="${base}/skin/system/js/dwz.combox.js" type="text/javascript"></script>

<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="${base}/skin/system/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${base}/skin/system/dwz.frag.xml", {
//		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"${base}/skin/system/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="http://www.love320.com">标志</a>
				<ul class="nav">
					<li><a href="config.action" target="navTab" rel="config">设置</a></li>
					<li><a href="http://weibo.com/love320soft" target="_blank">微博</a></li>
					<li><a href="http://www.love320.com" target="_blank">帮助</a></li>
					<li><a href="${base}/" target="_blank">前台浏览</a></li>
					<li><a href="${base}/j_spring_security_logout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

					<div class="accordion" fillSpace="sidebar">
					
					<!-- 信息管理 -->
					<div class="accordionHeader">
    		 	       <h2><span>icon</span>信息管理</h2>
 					</div>
   					<div class="accordionContent">
    					<ul class="tree treeFolder">
    						<@security.authorize ifAnyGranted="ROLE_浏览栏目">  
							<li><a href="${base}/account/arctype.action" target="navTab" rel="arctype">栏目管理</a></li>
							</@security.authorize> 
							<@security.authorize ifAnyGranted="ROLE_浏览文章"> 
							<li><a href="${base}/account/archives.action" target="navTab" rel="archives">文档管理</a></li>
							</@security.authorize> 
							<@security.authorize ifAnyGranted="ROLE_浏览商品"> 
							<li><a href="${base}/account/shop.action" target="navTab" rel="shop">商品管理</a></li>
							</@security.authorize> 
							<@security.authorize ifAnyGranted="ROLE_回收站"> 
							<li><a href="${base}/account/recycling.action" target="navTab" rel="recycling">文档回收站</a></li>
							</@security.authorize> 
							<@security.authorize ifAnyGranted="ROLE_浏览评论"> 
							<li><a href="${base}/account/feedback.action" target="navTab" rel="feedback">评论管理</a></li>
							</@security.authorize> 
							<@security.authorize ifAnyGranted="ROLE_浏览单页面"> 
							<li><a href="${base}/account/sgpage.action" target="navTab" rel="sgpage">单页面管理</a></li>
							</@security.authorize> 
							<@security.authorize ifAnyGranted="ROLE_浏览数据模型"> 
							<li><a href="${base}/account/entitymode.action" target="navTab" rel="entitymode">数据模型管理</a></li>
							</@security.authorize> 
						</ul>
 		   	  	    </div>
 		   	  	    <!-- 辅助功能 -->
 		   	  	    <@security.authorize ifAnyGranted="ROLE_辅助功能"> 
 		   	  	    <div class="accordionHeader">
    		 	       <h2><span>icon</span>辅助功能</h2>
 					</div>
 					<div class="accordionContent">
 						<ul class="tree treeFolder">
							<@security.authorize ifAnyGranted="ROLE_广告管理"> 
 							<li><a href="${base}/account/advertising.action" target="navTab" rel="advertising">广告管理</a></li>
 							</@security.authorize> 
							<@security.authorize ifAnyGranted="ROLE_浏览留言"> 
 							<li><a href="${base}/account/guestbook.action" target="navTab" rel="guestbook">留言管理</a></li>
 							</@security.authorize> 
							<@security.authorize ifAnyGranted="ROLE_浏览在线订单"> 
 							<li><a href="${base}/account/order.action" target="navTab" rel="order">在线订单管理</a></li>
 							</@security.authorize> 
 						</ul>
 					</div>
 					</@security.authorize> 
 					<!-- 会员功能 -->
 					<@security.authorize ifAnyGranted="ROLE_会员功能"> 
 		   	  	    <div class="accordionHeader">
    		 	       <h2><span>icon</span>会员功能</h2>
 					</div>
 					<div class="accordionContent">
 						<ul class="tree treeFolder">
 							<@security.authorize ifAnyGranted="ROLE_浏览会员"> 
 							<li><a href="${base}/account/member.action" target="navTab" rel="member">会员管理</a></li>
 							</@security.authorize> 
 							<@security.authorize ifAnyGranted="ROLE_浏览会员类型"> 
 							<li><a href="${base}/account/membertype.action" target="navTab" rel="membertype">会员类型管理</a></li>
 							</@security.authorize> 
 							<@security.authorize ifAnyGranted="ROLE_浏览班级"> 
 							<li><a href="${base}/account/memberclass.action" target="navTab" rel="memberclass">班级管理</a></li>
 							</@security.authorize> 
 						</ul>
 					</div>
 					</@security.authorize> 
 					<!-- 通信应用功能 -->
 					<@security.authorize ifAnyGranted="ROLE_通信应用"> 
 					<div class="accordionHeader">
    		 	       <h2><span>icon</span>通信应用</h2>
 					</div>
 					<div class="accordionContent">
 						<ul class="tree treeFolder">
 							<li><a href="${base}/account/ucconfig.action" target="navTab" rel="ucconfig">配置uc</a></li>
 							<li><a href="${base}/account/sms.action" target="navTab" rel="sms">短信管理</a></li>
 						</ul>
 					</div>
 					</@security.authorize> 
 					<!-- 静态页面管理 -->
 					<@security.authorize ifAnyGranted="ROLE_生成静态页面"> 
 		   	  	    <div class="accordionHeader">
    		 	       <h2><span>icon</span>静态页面管理</h2>
 					</div>
 					<div class="accordionContent">
 						<ul class="tree treeFolder">
 							<li><a href="${base}/account/htmlgenerator!htmlHomePage.action" target="ajaxTodo" rel="htmlgenerator">生成首页</a></li>
 							<li><a href="${base}/account/htmlgenerator!htmlType.action" target="ajaxTodo" rel="htmlgenerator">生成栏目</a></li>
 							<li><a href="${base}/account/htmlgenerator!htmlArticle.action" target="ajaxTodo" rel="htmlgenerator">生成文档</a></li>
 							<li><a href="${base}/account/htmlgenerator!htmlShop.action" target="ajaxTodo" rel="htmlgenerator">生成商品</a></li>
 							<li><a href="${base}/account/htmlgenerator!htmlSgpage.action" target="ajaxTodo" rel="htmlgenerator">生成单页面</a></li>
 							<li><a href="${base}/account/htmlgenerator!oneHtmlGPage.action" target="ajaxTodo" rel="htmlgenerator">一键生成</a></li>
 							<li><a href="${base}/account/upcache!deletehtml.action" target="ajaxTodo" rel="htmlgenerator">清空静态页面</a></li>
							<li><a href="${base}/account/htmlgeneratorajax.action" target="navTab" rel="htmlgeneratorajax">Ajax生成静态页面</a></li>
 						</ul>
 					</div>
 					</@security.authorize> 
 					<!-- 标签管理  -->
 					<@security.authorize ifAnyGranted="ROLE_浏览标签"> 
 		   	  	    <div class="accordionHeader">
    		 	       <h2><span>icon</span>标签管理</h2>
 					</div>
 					<div class="accordionContent">
 						<ul class="tree treeFolder">
 							
 							<li><a href="${base}/account/taglabel.action" target="navTab" rel="taglabel">标签管理</a></li>
 							<li><a href="${base}/account/taglabeltype.action" target="navTab" rel="taglabeltype">标签类型管理</a></li>
 							
 						</ul>
 					</div>
 					</@security.authorize>
 					<!-- 系统管理 -->
 					<@security.authorize ifAnyGranted="ROLE_浏览系统配置"> 
 		   	  	    <div class="accordionHeader">
    		 	       <h2><span>icon</span>系统管理</h2>
 					</div>
 					<div class="accordionContent">
 						<ul class="tree treeFolder">
 							<li><a href="${base}/account/config.action" target="navTab" rel="config">系统配置</a></li>
 							<li><a href="${base}/account/configtemplate.action" target="navTab" rel="configtemplate">系统模板管理</a></li>
 							<li><a href="${base}/account/servermode.action" target="ajaxTodo" rel="servermode">更换运行模式</a></li>
 							<li><a href="${base}/account/arcatt.action" target="navTab" rel="arcatt">自定义文档属性表</a></li>
 							<li><a href="${base}/account/user.action" target="navTab" rel="user">帐号列表</a></li>
 							<li><a href="${base}/account/authority.action" target="navTab" rel="authority">权限列表</a></li>
 							<li><a href="${base}/account/role.action" target="navTab" rel="role">角色列表</a></li>
 							<li><a href="${base}/account/upcache.action" target="navTab" rel="upcache">更新缓存</a></li>
 							<li><a href="${base}/" target="navTab" rel="homepage" external="true">游览网站</a></li>
 							<li><a href="http://www.love320.com/" target="navTab" rel="love320" external="true">进入冰迪</a></li>
 							<li><a href="${base}/j_spring_security_logout" target="ajaxTodo" title="确定要退出登录吗?">退出登录</a></li>
 						</ul>
 					</div>
 					</@security.authorize>
 					<#-- end -->
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>		
                <#include "index_body.ftl" >
			</div>
		</div>

	</div>
	
	<div id="footer">Copyright © 2009-2012 <a href="http://www.love320.com/" target="navTab" rel="love320" external="true">love320.com</a> All Rights Reserved
	</div>
</body>
</html>