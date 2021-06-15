<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>​
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>​
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		</div>
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath}/user/update">
				<label
						class="block-label" for="name">이름</label>
						 <input id="name" name="name" type="text" value="${user.name}"> 
						 <label
						class="block-label" for="name">이메일</label>
						 <label class="block-label">${user.email}</label> 
					
						

						 <label class="block-label">패스워드</label> 
						 <input name="password" type="password" value="">

					<fieldset>
						<legend>성별</legend>
					
						<c:choose>
						
						 <c:when test='${user.gender == "female" }'>
						 	<label>여</label> 
						 		<input type="radio" name="gender" value="female"checked="checked">
						 			<label>남</label> 
						 	<input type="radio" name="gender" value="female">  
						 </c:when>
						 <c:otherwise>
						 		 	<label>여</label> 
						 		<input type="radio" name="gender" value="female">
						 			<label>남</label> 
						 	<input type="radio" name="gender" value="female"checked="checked">  
						 </c:otherwise>
						</c:choose>
					
				
					
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="수정하기">

				</form>
			</div>
		</div>
		<div id="navigation">
			<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		</div>
		<div id="footer">
			<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
		</div>
	</div>
</body>
</html>