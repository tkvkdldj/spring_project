<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<main class="maincss">
<section>
<p>상품관리 페이지</p>
<div class="subpage_view">
    <span>등록된 상품 ${total}건</span>
    <span>
        <form>
        <select class="p_select1">
            <option>상품명</option>
            <option>상품코드</option>
        </select>
        <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요">
        <input type="submit" value="검색" title="상품검색" class="p_submit">
        </form>
    </span>
</div>
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox"></li>
        <li>코드</li>
        <li>이미지</li>
        <li>상품명</li>
        <li>카테고리 분류</li>
        <li>판매가격</li>
        <li>할인가격</li>
        <li>할인율</li>
        <li>재고현황</li>
        <li>판매유/무</li>
        <li>품절</li>
        <li>관리</li>
    </ul>
<cr:choose>
<cr:when test="${total == 0}">
  	<ul>
        <li style="width: 100%;">등록된 상품이 없습니다.</li>
    </ul>
</cr:when>
<cr:otherwise>  
<cr:forEach var="list" items="${result}" varStatus="n">
<cr:set var="imgs" value="${fn:split(list.getPdreimage(),',')}"/>
    <ul>
        <li><input type="checkbox"></li>
        <li>${list.getPdcode()}</li>
        <li><img src="../upload/${imgs[0]}"></li>
        <li>${list.getPdname()}</li>
        <li>${list.getCsortcode()}</li>
        <li>${list.getPdprice()}</li> <!-- ,써줘야함 -->
        <li>${list.getPd_disprice()}</li>
        <li>${list.getPd_discount()}%</li>
        <li>${list.getPdstock()}</li>
        <li>${list.getIs_sale()}</li>
        <li>${list.getPdearlyout()}</li>
        <li>관리</li>
    </ul>
</cr:forEach>
</cr:otherwise> 
</cr:choose>
</div>
<div class="subpage_view3">
    <ul class="pageing">
        <li><img src="./ico/double_left.svg"></li>
        <li><img src="./ico/left.svg"></li>
        <li>1</li>
        <li><img src="./ico/right.svg"></li>
        <li><img src="./ico/double_right.svg"></li>
    </ul>
</div>
<form id="frm_pdlist"> <!-- 이래도 되나...날리는 값도 없는데 -->
<div class="subpage_view4">
    <input type="button" value="선택상품 삭제" title="선택상품 삭제" class="p_button">
    <span style="float: right;">
    <input type="button" value="신규상품 등록" title="신규상품 등록" class="p_button p_button_color1" id="new_enroll">
    <input type="button" value="카테고리 등록" title="카테고리 등록" class="p_button p_button_color2" id="cate_enroll">
    </span>
</div>
 </form>   
</section>
</main>
<script type="module">
document.querySelector("#new_enroll").addEventListener("click", function(){
	frm_pdlist.method = "post";
	frm_pdlist.action = "./product_write.do";
	frm_pdlist.submit();
});

document.querySelector("#cate_enroll").addEventListener("click", function(){
	frm_pdlist.method = "get";
	frm_pdlist.action = "./cate_list.do";
	frm_pdlist.submit();
});
</script>