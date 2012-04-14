<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${(pagecontent.configFile.indexname)!}</title>
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</head>
<body>
<a href="login.action">登录</a>
{love320_没有的标签  /}{love320_位置导航 /}
<br/>
当前栏目名:${(pagecontent.theTypeName)!}<br/><div >顶栏目--------------------------------{love320_顶栏目  /}
</div>

<div >
测试栏目--------------------------------
{love320_测试栏目 /}
</div>

<div >
测试栏目--------------------------------
{love320_测试栏目 /}
</div>

<div >
测试栏目--------------------------------
{love320_测试栏目 /}
</div>

<div>
通用列表 -------------------------------------------
<br/>
{love320_通用列表 /}哩枯叶 是右嚅
<br/>
{love320_下一页 /}相映${pagecontent.listLabel.get(pagecontent.temIterationId).page.totalPages}成趣{love320_上一页/}
<br/>
<${pagecontent.listLabel.get(pagecontent.temIterationId).page.getPageNo()}>
<br/>
{love320_通用分页号 /}
<br/>
通用栏目-------------------------------------------------
{love320_通用栏目 /}

<br/>
通用栏目-------------------------------------------------
{love320_通用栏目 /}

<br/>
特殊标签
{love320_sys_include=default/listA.ftl sys/}中固体燃料吸为要{love320_sys_include=xoslow.htm sys/}体燃料吸为要{love320_sys_j2ee=xoslow.htm sys/}
 	
</body>
</html>
