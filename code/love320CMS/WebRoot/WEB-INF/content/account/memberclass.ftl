
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.getPageNo()}" />
	<input type="hidden" name="numPerPage" value="${page.getPageSize()}" />
	<input type="hidden" name="orderField" value="${page.getOrderBy()}" />
	<input type="hidden" name="orderDirection" class="${page.getOrder()}" value="${page.getOrder()}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="memberclass.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>班级名：</label>
				<input type="text" name="filter_LIKES_className" value="${Parameters["filter_LIKES_className"]!''}"/>
			</li>
			<li>
				<label>班级号：</label>
				<input type="text" name="filter_LIKES_classId" value="${Parameters["filter_LIKES_classId"]!''}"/>
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
			<li><a class="add" href="memberclass!input.action" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="memberclass!delete.action?id={xx_id}" target="ajaxTodo" title="你确定要删除吗？" warn="请选择一个文档"><span>删除</span></a></li>
			<li><a class="edit" href="memberclass!input.action?id={xx_id}" target="navTab" warn="请选择一个文档"><span>修改</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="memberclass!deleteIds.action" class="delete"><span>批量删除默认方式</span></a></li>		
			<li class="line">line</li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="membertype!deleteIds.action" class="delete"><span>批量删除逗号分隔</span></a></li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="500" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th orderField="className">班级名</th>
				<th width="120" orderField="gradeId">年级号</th>
				<th orderField="classId">班级号</th>
				<th orderField="classId">班级学生人数</th>
			</tr>
		</thead>
		<tbody>
			<#list page.result as xx >
			<tr target="xx_id" rel="${xx.id}">
				<td><input name="ids" value="${xx.id}" type="checkbox"></td>
				<td>${xx.className}</td>
				<td>${xx.gradeId}</td>
				<td>${xx.classId}</td>
				<td>${(xx.students.size())!}</td>
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
