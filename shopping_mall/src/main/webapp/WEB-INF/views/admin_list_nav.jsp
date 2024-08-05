<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
HttpSession main_ses = request.getSession();
String rd = (String)main_ses.getAttribute("rd");
%>
<form id="frm_nav">
<nav class="navcss">
    <div class="nav_div">
        <ol> <%--?rd=<%=rd%> --%>
            <li title="쇼핑몰 상품관리"><span onclick="go_list()">쇼핑몰 관리자 리스트</span></li>
            <li title="쇼핑몰 회원관리">쇼핑몰 회원관리</li>
            <li title="쇼핑몰 상품관리"><span onclick="go_product()">쇼핑몰 상품관리</span></li>
            <li title="쇼핑몰 기본설정"><span onclick="go_setting()">쇼핑몰 기본설정</span></li>
            <li title="쇼핑몰 공지사항">쇼핑몰 공지사항</li>
        </ol>
    </div>
</nav>
</form>
<script>
function go_product(){
	frm_nav.method = "post";
	frm_nav.action = "./product_list.do";
	frm_nav.submit();
}

function go_list(){
	frm_nav.method = "post";
	frm_nav.action = "./admin_list.do";
	frm_nav.submit();
}

function go_setting(){
	frm_nav.method = "post";
	frm_nav.action = "./admin_siteinfo.do";
	frm_nav.submit();
} 


</script>
