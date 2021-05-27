<%@page import="com.douzone.mysite.repository.GuestBookRepository"%>
<%@page import="com.douzone.mysite.vo.GuestBookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<GuestBookVo> list = new GuestBookRepository().findAll();
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
			<div id="header">
	 <jsp:include page="/WEB-INF/views/include/header.jsp"/>
	 </div>
		<div id="content">
			<div id="guestbook">
				<form action="<%=request.getContextPath()%>/guestbook" method="post">
				<input type ="hidden" name ="a" value ="add"/>
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<li>
						<table>
							<%
							int i = 0;
							for (GuestBookVo vo : list) {
								i++;
							%>
							<tr>
								<td><%=i%></td>
								<td><%=vo.getName()%></td>
								<td><%=vo.getRegDate()%></td>
								<td><a
									href="<%=request.getContextPath()%>/guestbook?a=deleteform&no=<%=vo.getNo()%>">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4><pre><%=vo.getMessage()%>		</pre></td>
							</tr>
							<%
							}
							%>
						</table> <br>
					</li>
				</ul>
			</div>
		</div>
		<div id="navigation">
				 <jsp:include page="/WEB-INF/views/include/nevigation.jsp"/>
		</div>
		<div id="footer">
		 <jsp:include page="/WEB-INF/views/include/footer.jsp"/>
		</div>
	</div>
</body>
</html>