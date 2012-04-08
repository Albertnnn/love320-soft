
<div class="pageContent">
	<form method="post" action="taglabeltype!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>标签类型名：</label>
				<input class="required" type="text" name="typeName" size="30" id="typeName" value="${typeName!''}"/>
			</p>
			<p>
				<label>标签类的类名：</label>
				<input class="required" type="text" name="className" size="30" id="className" value="${className!''}"/>
			</p>	
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
