<%@page import="model.ProductBase64Dao"%>
<%@page import="model.CartDto"%>
<%@page import="model.CartItemDtoBean,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>outcomePage</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<style>
.picBoundary {
	width: 80%; <!--
	border-style: dashed; --> <!--
	border-width: 0.2em; -->
	height: 80%;
}

img {
	max-width: 100%;
	max-height: 100%;
}

tr {
	vertical-align: middle;
	text-align: center;
}
.widthControll{
	width:20%;
}
</style>

</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>

	<jsp:useBean id="cartDto" class="model.CartDto" scope="session"></jsp:useBean>
	<div class="container">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th></th>
					<th>商品id</th>
					<th class="widthControll">圖片</th>
					<th>價格</th>
					<th >數量</th>
					<th>金額</th>
					<th>修改</th>
					<th>刪除</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<CartItemDtoBean> items = cartDto.getItems();

				int tmpCartTotal = 0;
				int tmpOneItemTotal;

				int tmpItemId = 0;
				//使用base 64顯示圖片
				ProductBase64Dao p64dao = new ProductBase64Dao();

				for (CartItemDtoBean item : items) {
					tmpItemId++;
				%>
				<tr>
					<td><input type="checkbox"><%=tmpItemId%></td>
					<td><%=item.getProdId()%></td>
					<td class="widthControll"><div class="picBoundary">
							<img src="<%=p64dao.selectById(item.getProdId())%>" />
						</div></td>
					<td><%=item.getPrice()%></td>
					<td >
						<form action="PlaceAnOrderServlet" method="post">
							<input type="hidden" name="toDo" value="edit"> <input
								type="hidden" name="prodId" value="<%=item.getProdId()%>">
							<input type="hidden" name="index" value="<%=tmpItemId - 1%>" /> <input
								type="text" name="newQty" value="<%=item.getQty()%>" size="6"/>
						</form>
					</td>
					<td><%=item.getItemTotal()%></td>
					<%
					tmpOneItemTotal = item.getItemTotal();
					tmpCartTotal += tmpOneItemTotal;
					%>

					<!-- 編輯商品數量 -->
					<td><a href="http://localhost:8080/dinero/gewei/EditItem.jsp?index=<%=tmpItemId - 1%>"
						class="btn btn-primary">編輯</a></td>
					<td>
						<form action="PlaceAnOrderServlet" method="post">
							<input type="hidden" name="index" value="<%=tmpItemId - 1%>">
							<input type="hidden" name="toDo" value="delete" /> <input
								type="submit" value="刪除" class="btn btn-danger" />
						</form>
					</td>
				</tr>
				<%
				}
				%>
				<jsp:setProperty property="cartTotal" name="cartDto"
					value="<%=tmpCartTotal%>" />

				<tr>
					<td colspan="5">總額</td>
					<td id="sum"><jsp:getProperty property="cartTotal"
							name="cartDto" /></td>
					<td>
						<button type="button" class="btn btn-success"
							data-bs-toggle="modal" data-bs-target="#exampleModal"
							data-bs-whatever="@mdo">結帳</button>
					</td>
					<td>
						<form action="PlaceAnOrderServlet" method="post">

							<input type="hidden" name="toDo" value="clearCart" /> <input
								type="submit" value="清空" class="btn btn-danger" />
						</form>
					</td>
				</tr>
			</tbody>
		</table>

		<%-- 結帳彈跳 --%>
		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">確認付款</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
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
									<td id="sum"><jsp:getProperty property="cartTotal"
											name="cartDto" /></td>


								</tr>
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">回前頁</button>

						<form action="PlaceAnOrderServlet" method="post">

							<input type="hidden" name="toDo" value="toGreenService" /> <input
								type="submit" value="結帳" class="btn btn-success" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		let sumTdRow = document.getElementById("sum");
		let sum = sumTdRow.innerHTML;
	</script>
	<%
	//System.out.println("from outcome page");
	//System.out.println(cartDto);
	%>
</body>
</html>