<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.ac.sungkyul.mysite.vo.UsersVo"%>
<%
	String result = request.getParameter("res");
	UsersVo usersVo = (UsersVo)request.getAttribute("userVo");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
<script>
	<%
		if( "success".equals( result ) ) {
	%>
		alert( "성공적으로 수정하였습니다." );
	<%
		}
	%>
</script>
<title>Insert title here</title>
</head>
<body>
	<div id="container">
	<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="user">
				<form id="join-form" name="modifyForm" method="post" action="/mysite/users">
				<input type="hidden" name="a" value="modify"/>
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="<%=usersVo.getName()%>">

					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<%	if("FEMALE".equals(usersVo.getGender())){ %>
						<label>여</label> <input type="radio" name="gender" value="FEMALE" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="MALE">
						<%
							} else{
						%>
						<label>여</label> <input type="radio" name="gender" value="FEMALE">
						<label>남</label> <input type="radio" name="gender" value="MALE" checked="checked">
						<%
							}
						%>
					</fieldset>
					<input type="submit" value="수정하기">
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navi.jsp"></jsp:include>
		
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	</div>
</body>
</html>