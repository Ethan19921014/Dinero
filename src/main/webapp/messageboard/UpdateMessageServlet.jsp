<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="../dineroNavBar.jsp"></jsp:include>
<form action="../messageUpdateServlet" method="get">
	<label>文章編號</label>
	<input type="number" id="messageId" name="messageId" value="${param.messageId}" readonly><br>
	<label>修改標題</label>
	<input type="text" id="messagetitle" name="messagetitle" value="${param.messagetitle}"><br>
	<label>修改文章</label>
	<textarea rows="10" id="message" name="message">${param.message}</textarea><br>
	
	<input type="submit" value="修改">
	
	
	

</form>



</body>
</html>