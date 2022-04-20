<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>

<title>Insert title here</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container">

		<h2>註冊表單</h2>
		<div class="col-md-6 col-md-offset-3">
			<div class="alert alert-success center" role="alert">
				<p>${NOTIFICATION}</p>
			</div>

			<!-- 		<form action="register" method="GET" name="userForm" onSubmit="return validateForm()"> -->
			<form action="<%=request.getContextPath()%>/registertest" method="post">



				<div class="form-group">
					<label for="uname">姓名:</label> <input type="text"
						class="form-control" id="name" placeholder="姓名" name="name"
						required>
				</div>

				<div class="form-group">
					<label for="uname">信箱:</label> <input type="text"
						class="form-control" id="email" placeholder="信箱" name="email"
						required>
				</div>

				<div class="form-group">
					<label for="uname">帳號:</label> <input type="text"
						class="form-control" id="username" placeholder="帳號"
						name="username" required>
				<span id="available" class="form-group"></span>
				</div>
				
					<div class="form-group">
						<label for="uname">密碼:</label> <input type="password"
							class="form-control" id="password" placeholder="密碼"
							name="password" required>
					</div>

		<button type="submit" value="Save" class="btn btn-primary">提交</button>
		</form>
	</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>

		<script type="text/javascript">
			$(document).ready(function() {

				$("#username").blur(function() {

					var username = $("#username").val();

					if (username.length >= 3) {
						$.ajax({
							url : "check.jsp",
							type : "post",
							data : "uname=" + username,
							dataType : "text",
							success : function(data) {
								$("#available").html(data)
							}
						});
					} else {
						$("#available").html(" ");
					}

				});

			});
		</script>


</body>
</html>