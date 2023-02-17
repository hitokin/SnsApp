<%@page import="dto.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dto.Account" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String errorCode = request.getParameter("error");
		if(errorCode != null && errorCode.equals("1")){
			Account ac = (Account)session.getAttribute("input_data");
		%>
		<p style="color:red">登録に失敗しました。</p>
		<h2>新規登録</h2>
	<form action="AccountRegistServlet" method="post">
		名前：<input type="text" name="userName"><br>
		ID：<input type="text" name="userId"><br>
		メールアドレス：<input type="email" name="userMail"><br>
		生年月日：<input type="date" name="userBirthday"><br>
		パスワード：<input type="text" name="password"><br>
		<input type="submit" value="登録">
	</form>
	
	<%
	} else {
	%>
	<h2>新規登録</h2>
	<form action="AccountRegistConfirmServlet" method="post">
		名前：<input type="text" name="userName"><br>
		ID：<input type="text" name="userId"><br>
		メールアドレス：<input type="email" name="userMail"><br>
		生年月日：<input type="date" name="userBirthday"><br>
		パスワード：<input type="text" name="password"><br>
		<input type="submit" value="登録">
	</form>
	<%} %>

</body>
</html>