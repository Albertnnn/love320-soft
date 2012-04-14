<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员注册</title>
<meta name="author" content="Love320.com-Sund`" />

<style type="text/css">
.headtop{ background:#F6F6F6; border:1px #CCC solid; padding:10px;}
.G{background:#F6F6F6; border:1px #CCC solid; padding:10px; margin-top:10px;}
</style>

<script type="text/javascript" language="javascript" src="../skin/default/scriptaculous/lib/prototype.js"></script>
<script type="text/javascript" language="javascript" src="../skin/default/scriptaculous/src/scriptaculous.js"></script>
<script type="text/javascript" language="javascript" src="../skin/default/jsvalidate.js"></script>
</head>
<body>
<div class="G">
<form name="query1" method="post" action="user!regSave.action">

			<p>
				<label>用户帐号：</label>
				<input type="text" name="userid" class="jsrequired jsvalidate_alpha" alt="必须填写"  size="30" id="userid" value="">
			</p>
			<p>
				<label>用户密码：</label>
				<input type="password" name="pwd" class="jsrequired jsvalidate_alpha" alt="必须填写" minlength="6" maxlength="20" size="30" id="pwd" value="">
			</p>
			<p>
				<label>确认密码：</label>
				<input type="password" name="repwd" class="jsrequired jsvalidate_alpha" alt="必须填写" minlength="6" maxlength="20" size="30" id="repwd" value="">
			</p>
			<p>
				<label>用户名：</label>
				<input type="text" name="uname" size="30" id="uname" value="" class="textInput">
			</p>
			<p>
				<label>性别：</label>
				<select class="" name="sex">
						<option value="secret" selected="">保密</option>
						<option value="male">男</option>
						<option value="female">女</option>
				</select>
			</p>
			<p>
				<label>会员类型：</label>
				<select class="" name="mtmId">
					<#list listMT as xx>
                    	<option value="${xx.id}">${xx.typeName}</option>
                    </#list>
				</select>
			</p>
			<p>
				<label>学生班级：</label>
				<select class="" name="mcId">
					<#list listMC as xx>
                    	<option value="${xx.id}">${xx.className}</option>
                    </#list>
				</select>
			</p>
			<p>
				<label>Email：</label>
				<input type="text" name="email" class="jsrequired jsvalidate_email" alt="必须填写正确的邮件" size="30" id="email" value="">
			</p>
			<p>
				<label>手机号：</label>
				<input type="text" name="phone" class="jsrequired jsvalidate_usphone" alt="必须填写正确的手机" size="30" id="phone" value="">
			</p>
			<p>
				<label>安全提示问题 ：</label>
				<input type="text" name="safequestion" size="30" id="safequestion" value="" class="textInput">
			</p>
			<p>
				<label>安全提示问题答案：</label>
				<input type="text" name="safeanswer" size="30" id="safeanswer" value="" class="textInput">
			</p>
			<button type="submit">保存</button>
                 </form>
			<div class="divider"></div>
		</div>
</body>
<html>