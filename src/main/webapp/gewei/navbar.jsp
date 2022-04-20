<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">


    
</head>
<body>
	  <nav class="navbar navbar-light navbar-expand-lg" style="background-color: coral;">
        <div class="container-fluid">
          <a class="navbar-brand" href="/dinero/dineroHome.jsp">dinero</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="/dinero/gewei/cartHome.jsp" style="color: midnightblue;">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/dinero/gewei/ShowProductLList.jsp" style="color: midnightblue;">購物</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/dinero/gewei/outcome.jsp" style="color: midnightblue;">我的訂單</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/dinero/gewei/ServerSideManager.jsp" style="color: midnightblue;">訂單管理</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/dinero/gewei/CreateProduct.jsp" style="color: midnightblue;">上架商品</a>
              </li>
              
              
            </ul>
            <form class="d-flex">
              <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
              <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
          </div>
        </div>
      </nav>
</body>
</html>