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
				<th width="120" >考试ID</th>
				<th width="120" >分  数  情  况  内 容</th>
				<th width="120" >学生</th>
				<th width="120" >老师 </th>
				<th width="220" >接收时间</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody>
			<#list websmslist as xx >
			<tr target="xx_id" rel="${xx.id}">
				<#if xx.typeid == 1>
				<td>${xx.id}</td>
				<td>${xx.content}</td>
				<td>${xx.students.uname}</td>
				<td>${xx.teachers.uname}</td>
				<td>${(xx.newDate?string('yyyy-MM-dd HH:mm:ss'))!}</td>
				<td><a href="websms!deletesubjects.action?id=${xx.id}">删除</a>
				</#if>
			</tr>
			</#list>
		</tbody>
	</table>
</div>
</body>
<html>