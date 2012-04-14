
<div class="pageContent">
	<form method="post" action="guestbook!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>标题：</label>
				<input type="text" name="title" size="30" id="title" value="${title!''}"/>
			</p>
			<p>
				<label>留言姓名：</label>
				<input type="text" name="uname" size="30" id="uname" value="${uname!''}"/>
			</p>
			<p>
				<label>邮箱：</label>
				<input class="email" type="text" name="email" size="30" id="email" value="${email!''}"/>
			</p>
			<p>
				<label>个人主页：</label>
				<input class="url" type="text" name="homepage" size="30" id="homepage" value="${homepage!''}"/>
			</p>
			<p>
				<label>QQ：</label>
				<input class="digits" type="text" minlength="6" maxlength="20" name="qq" size="30" id="qq" value="${qq!''}"/>
			</p>
			<p>
				<label>留言IP：</label>
				<input class="" type="text" name="ip" size="30" id="ip" value="${ip!''}"/>
			</p>
			<div class="divider"></div>
				<label>留言信息：</label>
				 <textarea name="msg"  cols="80" rows="5">${msg!''}</textarea>
			<div class="divider"></div>
				<label>回复信息</label>
				<textarea name="replymsg"  cols="80" rows="5">${replymsg!''}</textarea>
			<div class="divider"></div>
			
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
