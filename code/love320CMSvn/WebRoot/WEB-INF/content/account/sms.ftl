
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.getPageNo()}" />
	<input type="hidden" name="numPerPage" value="${page.getPageSize()}" />
	<input type="hidden" name="orderField" value="${page.getOrderBy()}" />
	<input type="hidden" name="orderDirection" class="${page.getOrder()}" value="${page.getOrder()}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="sms.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>手机号：</label>
				<input type="text" name="filter_LIKES_phone" value="${Parameters["filter_LIKES_phone"]!''}"/>
			</li>
			<li>
				<label>短信内容：</label>
				<input type="text" name="filter_LIKES_content" value="${Parameters["filter_LIKES_content"]!''}"/>
			</li>
		</ul>
		
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="sms!input.action" target="navTab"><span>发送短信</span></a></li>
			<li><a class="edit" href="sms!show.action?id={xx_id}" target="navTab" warn="请选择一个文档"><span>查看短信</span></a></li>
			<li><a class="delete" href="sms!delete.action?id={xx_id}" target="ajaxTodo" title="你确定要删除吗？" warn="请选择一个文档"><span>删除短信</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="sms!deleteIds.action" class="delete"><span>批量删除默认方式</span></a></li>
			<li><a class="add" href="sms!receiving.action" target="ajaxTodo"><span>接收短信</span></a></li>
			<li><a class="add" href="sms!balance.action" target="ajaxTodo"><span>查询余额</span></a></li>
			<li><a class="add" href="sms!processing.action" target="ajaxTodo"><span>处理短信</span></a></li>
		</ul>
	</div>
	<table class="table" width="1000" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="120" orderField="id">短信ID</th>
				<th orderField="typeId">短信类型</th>
				<th orderField="phone">手机号</th>
				<th orderField="strType">信息类别</th>
				<th orderField="newDate">创建时间</th>
				<th orderField="smsDate">服务时间</th>
				<th orderField="optionDate">定时发送时间</th>
				<th orderField="smsAction">处理状态</th>
			</tr>
		</thead>
		<tbody>
			<#list page.result as xx >
			<tr target="xx_id" rel="${xx.id}">
				<td><input name="ids" value="${xx.id}" type="checkbox"></td>
				<td>${xx.id}</td>
				<td>
				<#if xx.typeId == 1 >发短信</#if>
				<#if xx.typeId == 2 >收短信</#if>
				<#if xx.typeId == 3 >发彩信</#if>
				<#if xx.typeId == 4 >收彩信</#if>
				</td>
				<td>${xx.phone}</td>
				<td>${xx.strType!}</td>
				<td>${(xx.newDate?string('yyyy-MM-dd HH:mm:ss'))!}</td>
				<td>${(xx.smsDate?string('yyyy-MM-dd HH:mm:ss'))!}</td>
				<td>${(xx.optionDate?string('yyyy-MM-dd HH:mm:ss'))!}</td>
				<td>
				<#if xx.smsAction == 0 >待处理</#if>
				<#if xx.smsAction == 1 >以处理</#if>
				</td>
			</tr>
			</#list>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
			    <option value="1">默认</option>
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>${page.totalCount}条，共${page.totalPages}页</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount}" numPerPage="${page.getPageSize()}" pageNumShown="10" currentPage="${page.getPageNo()}"></div>

	</div>
</div>
