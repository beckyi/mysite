<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
		<h1>MySite</h1>
		<ul>
			<li><a href="/mysite/users?a=loginform">로그인</a><li>
			<li><a href="/mysite/users?a=joinform">회원가입</a><li>
		</ul>
	</div>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="/mysite/users">
				<input type="hidden" name="a" value="join"/>
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<input type="button" value="id 중복체크">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="FEMALE" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="MALE">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navi.jsp"></jsp:include>
		
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	</div>
</body>
</html>