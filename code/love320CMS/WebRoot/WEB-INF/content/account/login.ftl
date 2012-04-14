<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Love320!CMS 管理系统 | 登录</title>
<link href="${base}/skin/system/themes/css/login.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="login_G">
	 	<div class="login_content">
        <form id="loginForm" action="${base}/j_spring_security_check" method="post">
        	<p>
            	<label>用户名：</label>
                <input type="text" class="intext" id="j_username" name="j_username"/>
            </p>
            <p>
            	<label>锁密码：</label>
                <input type="password" id="j_password" name="j_password" class="intext" />
            </p>
            <p>
            	<label>验证码：</label>
                <input type="text" name="j_captcha" class="authentication" />
                <a href="javascript:refreshCaptcha()" title="看不清楚换一张"><img id="captchaImg" src="${base}/security/jcaptcha.jpg" width="95" height="20" /></a>
            	<script src="${base}/skin/system/js/jquery-1.4.4.js" type="text/javascript"></script>
					<script type="text/javascript">
						function refreshCaptcha() {
							$('#captchaImg').hide().attr('src','${base}/security/jcaptcha.jpg?' + Math.floor(Math.random()*100)).fadeIn();
						}
	
						</script>
            </p>
            <p>
            	<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            	<input type="checkbox" name="_spring_security_remember_me"/>两周内记住我
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:refreshCaptcha()">看不清楚换一张</a>
            </p>
            <p>
            	<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <input type="submit" class="btn" value="ф登录" />
            </p>
        </form>
        </div>
	</div>
</body>
</html>