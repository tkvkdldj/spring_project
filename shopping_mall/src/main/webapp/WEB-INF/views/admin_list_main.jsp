<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<main class="maincss">
<section>
    <p>신규등록 관리자</p>
    <ol class="new_admin_title2">
        <li>NO</li>
        <li>관리자명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>담당부서</li>
        <li>담당직책</li>
        <li>가입일자</li>
        <li>승인여부</li>
    </ol>
<cr:choose>
<cr:when test="${total == 0}">
    <ol class="new_admin_none">
        <li>신규 등록된 관리자가 없습니다.</li>
    </ol>
</cr:when>
<cr:otherwise>
<cr:forEach var="list" items="${result}" varStatus="n">
    <ol class="new_admin_lists2">
        <li>${total - n.index}</li>
        <li>${list.getAname()}</li>
        <li>${list.getAid()}</li>
        <li>${list.getAtel()}</li>
        <li>${list.getAemail()}</li>
        <li>${list.getAdepart()}</li>
        <li>${list.getAposition()}</li>
        <li>${list.getAdate().substring(0,10)}</li>
        <li>
        <cr:choose>
        <cr:when test="${list.getIsgrant() == '승인'}">
            <input type="button" value="승인" class="new_addbtn1" title="승인">
        </cr:when>
        <cr:otherwise>    
            <input type="button" value="미승인" class="new_addbtn2" title="미승인">
        </cr:otherwise>
        </cr:choose>
        </li>
    </ol>
</cr:forEach>
</cr:otherwise>
</cr:choose>    
</section>
<section></section>
<section></section>
</main>