<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ProductDao,model.ProductDto,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>

	<%ProductDao dao = (ProductDao) session.getAttribute("dao"); 
	 int prodId =Integer.parseInt(request.getParameter("prodId"));
	 ProductDto product = dao.selectProdById(prodId);
	 pageContext.setAttribute("product",product);
	 //System.out.print("test");%>
	 
	 <figure>  <img src="/image/${product.prodName}.jpg">  </figure>
	 
	 <form action="PlaceAnOrderServlet" method="post">
	 <input type="text" name="qty"/> <span>目前存貨:${product.stock}</span>
	 <input type="hidden" name="custId" value="123"/>
	 <input type="hidden" name="price" value="${product.price}" />
	 <input type="hidden" name="prodId" value="${product.prodId}" />
	 <input type="hidden" name="toDo" value="insert"/>
	 <input type="submit" value="加入購物車" />
	 </form>
	 
	 
	 
</body>
</html>