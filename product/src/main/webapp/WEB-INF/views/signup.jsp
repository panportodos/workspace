<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    //내장객체
    //저장소
    //application 객체 저장소
    //request 객체 저장소
    //session 객체 저장소
   
    String value4 = (String) request.getAttribute("key4"); //type mismatch문제로 attribute를 가져올 때는 항상 다운캐스팅을 해야한다
    String value44 = (String) application.getAttribute("key4");
    String value444 = (String) session.getAttribute("key4");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>데이터 확인</h1>
	<h2><%=value4 %></h2>
	<h3>${key5}</h3>
	
	<input type="text" placeholder="username">
	<input type="password" placeholder="password">
	<input type="text" placeholder="name">
	<input type="email" placeholder="email">
	
	<button onclick = "handleSignupSubmit();">회원가입</button>
	
	<img src="/product/hellotest/java.png">
	
	<script src="/product/static/js/signup.js"></script> <!-- 루트(Webapp)에서부터 찾아들어간다. 절대경로-->
</body>
</html>