<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<form action="login" method="post">
		<label>Login:</label>
		<input type="text" name="login"/>
		<br/>
		<label>Senha:</label>
		<input type="password" name="senha"/>
		<br/>
		<input type="submit" name="logar" value="Logar"/>
	</form>
</body>
</html>