
<div class="pageContent">
	<form method="post" action="feedback!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">

			<p>
				<label>文章ID</label>
				${archives.title!}
			</p>
			<p>
				<label>评论标题</label>
                <input class="text" type="text" name="title" size="30" id="title" value="${title!}"/>
                                        
			</p>
			<p>
				<label>评论用户名</label>
                <input class="text" type="text" name="username" size="30" id="username" value="${username!}"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>评论内容</label>
                <textarea name="msg"  cols="80" rows="3">${msg!}</textarea>
			</p>
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
