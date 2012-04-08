<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 
<div class="pageContent">
	<form method="post" action="memberclass!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>年级号</label>
                <input class="text" type="text" name="gradeId" id="gradeId" size="30" value="${gradeId!}"/>
                                        
			</p>
			<p>
				<label>班级号</label>
                <input class="text" type="text" name="classId" id="classId" size="30" value="${classId!}"/>
                                        
			</p>
			<p>
				<label>班级名</label>
                <input class="text" type="text" name="className" id="className" size="30" value="${className!}"/>
                                        
			</p>
			<p>
				<label>班级说明</label>
                <input class="text" type="text" name="classText" id="classText" size="30" value="${classText!}"/>
                                        
			</p>
					
		</div>
		<div class="formBar">
			<ul>
				<@security.authorize ifAnyGranted="ROLE_修改班级"> 
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				</@security.authorize>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
