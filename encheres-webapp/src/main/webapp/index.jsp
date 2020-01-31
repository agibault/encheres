<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <%@include file="/WEB-INF/jsp/_include/head.jsp" %>
    <title>Eni-Encheres - Accueil</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/_include/header.jsp" %>
<main>
    <h1 class="main_title text-center m-3">Liste des encheres</h1>

</main>
<div class="container ">

    <form action="${pageContext.request.contextPath}/search" method="post" class="m-4">


        <div class="form-row">
            <input type="text" class="form-control col-md-8" name="tag" placeholder="le nom de l'article contient">
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-2">Rechercher</button>
            </div>
        </div>

    </form>

    <c:if test="${not empty user.id}">
        <%@include file="/WEB-INF/jsp/form/search_product.jsp" %>
    </c:if>


    <c:if test="${empty products}">
    <p>Aucune enchere disponible </p><a href="${pageContext.request.contextPath}/sell"></a>

    </c:if>
    <div class="row row-cols-1 row-cols-md-3">
        <c:forEach items="${products}" var="product">

            <div class="col mb-4">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <div class="card-title product_name">
                            <c:if test="${ not empty user.id}">
                                <a href="<c:url value="/auction">
                                    <c:param name="id" value="${product.id}"/>
                                </c:url>" class="items">${product.name}</a>
                            </c:if>
                            <c:if test="${empty user.id}">
                                ${product.name}
                            </c:if>
                        </div>

                        <h6 class="card-subtitle mb-2 text-muted">Prix : <c:out
                                value="${product.actualPrice}">${product.startingPrice}</c:out> points</h6>
                        <p class="card-text"></p>

                        <fmt:parseDate value="${product.dateEndBid}" pattern="yyyy-MM-dd" var="myDate"/>

                        <p>Fin de l'enchere : <fmt:formatDate value="${myDate}" pattern="dd/MM/yyyy"/></p>
                        <c:if test="${not empty user.id}">
                            <a href="  <c:url value="/profile">
                                <c:param name="id" value="${product.seller.id}"/>
                           </c:url>" class="btn btn-primary">${product.seller.username}</a>
                        </c:if>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
    <%@include file="/WEB-INF/jsp/_include/footer.jsp" %>
</body>
</html>