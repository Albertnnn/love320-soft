
<div class="pageContent">

<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="sms!receiving.action" target="ajaxTodo"><span>接收</span></a></li>
			<li><a class="add" href="sms!balance.action" target="ajaxTodo"><span>查询余额</span></a></li>
		</ul>
	</div>

	<form method="post" action="sms!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="80">
				<label>手机号:</label>
                <input class="text" type="text" name="phone" id="phone" size="30" value="${phone!}"/>    
                <span class="info">&nbsp;多个手机号用","分开</span>              
			<div class="divider"></div>   
				<label>定时短信:</label>
				<input type="text" name="optionDate" id="optionDate" class="date textInput readonly" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="30" value="${optionDate!}"/>
				<span class="info">&nbsp;不填写为即时短信</span>  
			<div class="divider"></div> 
				<label>短信内容:</label>
                <textarea name="content" cols="60" rows="10">${content!''}</textarea>
            <div class="divider"></div>   
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">发送</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
