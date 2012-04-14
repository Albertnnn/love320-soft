<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<div class="pageContent">
	<form method="post" action="sgpage!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>标题：</label>
				<input class="required" type="text" name="title" size="30" id="title" value="${title!''}"/>
			</p>
			<p>
				<label>简明标题：</label>
				 <input class="" type="text" name="shorttitle" size="30" id="shorttitle" value="${shorttitle!''}"/>
			</P>
			<p>
				<label>作者：</label>
                <input class="" type="text" name="writer" size="30" id="writer" value="${writer!''}"/>
			</p>
			<p>
				<label>文档来源：</label>
             	<input class="" type="text" name="source" size="30" id="source" value="${source!''}"/>
			</p>
            <p>
            	<label>请选择栏目：</label>
            	<select name="typeid" class="required combox">
            	<#list checkedarctypes as xx>
					<option value="${xx.id}"  <#if xx.id == typeid >selected</#if>>${xx.typename}</option>
				</#list>
				</select>
            </p>
            <p>
				<label>关联标识：</label>
             	<input class="" type="text" name="likeid" size="30" id="likeid" value="${likeid!}""/>
			</p>
            <div class="divider"></div>
				<label>模板文件</label>
				<input name="template" size="80" type="text" value="${template!''}" suggestFields="template" lookupGroup="" lookupPk="template"/>
				<a class="btnLook" href="upfile.action?dirRoot=WEB-INF/template&dirThe=&dir=&revarname=template" lookupGroup="" lookupPk="template">查找单页页面模板文件</a>
			<div class="divider"></div>
            <p>
				<label>关键字：</label>
                 <input class="text-input small-input" type="text" name="keywords" size="30" id="keywords" value="${keywords!''}"/>
			</p>
			<div class="divider"></div>
				<label>摘要：</label>
                <textarea name="description" cols="60" rows="3">${description!''}</textarea>
			<div class="divider"></div>
				<label>内容：</label>
       			 <textarea class="editor" id="body" name="body" rows="15" cols="80">${body!''}</textarea>
		</div>
		<div class="formBar">
			<ul>
				<@security.authorize ifAnyGranted="ROLE_修改单页面"> 
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				</@security.authorize>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
