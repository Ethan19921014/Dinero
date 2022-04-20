<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="dao" class="model.ProductBase64Dao" scope="page" ></jsp:useBean>
	<%   String answer  = (String)dao.selectById(1);
	pageContext.setAttribute("answer",answer);%>
	<img src="${answer}" >
</body>
</html>