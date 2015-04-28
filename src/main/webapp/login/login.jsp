<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
</head>
<body>
	<s:form action="/loginAction_login" method="post">
		<s:textfield label="用户名" name="username"></s:textfield>
		<s:password name="password" label="密码"></s:password>
		<s:submit value="登陆"></s:submit>
		<s:reset value="重写"></s:reset>
	</s:form>
</body>
</html>