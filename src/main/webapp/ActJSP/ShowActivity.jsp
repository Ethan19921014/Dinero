<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bean.ActivityBean"%>
<%@page import="dao.ActivityDao"%>
<%@ page import="java.util.List" %>   
<%
response.setContentType("text/html;charset=UTF-8");
response.setCharacterEncoding("UTF-8");
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顯示所有活動</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<jsp:include page="../dineroNavBar.jsp"></jsp:include>

<%-- <jsp:useBean id="reg_activity" class="bean.ActivityBean" scope="session" />  --%>
 
<!-- Servlet set, JSP get -->
<h2>顯示所有活動</h2>
<a href="/dinero/ActJSP/Create.jsp">新增</a>
<form action="SearchAct.jsp" method="get"> 
<input type="text" name="actName" size="5" maxlength="5">
<input type="submit" name="submit" value="搜尋">
</form>
<% ActivityDao activityDao = new ActivityDao(); %>

<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<%List<ActivityBean> list = activityDao.readAllActivity();
// ActivityBean test = list.get(0);
%> 
<!-- <table border="3"> -->
 <tr>
 <th>活動編號:</th>
 <th>活動名稱:</th>
 <th>活動內容：</th>
 <th>活動報名截止日期：</th>
 <th>剩餘名額：</th>
 <th>人數上限：</th>
 <th></th>
 </tr>
 <tr>
 <% for(int i=0; i<list.size(); i++){%>
 <td><%=list.get(i).getActId() %></td>
 <td><%=list.get(i).getActName()%></td> 
 <td><%=list.get(i).getActContent()%></td>
 <td><%=list.get(i).getEndDate()%></td>	
 <td><%=list.get(i).getMemberRemain()%></td>
 <td><%=list.get(i).getMemberLimit()%></td>

 <% list.get(i).getActId();  %>
 <td><a href="/dinero/ActJSP/Update.jsp?actId=<%=list.get(i).getActId()%>">修改</a>
 
 <a href="/dinero/DeleteServlet?actId=<%=list.get(i).getActId()%>">刪除</a>
 </td>
 </tr>
 <% } %>
 </table>

<!-- <tr bgcolor="#FFFFE1"> -->
<!--     <th>活動編號:</th> -->
<%--     <td><jsp:getProperty name="reg_activity" property="actId" /> --%>
<%-- 	<% for(ActivityBean a :list){%> --%>
<%-- 	<%=a.getActId()%> --%>
<%-- 	<% } %>     --%>
<!--     </td> -->
    
<!--     <th>活動名稱:</th> -->
<%--     <td> <jsp:getProperty name="reg_activity" property="actName" />  --%>
<%-- 	<% for(ActivityBean a :list){%> --%>
<%-- 	<%=a.getActName()%> --%>
<%-- 	<% } %> --%>
<!--     </td> -->
<!-- </tr> -->

<center>

</center>
</form>
</body>
</html>