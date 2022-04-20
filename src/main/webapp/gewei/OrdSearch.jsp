<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<style>
.center {
	margin-left: auto;
	margin-right: auto;
	/* border: 2px black solid; */
}

.controll {
	width: 50%;
	margin-left: auto;
	margin-right: auto;
	/* border: 2px black solid; */
	position: relative;
}

hr {
	margin-top: 1rem;
	margin-bottom: 1rem;
	border: 0;
	border-top: 1px solid rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
		<form action="OrdSelectByServlet" method="post"
			class="form-horizontal center">
			<div class="form-group controll">
				<label for=""> 請選擇搜尋方式</label><br> <select name="selectBy"
					id="">
					<option value="cartId">訂單編號</option>
					<option value="custId">顧客編號</option>
					<option value="cartTotal">訂單總額</option>
					<option value="tradeDate">交易日期</option>
					<option value="advanced">進階搜尋</option>
				</select> 
			</div>
			<hr>
			<div class="form-group controll">
				<label for="">訂單編號<br>
				<input type="text" name="cartId" class="form-control" />
				</label>
			</div>
			<hr>
			
			<div class="form-group controll">
				<label for="">顧客編號<br>
				<input type="text" name="custId" class="form-control" />
				</label>
			</div>
			<hr>
			
			<div class="form-group controll">
				<label for="">日期</label><br> <label for=""> 最早日期 <input
					type="text" name="minDate" class="form-control" />
				</label><br> <label for=""> 最大日期 <input type="text"
					name="maxDate" class="form-control" />
				</label>
			</div>
			<hr>
			<div class="form-group controll">

				<div>金額</div>
				<div>
					最小金額 <input type="text" name="minTotal" class="form-control" />
				</div>
				<div>
					最大金額 <input type="text" name="maxTotal" class="form-control" />
				</div>
			</div>
			<hr>
			<div class="form-group controll">
				<input type="submit" value="送出查詢">

			</div>

		</form>
	</div>
</body>
</html>