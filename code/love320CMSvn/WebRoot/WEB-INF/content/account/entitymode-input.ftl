<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 
<div class="pageContent">
	<form method="post" action="entitymode!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>模型名：</label>
				<input class="required" type="text" name="modeName" size="30" id="modeName" value="${modeName!''}"/>
			</p>
			<p>
				<label>模型对象名：</label>
				<input class="required" type="text" name="entityName" size="30" id="entityName" value="${entityName!''}"/>
			</p>
			<p>
				<label>识别id：</label>
				<input class="required" type="text" name="nid" size="30" id="nid" value="${nid!''}"/>
			</p>
			<p>
				<label>附加表：</label>
				<input class="required" type="text" name="addTable" size="30" id="addTable" value="${addTable!''}"/>
			</p>
			<p>
				<label>模板列表模板：</label>
				<input class="required" type="text" name="listTmpl" size="30" id="listTmpl" value="${listTmpl!''}"/>
			</p>
			<p>
				<label>模板文档模板：</label>
				<input class="required" type="text" name="articleTmpl" size="30" id="articleTmpl" value="${articleTmpl!''}"/>
			</p>
			
		</div>
		<div class="formBar">
			<ul>
				<@security.authorize ifAnyGranted="ROLE_修改数据模型"> 
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				</@security.authorize>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
