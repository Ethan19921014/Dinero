<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="model.CartItemDao , model.CartItemDtoBean , java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單明細</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
	<jsp:useBean id="cartItemDao" class="model.CartItemDao"></jsp:useBean>
	<%
	String cartId = request.getParameter("cartId");
	List<CartItemDtoBean> items = cartItemDao.selectByOrdId(cartId);
	%>

	<div class="container">
		<table class="table table-bordered">
			<thead>
				<tr>
					<td colspan="3">訂單編號</td>
					<td colspan="1"><%=cartId%></td>
				</tr>

			</thead>
			<tbody>
				<tr>
					<td>產品編號</td>
					<td>價格</td>
					<td>數量</td>
					<td>總額</td>
				</tr>
				<%
				for (CartItemDtoBean item : items) {
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
			</tbody>

		</table>
	</div>

</body>
</html>