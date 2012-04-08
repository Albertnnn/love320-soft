
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.getPageNo()}" />
	<input type="hidden" name="numPerPage" value="${page.getPageSize()}" />
	<input type="hidden" name="orderField" value="${page.getOrderBy()}" />
	<input type="hidden" name="orderDirection" class="${page.getOrder()}" value="${page.getOrder()}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="member.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>会员帐号：</label>
				<input type="text" name="filter_LIKES_userid" value="${Parameters["filter_LIKES_userid"]!''}"/>
			</li>
			<li>
				<label>Email：</label>
				<input type="text" name="filter_LIKES_email" value="${Parameters["filter_LIKES_email"]!''}"/>
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
			<li><a class="add" href="member!input.action" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="member!delete.action?id={xx_id}" target="ajaxTodo" title="你确定要删除吗？" warn="请选择一个文档"><span>删除</span></a></li>
			<li><a class="edit" href="member!input.action?id={xx_id}" target="navTab" warn="请选择一个文档"><span>修改</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="member!deleteIds.action" class="delete"><span>批量删除默认方式</span></a></li>		
			<li class="line">line</li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="member!deleteIds.action" class="delete"><span>批量删除逗号分隔</span></a></li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1024" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="120" orderField="id">会员ID</th>
				<th orderField="userid">会员帐号</th>
				<th orderField="uname">用户名</th>
				<th orderField="sex">性别</th>
				<th orderField="rank">会员级别值</th>
				<th orderField="scores">积分</th>
				<th orderField="spacesta">会员空间状况</th>
				<th orderField="face">头像</th>
				<th orderField="jointime">注册时间</th>
				<th orderField="joinip">注册IP</th>
				<th orderField="logintime">登陆时间</th>
				<th orderField="loginip">登陆IP</th>
			</tr>
		</thead>
		<tbody>
			<#list page.result as xx >
			<tr target="xx_id" rel="${xx.id}">
				<td><input name="ids" value="${xx.id}" type="checkbox"></td>
				<td>${xx.id}</td>
				<td>${xx.userid!}</td>
				<td>${xx.uname!}</td>
				<td>
				<#assign sexList = [{'key':'secret','name':'保密'}, {'key':'male','name':'男'}, {'key':'female','name':'女'}]>  
				<#list sexList as xxx>
					<#if xx.sex == xxx.key >${xxx.name!}</#if>
				</#list>
				</select>
				</td>
				<td>${xx.rank!}</td>
				<td>${xx.scores!}</td>
				<td>${xx.spacesta!}</td>
				<td>${xx.face!}</td>
				<td>${(xx.jointime?string('yyyy-MM-dd'))!}</td>
				<td>${xx.joinip!}</td>
				<td>${(xx.logintime?string('yyyy-MM-dd'))!}</td>
				<td>${xx.loginip!}</td>
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
