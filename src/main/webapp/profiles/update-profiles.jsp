<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>User Management Application</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="" class="navbar-brand">Dinero</a>
			</div>

<!-- 			<ul class="navbar-nav"> -->
<%-- 				<li><a href="<%=request.getContextPath()%>/List" --%>
<!-- 					class="nav-link">User</a></li> -->
<!-- 			</ul> -->

<!-- 			<ul class="navbar-nav navbar-collapse justify-content-end"> -->
<%-- 				<li><a href="<%=request.getContextPath()%>/logout" --%>
<!-- 					class="nav-link">Logout</a></li> -->
<!-- 			</ul> -->
		</nav>
	</header>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
 					
				<caption>
					<h2>
            			編輯會員
					</h2>
				</caption>

				
		<form action="UserControllerServlet" method="GET" name="userForm" onSubmit="return validateForm()">
		<input type="hidden" name="action" value="update" />
		<input type="hidden" name="userID" value="${User.userID}" />
		

				<fieldset class="form-group">
					<label>姓名</label> <input type="text"
						 class="form-control" value="${User.name}"
						name="name" required="required" minlength="1">
				</fieldset>

				<fieldset class="form-group">
					<label>信箱</label> <input type="text"
						 class="form-control" value="${User.email}"
						name="email" minlength="1">
				</fieldset>

				<fieldset class="form-group">
					<label>帳號</label> <input type="text"
						 class="form-control" value="${User.username}"
						name="username" minlength="1">
						
						
						
					
				</fieldset>
					
					
<!-- 				<fieldset class="form-group"> -->
<!-- 					<label>生日</label> <input type="date" -->
<%-- 						value="<c:out value='${Profiles.birthday}' />" class="form-control" --%>
<!-- 						name="birthday" required="required"> -->
<!-- 				</fieldset> -->
				
					<fieldset class="form-group">
					<label>密碼</label> <input type="text"
						 class="form-control" value="${User.password}"
						name="password" minlength="1">
						
				</fieldset>

				<button type="submit" value="Save" class="btn btn-success">新增</button>
				</form>
				
				
				
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
