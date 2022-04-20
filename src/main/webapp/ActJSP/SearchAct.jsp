<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bean.ActivityBean"%>
<%@page import="dao.ActivityDao"%>
<%@ page import="java.util.List" %>   

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顯示所有活動</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<jsp:include page="../dineroNavBar.jsp"></jsp:include>
 
<!-- Servlet set, JSP get -->
<h2>顯示所有活動</h2>

<form action="" method="post"> 
<input type="text" name="actName" size="5" maxlength="5">
<input type="submit" name="submit" value="送出">
</form>
<% ActivityDao activityDao = new ActivityDao(); %>


<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<%List<ActivityBean> list = activityDao.searchByActName(request.getParameter("actName"));
System.out.print(request.getParameter("actName"));
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
 <td><a href="Update.jsp?actId=<%=list.get(i).getActId()%>">修改</a>
 <a href="DeleteServlet?actId=<%=list.get(i).getActId()%>">刪除</a>
 </td>
 </tr>
 <% } %>
 </table>
<button><a href="<%=request.getContextPath() %>/ActJSP/ShowActivity.jsp">首頁</a></button>

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