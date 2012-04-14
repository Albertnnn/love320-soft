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
<form method="post" action="websms!save.action" ">
		  <div class="pageFormContent" >
				<label>发布在校表现     :</label><br/>
                <#list memberPhone as xx>
               	 <#if xx.id != member.id >
                		<#if xx.smsint < 5 >      			
								<input name="memberids" value="${xx.id}" type="hidden">
								${xx.uname!}
								<input name="memberStrs" value="" type="text" >
								<br/>
							<#else>
								${xx.uname!}
								<br/>
						</#if>
						
					</#if>
				</#list>             
			<div class="divider"></div>   
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">发送</button></div></div></li>
			</ul>
		</div>
</form>
</div>
</body>
<html>