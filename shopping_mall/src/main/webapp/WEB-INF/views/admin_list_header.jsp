<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpSession main_ses = request.getSession();
	String id = (String)main_ses.getAttribute("aid");
	String name = (String)main_ses.getAttribute("aname");
	//out.print(id);
	//out.print(name);
%>
<form id="frm_logout">
<header class="headercss">
    <div class="header_div">
        <p><img src="./img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
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