<h2 class="contentTitle">系统配置</h2>


<div class="pageContent">
	
	<form action="config!save.action" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this)">
		<div class="pageFormContent nowrap" layoutH="97">
			
			<#list configs as xx>
			<dl>
				<dt>${xx.name!}：</dt>
				<dd>
					<input type="text" size="30" name="cv.${xx.nameform}"  id="cv.${xx.nameform}" value="${xx.value}" alt="${xx.explain}"/>
					<span class="info">&nbsp;&nbsp;&nbsp;&nbsp;${xx.explain}</span>
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

