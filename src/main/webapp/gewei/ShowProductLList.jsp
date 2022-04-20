<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.ProductDao,model.ProductDto,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShowProduct</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" href="productStyle.css" />
<style>
        .suwaCenter{
            width: 60%;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	
	<jsp:useBean id="dao" class="model.ProductDao" scope="session"></jsp:useBean>
	<%
	List<ProductDto> products = dao.selectAllProductFromDB();
	request.setAttribute("products", products);
	%>

	<div class="container">
		<div class="col-xs-12 col-md-6 suwaCenter">

			<c:forEach var="product" items="${products}">
		
				<!-- First product box start here-->
				<div class="prod-info-main prod-wrap clearfix">

					<div class="row">

						<div class="col-md-5 col-sm-12 col-xs-12">

							<div class="product-image">

								<img src="/image/${product.prodName}.jpg" class="img-responsive">

								<span class="tag2 hot"> 新品 </span>

							</div>

						</div>

						<div class="col-md-7 col-sm-12 col-xs-12">

							<div class="product-deatil">

								<h5 class="name">

									<a href="#"> ${product.prodName} </a> <a href="#"> <span>${product.category}</span>

									</a>

								</h5>

								<p class="price-container">

									<span>${product.price}</span>

								</p>

								<span class="tag1"></span>

							</div>

							<div class="description">

								<p>${product.descript}</p>

							</div>

							<div class="product-info smart-form">

								<div class="row">

									<div class="col-md-12">
										<form action="http://localhost:8080/dinero/gewei/ShowProduct.jsp" method="get">
										<input type="hidden" name="prodId" value="${product.prodId}"/>
										<input type="submit" value="Add to cart" class="btn btn-danger"/>
										</form>
										 

									</div>

									<div class="col-md-12">

										<div class="rating">
											Rating: <label for="stars-rating-5"><i
												class="fa fa-star text-danger"></i></label> <label
												for="stars-rating-4"><i
												class="fa fa-star text-danger"></i></label> <label
												for="stars-rating-3"><i
												class="fa fa-star text-danger"></i></label> <label
												for="stars-rating-2"><i
												class="fa fa-star text-warning"></i></label> <label
												for="stars-rating-1"><i
												class="fa fa-star text-warning"></i></label>

										</div>

									</div>

								</div>

							</div>

						</div>

					</div>
				</div>

			</c:forEach>

		</div>


	</div>
</body>
</html>