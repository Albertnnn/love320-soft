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
				<th width="60" >短信ID</th>
				<th width="120" >班级名称</th>
				<th width="120" >老师</th>
				<th width="200" >家庭作业标题</th>
				<th width="120" >作业 内 容</th>
				<th width="220" >布置时间</th>	
				<th >操作</th>
			</tr>
		</thead>
		<tbody>
			<#list mcnList as xx >
			<#if xx.typeid = 2>
			<tr target="xx_id" rel="${xx.id}">
			
				<td>${xx.id}</td>
				<td>${xx.memberClass.className}</td>
				<td>${xx.member.uname}</td>
				<td>${(xx.sendDate?string('yyyy年MMMdd日'))!}家庭作业</td>
				<td>${xx.content}</td>
				<td>${(xx.sendDate?string('yyyy-MM-dd HH:mm:ss'))!}</td>
				<td><a href="memberclassnotice!deletework.action?id=${xx.id}">删除</a>
			</tr>
			</#if>
			</#list>
		</tbody>
	</table>
</div>
</body>
<html>