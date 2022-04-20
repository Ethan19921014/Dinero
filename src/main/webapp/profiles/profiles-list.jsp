<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<meta charset="UTF-8">
<title>User Management Application</title>
<script src ="js/script.js" type="text/javascript"></script>


<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

</head>
<body>
<jsp:include page="../dineroNavBar.jsp"></jsp:include>

	<header>
	
			
			

<!-- 			<ul class="navbar-nav"> -->
<%-- 				<li><a href="<%=request.getContextPath()%>/home" --%>
<!-- 					class="nav-link">User</a></li> -->
<!-- 			</ul> -->

<%-- 				<li><a href="<%=request.getContextPath()%>/logout" --%>
<!-- 					class="nav-link">Logout</a></li> -->
<!-- 			</ul> -->
	</header>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of User</h3>
			<hr>
			<div class="container text-left">

			<c:url var = "newLink" value = "/UserControllerServlet">
			<c:param name = "action"  value ="new" />
			</c:url>

				<a href="${newLink}"
					class="btn btn-success">新增會員</a>
					
					
			<c:url var = "listLink" value = "/UserControllerServlet">
			<c:param name = "action"  value ="list" />
			</c:url>
			
				<a href="${listLink}"
					class="btn btn-success">查詢所有會員</a>
					
			
<%-- 			<c:forEach var="listUser" items="${user.rows}">  --%>
<!--   			 <tr> -->
<%--    				 <td><c:out value="${listUser.userId}" /></td>  --%>
<%--     		<td><c:out value="${listUser.name}" /></td> --%>
<!-- 			<!--same for other fields as well--> -->
<!--    			</tr>       -->
<%-- 			</c:forEach>   --%>
					
		<input type="search"  class="light-table-filter" data-table="table table-bordered" placeholder="請輸入關鍵字">
					

			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>		
						<th>姓名</th>
						<th>信箱</th>
						<th>帳號</th>
						<th>密碼</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo = todo: todos) {  -->
					<c:forEach var="user" items="${requestScope.listUser}">
					
					<c:url var = "loadLink" value = "UserControllerServlet">
					<c:param name = "action"  value ="load" />
					<c:param name = "userID" value = "${user.userID}"/>
					</c:url>
					
					
					<c:url var = "deleteLink" value = "UserControllerServlet">
					<c:param name = "action"  value ="delete" />
					<c:param name = "userID" value = "${user.userID}"/>
					</c:url>

						<tr>
							<td><c:out value="${user.userID}" /></td>
							<td><c:out value="${user.name}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.username}" /></td>
							<td><c:out value="${user.password}" /></td>
							
							<td><a href="${loadLink}">編輯</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="${deleteLink}">刪除</a></td>

<!-- 							<td><button (click)="updateTodo(todo.id)" class="btn btn-success">Update</button> -->
<!--           							<button (click)="deleteTodo(todo.id)" class="btn btn-warning">Delete</button></td> -->
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
