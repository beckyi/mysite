<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="kr.ac.sungkyul.mysite.vo.UsersVo" %>
<div id="header">
	<h1>MySite</h1>
		<ul>
			<c:choose>
				<c:when test='${empty sessionScope.authUser }'>
					<li><a href="/mysite/users?a=loginform">로그인</a></li>
					<li><a href="/mysite/users?a=joinform">회원가입</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/mysite/users?a=modifyform">회원정보수정</a></li>
					<li><a href="/mysite/users?a=logout">로그아웃</a></li>
					<li>${authUser.name}님 안녕하세요 ^^;</li>
					<!-- <li>${sessionScope.authUser.name}님 안녕하세요<li> -->
				</c:otherwise>
			</c:choose>
		</ul>
</div>  