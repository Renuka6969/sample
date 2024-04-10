<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="db.MathFunctions" %>
<%
int result = 5;

MathFunctions mf = new MathFunctions();
result = mf.getSquareOfNumber(result);
%>
<html>
<body>
<h1>Math Functions <%= result %></h1>
</body>
</html>

