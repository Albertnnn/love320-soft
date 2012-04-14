
<#list list as xx>
	   <li><a href="arctype!input.action?id=${xx.id!}" target="navTab">${(xx.typename)!}[ID:${xx.id!}]</a>
   <#if xx.typeunits?exists >
   	<ul>
      <#assign list=xx.typeunits>
      <#include "arctypeunit.ftl" >
    </ul>
    </#if>
    </li>  
</#list>