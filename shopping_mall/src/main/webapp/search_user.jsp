<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정보 찾기</title>
</head>
<body>
<form id="frm">
<p>아이디 찾기</p>
고객명 : <input type="text" name="uname"><br>
가입이메일 : <input type="text" name="uemail"><br>
<input type="button" value="아이디 찾기" onclick="btn1()">
<p>패스워드 찾기</p>
아이디 : <input type="text" name="uid"><br>
고객명 : <input type="text" name="uname"><br> <!-- form을 같이쓰면 name중복되어서 주의 -->
<input type="button" value="패스워드 변경" onclick="btn2()">
</form>
</body>
<script>
function btn1(){
	frm.method="post";
	frm.action="./idsearch.do";
	frm.submit();
}

//하나의 form에 여러개 action을 달 수 있음
function btn2(){
	frm.method="post";
	frm.action="./passmodify.do";
	frm.submit();
}


</script>
</html>