<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
    <form action="PlaceAnOrderServlet" method="get">
        <div>
            <label>客戶id
                <input type="text" name="custId">
            </label>
        </div>
        <div>
            <label for="">商品id
                <input type="text" name="prodId">
            </label>
        </div>
        <div>
            <label>價格:
                <input type="text" name="price">
            </label>
        </div>
        <div>
            <label>數量:
                <input type="text" name="qty">
            </label>
        </div>
        <input type="hidden" name="toDo" value="insert">
        <input type="submit" value="submit">
    </form>
</body>
</html>