<%@page import="model.CartDto"%>
<%@page import="model.CartItemDtoBean,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/SearchError.jsp" %>
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
	<%   
	List<CartDto> ords = (List<CartDto>)session.getAttribute("SearchedOrds"); %>
	
	<div class="container">
		<table class="table table-bordered">
			<thead>
				<tr>
					
					<th>訂單編號</th>
					<td>顧客編號</td>
					<th>訂單總額</th>
					<th>訂單日期</th>
					<th>訂單明細</th>			
					
				</tr>
			</thead>
			<tbody>
				<%
				

				for (CartDto ord : ords) {
				
				%>
				<tr>
					
					<td><%=ord.getCartId()%></td>
					<td><%=ord.getCustId() %></td>
					<td><%=ord.getCartTotal()%></td>
					<td><%=ord.getTradeDate()%></td>
					<td><a href="http://localhost:8080/dinero/gewei/Uchiwake.jsp?cartId=<%=ord.getCartId()%>">明細</a></td>
					
				
				</tr>
				<%
				}
				%>
				
			</tbody>
		</table>
	</div>
</body>
</html>