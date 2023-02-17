<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<form action="LoginServlet" method="post">
		メールアドレス：<input type="email" name="userMail">
		パスワード：<input type="text" name="password">
		<input type="submit" value="ログイン">
	</form>

</body>
</html>