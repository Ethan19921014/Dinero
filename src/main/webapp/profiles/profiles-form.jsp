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

		</nav>
	</header>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
<%-- 				<c:if test="${User != null}"> --%>
<!-- 					<form action="update" method="post"> -->
<%-- 				</c:if> --%>
				<c:if test="${User == null}">
				
				
			
		
				</c:if>

				<caption>
					<h2>
						<c:if test="${User != null}">
            			編輯會員
            		</c:if>
						<c:if test="${User == null}">
            			新增會員
            		</c:if>
					</h2>
				</caption>



					<c:if test="${User == null}">

		<form action="UserControllerServlet" method="GET" name="userForm" onSubmit="return validateForm()">
		<input type="hidden" name="action" value="insert" />
            		</c:if>


				<fieldset class="form-group">
					<label>姓名</label> <input type="text"
						 class="form-control"
						name="name" required="required" minlength="1">
				</fieldset>

				<fieldset class="form-group">
					<label>信箱</label> <input type="text"
						 class="form-control"
						name="email" minlength="1">
				</fieldset>

				<fieldset class="form-group">
					<label>帳號</label> <input type="text"
						 class="form-control"
						name="username" minlength="1">
						
						
						
					
				</fieldset>
					
					
<!-- 				<fieldset class="form-group"> -->
<!-- 					<label>生日</label> <input type="date" -->
<%-- 						value="<c:out value='${Profiles.birthday}' />" class="form-control" --%>
<!-- 						name="birthday" required="required"> -->
<!-- 				</fieldset> -->
				
					<fieldset class="form-group">
					<label>密碼</label> <input type="text"
						 class="form-control"
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
