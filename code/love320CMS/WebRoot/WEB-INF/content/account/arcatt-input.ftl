
<div class="pageContent">
	<form method="post" action="arcatt!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>文档属性字符：</label>
				<input class="required" type="text" name="att" size="30" id="typeName" value="${att!''}"/>
			</p>
			<p>
				<label>属性说明：</label>
				<input class="required" type="text" name="attName" size="30" id="attName" value="${attName!''}"/>
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
