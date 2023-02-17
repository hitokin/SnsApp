<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dto.Account" %>
    <%@page import="dto.Post" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
</head>
<body>
	<nav>
	<h1>
	名前：<%=session.getAttribute("userName") %>
	</h1>
	<a href="#">設定</a>
	<a href="#">メッセージ</a>
	<a href="#">話題を検索</a>
	<a href="#">タイムラインへ</a>
	<a href="Lo">ログアウト</a>
	</nav>
	<br>
	<%-- アカウント登録後または、ログイン後投稿が存在しない場合に表示する画面 --%>
	<%
		int registFlg = ((Integer)(session.getAttribute("registFlg"))).intValue();
		int recordFlg = ((Integer)(session.getAttribute("recordFlg"))).intValue();
		if (registFlg == 1 || recordFlg == 1){
	%>
	<p>投稿がありません。</p><br>
	<%
		} else {
	%>
	<%-- 投稿が存在する場合に表示する画面 --%>
	<% 
		ArrayList<Post> post = (ArrayList<Post>)session.getAttribute("getTimeline");
			int size = ((Integer)(session.getAttribute("RecordSize"))).intValue();
			for(int i = 0; i < size; i++){
				Post record = post.get(i);
			
	%>
	<p>
		<%=record.getAccount_name() %>
		<%=record.getPost() %>
		<%=record.getLikes() %>
		<%=record.getCreatedtime() %>
	</p>
	<%
			} 
	%>
	<%} %>
	<form action="PostServlet" method="post">
		<input type="text" name="SubmissionDetails">
		<input type="hidden" name="userId" value="<%=session.getAttribute("userId")%>">
		<input type="submit" value="投稿">
	</form>
</body>
</html>