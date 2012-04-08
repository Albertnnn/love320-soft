<div class="headtop">
用户 ${(Model.userid)!(member.uname)}&nbsp;&nbsp;&nbsp;
<a href="user!edit.action" >修改个人信息</a>&nbsp;&nbsp;&nbsp;<br/><br/>
<#if member.mtype ??>
<#if member.mtype.typeMark == "teacher" >
<a href="member!sendsms.action" >发短信</a> &nbsp;&nbsp;&nbsp;
<a href="member!receive.action" >收短信箱</a>&nbsp;&nbsp;&nbsp;
<a href="member!send.action" >发短信箱</a>&nbsp;&nbsp;&nbsp;<br/><br/>
<a href="memberclassnotice!list.action" >班级通知</a>&nbsp;&nbsp;&nbsp;
<a href="memberclassnotice!input.action" >发布班级通知</a>&nbsp;&nbsp;&nbsp;<br/><br/>
<a href="memberclassnotice!listwork.action" >班级作业</a>&nbsp;&nbsp;&nbsp;
<a href="memberclassnotice!inputwork.action" >发布班级作业</a>&nbsp;&nbsp;&nbsp;
<#else>
<a href="memberclassnotice!list.action" >班级通知</a>&nbsp;&nbsp;&nbsp;
<a href="memberclassnotice!listwork.action" >班级作业</a>&nbsp;&nbsp;&nbsp;
</#if>
<#else>
<a href="memberclassnotice!list.action" >班级通知</a>&nbsp;&nbsp;&nbsp;
<a href="memberclassnotice!listwork.action" >班级作业</a>&nbsp;&nbsp;&nbsp;
</#if><br/><br/>
<a href="websms!list.action" >在校表现</a>&nbsp;&nbsp;&nbsp;
<a href="websms!listsubjects.action" >考试管理</a>&nbsp;&nbsp;&nbsp;
<#if member.mtype.typeMark == "teacher" ><br/><br/>
<a href="websms!input.action" >发布在校表现</a>&nbsp;&nbsp;&nbsp;
<a href="websms!inputsubjects.action" >发布考试成绩</a>&nbsp;&nbsp;&nbsp;
</#if>
</div>