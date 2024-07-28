<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//인자에 디폴트가 true, false로 하면 없으면 null로 리턴(옛날 코드)
	HttpSession hs = request.getSession();
	//out.print(hs);
	String mid = (String)hs.getAttribute("mid");
	out.print(mid);
	hs.invalidate(); //session 전체 삭제
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>