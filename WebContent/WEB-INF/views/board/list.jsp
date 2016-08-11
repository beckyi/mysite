<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="kr.ac.sungkyul.mysite.vo.GuestbookVo"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/include/header.jsp'/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var='countList' value='${fn:length(blist)}'/>
					<c:forEach var='vo' items='${blist }' varStatus='status'>	
					<tr>
						<td>${countList - status.index }</td>
						<td style="text-align:left"><a href="/mysite/bs?a=viewform&r=${vo.no }">${vo.title}</a></td>
						<td>${vo.userName }</td>
						<td>${vo.viewCount }</td>
						<td>${vo.regDate }</td>
						<td>
						<c:choose>
							<c:when test='${not empty authUser && authUser.no == vo.userNo }'>
									<a href="/mysite/bs?a=delete&r=${vo.no }" class="del">삭제</a>
							</c:when>
								<c:otherwise>
									&nbsp;
								</c:otherwise>
						</c:choose>
						</td>
					</tr>
					</c:forEach>
				</table>
				<!-- begin:paging -->
				<div class="pager">
					<ul>
					<c:if test="">
						<li><a href="">◀</a></li>
					</c:if>
					<c:forEach begin='${firstPage }' end='${lastPage }' step='1' var='i'>
							<li><a href="/mysite/bs?page=${i }">${i }</a></li>
					</c:forEach>
					<c:if test="">
						<li><a href="">▶</a></li>
					</c:if>
					</ul>
				</div>
				<!-- end:paging -->
				<div class="bottom">
					<c:if test ='${not empty sessionScope.authUser }'>
					<a href="/mysite/bs?a=writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url='/WEB-INF/views/include/navi.jsp'/>
		<c:import url='/WEB-INF/views/include/footer.jsp'/>
	</div>
</body>
</html>