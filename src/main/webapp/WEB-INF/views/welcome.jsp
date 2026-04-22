<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
<title>Welcome</title>
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="menu.jsp" />
    <div class="jumbotron">  
        <div class="container">
            <h1 class="display-3">${greeting}</h1>
        </div>
    </div>
    <div class="container">   
        <div class="text-center">
            <h3>${strapline}</h3>
        </div>
    </div> 
    <footer class = "container">  
        <hr>
        <p>&copy; WebMarket</p>
    </footer> 
</body>
</html>