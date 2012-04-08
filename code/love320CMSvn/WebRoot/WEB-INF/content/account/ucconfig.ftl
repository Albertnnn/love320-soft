<h2 class="contentTitle">UC配置</h2>


<div class="pageContent">
	
	<form action="ucconfig!save.action" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this)">
		<div class="pageFormContent nowrap" layoutH="97">

			<dl>
				<dt>UC_API：</dt>
				<dd>
					<input type="text" size="30" name="api" id="api" value="${api!}" />
					<span class="info">&nbsp;&nbsp;&nbsp;&nbsp;应用的主 URL</span>
				</dd>
			</dl>
			<dl>
				<dt>UC_IP：</dt>
				<dd>
					<input type="text" size="30" name="ip" id="ip" value="${ip!}" />
					<span class="info">&nbsp;&nbsp;&nbsp;&nbsp;应用 IP</span>
				</dd>
			</dl>
			<dl>
				<dt>UC_KEY：</dt>
				<dd>
					<input type="text" size="30" name="key" id="key" value="${key!}" />
					<span class="info">&nbsp;&nbsp;&nbsp;&nbsp;通信密钥</span>
				</dd>
			</dl>
			<dl>
				<dt>UC_APPID：</dt>
				<dd>
					<input type="text" size="30" name="appid" id="appid" value="${appid!}" />
					<span class="info">&nbsp;&nbsp;&nbsp;&nbsp;应用ID</span>
				</dd>
			</dl>
			<dl>
				<dt>UC_CONNECT：</dt>
				<dd>
					<input type="text" size="30" name="connect" id="connect" value="${connect!}" />
					<span class="info">&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>

