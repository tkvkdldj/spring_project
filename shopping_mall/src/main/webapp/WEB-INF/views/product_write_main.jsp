<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="maincss">
<section>
<p>상품 등록 페이지</p>
<form id="frm_pdwrite" enctype="multipart/form-data">
<div class="product_insert">
    <ul>
        <li>대메뉴 카테고리</li>
        <li>
            <select class="product_input1" name="csortcode">
            <cr:forEach var="list" items="${catecode}">
                <option>${list}</option>
            </cr:forEach>
            </select><input type="button" value="카테고리 등록" title="카테고리 등록" class="product_btn" id="cate_enroll"> <span class="help_text">※ 해당 카테고리가 없을 경우 신규 등록하시길 바랍니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품코드</li>
        <li>
            <input type="text" class="product_input1" name="pdcode" id="pdcode"> 
            <img src="./img/change_icon.png" width="30" height="30" id="ch_icon" style="margin-right: 7px; cursor:pointer;">
            <input type="button" value="중복확인" title="중복확인" class="product_btn" id="ck_dup"> 
       		<span class="code_check" id="ck_code"></span>
            <span class="help_text">※ 상품코드는 절대 중복되지 않도록 합니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품명</li>
        <li>
            <input type="text" class="product_input2" maxlength="100" name="pdname"> <span class="help_text">※ 상품명은 최대 100자까지만 적용할 수 있습니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품 부가설명</li>
        <li>
            <input type="text" class="product_input4" maxlength="200" name="pdaddexplan"> <span class="help_text">※ 상품명은 최대 200자까지만 적용할 수 있습니다.</span>
        </li>
    </ul>
    <ul>
        <li>판매가격</li>
        <li>
            <input type="text" class="product_input3" maxlength="7" name="pdprice" id="pdprice"> <span class="help_text">※ , 없이 숫자만 입력하세요 최대 7자리</span>
        </li>
    </ul>
    <ul>
        <li>할인율</li>
        <li>
            <input type="text" class="product_input3" maxlength="2" value="0" name="pd_discount" id="pd_discount">% <span class="help_text">※ 숫자만 입력하세요</span>
        </li>
    </ul>
    <ul>
        <li>할인가격</li>
        <li>
            <input type="text" class="product_input3" maxlength="7" value="0" name="pd_disprice" readonly> <span class="help_text">※ 할인율이 0%일 경우 할인가격은 0으로 처리 합니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품재고</li>
        <li>
            <input type="text" class="product_input3" maxlength="4" value="0" name="pdstock">EA <span class="help_text">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
        </li>
    </ul>
    <ul>
        <li>판매 유/무</li>
        <li>
            <label class="product_label">
            <input type="radio" name="is_sale" value="start" style="vertical-align:-1px;" checked> 판매시작
            </label>
            <label class="product_label">
            <input type="radio" name="is_sale" value="end" style="vertical-align:-1px;"> 판매종료
            </label> <span class="help_text">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
        </li>
    </ul>
    <ul>
        <li>조기품절</li>
        <li>
            <label class="product_label">
                <input type="radio" name="pdearlyout" value="Y" style="vertical-align:-1px;"> 사용
            </label>
            <label class="product_label">
                <input type="radio" name="pdearlyout" value="N" style="vertical-align:-1px;" checked> 미사용
            </label>
        </li>
    </ul>
    <ul style="height: 160px;">
        <li>상품 대표이미지</li>
        <li>
            <ol style="width:100%; height: auto;">
            <li style="width:100%; height:45px;">
            <input type="file" name="pdimage">
            <span class="help_text">※ 상품 대표이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            <li style="height:45px;">
            <input type="file" name="pdimage">
            <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            <li style="height:45px;">
            <input type="file" name="pdimage">
            <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            </ol>
        </li>
    </ul>
    <ul style="height: 400px;">
        <li>상품 상세설명</li>
        <li>
            <textarea class="product_text1" name="pd_detailexplan"></textarea>
        </li>
    </ul>
</div>
<div class="subpage_view4" style="text-align:center; margin-bottom: 100px;">
    <input type="button" value="상품 리스트" title="상품 리스트" class="p_button p_button_color1" style="margin-right: 5px;" id="go_pdlist">
    <input type="button" value="상품 등록" title="상품 등록" class="p_button p_button_color2" id="pd_enroll">
    </span>
</div>
</form>
</section>
</main>

<script type="module">
import {send_data} from "./js/product_write_main.js?v=4";

new send_data().ck_pdcode();

//상품코드 변화 감지
document.querySelector("#pdcode").addEventListener("input", function(){
	document.getElementById('ck_code').innerText = "";
});


document.querySelector("#ch_icon").addEventListener("click", function(){
	new send_data().ck_pdcode();
	document.getElementById('ck_code').innerText = "";
});

//할인가격 자동 계산
document.querySelectorAll("#pd_discount, #pdprice").forEach(function(element) {
    element.addEventListener("input", function() {
        new send_data().discount_price();
    });
});


document.querySelector("#ck_dup").addEventListener("click", function(){
	new send_data().ck_isdupli();
});

document.querySelector("#go_pdlist").addEventListener("click", function(){
	location.href = "./product_list.do";
});


document.querySelector("#pd_enroll").addEventListener("click", function(){
	new send_data().ck_values();
});


document.querySelector("#cate_enroll").addEventListener("click", function(){
	location.href = "./cate_list.do";
});

</script>