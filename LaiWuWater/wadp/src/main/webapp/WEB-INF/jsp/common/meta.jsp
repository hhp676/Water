<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="com.hongguaninfo.hgdf.wadp.shiro.conf.*"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<c:set var="uri" value="${pageContext.request.requestURI}"></c:set>
<c:set var="defaultPageSize" value="15"></c:set>
<c:set var="defaultPageList" value="[15,20,30]"></c:set>
<!--  全局 -->
<script>
G_COUNT++;
var G_CTX_PATH = "${ctx}";
if (G_COUNT == 1) {
	var requestUri = "${uri}";
}
</script>
