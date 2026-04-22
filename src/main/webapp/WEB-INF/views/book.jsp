<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
  <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
  <script src="<c:url value='/resources/js/controllers.js' />" ></script>
  
  <meta charset="UTF-8">
  <title>도서 상세 정보</title>
</head>

<body>
 <jsp:include page="menu.jsp" />

  <div class="container">
    <div class="row">
      <div class="col-md-4">
        <c:choose>
          <c:when test="${book.bookImage == null}">
            <img src="<c:url value='/upload/${book.fileName}' />" style="width: 100%" />
          </c:when>
          <c:otherwise>
            <img src="<c:url value='/upload/${book.fileName}' />" style="width: 100%" />
          </c:otherwise>
        </c:choose>
      </div>

      <div class="col-md-8">
        <h3>${book.name}</h3>
        <p>${book.description}</p>
        <br>
        <p>
          <b>도서코드 : </b><span class="badge badge-info">${book.bookId}</span>
        </p>
        <p>
          <b>저자 : </b>${book.author}
        <p>
          <b>출판사 : </b>${book.publisher}
        <p>
          <b>출판일 : </b>${book.releaseDate}
        <p>
          <b>분류 : </b>${book.category}
        <p>
          <b>재고수 : </b>${book.unitsInStock}
        <h4>${book.unitPrice}원</h4>
        <br>
        <c:url var="addCartUrl" value="/cart/add/${book.bookId}"/>
        <form:form name="addForm" method="put" action="${addCartUrl}">
          <p>
            <button type="button" onclick="addToCart()" class="btn btn-primary">도서주문 &raquo;</button>
            <a href="<c:url value='/cart' />" class="btn btn-warning">
              장바구니 &raquo; </a>
            <a href="<c:url value='/books' />" class="btn btn-secondary">도서 목록
              &raquo;</a>
            <a href="<c:url value="/books/update?id=${book.bookId}" />" class="btn btn-success">도서수정 &raquo;</a>
            <button type="button" onclick="deleteConfirm('${book.bookId}')" class="btn btn-danger">도서삭제 &raquo;</button>
          </p>
        </form:form>
      </div>
    </div>
    <hr>
    <footer>
      <p>&copy; BookMarket</p>
    </footer>
  </div>
</body>

</html>