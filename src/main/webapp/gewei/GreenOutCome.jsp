<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String merchantID= request.getParameter("MerchantID");
	String merchantTradeNo= request.getParameter("MerchantTradeNo");
	String storeID= request.getParameter("StoreID");
	String rtnCode= request.getParameter("RtnCode");
	String rtnMsg= request.getParameter("RtnMsg");
	String tradeNo= request.getParameter("TradeNo");
	String tradeAmt= request.getParameter("TradeAmt");
	String paymentDate= request.getParameter("PaymentDate");
	String paymentType= request.getParameter("PaymentType");
	String tradeDate= request.getParameter("TradeDate");
	String simulatePaid= request.getParameter("SimulatePaid ");
	%>
	
	<%= merchantID%>
	<%= merchantTradeNo%>
	<%= storeID%>
	<%= rtnCode%>
	<%= rtnMsg%>
	<%= tradeNo%>
	<%= tradeAmt%>
	<%= paymentDate%>
	<%= paymentType%>
	<%= tradeDate%>
	<%= simulatePaid%>
</body>
</html>