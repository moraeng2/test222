<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<nav class="navbar navbar-expand navbar-dark bg-dark">
  <div class="container">
    <div class="navbar-header">
      <a href="<c:url value='/' />" class="navbar-brand">Book Market</a>
    </div>
    <div>
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <a href="<c:url value='/'/>" class="nav-link">Home</a>
        </li>
        <li class="nav-item">
          <a href="<c:url value='/books'/>" class="nav-link">Books</a>
        </li>
        <li class="nav-item">
          <a href="<c:url value='/books/add'/>" class="nav-link">Add Book</a>
        </li>
        <li class="nav-item">
          <a href="<c:url value='/cart'/>" class="nav-link">Cart</a>
        </li>
      </ul>
    </div>
  </div>

</nav>