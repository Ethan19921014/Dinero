<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="model.CartDto,model.CartItemDtoBean,java.util.List"%>
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
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<h1 class="text-center">訂單新增成功</h1>
	<jsp:useBean id="cartDto" class="model.CartDto" scope="application"></jsp:useBean>
	<div class="container">
		<table class="table table-bordered">
			<thead>
				<tr>

					<th>商品id</th>
					<th>價格</th>
					<th>數量</th>
					<th>金額</th>

				</tr>
			</thead>
			<tbody>
				<%
				List<CartItemDtoBean> items = cartDto.getItems();

				int tmpCartTotal = 0;
				int tmpOneItemTotal;

				int tmpItemId = 0;
				for (CartItemDtoBean item : items) {
					tmpItemId++;
				%>
				<tr>

					<td><%=item.getProdId()%></td>
					<td><%=item.getPrice()%></td>
					<td><%=item.getQty()%></td>
					<td><%=item.getItemTotal()%></td>

				</tr>
				<%
				}
				%>
				<tr>
					<td colspan="3">總額</td>
					<td id="sum"><jsp:getProperty property="cartTotal" name="cartDto" /></td>
				</tr>
			</tbody>
		</table>
	</div>

	<%
	application.removeAttribute("cartDto");
	session.removeAttribute("cartDto");
	%>

</body>
</html>