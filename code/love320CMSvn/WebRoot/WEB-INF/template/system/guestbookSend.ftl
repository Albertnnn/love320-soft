<style type="text/css">
/*guest*/
.guestTab {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}
.guestTab td {
	padding: 4px;
	border: solid 1px #CCC;
	text-align: left;
}
.guestTab p {
	line-height: 25px;
}
.bookTr {
}
.guestAdd td {
	text-align: left;
	padding: 4px;
}
</style>
<form id="inputForm" action="${base}/html/guestbook!save.action" method="post">
  <input type="hidden" name="id" value="" />
  <table class="noborder">
    <tr>
      <td align="right">标题 :</td>
      <td><input type="text" name="entity.title" size="30" id="entity.title" value="" /></td>
    </tr>
    <tr>
      <td align="right">姓名:</td>
      <td><input type="text" name="entity.uname" size="30" id="entity.uname" value="" /></td>
    </tr>
    <tr>
      <td align="right">邮箱 :</td>
      <td><input type="text" name="entity.email" size="30" id="entity.email" value="" /></td>
    </tr>
    <tr>
      <td align="right">个人主页: </td>
      <td><input type="text" name="entity.homepage" size="30"
					id="entity.homepage" value="" /></td>
    </tr>
    <tr>
      <td align="right"> QQ: </td>
      <td><input type="text" name="entity.qq" size="30" id="entity.qq"
					value="" /></td>
    </tr>
    <tr>
      <td align="right"> 选择头像: </td>
      <td><p>
          <label>
            <input type="radio" name="entity.face" value="1" id="entity.face" />
            <img src="${base}/skin/system/themes/default/images/pic/1.gif" width="25" height="25" alt="1" /> </label>
          <label>
            <input type="radio" name="entity.face" value="2" id="entity.face" />
            <img src="${base}/skin/system/themes/default/images/pic/2.gif" width="25" height="25" alt="2" /> </label>
          <label>
            <input type="radio" name="entity.face" value="3" id="entity.face" />
            <img src="${base}/skin/system/themes/default/images/pic/3.gif" width="25" height="25" alt="3" /> </label>
          <label>
            <input type="radio" name="entity.face" value="4" id="entity.face" />
            <img src="${base}/skin/system/themes/default/images/pic/4.gif" width="25" height="25" alt="4" /> </label>
          <label>
            <input type="radio" name="entity.face" value="5" id="entity.face" />
            <img src="${base}/skin/system/themes/default/images/pic/5.gif" width="25" height="25" alt="5" /> </label>
          <label>
            <input type="radio" name="entity.face" value="6" id="entity.face" />
            <img src="${base}/skin/system/themes/default/images/pic/6.gif" width="25" height="25" alt="6" /> </label>
          <label>
            <input type="radio" name="entity.face" value="7" id="entity.face" />
            <img src="${base}/skin/system/themes/default/images/pic/7.gif" width="25" height="25" alt="7" /> </label>
          <label>
            <input type="radio" name="entity.face" value="8" id="entity.face" />
            <img src="${base}/skin/system/themes/default/images/pic/8.gif" width="25" height="25" alt="8" /> </label>
          <label>
            <input type="radio" name="entity.face" value="9" id="entity.face" />
            <img src="${base}/skin/system/themes/default/images/pic/9.gif" width="25" height="25" alt="9" /> </label>
          <label>
            <input type="radio" name="entity.face" value="10" id="entity.face" />
            <img src="${base}/skin/system/themes/default/images/pic/10.gif" width="25" height="25"
							alt="10" /> </label>
          <label>
            <input type="radio" name="entity.face" value="11" id="entity.face" />
            <img src="${base}/skin/system/themes/default/images/pic/11.gif" width="25" height="25"
							alt="11" /> </label>
        </p></td>
    </tr>
    <tr>
      <td align="right" valign="top"> 留言信息 </td>
      <td><textarea name="entity.msg" id="entity.msg" cols="50" rows="3"></textarea></td>
    </tr>
    <tr>
      <td align="right"> 验证码: </td>
      <td><input class="text-input"  type='text' name='j_captcha' size='5'/>
        <img id="captchaImg" src="${base}/security/jcaptcha.jpg" width="80" height="30"/></td>
    </tr>
    <tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td><input class="button" type="submit" value="提交" /></td>
    </tr>
  </table>
</form>
