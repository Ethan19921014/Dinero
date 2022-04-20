<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="db" class="tw.dinero.model.MessageDao" />
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<title>留言板</title>
</head>
<body>
	<jsp:include page="../dineroNavBar.jsp"></jsp:include>
	<div class="container" style="background-color:#F3F3FA ; border:2px solid black">
	<h1>留言管理系統</h1>
	
	<form action="messageReadServlet" method="get" style="display:inline">
		<label>文章編號</label> <input type="number" id="messageId"
			name="messageId"><input type="submit"	
			value="查詢" class="btn btn-primary btn-sm">
	</form>
	<a href="/dinero/messageIndexServlet" class="btn btn-secondary btn-sm">show all</a>
	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
		<a href="/dinero/messageboard/CreateMessage.jsp" "class="btn btn-danger btn-sm">
			<button class="btn btn-danger btn-sm">新增</button>
		</a>
		</div>
	<br>
	<br>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>No.</th>
					<th>標題</th>
					<th>文章</th>
					<th>時間</th>
					<th>　　　　　　</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="message" items="${messages}">
					<tr>
						<td>${message.messageId}</td>
						<td>${message.messagetitle}</td>
						<td>${message.message}</td>
						<td>${message.messagetime}</td>
						<td>
						<a href="/dinero/messageboard/UpdateMessageServlet.jsp?messageId=${message.messageId}&messagetitle=${message.messagetitle}&message=${message.message}&messagetime=${message.messagetime}" class="btn btn-secondary btn-sm">修改</a>
						<a href="/dinero/messageDeleteServlet?messageId=${message.messageId}" class="btn btn-danger btn-sm">刪除</a>						
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>
	<!-- <a href="ReadMessage.jsp?messageId=${message.messageId}&messagetitle=${message.messagetitle}&message=${message.message}&messagetime=${message.messagetime}"class="btn btn-secondary btn-sm">查詢</a> -->
	
</body>
</html>