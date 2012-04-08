<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>1111</title>
<link href="/skin/default/matser.css" rel="stylesheet" type="text/css" />
</head>
<body>
<style type="text/css">
.headtop{ background:#F6F6F6; border:1px #CCC solid; padding:10px;}
.G{background:#F6F6F6; border:1px #CCC solid; padding:10px; margin-top:10px;}
</style>

<script type="text/javascript" language="javascript" src="../skin/default/scriptaculous/lib/prototype.js"></script>
<script type="text/javascript" language="javascript" src="../skin/default/scriptaculous/src/scriptaculous.js"></script>
<script type="text/javascript" language="javascript" src="../skin/default/jsvalidate.js"></script>
<#include "navlist.ftl">
<div class="G">
	<form name="query1" method="post" action="user!save.action">
	<input type="hidden" name="id" value="${member.id!}">
			<p>
				<label>用户密码：</label>
				<input type="password" name="pwd" size="30" id="pwd" value="${member.pwd!}">
			</p>
			<p>
				<label>用户名：</label>
				<input type="text" name="uname" size="30" id="uname" value="${member.uname!}" class="textInput">
			</p>
			<p>
				<label>性别：</label>
				<select class="" name="sex">
						<option value="secret" <#if member.sex == "secret" >selected </#if>>保密</option>
						<option value="male" <#if member.sex == "male" >selected </#if>>男</option>
						<option value="female" <#if member.sex == "female" >selected </#if>>女</option>
				</select>
			</p>
			<p>
				<label>Email：</label>
				<input type="text" name="email" class="jsrequired jsvalidate_email" alt="必须填写正确的邮件" size="30" id="email" value="${member.email!}">
			</p>
			<p>
				<label>手机号：</label>
				<input type="text" name="phone" class="jsrequired jsvalidate_usphone" alt="必须填写正确的手机" size="30" id="phone" value="${member.phone!}">
			</p>
			<p>
				<label>安全提示问题 ：</label>
				<input type="text" name="safequestion" size="30" id="safequestion" value="${member.safequestion!}" class="textInput">
			</p>
			<p>
				<label>安全提示问题答案：</label>
				<input type="text" name="safeanswer" size="30" id="safeanswer" value="${member.safeanswer!}" class="textInput">
			</p>
			<button type="submit">保存</button>
                 </form>
</body>
<html>