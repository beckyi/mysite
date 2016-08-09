<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="kr.ac.sungkyul.mysite.vo.GuestbookVo"%>
<%@page import="kr.ac.sungkyul.mysite.vo.UsersVo" %>
<%@page import="java.util.List"%>
<%
	pageContext.setAttribute("newLine", "\n"); // JSTL에서 \를 인식 못하기 때문에 \n 이라는 문자를 newLine으로 대체
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/include/header.jsp'/>
		<div id="content">
			<div id="guestbook">
				<form action="/mysite/gs" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<c:choose>
							<c:when test='${empty sessionScope.authUser }'>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="pass"></td>
							</c:when>
							<c:otherwise>
							<td>이름</td><td>${authUser.name}
							<input type="hidden" name="name" value="${authUser.name}">
							<input type="hidden" name="pass" value="${authUser.password}">
							</td>
							</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<br>
				<ul>
					<c:set var='countList' value='${fn:length(list)}'/>
               		<c:forEach var='vo' items='${list }' varStatus='status'>
					<li>
						<table>
							<tr>
								<td>${countList - status.index }</td>
								<td>${vo.name }</td>
								<td>${vo.regDate }</td>
								<td><a href="/mysite/gs?a=deleteform&no=${vo.no}">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>
								<!-- String content = vo.getContent().replace("\n", "<br/>"); -->
								 ${fn:replace(vo.content,  newLine, '<br>')} 
								</td>
							</tr>
						</table>
						<br>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<c:import url='/WEB-INF/views/include/navi.jsp'/>
		<c:import url='/WEB-INF/views/include/footer.jsp'/>
	</div>
</body>
</html>