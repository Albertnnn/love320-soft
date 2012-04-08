<h2 class="contentTitle">网站栏目</h2>

<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="arctype!input.action" target="navTab"><span>添加</span></a></li>
		</ul>
</div>

<div layouth="100" style="height: 167px; overflow-x: auto; overflow-y: auto; ">
		<ul class="tree expand">
			<#assign list=typeUnit.typeunits>
			<#include "arctypeunit.ftl" >
		</ul>	
</div>

