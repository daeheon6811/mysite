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
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/header.jsp" />
		</div>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath}/board/kwd/" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
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


					<c:set var="count" value=" ${fn:length(list) }" />
					<c:forEach items="${list}" var="vo" varStatus="status" begin="0">
						<tr>
							<c:choose>
								<c:when test="${curPageNum eq 0 }">
									<td>${status.index }</td>
								</c:when>
								<c:otherwise>
									<td>${ status.index + (curPageNum * 5)  - 5 }</td>
								</c:otherwise>
							</c:choose>

                                 <c:choose>
					          	<c:when test="${vo.depth eq 0 }">
										<td style="text-align:left; padding-left:${vo.depth * 20}px ">  <a
								href="${pageContext.request.contextPath}/board/view/${vo.no}">${vo.title}</a></td>
						
								</c:when>
								<c:otherwise>
									<td style="text-align:left; padding-left:${vo.depth * 20}px ">   
									<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png'/><a
								href="${pageContext.request.contextPath}/board/view/${vo.no}">${vo.title}</a></td>
								</c:otherwise>
							</c:choose>

							<td>${vo.userName}</td>
							<td>${vo.hit}</td>
							<td>${vo.redDate}</td>

							<c:choose>
								<c:when test="${authUser.no eq vo.userNo}">
									<td><a
										href="${pageContext.request.contextPath}/board/delete/${vo.no}"
										class="del">삭제</a></td>
								</c:when>
							</c:choose>
						</tr>


					</c:forEach>

				</table>


				<div class="pager">
					<ul>
						<c:if test="${ curPageNum > 5 && !empty kwd }">
							<li><a
								href="${pageContext.request.contextPath}/board/page/${ blockStartNum - 1 }/kwd/${ kwd }">◀</a></li>
						</c:if>

						<c:if test="${ curPageNum > 5 }">
							<li><a
								href="${pageContext.request.contextPath}/board/page/${ blockStartNum - 1 }">◀</a></li>
						</c:if>

						<c:forEach var="i" begin="${ blockStartNum }"
							end="${ blockLastNum }">
							<c:choose>
								<c:when test="${ i > lastPageNum }">
									<li>${ i }</li>
								</c:when>
								<c:when test="${ i == curPageNum }">
									<li class="selected">${ i }</li>
								</c:when>
								<c:when test="${ !empty kwd}">
									<li><a
										href="${pageContext.request.contextPath}/board/page/${ i }/kwd/${ kwd }">${ i }</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath}/board/page/${ i }">${ i }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:if test="${ lastPageNum > blockLastNum && !empty kwd }">
							<li><a
								href="${pageContext.request.contextPath}/board/page${ blockLastNum + 1 }/kwd/${ kwd }">▶</a></li>
						</c:if>

						<c:if test="${ lastPageNum > blockLastNum }">
							<li><a
								href="${pageContext.request.contextPath}/board/page${ blockLastNum + 1 }">▶</a></li>
						</c:if>
					</ul>
				</div>

				<c:choose>
					<c:when test="${not empty authUser }">
						<div class="bottom">
							<a href="${pageContext.request.contextPath}/board/write"
								id="new-book">글쓰기</a>
						</div>
					</c:when>
				</c:choose>
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