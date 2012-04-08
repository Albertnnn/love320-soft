<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${articleName!}-${(pagecontent.typeName)!}</title>
	</head>
	<body>
		<a href="${base}/login.action">登录</a>
		<br />
		<div>

			<div>
				<form id="for1ms" action="${base}/html/searcher!searcher.action" method="post">
					<input type="text" name="filter_LIKES_title"
						value="${(Parameters['filter_LIKES_title'])!}" size="9" />
					<input type="submit" value="搜索" />
				</form>
			</div>
			<br />
			{love320_网站搜索 /}
			<div>

			</div>
			
			
	</body>
</html>

