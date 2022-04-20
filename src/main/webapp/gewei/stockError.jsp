<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" import="model.CartDto"%>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購買數量過多</title>
 <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>

	<jsp:useBean id="cartDto" class="model.CartDto" scope="session"></jsp:useBean>	
	<h1 class="display-1">BackEndTesting</h1>
	<footer class="blockquote-footer">請重新購買<cite title="Source Title"><%=exception %></cite></footer>
	<jsp:include page="ShowProductLList.jsp"></jsp:include>
</body>
</html>