<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
			<div id="header">
	 <jsp:include page="/WEB-INF/views/include/header.jsp"/>
	 </div>
		 
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="<%=request.getContextPath()%>/guestbook">
					<input type="hidden" name="a" value="delete">
					<input type='hidden' name="no" value="<%=request.getParameter("no") %>">
					<label>비밀번호</label>
					<input type="password" name="password">
					<input type="submit" value="확인">
				</form>
				<a href="">방명록 리스트</a>
			</div>
		</div>
		<div id="navigation">
		 <jsp:include page="/WEB-INF/views/include/nevigation.jsp"/>
		</div>
		<div id="footer">
			<p>(c)opyright 2015, 2016, 2017, 2018</p>
		</div>
	</div>
</body>
</html>