
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.getPageNo()}" />
	<input type="hidden" name="numPerPage" value="${page.getPageSize()}" />
	<input type="hidden" name="orderField" value="${page.getOrderBy()}" />
	<input type="hidden" name="orderDirection" class="${page.getOrder()}" value="${page.getOrder()}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="archives.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>文章名：</label>
				<input type="text" name="filter_LIKES_title" value="${Parameters["filter_LIKES_title"]!''}"/>
			</li>
			<li>
				<label>栏目名： </label>
				<select class="combox" name="filter_EQI_typeid">
					<#list checkedarctypes as xx>
						<#if Parameters["filter_EQI_typeid"]??>
							<option value="${xx.id!}" <#if (Parameters["filter_EQI_typeid"] == xx.id?string) >SELECTED</#if> >${xx.typename!}</option> 
						<#else>
							<option value="${xx.id!}" >${xx.typename!}</option>
						</#if>
					</#list>
				</select>
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
			<li><a class="add" href="archives!input.action" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="archives!delete.action?id={xx_id}" target="ajaxTodo" title="你确定要删除吗？" warn="请选择一个文档"><span>删除</span></a></li>
			<li><a class="edit" href="archives!input.action?id={xx_id}" target="navTab" warn="请选择一个文档"><span>修改</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="archives!deleteIds.action" class="delete"><span>批量删除默认方式</span></a></li>		
			<li class="line">line</li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="archives!deleteIds.action" class="delete"><span>批量删除逗号分隔</span></a></li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1000" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="120" orderField="id">文章ID</th>
				<th orderField="title">简明标题</th>
				<th>文档属性</th>
				<th width="80" orderField="click">点击率</th>
				<th width="130" orderField="typeid">所属栏目</th>
				<th width="150" align="center" orderField="senddate">时间</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<#list page.result as xx >
			<tr target="xx_id" rel="${xx.id}">
				<td><input name="ids" value="${xx.id}" type="checkbox"></td>
				<td>${xx.id}</td>
				<td>${xx.title}</td>
				<td><#list xx.flagList as xxx>
						<font color="red" >${xxx.attName},</font>
					</#list></td>
				<td>${xx.click}</td>
				<td>${xx.arctype.typename}(${xx.typeid})</td>
				<td>${xx.senddate?string('EEE, dd MMM yyyy')}</td>
				<td>
					<a title="删除" target="ajaxTodo" href="archives!delete.action?id=${xx.id}" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="archives!input.action?id=${xx.id}" class="btnEdit">编辑</a>
					<a title="浏览" target="navTab" href="${base}/html/article.action?id=${xx.id}" class="btnView" external="true">浏览</a>
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
