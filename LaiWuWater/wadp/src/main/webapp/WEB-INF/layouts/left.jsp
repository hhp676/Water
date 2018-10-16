<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>

<c:forEach items="${auths}" var="auth" varStatus="vs">
	<li>
		<a class="dropdown-toggle" href="#">
			<i class="${auth.authIcon}"></i>
			<span class="menu-text">${auth.authName}</span>
			<b class="arrow icon-angle-down"></b>
		</a>
		<ul class='submenu'>
			<c:forEach items="${auth.childs}" var="childAuth" varStatus="childVs">
				<li>
					<a href="${ctx}${childAuth.authUri}">
						<i class="icon-angle-right"></i>
						<span class="menu-text">${childAuth.authName}</span>
					</a>
				</li>
			</c:forEach>
		</ul>
	</li>
</c:forEach>