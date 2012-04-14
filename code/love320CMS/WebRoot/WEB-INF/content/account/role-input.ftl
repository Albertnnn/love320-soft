
<div class="pageContent">
	<form method="post" action="role!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>角色名：</label>
				<input class="required" type="text" name="name" size="30" id="name" value="${name!''}"/>
			</p>
			<div class="divider"></div>
				<label>授权：</label>
				<#list allAuthorityList as xx>
					<label><input type="checkbox" name="checkedAuthIds" value="${xx.id}" <#list checkedAuthIds as xxx><#if xx.id == xxx >checked</#if></#list>/>${xx.name}</label>
				</#list>
			
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
