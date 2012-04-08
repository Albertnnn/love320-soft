
<div class="pageContent">

<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="sms!receiving.action" target="ajaxTodo"><span>接收短信</span></a></li>
			<li><a class="add" href="sms!balance.action" target="ajaxTodo"><span>查询余额</span></a></li>
		</ul>
	</div>

		<div class="pageFormContent" layoutH="80">
				<p>
				<label>手机号:</label>
				<span class="unit">${phone!}</span>
				</p><p>
            	<label>短信类型:</label>
				<span class="unit">
				<#if typeId == 1 >发短信</#if>
				<#if typeId == 2 >收短信</#if>
				<#if typeId == 3 >发彩信</#if>
				<#if typeId == 4 >收彩信</#if>
				</span>
 				</p><p>
            	<label>创建时间:</label>
				<span class="unit">${newDate!}</span>
				</p><p>
				<label>服务时间:</label>
				<span class="unit">${smsDate!}</span>
				</p><p>
            	<label>定时发送时间:</label>
				<span class="unit">${optionDate!}</span>
				</p><p>
				<label>发送标志:</label>
				<span class="unit">${sendType!}</span>
				</p><p>
            	<label>处理状态:</label>
				<span class="unit">${smsAction!}</span>
				</p>
				<div class="divider"></div> 
				<label>短信内容:</label>
				<span class="unit">${content!}</span>

		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
				</li>
			</ul>
		</div>
</div>
