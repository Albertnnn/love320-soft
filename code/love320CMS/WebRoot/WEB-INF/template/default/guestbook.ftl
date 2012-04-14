<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Mini-Web 帐号管理</title>
		<script>
$(document).ready(function() {
	//聚焦第一个输入框
		$("#loginName").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate(
				{
					rules : {
						loginName : {
							required : true,
							remote : "user!checkLoginName.action?oldLoginName="
									+ encodeURIComponent('${loginName!}')
						},
						name : "required",
						password : {
							required : true,
							minlength : 3
						},
						passwordConfirm : {
							equalTo : "#password"
						},
						email : "email",
						checkedRoleIds : "required"
					},
					messages : {
						loginName : {
							remote : "用户登录名已存在"
						},
						passwordConfirm : {
							equalTo : "输入与上面相同的密码"
						}
					}
				});
	});
</script>
	</head>

	<body>
	{love320_留言板列表   /}{love320_留言板列表   /}{love320_留言板列表   /}{love320_留言板列表   /}
		<div id="doc3">
			<div id="bd">
				<div id="yui-main">
					<div class="yui-b">
						<h2>
							<s:if test="id == null">创建</s:if>
							<s:else>修改</s:else>
							留${pagecontent.temIterationId!}言
						</h2>
							{love320_留言板列表   /}
							{love320_留言板分页 /}
							{love320_发留言/}
							
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
