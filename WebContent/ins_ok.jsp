<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="bean" class="pack.business.DataFormBean" />
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="processDao" class="pack.business.ProcessDao" />

<%
	boolean isSuccess = processDao.insertData(bean);

	if(isSuccess) response.sendRedirect("list.jsp");
	else response.sendRedirect("fail.jsp");
%>
	
