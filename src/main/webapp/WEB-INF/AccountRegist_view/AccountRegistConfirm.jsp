<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dto.Account" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<p>下記の内容で登録します。よろしいですか？</p>
	<%
		Account account = (Account)session.getAttribute("registAccountData");
	%>
		名前：<%=account.getUserName() %><br>
		ユーザID：<%=account.getUserId() %><br>
		生年月日：<%=account.getUserBirthday() %><br>
		パスワード：<%=account.getPassword() %><br>
		メールアドレス：<%=account.getUserMail() %><br>
		<a href="AccountRegistExecuteServlet">OK</a><br>
		<a href="AccountRegistFormServlet">戻る</a><br>
</body>
</html>