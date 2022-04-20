<%@page import="bean.ActivityBean"%>
<%@page import="dao.ActivityDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改活動</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<jsp:include page="../dineroNavBar.jsp"></jsp:include>

<%
    ActivityDao activityDao = new ActivityDao();
    ActivityBean activityBean = activityDao.findActivity(request.getParameter("actId")); 

	System.out.print(request.getParameter("actId"));
	System.out.print(activityBean.getActName());
	
    String actId = activityBean.getActId();
    String actName = activityBean.getActName();
    String actContent = activityBean.getActContent();
    String endDate = activityBean.getEndDate();
    String memberRemain = activityBean.getMemberRemain();
    String memberLimit = activityBean.getMemberRemain();
%>
<form action="/dinero/UpdateServlet" method="post">

    <input type="text" name="actId" value="<%=actId%>">
    <input type="text" name="actName" value="<%=actName%>"> 
    <input type="text" name="actContent" value="<%=actContent%>"> 
    <input type="text" name="endDate" value="<%=endDate%>"> 
    <input type="text" name="memberRemain" value="<%=memberRemain%>"> 
    <input type="text" name="memberLimit" value="<%=memberLimit%>"> 
    
<%--     <input type="text" name="actId" value="${aBean.actId}"> --%>
<%--     <input type="text" name="actName" value="${aBean.actName}"> --%>
    
    <input type="submit" value="修改">
  </form>
</body>
</html>
