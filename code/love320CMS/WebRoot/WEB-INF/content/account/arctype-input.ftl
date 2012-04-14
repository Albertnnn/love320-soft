<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 
<div class="pageContent">
	<form method="post" action="arctype!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">
				<label>栏目名：</label>
				<input class="required" type="text" name="typename" size="30" id="typename" value="${typename!''}"/>
				<span>批量增加栏目以“,”分开.例:栏目一,栏目二,栏目三</span>
			<div class="divider"></div>
            <p>
            	<label>请选择栏目：</label>
            	<select name="reid" class="required combox">
            	<#list typeList as xx>
					<option value="${xx.id}"  <#if xx.id == reid >selected</#if>>${xx.typename}</option>
				</#list>
				</select>
            </p>
            <p>
            	<label>数据模型：</label>
            	<select name="emid" class="required combox">
            	<#list emList as xx>
            		<#if emid??>
            			<option value="${xx.id}" <#if xx.id == emid >selected</#if>>${xx.modeName}</option>
            		<#else>
            			<option value="${xx.id}">${xx.modeName}</option>
            		</#if>
				</#list>
				</select>
            </p>
            <p>
            	<label>显示与隐藏：</label><#assign ishiddenList = [{'key':0,'name':'显示'}, {'key':1,'name':'隐藏'}]>  
				<select name="ishidden" class="combox">
            	<#list ishiddenList as xx>
				<option value="${xx.key!}" <#if xx.key == ishidden >selected</#if> >${xx.name!}</option>
				</#list>
				</select>
			</p>
			<p>
            	<label>打开方式：</label><#assign clickmethodsList = [{'key':'_self','name':'当前窗口'},{ 'key':'_blank','name':'新窗口'}, {'key':'_media','name':'_media'}, {'key':'_parent','name':'_parent'}, {'key':'_top','name':'_top'}]>  
				<select name="clickmethods" class="combox">
            	<#list clickmethodsList as xx>
				<option value="${xx.key!}" <#if xx.key == clickmethods?if_exists >selected</#if>>${xx.name!}</option>
				</#list>
				</select>
			</p>
			<p>
            	<label>栏目属性：</label><#assign ispartList = [{'key':0,'name':'最终列表栏目'}, {'key':1,'name':'频道封面'}, {'key':2,'name':'外部连接'}]>  
				<select name="ispart" class="combox required">
            	<#list ispartList as xx>
				<option value="${xx.key!}" <#if xx.key == ispart >selected</#if>>${xx.name!}</option>
				</#list>
				</select>
			</p>
			<p>
				<label>文件保存目录：</label>
				<input type="text" name="typedir" size="30" id="typedir" value="${typedir!''}"/>
			</p>
			<p>
				<label>关键字：</label>
				<input type="text" name="keywords" size="30" id="keywords" value="${keywords!''}"/>
			</p>
			<div class="divider"></div>
				<label>列表模板：</label>
				<input name="templist" size="80" type="text" value="${templist!''}" suggestFields="templist" lookupGroup="" lookupPk="templist"/>
				<a class="btnLook" href="upfile.action?dirRoot=WEB-INF/template&dirThe=&dir=&revarname=templist" lookupGroup="" lookupPk="templist">查找列表模板</a>
			<div class="divider"></div>
				<label>内容模板：</label>
				<input name="temparticle" size="80" type="text" value="${temparticle!''}" suggestFields="temparticle" lookupGroup="" lookupPk="temparticle"/>
				<a class="btnLook" href="upfile.action?dirRoot=WEB-INF/template&dirThe=&dir=&revarname=temparticle" lookupGroup="" lookupPk="temparticle">查找内容模板</a>
			<div class="divider"></div>
		</div>
		<div class="formBar">
			<ul>
				<@security.authorize ifAnyGranted="ROLE_修改栏目"> 
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><a class="button" href="arctype!delete.action?id=${id!}" target="ajaxTodo" ><span>删除</span></a></li>
				</@security.authorize>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
