
<div class="pageContent">
	<form method="post" action="advertising!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">
			<#if id?exists >
       		<label> 广告投放代码：</label>
            <textarea cols="80" rows="3"><script type="text/javascript" src="${r"${base}"}/ajax/advertising.action?id=${id!''}"></script></textarea>
			<div class="divider"></div>
			</#if>
			<p>
				<label>广告位名称：</label>
				<input class="required" type="text" name="adname" size="30" id="adname" value="${adname!''}"/>
			</p>
			<p>
				<label>广告标识：</label>
				 <input class="" type="text" name="tagname" size="30" id="tagname" value="${tagname!''}"/>
			</P>
            <p>
            	<label>限制类型：</label>
            	<select name="timeset" class="required combox">
					<option value="0" <#if timeset == 0 >SELECTED</#if> >永久有效</option>
					<option value="1" <#if timeset == 1 >SELECTED</#if> >时间限制</option>
				</select>
            </p>
			<p>
				<label>时间限制：</label>
				<input type="text" class="date" format="yyyy-MM-dd HH:mm:ss" name="starttime" id="starttime" value="${(starttime?string('yyyy-MM-dd HH:mm:ss'))!''}"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>结束时间：</label>
   			    <input type="text" class="date" format="yyyy-MM-dd HH:mm:ss" name="endtime" id="endtime" value="${(endtime?string('yyyy-MM-dd HH:mm:ss'))!''}"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<div class="divider"></div>
				<label>广告内容</label>
                <textarea name="normbody"  cols="80" rows="5">${normbody!''}</textarea>
       	   <div class="divider"></div>
       	 	    <label>过期显示内容</label>
       	    	<textarea name="expbody"  cols="80" rows="5">${expbody!''}</textarea>
       	   <div class="divider"></div>
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
