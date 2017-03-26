<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="#" id="menu-toggler" class="menu-toggler"><span class="menu-text"></span></a>
<div id="sidebar" class="sidebar">
	<c:set value="${'$'}{param.p.split(\"-\")[0] }" var="fid"></c:set>
	<c:set value="${'$'}{param.p.split(\"-\")[1] }" var="sid"></c:set>
	<ul class="nav nav-list">
		<li id="1"><a href="/main/"> <i class=" icon-home"></i> <span class="menu-text"> HomePage </span></a></li>
		<#list tbinfos as tbinfo>
		<li id="${tbinfo.tbname}_m"><a href="#" class="dropdown-toggle"> <i class=" icon-user"></i> <span class="menu-text"> ${tbinfo.tbname} </span> <b class="arrow icon-angle-down"></b></a>
			<ul class="submenu">
				<li id="${tbinfo.tbname}_l"><a href="${'$'}{WebUtil.projectname }/${tbinfo.tbname}/list"> <i class="icon-double-angle-right"></i> ${tbinfo.tbname} list</a></li>
			</ul></li>
		</#list>
		<li id="14"><a href="#" class="dropdown-toggle"> <i class="icon-cog"></i> <span class="menu-text"> System </span> <b class="arrow icon-angle-down"></b></a>
			<ul class="submenu">
				<li id="118"><a href="javascript:;"> <i class="icon-double-angle-right"></i> 管理员(n)</a></li>
				<li id="119"><a href="javascript:;"> <i class="icon-double-angle-right"></i> 权限管理(n)</a></li>
				<li id="120"><a href="javascript:;"> <i class="icon-double-angle-right"></i> 角色管理(n)</a></li>
				<li id="121"><a href="javascript:;"> <i class="icon-double-angle-right"></i> 操作日志(n)</a></li>
			</ul></li>
		<li id="15"><a href="${'$'}{WebUtil.projectname }/j_spring_security_logout"> <i class=" icon-signout"></i> <span class="menu-text"> 退出 </span></a></li>
	</ul>
	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	</div>
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
		function initMenu(){
			if ('${'$'}{fid}') {
				$('#${'$'}{fid}').addClass('active');
			}
			if ('${'$'}{sid}') {
				$('#${'$'}{sid}').addClass('active');
			}
		}
	</script>
</div>