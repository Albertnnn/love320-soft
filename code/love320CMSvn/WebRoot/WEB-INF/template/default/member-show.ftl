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
				<p>
				<label>手机号:</label>
				<span class="unit">${mcsEntity.phone!}</span>
				</p><p>
            	<label>短信类型:</label>
				<span class="unit">
				<#if mcsEntity.typeId == 1 >发短信</#if>
				<#if mcsEntity.typeId == 2 >收短信</#if>
				<#if mcsEntity.typeId == 3 >发彩信</#if>
				<#if mcsEntity.typeId == 4 >收彩信</#if>
				</span>
 				</p><p>
            	<label>创建时间:</label>
				<span class="unit">${mcsEntity.newDate!}</span>
				</p><p>
				<label>服务时间:</label>
				<span class="unit">${mcsEntity.smsDate!}</span>
				</p><p>
            	<label>定时发送时间:</label>
				<span class="unit">${mcsEntity.optionDate!}</span>
				</p><p>
				<label>发送标志:</label>
				<span class="unit">${mcsEntity.sendType!}</span>
				</p><p>
            	<label>处理状态:</label>
				<span class="unit">${mcsEntity.smsAction!}</span>
				</p>
				<div class="divider"></div> 
				<label>短信内容:</label>
				<span class="unit">${mcsEntity.content!}</span>
</div>
</body>
<html>