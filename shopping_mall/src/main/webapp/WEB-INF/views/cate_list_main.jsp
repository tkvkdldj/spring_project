<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<main class="maincss">
    <section>    
<p>카테고리관리 페이지</p>
<div class="subpage_view">
    <span>등록된 카테고리 ${total}건</span>
    <span>
        <form id="frm_csearch">
        <select class="p_select1" name="part">
            <option value="1">카테고리명</option>
            <option value="2">카테고리코드</option>
        </select>
        <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요" name="search" value="${search}">
        <input type="submit" value="검색" title="카테고리 검색" class="p_submit">
        </form>
    </span>
</div>
<form id="frm_catewrite">
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox" id="all_ck"></li>
        <li>분류코드</li>
        <li>대메뉴 코드</li>
        <li>대메뉴명</li>
        <li>소메뉴 코드(사용안함)</li>
        <li>소메뉴명(사용안함)</li>
        <li>사용 유/무</li>
        <li>관리</li>
    </ul>
<cr:choose>
<cr:when test="${total == 0}"> 
    <ul>
        <li style="width: 100%;">등록된 카테고리가 없습니다.</li>
    </ul>
</cr:when>
<cr:otherwise>
<cr:forEach var="list" items="${cate_data}" varStatus="n">
    <ul>
        <li><input type="checkbox" name="each_ck" value="${list.getCidx()}"></li>
        <li style="text-align: left; text-indent: 5px;">${list.getCsortcode()}</li>
        <li>${list.getCmenucode()}</li>
        <li style="text-align: left; text-indent: 5px;">${list.getCmenuname()}</li>
        <li>-</li>
        <li style="text-align: left; text-indent: 5px;">-</li>
        <li>${list.getIsuse()}</li>
        <li>[수정]</li>
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
<div class="subpage_view4">
    <input type="button" value="카테고리 삭제" title="카테고리 삭제" class="p_button" id="delete_cate">
    <span style="float: right;">
    <input type="button" value="상품 리스트" title="상품 리스트" class="p_button p_button_color1" id="go_pdlist">
    <input type="button" value="카테고리 등록" title="카테고리 등록" class="p_button p_button_color2" id="cate_enroll2">
    </span>
</div>
</form>
</section>
</main>

<script type="module">
import {delete_cate} from "./js/cate_list_main.js?v=2"

var sel_ck = "${part}";
if(sel_ck != ""){
	frm_csearch.part.value = sel_ck;
}

document.querySelector("#frm_csearch").addEventListener("submit", function(z){
	z.preventDefault();
	frm_csearch.method = "post";
	frm_csearch.action = "./cate_list.do";
	frm_csearch.submit();
});


document.querySelector("#delete_cate").addEventListener("click", function(){
	if(confirm('해당 데이터는 더 이상 복구 되지 않습니다.')){
		frm_catewrite.method = "post";
		frm_catewrite.action = "./cate_deleteok.do";
		frm_catewrite.submit();
	}
});

document.querySelectorAll('[name="each_ck"]').forEach(function(element) {
  element.addEventListener("click", function() {
    new delete_cate().count_each_ck();
 });
});

document.querySelector("#all_ck").addEventListener("click", function(){
	new delete_cate().handling_all_ck();
});

document.querySelector("#go_pdlist").addEventListener("click", function(){
	location.href = "./product_list.do";
});

document.querySelector("#cate_enroll2").addEventListener("click", function(){
	frm_catewrite.method = "post";
	frm_catewrite.action = "./cate_write.do";
	frm_catewrite.submit();
});

</script>