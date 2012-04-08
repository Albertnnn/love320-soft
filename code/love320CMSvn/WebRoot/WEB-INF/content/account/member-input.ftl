<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 
<div class="pageContent">
	<form method="post" action="member!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${id!''}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户帐号：</label>
				<input type="text" name="userid" class="required" size="30" id="userid" value="${userid!''}"/>
			</p>
			<p>
				<label>用户密码：</label>
				<input type="password" name="pwd" class="required alphanumeric" minlength="6" maxlength="20" size="30" id="pwd" value="${pwd!''}"/>
			</p>
			<p>
				<label>用户名：</label>
				<input type="text" name="uname" size="30" id="uname" value="${uname!''}"/>
			</p>
			<p>
				<label>性别：</label>
				<select class="combox" name="sex" size="30">
				<#assign sexList = [{'key':'secret','name':'保密'}, {'key':'male','name':'男'}, {'key':'female','name':'女'}]>  
				<#list sexList as xx>
					<#if sex ??>
						<option value="${xx.key!}" <#if xx.key == sex >selected</#if>>${xx.name!}</option>
					<#else>
						<option value="${xx.key!}" <#if xx.key == 'secret' >selected</#if>>${xx.name!}</option>
					</#if>
				</#list>
				</select>
			</p>
			<p>
				<label>会员类型：</label>
				<select class="combox" name="mtmId" size="30">
				<#list memberTypes as xx>
					<#if mtype ??>
						<option value="${xx.id!}" <#if xx.id == mtype.id >selected</#if>>${xx.typeName!}</option>
					<#else>
						<option value="${xx.id!}" <#if xx.id == 1 >selected</#if>>${xx.typeName!}</option>
					</#if>
				</#list>
				</select>
			</p>
			<p>
				<label>会员级别值：</label>
				<input type="text" name="rank" size="30" id="rank" value="${rank!''}"/>
			</p>
			<p>
				<label>是否待升级：</label>
				<input type="text" name="uprank" size="30" id="uprank" value="${uprank!''}"/>
			</p>
			<p>
				<label>会员金币：</label>
				<input type="text" name="money" size="30" id="money" value="${money!''}"/>
			</p>
			<p>
				<label>学生班级：</label>
				<select class="combox" name="mcId" size="30">
				<#list mcList as xx>
					<#if memberclassstudent ??>
						<option value="${xx.id!}" <#if xx.id == memberclassstudent.id >selected</#if>>${xx.className!}</option>
					<#else>
						<option value="${xx.id!}" <#if xx.id == 1 >selected</#if>>${xx.className!}</option>
					</#if>
				</#list>
				</select>
			</p>
			<p>
				<label>是否待充值：</label>
				<input type="text" name="upmoney" size="30" id="upmoney" value="${upmoney!''}"/>
			</p>
			<p>
				<label>Email：</label>
				<input type="text" name="email" class="required email" size="30" id="email" value="${email!''}"/>
			</p>
			<p>
				<label>手机号：</label>
				<input type="text" name="phone" class="required phone" size="30" id="phone" value="${phone!''}"/>
			</p>
			<p>
				<label>积分：</label>
				<input type="text" name="scores" size="30" id="scores" value="${(scores?c)!''}"/>
			</p>
			<p>
				<label>推荐：</label>
				<input type="text" name="matt" size="30" id="matt" value="${matt!''}"/>
			</p>
			<p>
				<label>会员空间状况：</label>
				<input type="text" name="spacesta" size="30" id="spacesta" value="${spacesta!''}"/>
			</p>
			<p>
				<label>头像：</label>
				<input type="text" name="face" size="30" id="face" value="${face!''}"/>
			</p>
			<p>
				<label>安全提示问题 ：</label>
				<input type="text" name="safequestion" size="30" id="safequestion" value="${safequestion!''}"/>
			</p>
			<p>
				<label>安全提示问题答案：</label>
				<input type="text" name="safeanswer" size="30" id="safeanswer" value="${safeanswer!''}"/>
			</p>
			<p>
				<label>注册时间：</label>
				<input type="text" name="jointime" size="30" id="jointime" value="${(jointime?string('yyyy-MM-dd'))!}" class="date"/>
			</p>
			<p>
				<label>注册IP：</label>
				<input type="text" name="joinip" size="30" id="joinip" value="${joinip!''}"/>
			</p>
			<p>
				<label>登陆时间：</label>
				<input type="text" name="logintime" size="30" id="logintime" value="${(logintime?string('yyyy-MM-dd'))!}" class="date"/>
			</p>
			<p>
				<label>登陆IP：</label>
				<input type="text" name="loginip" size="30" id="loginip" value="${loginip!''}"/>
			</p>
			<div class="divider"></div>
				<label>班级管理：</label>
				<#list mcList as xx>
				<label><input type="checkbox" name="checkedMemberClassIds" value="${xx.id}" <#list memberClassLists as xxx><#if xx.id == xxx.id >checked</#if></#list> />${xx.className}</label>
				</#list>
			<div class="divider"></div>
		</div>
		<div class="formBar">
			<ul>	
				<@security.authorize ifAnyGranted="ROLE_修改会员"> 
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				</@security.authorize> 
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
