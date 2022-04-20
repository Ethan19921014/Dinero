<%@page import="tw.dinero.model.Product,model.ProductBase64Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</head>
<body>

<jsp:include page="../dineroNavBar.jsp"></jsp:include>

<form style="display:inline;" action="ReadProduct" method="post">
<input type="submit" name="read" value="目錄">
</form>

<form style="display:inline;" action="Productjsp/CreateProduct.jsp" method="post">
<input type="submit" name="create" value="新增">
<!-- <br>Id:<input type="text" name="prodId"> -->
<!-- <br>產品名稱:<input type="text" name="prodName"> -->
<!-- <br>產品價格:<input type="text" name="price"> -->
<!-- <br>產品描述:<input type="text" name="descript"> -->
<!-- <br>產品庫存:<input type="text" name="stock"> -->
<!-- <br>產品種類:飼料<input type="radio" name="category" value="飼料">鮮食<input type="radio" name="category" value="鮮食"> -->
<!-- <br>適合寵物類型:狗<input type="radio" name="petkind" value="狗">貓<input type="radio" name="petkind" value="貓"> -->
</form>


<form style="display:inline;" action="Productjsp/UpdateProduct.jsp" method="post">
<input type="submit" name="update" value="修改">
<!-- <br>欲修改的Id:<input type="text" name="readId"> -->
<!-- <br>修改成 -->
<!-- <!-- <br>Id:<input type="text" name="prodId"> --> 
<!-- <br>產品名稱:<input type="text" name="prodName"> -->
<!-- <br>產品價格:<input type="text" name="price"> -->
<!-- <br>產品描述:<input type="text" name="descript"> -->
<!-- <br>產品庫存:<input type="text" name="stock"> -->
<!-- <br>產品種類:飼料<input type="radio" name="category" value="飼料">鮮食<input type="radio" name="category" value="鮮食"> -->
<!-- <br>適合寵物類型:狗<input type="radio" name="petkind" value="狗">貓<input type="radio" name="petkind" value="貓"> -->
</form>

<form style="display:inline;" action="ReadProductById" method="get">
<br><br>輸入Id:<input type="text" name="prodId">
<input type="submit" name="read" value="查詢">
</form>

<form style="display:inline" action="ReadProductByName" method="post">
輸入關鍵字:<input type="text" name="prodName">
<input type="submit" name="read" value="查詢">
</form>

<!-- <form style="display:inline;" action="DeleteProduct" method="get"> -->
<!-- 輸入Id:<input type="text" name="prodId"> -->
<!-- <input type="submit" name="delete" value="刪除"> -->
<!-- </form> -->
<br>


<table class="table table-hover">
    <thead>
		<tr>
			<th>ID:</th>
			<th>庫存:</th>
			<th>價格:</th>
			<th>名稱:</th>
			<th>描述:</th>
			<th>種類:</th>
			<th>適合寵物類型:</th>
			<th>圖片:</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
<%
List<Product> data = (List<Product>)request.getAttribute("data");

//ProductBase64Dao p64dao = new ProductBase64Dao();
//<%=p64dao.selectById(p.getProdId())

for(Product p : data) {%>
		<tr>
			<td><%=p.getProdId() %></td>
			<td><%=p.getStock() %></td>
			<td><%=p.getPrice() %></td>
			<td><%=p.getProdName() %></td>
			<td><%=p.getDescript() %></td>
			<td><%=p.getCategory() %></td>
			<td><%=p.getPetkind() %></td>
			<td>
<!-- 			<form action="ReadProdImg" method="post" enctype="multipart/form-data"> -->
<%-- 			  <input type="hidden" name="prodId" value="<%=p.getProdId() %>"> --%>
<!-- 			</form> -->
				
               <img src="/Img/<%=p.getProdName() %>.jpg" style="width:80px">
			</td>
			<td>
			<form style="display:inline;" action="DeleteProduct" method="get">
    		  <input type="hidden" name="prodId" value="<%=p.getProdId() %>">
    		  <input type="submit" name="delete" value="刪除">
    		</form>
			</td>		
		</tr>
<% }%>
	</tbody>
</table>

</body>
</html>