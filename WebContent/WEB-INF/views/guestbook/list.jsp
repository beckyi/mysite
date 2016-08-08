<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.ac.sungkyul.mysite.vo.GuestbookVo"%>
<%@page import="kr.ac.sungkyul.mysite.vo.UsersVo" %>
<%@page import="java.util.List"%>
<%
	List<GuestbookVo> list = (List<GuestbookVo>)request.getAttribute("index");
	UsersVo authUser = (UsersVo)session.getAttribute("authUser");

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
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="/mysite/gs" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<%
								if(authUser == null){
							%>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="pass"></td>
							<%
								} else {
							%>
							<td>이름</td><td><%=authUser.getName() %>
							<input type="hidden" name="name" value="<%=authUser.getName() %>">
							<input type="hidden" name="pass" value="<%=authUser.getPassword() %>">
							</td>
							<%
								}
							%>
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
				<%
					for(GuestbookVo vo : list){
				%>
				<ul>
					<li>
						<table>
							<tr>
								<td>[3]</td>
								<td><%=vo.getName() %></td>
								<td><%=vo.getRegDate() %></td>
								<td><a href="/guestbook2/gs?a=deleteform&no=<%=vo.getNo()%>">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>
								<%
									String content = vo.getContent().replace("\n", "<br/>");
								%>
								<%= content %>	
								</td>
							</tr>
						</table>
						<br>
					</li>
				</ul>
				<%
					}
				%>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navi.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	</div>
</body>
</html>