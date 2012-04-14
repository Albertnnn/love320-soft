
<form id="pagerForm" action="#rel#" >
	<input type="hidden" id="dirRoot" name="dirRoot" value="${dirRoot!}" />
	<input type="hidden" id="dirThe" name="dirThe" value="${dirThe!}" />
	<input type="hidden" id="revarname" name="revarname" value="${revarname!}"/>
</form>

<div class="pageHeader">

	<form rel="pagerForm" method="post" action="upfile.action" onsubmit="return dwzSearch(this, 'dialog');">
	<input type="hidden" id="dirRoot" name="dirRoot" value="${dirRoot!}" />
	<input type="hidden" id="dirThe" name="dirThe" value="${dirThe!}" />
	<input type="hidden" id="revarname" name="revarname" value="${revarname!}"/>
		<ul>
			<li>
				<label>选择文件夹:</label>
				<#list dirs as xx>
					<#if xx != "">
						<label><input type="radio" name="dir" value="${xx!}"/>${xx!}</label>
					<#else>
						<label><input type="radio" name="dir" value="revarname"/>返回上级目录</label>
					</#if>
				</#list>
				
			</li>			
		</ul>
		<div class="divider"></div>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</form>
</div>

<div class="pageContent">
	
	<table class="table" layoutH="170" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th orderfield="fileName">文件名</th>
				<th>文件大小</th>
				<th>文件属性</th>
				<th>创建时间</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<#list files as xx>
			<#if xx != "">
			<tr>
				<td>${xx!}</td>
				<td>无</td>
				<td>无</td>
				<td>不知</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({${revarname!}:'${dirThe}/${xx!}'})" title="查找带回">选择</a>
				</td>
			</tr>
			</#if>
			</#list>
		</tbody>
	</table>
</div>
<div class="pageContent">
	<form action="upfile!upfile.action" enctype="multipart/form-data" method="post" class="pageForm required-validate" onsubmit="return iframeCallback(this, $.bringBack)">
	<input type="hidden" id="revarname" name="revarname" value="${revarname!}"/>
	<div class="pageFormContent">
		<dl>
			<dt>上传文件：</dt><dd><input type="file" name="upload" id="upload" class="required" size="30" /></dd>
		</dl>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">上传</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
	</form>
</div>