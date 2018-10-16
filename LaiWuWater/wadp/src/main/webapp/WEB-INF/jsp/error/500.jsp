<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.StringWriter"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<head>
<title>出问题了 - 系统内部错误</title>
<script type="text/javascript" src="${ctx}/static/js/jquery-1.11.0.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-blockUI/jquery.blockUI.js"></script>
</head>
<body>
	<script>
		$.blockUI({message:'对不起,您访问的页面出了一点内部小问题!',
		css : {
				backgroundColor : 'red',
				height : 30
			}
		});
	</script>
</body>
</html>

