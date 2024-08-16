<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/product.css?v=6">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
<!-- 헤더 시작 -->
<%@ include file="./admin_list_header.jsp" %>
<!-- 헤더 끝 -->

<!-- 내비게이션 시작 -->
<%@ include file="./admin_list_nav.jsp" %>
<!-- 내비게이션 끝 -->

<!-- 메인 시작 -->
<%@ include file="./product_list_main.jsp" %>
<!-- 메인 끝 -->

<!-- 푸터 시작 -->
<%@ include file="./admin_list_footer.jsp" %>
<!-- 푸터 끝 -->
</body>
</html>