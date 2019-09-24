<%@page import="pack.business.DataDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="processDao" class="pack.business.ProcessDao" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
	<h2>* 회원 정보 *</h2>
	<a href="ins.jsp">회원 추가</a><br>
	<table border="1">
		<tr>
			<th>id</th><th>name</th><th>pwd</th><th>date</th>
		</tr>
<%
		ArrayList<DataDto> list = (ArrayList)processDao.selectDataAll();
%>
		<c:set var="list" value="<%=list %>" />
		<c:if test="${empty list }">
			<tr><td colspan="4">등록된 자료가 없습니다.</td></tr>
		</c:if>
		<c:forEach var="m" items="<%=list %>">
			<tr>
				<td>${m.id }</td>
				<td>${m.name }</td>
				<td>${m.passwd }</td>
				<td>${m.reg_date }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>