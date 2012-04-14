
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="role!input.action" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="role!delete.action?id={xx_id}" target="ajaxTodo" title="你确定要删除吗？" warn="请选择一个文档"><span>删除</span></a></li>
			<li><a class="edit" href="role!input.action?id={xx_id}" target="navTab" warn="请选择一个文档"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="role!deleteIds.action" class="delete"><span>批量删除默认方式</span></a></li>		
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="role!deleteIds.action" class="delete"><span>批量删除逗号分隔</span></a></li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="980" layoutH="70">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="120" orderField="id">标签ID</th>
				<th width="120">名称</th>
				<th >授权</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<#list allRoleList as xx >
			<tr target="xx_id" rel="${xx.id}">
				<td><input name="ids" value="${xx.id}" type="checkbox"></td>
				<td>${xx.id}</td>
				<td>${xx.name}</td>
				<td>${xx.authNames}</td>
				<td>
					<a title="删除" target="ajaxTodo" href="role!delete.action?id=${xx.id}" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="role!input.action?id=${xx.id}" class="btnEdit">编辑</a>
				</td>
			</tr>
			</#list>
		</tbody>
	</table>
	<div class="panelBar">			
	</div>
</div>
