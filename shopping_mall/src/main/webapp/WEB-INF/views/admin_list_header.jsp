<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="frm_logout">
<header class="headercss">
    <div class="header_div">
        <p><a href="./index.jsp"><img src="./img/logo.png" class="logo_sm">ADMINISTRATOR</a></p>
        <p>홍길동 관리자 <a href="#">[개인정보 수정]</a> <a href="#" onclick="go_logout()">[로그아웃]</a></p>
    </div>
</header>
</form>

<script>
function go_logout(){
	if(confirm("로그아웃 하시겠습니까?")){
		frm_logout.method="post";
		frm_logout.action="./admin_logout.do";
		frm_logout.submit();	
	}
}

</script>