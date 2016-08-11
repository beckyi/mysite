<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="kr.ac.sungkyul.mysite.vo.UsersVo"%>

<c:if test='${"success" == param.res }'>
	<script>
		alert( "성공적으로 수정하였습니다." );
	</script>
</c:if>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
<c:if test='${"success"== param.res }'>
	<script>
			alert( "성공적으로 수정하였습니다." );
	</script>
</c:if>
<title>Insert title here</title>
</head>
<body>
	<div id="container">
	<c:import url='/WEB-INF/views/include/header.jsp'/>
		<div id="content">
			<div id="user">
				<form id="join-form" name="modifyForm" method="post" action="/mysite/users">
				<input type="hidden" name="a" value="modify"/>
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userVo.name }">

					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<c:choose>
							<c:when test='${"FEMALE" == userVo.gender }'>
								<label>여</label> <input type="radio" name="gender" value="FEMALE" checked="checked">
								<label>남</label> <input type="radio" name="gender" value="MALE">
							</c:when>
							<c:otherwise>
								<label>여</label> <input type="radio" name="gender" value="FEMALE">
								<label>남</label> <input type="radio" name="gender" value="MALE"  checked="checked">
							</c:otherwise>
						</c:choose>
					</fieldset>
					<input type="submit" value="수정하기">
				</form>
			</div>
		</div>
		<c:import url='/WEB-INF/views/include/navi.jsp'/>
		<c:import url='/WEB-INF/views/include/footer.jsp'/>
	</div>
</body>
</html>