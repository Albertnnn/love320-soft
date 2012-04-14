<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>asa-${pagecontent.configFile.indexname!}</title>
	<link href="${base}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${base}/css/style.css" type="text/css" rel="stylesheet"/>
	<script src="${base}/js/jquery.js" type="text/javascript"></script>
	<script src="${base}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${base}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</head>
<body>
<a href="${base}/login.action">登录</a>
<br/>
<script type="text/javascript" src="${base}/ajax/advertising.action?id=1"></script>
--<br/>
{love320_动态新闻/}
${(sfs.sdif.sdf)!}
<br/>
{love320_测试栏目  /}
<script type="text/javascript" src="${base}/ajax/advertising.action?id=2"></script>
--<br/>
</body>
</html>

