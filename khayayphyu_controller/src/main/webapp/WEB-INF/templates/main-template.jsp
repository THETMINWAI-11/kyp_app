<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/importTag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no">
<%@ include file="../include/csstemplate.jsp"%>
</head>
<title>KhaYayPhyu</title>
<body class="layout layout-header-fixed layout-sidebar-fixed"
	style="background-color: #eee;">

	<input id="frmMessage" type="hidden" value="${ frmMessage }">
	<input id="server-local-name" type="hidden" value="${server_name}" />

	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<div class="layout-main">
		<tiles:insertAttribute name="left-menu"></tiles:insertAttribute>
		<tiles:insertAttribute name="theme"></tiles:insertAttribute>
		<div class="layout-content">
			<div class="layout-content-body">
				<tiles:insertAttribute name="body"></tiles:insertAttribute>
			</div>
		</div>
	</div>
	<div>
		<%@ include file="../include/jstemplate.jsp"%>
	</div>
</body>
</html>