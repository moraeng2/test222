<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c: 조건문/반복문 (c:if, c:forEach) -->
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- fn : 문자열 함수 (fn:length, fn:contains) -->
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!-- fmt : 포맷/국제화 (fmt:formatDate) -->
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!-- JSP에서 JSTL 쓰려면 taglib 지시자를 쓴다
prefix = "접두어" : JSP에서 쓸 이름 (C, fn, fmt, ...)
uri = "고유한 uri" : JSTL이 제공하는 고정된 식별자(네임스페이스)-진짜 인터넷 주소가 아니라 식별자(가짜 주소)
 -->
<html>
<head>
<link href="<c:url value='/resources/css/bootstrap.min.css' />"
	rel="stylesheet">
<meta charset="UTF-8">
<title>도서 목록</title>
</head>
<body>
	<jsp:include page="menu.jsp" />

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">도서 목록</h1>
		</div>
	</div>

	<div class="container">
		<div class="row" align="center">
			<c:forEach items="${bookList}" var="book">
				<div class="col-md-4">
					<c:choose>
						<c:when test="${book.bookImage == null}">
							<img src="<c:url value='/upload/${book.fileName}' />" style="width: 60%"/>
						</c:when>
						<c:otherwise>						
							<img src="<c:url value='/upload/${book.fileName}' />" style="width: 60%"/>
						</c:otherwise>					
					</c:choose>				
				
					<h3>${book.name}</h3>
					<p>${book.author}
						<br>${book.publisher} | ${book.releaseDate}
					</p>
					<p align="left">
					${fn:substring(book.description, 0, 100)}...
					</p>
					<p>${book.unitPrice}원</p>
					<p>
						<a href="<c:url value='/books/book?id=${book.bookId}'/>"
							class = "btn btn-secondary" role="button">
							상세정보 &raquo;
						</a>
					</p>
				</div>
			</c:forEach>
		</div>
		<hr>
		<footer>
			<p>&copy; BookMarket</p>
		</footer>
	</div>
</body>
</html>