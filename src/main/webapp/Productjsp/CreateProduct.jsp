<%@page import="tw.dinero.model.Product"%>
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
<div class="container">
<form action="../CreateProduct" method="post" enctype="multipart/form-data">
	<div style="width:300px">
	  <label class="form-label">Id</label>
	  <input type="text" class="form-control" name="prodId">
	</div>
	<div style="width:300px">
	  <label class="form-label">產品名稱</label>
	  <input type="text" class="form-control" name="prodName">
	</div>
	<div style="width:300px">
	  <label class="form-label">產品價格</label>
	  <input type="text" class="form-control" name="price">
	</div>
	<div style="width:300px">
	  <label class="form-label">產品描述</label>
	  <input type="text" class="form-control" name="descript">
	</div>
	<div style="width:300px">
	  <label class="form-label">產品庫存</label>
	  <input type="text" class="form-control" name="stock">
	</div>
	<div style="width:300px">
	  <label class="form-label">產品種類</label>
	  <br>飼料<input type="radio" name="category" value="飼料">鮮食<input type="radio" name="category" value="鮮食">用具<input type="radio" name="category" value="用具">
	</div><br>
	<div style="width:300px">
	  <label class="form-label">適合寵物類型</label>
	  <br>狗<input type="radio" name="petkind" value="狗">貓<input type="radio" name="petkind" value="貓">
	</div>
	<div style="width:300px">
	    <br><label class="form-label">產品圖片</label>
	    <input type="file" name="photo">
	  </div>
	<br><input type="submit" class="btn btn-primary" name="create" value="新增">
</form>

<!-- 	<form action="../UploadProdImg" method="post" enctype="multipart/form-data"> -->
<!-- 	  <div style="width:300px"> -->
<!-- 	    <br><label class="form-label">產品圖片</label> -->
<!-- 	    <input type="file" name="photo"> -->
<!-- 	    <input type="submit" value="上傳" name="upload"> -->
<!-- 	  </div> -->
<!-- 	</form> -->
</div>

</body>
</html>