<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#loginName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					loginName: {
						required: true,
						remote: "user!checkLoginName.action?oldLoginName=" + encodeURIComponent('${loginName!''}')
					},
					name: "required",
					password: {
						required: true,
						minlength:3
					},
					passwordConfirm: {
						equalTo:"#password"
					},
					email:"email",
					checkedRoleIds:"required"
				},
				messages: {
					loginName: {
						remote: "用户登录名已存在"
					},
					passwordConfirm: {
						equalTo: "输入与上面相同的密码"
					}
				}
			});
		});
	</script>

<div class="pageContent">
	<form method="post" action="user!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>登录名：</label>
				<input class="required" type="text" name="loginName" size="30" id="loginName" value="${loginName!''}"/>
			</p>
			<p>
				<label>用户名：</label>
				<input type="text" name="name" size="30" id="name" value="${name!''}"/>
			</p>
			<p>
				<label>密码：</label>
				<input class="required" type="password" name="password" size="30" id="password" value="${password!''}"/>
			</p>
			<p>
				<label>确认密码：</label>
				<input class="required" type="password" name="passwordConfirm" size="30" id="passwordConfirm" value="${password!''}"/>
			</p>
			<p>
				<label>邮箱：</label>
				<input type="text" name="email" size="30" id="email" value="${email!''}"/>
			</p>
			<div class="divider"></div>
				<label>角色：</label>
				<#list allRoleList as xx>
					<label><input type="checkbox" name="checkedRoleIds" value="${xx.id}" <#list roleList as xxx><#if xx.id == xxx.id >checked</#if></#list>/>${xx.name}</label>
				</#list>
			
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
