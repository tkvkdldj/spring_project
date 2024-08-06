<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 페이징 사용법(프론트)</title>
</head>
<body>
<p>쿠폰리스트 총 개수</p>
</body>
<%
Date today = new Date();
%>
<script src="./coupon_list.js?v=<%=today%>"></script>
</html>