<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
<script>
	$(function() {
		btn = $('#btn-check');
		btn.click(function() {
			var email = $("#email").val();
			if (email == "") {
				return;
			}
			$.ajax({
				url : "/mysite03/user/api/checkemail?email=" + email,
				type : "get",
				dataType : "json",
				success : function(response) {
					if (response.result != "success") {
						console.log("error");
						return;
					}

					if (response.exist) {
						alert("존재하는 이메일입니다. 다른 이메일을 사용하세요.");
						$("#email").val("");
						$("#email").focus();
						return;
					}

					$("#btn-check").hide();
					$("#img-check").show();
				}
			});
		});
	});
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/header.jsp" />
		</div>
		<div id="content">
			<div id="user">
				<form:form modelAttribute="userVo" id="join-form" name="joinForm"
					method="post"
					action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>

					<form:input path="name" />
					<p style="color: #f00; text-align: left; padding-left: 0">
						<spring:hasBindErrors name="userVo">
							<c:if test="${errors.hasFieldErrors('name') }">
								<spring:message code="NotEmpty.userVo.name" />
							</c:if>
						</spring:hasBindErrors>
					</p>

					<label class="block-label" for="name">이메일</label>
					<form:input path="email" />
					<form:button id="btn-check" type="button" value="중복체크" />
					<img id="img-check"
						src="${pageContext.request.contextPath }/assets/images/check.png"
						style="width: 18px; vertical-align: bottom; display: none" />
					<p style="color: #f00; text-align: left; padding-left: 0">
						<form:errors path="email" />
					</p>
					<label class="block-label">패스워드</label>

					<!-- <input name="password" type="password" value=""> -->
					<form:password path="password" />
					<p style="color: #f00; text-align: left; padding-left: 0">
						<form:errors path="password" />
					</p>

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<div id="navigation">
			<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		</div>

		<div id="footer">
			<c:import url="/WEB-INF/views/includes/footer.jsp" />
		</div>

	</div>
</body>
</html>