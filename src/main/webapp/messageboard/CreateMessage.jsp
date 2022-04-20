<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="../dineroNavBar.jsp"></jsp:include>
	<form action="../messageCreateServlet" method="get">
		<label>標題</label> <input type="text" id="messagetitle"
			name="messagetitle"><br> <label></label> <label>留言區</label>
		<textarea rows="10" id="message" name="message"></textarea>
		<br> <input type="submit" value="送出">
	</form>
</body>
</html>