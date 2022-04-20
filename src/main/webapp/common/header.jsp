<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: coral">
		<div>
			<a href="<%= request.getContextPath() %>/dineroHome.jsp" class="navbar-brand"> dinero </a>
		</div>

		<ul class="navbar-nav navbar-collapse justify-content-end">
			<li><a href="<%= request.getContextPath() %>/login" class="nav-link">登入</a></li>
			<li><a href="<%= request.getContextPath() %>/registertest" class="nav-link">註冊</a></li>
		</ul>
	</nav>
</header>