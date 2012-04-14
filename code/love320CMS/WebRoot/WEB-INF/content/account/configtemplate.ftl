<h2 class="contentTitle">系统模板管理</h2>


<div class="pageContent">
	
	<form action="configtemplate!save.action" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this)">
		<div class="pageFormContent nowrap" layoutH="97">
			
			<#list configs as xx>
			<dl>
				<dt>${xx.name!}：</dt>
				<dd>
					<input name="ct.${xx.nameform}" id="ct.${xx.nameform}" size="80" type="text" value="${xx.value}" suggestFields="${xx.nameform}" lookupGroup="ct" lookupPk="${xx.nameform}"/>
					<a class="btnLook" href="upfile.action?dirRoot=WEB-INF/template&dirThe=&dir=&revarname=${xx.nameform}" lookupGroup="ct" lookupPk="${xx.nameform}">查找列表模板</a>
				
				</dd>
			</dl>
			</#list>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

