<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>sd</title>
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</head>
<body>
<a href="${base}/login.action">登录</a>
<br/>
{love320_顶栏目  /}
--------------------------------
<br/>
{love320_测试栏目   /}
<br/>
{love320_内容标签  /}
<br/>
---------------------------------
文章
<p>
通用列表 -------------------------------------------
{love320_通用列表 /}
</p>
<p>
<div>通用栏目--------------------------------------------------
{love320_通用栏目/}
</p>
<br/>
动态新闻--------------------------------------------------
{love320_动态新闻  /}
<div >
<br/>
--------动态新闻----------
{love320_动态新闻   /}
<br/>
{love320_位置导航 /}
<br/>
点击率:{love320_点击率 /}
<br/>
内容文章
文章ID：${id}<br/>
文章名：{love320_文章名 /}|${title}
<br/>
</div>
<br/>
文章内容:
<br/>
{love320_文章内容  /}
<br/>

<a href="${base}/html/article-${beforeId}/">下一文章</a>
<a href="${base}/html/article-${afterId}/">上一文章</a>
<br/>

{love320_下一篇  /}
{love320_上一篇  /}

{love320_评论列表  /}

{love320_发评论   /}

</body>
</html>

