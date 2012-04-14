<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 
<script type="text/javascript">
    //function $(id) { return document.getElementById(id); }

    // 在光标处插入字符串
    // myField    文本框对象
    // 要插入的值



    function insertAtCursor(myValue) 
    {
    	myField = document.getElementById('body')
        //IE support
        if (document.selection) 
        {
            myField.focus();
            sel = document.selection.createRange();
            sel.text    = myValue;
            sel.select();
        }
        //MOZILLA/NETSCAPE support
        else if (myField.selectionStart || myField.selectionStart == '0') 
        {
            var startPos    = myField.selectionStart;
            var endPos        = myField.selectionEnd;
            // save scrollTop before insert
            var restoreTop    = myField.scrollTop;
            myField.value    = myField.value.substring(0, startPos) + myValue + myField.value.substring(endPos, myField.value.length);
            if (restoreTop > 0)
            {
                // restore previous scrollTop
                myField.scrollTop = restoreTop;
            }
            myField.focus();
            myField.selectionStart    = startPos + myValue.length;
            myField.selectionEnd    = startPos + myValue.length;
        } else {
            myField.value += myValue;
            myField.focus();
        }
    }

</script>

<div class="pageContent">
	<form method="post" action="taglabel!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>标签名：</label>
				<input class="required" type="text" name="tagName" size="30" id="tagName" value="${tagName!''}"/>
			</p>
			<p>
				<label>标签类型：</label>
				<select name="tltId" class="required combox">
            	<#list tagLabelTypeList as xx>
            		<#if tagLabelType?exists >
						<option value="${xx.id!''}"  <#if (xx.id == tagLabelType.id ) >selected</#if> >${xx.typeName!''}</option>
					<#else>
						<option value="${xx.id!''}"  >${xx.typeName!''}</option>
					</#if>
				</#list>
				</select>
			</p>
			<p>
				<label>栏目：</label>
				<select name="typeid" class="required combox">
            	<#list typeList as xx>
					<option value="${xx.id}" <#if xx.id == typeid >selected</#if>>${xx.typename}</option>
				</#list>
				</select>
			</p>
			<p>
				<label>调用条数：</label>
				<input class="required" type="text" id="row" name="row" size="30" value="${row!''}"/>
			</p>
			<p>
				<label>排序值段：</label><#assign orderbyList = [{'key':'sortrank','name':'时间排序'}, {'key':'click','name':'点击数'}, {'key':'moeny','name':'消费点数'} ,{'key':'pubdate','name':'时间'},{'key':'senddate','name':'修改时间'}]>  
				<select name="orderby" class="combox">
            	<#list orderbyList as xx>
            		<#if orderby?? >
						<option value="${xx.key!}" <#if orderby == xx.key >selected</#if>>${xx.name!}</option>
					<#else>
						<option value="${xx.key!}" >${xx.name!}</option>
					</#if>
				</#list>
				</select>
			</p>
			<p>
				<label>排序类型：</label><#assign orderbyTypeList = [{'key':'asc','name':'升序'},{'key':'desc','name':'降序'}]>  
				<select name="orderbyType" class="combox">
            	<#list orderbyTypeList as xx>
					<#if orderbyType?? >
						<option value="${xx.key!}" <#if orderbyType == xx.key >selected</#if>>${xx.name!}</option>
					<#else>
						<option value="${xx.key!}" >${xx.name!}</option>
					</#if>
				</#list>
				</select>
			</p>
			<div class="divider"></div>
				<label>文档属性：</label>
				<#list acratts as xx>
				<label><input type="checkbox" name="checkedAcattIds" value="${xx.id}" <#list flagList as xxx><#if xx.id == xxx.id >checked</#if></#list>/>${xx.attName}</label>
				</#list>
			<div class="divider" ></div>
				<label>插入栏目标记：</label>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.id)!}"}')"><span>栏目ID</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${base}/html/list-${(xx.id)!}.html"}')"><span>栏目URL链接</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.clickmethods)!}"}')"><span>链接打开方式</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.typename)!}"}')"><span>栏目名</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.description)!}"}')"><span>栏目描述</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.keywords)!}"}')"><span>关键字</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.content)!}"}')"><span>栏目内容</span></a>
			<div class="divider" ></div>	
				<label>插入文章标记：</label>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${base}/html/article-${(xx.id)!}.html"}')"><span>文章URL链接</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.click)!}"}')"><span>点击数</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.title)!}"}')"><span>标题</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.shorttitle)!}"}')"><span>简明标题</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.writer)!}"}')"><span>作者</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.source)!}"}')"><span>文档来源</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${base}/uploads${(xx.litpic)!}"}')"><span>缩略图</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.senddate?string(\'yyyy MMM dd\'))!}"}')"><span>文章时间</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.userip)!}"}')"><span>IP</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.description)!}"}')"><span>摘要</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.body)!}"}')"><span>内容</span></a>
			<div class="divider" ></div>	
				<label>插入商品标记：</label>	
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.price)!}"}')"><span>市场价</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.trueprice)!}"}')"><span>优惠价</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.brand)!}"}')"><span>品牌</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.units)!}"}')"><span>计量单位</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${base}/html/shop-${(xx.id)!}.html"}')"><span>商品URL链接</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.specifications)!}"}')"><span>规格</span></a>
				<a class="button" href="javascript:;" onclick="insertAtCursor('${r"${(xx.origin)!}"}')"><span>起源</span></a>
			<div class="divider" ></div>
				<label>标签内容：</label>
				<textarea rows="14" cols="100" id="body" name="body" >${body!''}</textarea>
			</p>
			<div class="divider" ></div>
			
		</div>
		<div class="formBar">
			<ul>
				<@security.authorize ifAnyGranted="ROLE_修改标签">
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				</@security.authorize>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
