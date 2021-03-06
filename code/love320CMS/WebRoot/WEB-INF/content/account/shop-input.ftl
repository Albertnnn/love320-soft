<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 
<div class="pageContent">
	<form method="post" action="shop!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>标题：</label>
				<input class="required" type="text" name="title" size="30" id="title" value="${title!''}"/>
			</p>
			<div class="divider"></div>
				<label>文档属性：</label>
				<#list acratts as xx>
				<label><input type="checkbox" name="checkedAcattIds" value="${xx.id}" <#list flagList as xxx><#if xx.id == xxx.id >checked</#if></#list>/>${xx.attName}</label>
				</#list>
			<div class="divider"></div>
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
            <div class="divider"></div>
				<label>缩略图：</label>
                <input name="litpic" size="80" type="text" value="${litpic!''}" suggestFields="litpic" lookupGroup="" lookupPk="litpic"/>
				<a class="btnLook" href="upfile.action?dirRoot=uploads&dirThe=&dir=&revarname=litpic" lookupGroup="" lookupPk="litpic">查找缩略图</a>
			<div class="divider"></div>
			<p>
				<label>市场价</label>
  			   <input class="text-input small-input number" type="text" name="price" size="30" id="price" value="${price!''}"/>
			</p>
			<p>
				<label>优惠价</label>
   				 <input class="text-input small-input number" type="text" name="trueprice" size="30" id="trueprice" value="${trueprice!''}"/>
			</p>
			<p>
				<label>品牌</label>
  				  <input class="text-input small-input" type="text" name="brand" size="30" id="brand" value="${brand!''}"/>
			</p>
			<p>
				<label>规格</label>
   				 <input class="text-input small-input" type="text" name="specifications" size="30" id="specifications" value="${specifications!''}"/>
			</p>
			<p>
				<label>产地</label>
    			<input class="text-input small-input" type="text" name="origin" size="30" id="origin" value="${origin!''}"/>
			</p>
			<p>
				<label>计量单位</label>
    			<input class="text-input small-input" type="text" name="units" size="30" id="units" value="${units!''}"/>
			</p>
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
				<@security.authorize ifAnyGranted="ROLE_修改商品"> 
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				</@security.authorize>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
