<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>1111</title>
<link href="/skin/default/matser.css" rel="stylesheet" type="text/css" />
</head>
<body>
<style type="text/css">
.headtop{ background:#F6F6F6; border:1px #CCC solid; padding:10px;}
.G{background:#F6F6F6; border:1px #CCC solid; padding:10px; margin-top:10px;}
</style>
<script type="text/javascript">
</script>
<#include "navlist.ftl">
<div class="G">
<table>
		<thead>
			<tr>
				<th width="120" >短信ID</th>
				<th width="120" >手机号</th>
				<th width="220" >服务时间</th>
			</tr>
		</thead>
		<tbody>
			<#list listmcsms as xx >
			<tr target="xx_id" rel="${xx.id}">
				<td>${xx.id}</td>
				<td>${xx.phone}</td>
				<td>${(xx.smsDate?string('yyyy-MM-dd HH:mm:ss'))!}</td>
			</tr>
			</#list>
		</tbody>
	</table>
</div>
</body>
<html>