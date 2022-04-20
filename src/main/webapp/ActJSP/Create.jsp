<%@page import="bean.ActivityBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增活動</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活動</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<jsp:include page="../dineroNavBar.jsp"></jsp:include>

	<h2>活動管理</h2>
	<form action="../RegisterServlet" method="get">
		<table cellspacing="2" cellpadding="1" border="1" width="100%">
			<tr>
				<td>活動編號:</td>
				<td><input type="text" name="actId" size="2" maxlength="10"></td>
			</tr>
			<tr>
				<td>活動名稱:</td>
				<td><input type="text" name="actName" size="10" maxlength="10"></td>
			</tr>
			<tr>
				<td>活動內容:</td>
				<td><input type="text" name="actContent" maxlength="100"></td>
			</tr>
			<tr>
				<td>結束日期:</td>
				<td><input type="text" name="endDate" size="10" maxlength="10"> 
				</td>
			</tr>
			<tr>
				<td>剩餘名額:</td>
				<td><input type="text" name="memberRemain" size="5" maxlength="5"></td>
			</tr>
			<tr>
				<td>人數上限:</td>
				<td><input type="text" name="memberLimit" size="5" maxlength="5"></td>
			</tr>
		</table>
		<center>
			<input type="submit" name="submit" value="送出">
			<button><a href="/dinero/ActJSP/ShowActivity.jsp">首頁</a></button>
		</center>
	</form>
</body>
</html>