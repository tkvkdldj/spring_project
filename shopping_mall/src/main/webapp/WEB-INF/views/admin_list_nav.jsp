<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%//아....ㅅ비ㅏㄹ...
HttpSession main_ses = request.getSession();
String rd = (String)main_ses.getAttribute("rd");
%>
<nav class="navcss">
    <div class="nav_div">
        <ol>
            <li title="쇼핑몰 상품관리"><a href="./admin_main.do?rd=<%=rd%>">쇼핑몰 관리자 리스트</a></li>
            <li title="쇼핑몰 회원관리">쇼핑몰 회원관리</li>
            <li title="쇼핑몰 상품관리">쇼핑몰 상품관리</li>
            <li title="쇼핑몰 기본설정"><a href="./admin_siteinfo.do">쇼핑몰 기본설정</a></li>
            <li title="쇼핑몰 공지사항">쇼핑몰 공지사항</li>
        </ol>
    </div>
</nav>
