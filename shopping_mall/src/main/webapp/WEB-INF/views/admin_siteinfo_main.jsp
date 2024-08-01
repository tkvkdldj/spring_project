<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main class="maincss">
<section>
<form id="frm_setting"> <!--css때문에 강제로 form을 3개 때려야...? -->
<p>홈페이지 가입환경 설정</p>
<div class="subpage_view">
<ul class="info_form">
    <li>홈페이지 제목</li>
    <li>
        <input type="text" value="" class="in_form1" name="jhometitle"> 
    </li>
</ul>    
<ul class="info_form">
    <li>관리자 메일 주소</li>
    <li>
        <input type="text" class="in_form2" name="jemail"> ※ 관리자가 보내고 받는 용도로 사용하는 메일 주소를 입력합니다.(회원가입,인증메일,회원메일발송 등에서 사용)
    </li>
</ul> 
<ul class="info_form">
    <li>포인트 사용 유/무</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" value="Y" name="ispoint">포인트 사용</label></em> 
        <em><label><input type="radio" class="ckclass" value="N" name="ispoint" checked>포인트 미사용</label></em>
    </li>
</ul>
<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
    <li>회원가입시 적립금</li>
    <li>
        <input type="text" class="in_form3" maxlength="5" name="jpoint"> 점
    </li>
    <li>회원가입시 권한레벨</li>
    <li>
        <input type="text" class="in_form3" maxlength="1" name="jlevel"> 레벨
    </li>
</ul> 
</div>


<p>홈페이지 기본환경 설정</p>
<div class="subpage_view">
<ul class="info_form2">
    <li>회사명</li>
    <li>
        <input type="text" class="in_form0" name="hcomp_name"> 
    </li>
    <li>사업자등록번호</li>
    <li>
        <input type="text" class="in_form0" name="hcomp_num"> 
    </li>
</ul> 
<ul class="info_form2">
    <li>대표자명</li>
    <li>
        <input type="text" class="in_form0" name="hceo_name"> 
    </li>
    <li>대표전화번호</li>
    <li>
        <input type="text" class="in_form0" name="hceo_num"> 
    </li>
</ul>
<ul class="info_form2">
    <li>통신판매업 신고번호</li>
    <li>
        <input type="text" class="in_form0" name="htelemk_num"> 
    </li>
    <li>부가통신 사업자번호</li>
    <li>
        <input type="text" class="in_form0" name="hadd_num"> 
    </li>
</ul>
<ul class="info_form2">
    <li>사업장 우편번호</li>
    <li>
        <input type="text" class="in_form0" name="hbusi_post"> <!-- ?? -->
    </li>
    <li>사업장 주소</li>
    <li>
        <input type="text" class="in_form2" name="hbusi_addr"> 
    </li>
</ul>
<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
    <li>정보관리책임자명</li>
    <li>
        <input type="text" class="in_form0" name="hadmin_name"> 
    </li>
    <li>정보책임자 E-mail</li>
    <li>
        <input type="text" class="in_form1" name="hadmin_email"> 
    </li>
</ul>
</div>


<p>결제 기본환경 설정</p>
<div class="subpage_view">  
<ul class="info_form2">
    <li>무통장 은행</li>
    <li>
        <input type="text" class="in_form0" name="pbank"> 
    </li>
    <li>은행계좌번호</li>
    <li>
        <input type="text" class="in_form1" name="pbank_num"> 
    </li>
</ul>
<ul class="info_form">
    <li>신용카드 결제 사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" value="Y" name="pcard"> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" value="N" name="pcard" checked> 미사용</label></em> ※ 해당 PG사가 있을 경우 사용으로 변경합니다.
    </li>
</ul>
<ul class="info_form">
    <li>휴대폰 결제 사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" value="Y" name="pcelphone"> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" value="N" name="pcelphone" checked> 미사용</label></em> ※ 주문시 휴대폰 결제를 가능하게 할 것인지를 설정합니다.
    </li>
</ul>
<ul class="info_form">
    <li>도서상품권 결제사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" value="Y" name="pbookgift"> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" value="N" name="pbookgift" checked> 미사용</label></em> ※ 도서상품권 결제만 적용이 되며, 그 외에 상품권은 결제 되지 않습니다.
    </li>
</ul>
<ul class="info_form2">
    <li>결제 최소 포인트</li>
    <li>
        <input type="text" class="in_form0" maxlength="5" value="1000" name="pminpoint"> 점
    </li>
    <li>결제 최대 포인트</li>
    <li>
        <input type="text" class="in_form0" maxlength="5" name="pmaxpoint"> 점
    </li>
</ul>
<ul class="info_form">
    <li>현금 영수증 발급사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" value="Y" name="pcash"> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" value="N" name="pcash" checked> 미사용</label></em> ※ 현금영수증 관련 사항은 PG사 가입이 되었을 경우 사용가능 합니다.
    </li>
</ul>
<ul class="info_form2">
    <li>배송업체명</li>
    <li>
        <input type="text" class="in_form0" name="pdeli_name"> 
    </li>
    <li>배송비 가격</li>
    <li>
        <input type="text" class="in_form0" maxlength="7" name="pdeli_price"> 원
    </li>
</ul>
<ul class="info_form" style="border-bottom:1px solid rgb(81, 61, 61);">
    <li>희망배송일</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" value="Y" name="pdeli_date"> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" value="N" name="pdeli_date" checked> 미사용</label></em> ※ 희망배송일 사용시 사용자가 직접 설정 할 수 있습니다.
    </li>
</ul>
</div>
</form> 
</section>

<div class="btn_div">
    <button type="button" class="sub_btn1" title="설정 저장" id="set_save">설정 저장</button>
    <button type="button" class="sub_btn2" title="저장 취소">저장 취소</button>
</div>
<section></section>
<section></section>
</main>

<script type="module">
import {shopping_set} from "./js/admin_siteinfo_main.js?v=3"

document.querySelector("#set_save").addEventListener("click", function(){
	/*
	var ss = new shopping_set();
	if(ss.check_num() > 0){
		alert("'회원가입시 적립금', '결제 최소 포인트', '결제 최대 포인트'는 숫자만 입력 가능합니다.");	
	}
	
	var jcount = ss.default_join();
	var scount = ss.default_setting();
	var pcount = ss.default_pay();

	if(jcount > 0){
		alert("[홈페이지 가입환경 설정] 은 모든 정보를 필수로 입력하셔야 저장이 가능합니다.");
	}
	else if(jcount == 0 && scount > 0){
		alert("[홈페이지 기본환경 설정] 은 '통신판매업 신고번호'와 '부가통신 사업자번호' 외의 모든 정보를 필수로 입력하셔야 저장이 가능합니다.");
	}
	else if(jcount == 0 && scount == 0 && pcount > 0){
		alert("[결제 기본환경 설정] 은 '무통장 은행'과 '은행 계좌번호' 외의 모든 정보를 필수로 입력하셔야 저장이 가능합니다.\n ※'무통장 은행'과 '은행 계좌번호'는 같이 입력되어야만 합니다.");
	}
	else{
		console.log("ok 걍 do로 날리고 history -1하면 될듯");
	}
*/
});
</script>