<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@include file="/WEB-INF/jsp/_include/head.jsp" %>
    <title>Eni-Encheres - Detail</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/_include/header.jsp" %>
<h1 class="text-center m-3">Detail vente</h1>
<main>
    <div class="container">

        <c:if test="not empty ${errors_bid}">
            <div class="alert alert-primary" role="alert">
                Enchere Imposible
            </div>
        </c:if>
        <c:if test="not empty ${bid_succes}">
            <div class="alert alert-primary" role="alert">
                Enchere Imposible
            </div>
        </c:if>
        <h2 class="col-12 m-4 mx-auto"><c:out value="${product.name}"/></h2>
        <div class="row mx-auto">


            <p class="col-6">Description :</p>
            <p class="col-6"><c:out value="${product.description}"/></p>


            <c:if test="${not empty product.buyer.username}">
            <p class="col-6 ">Meilleur offre</p>
            <p class="col-6 ">
                    <c:out value="${product.actualPrice}"/> points par ${product.buyer.username}
                </c:if>

            <p class="col-6 ">Mise a prix</p>
            <p class="col-6 "><c:out value="${product.startingPrice}"/> points</p>
            <fmt:parseDate value="${product.dateEndBid}" pattern="yyyy-MM-dd" var="myDate"/>
            <p class="col-6">Fin de l'enchere</p>
            <p class="col-6"><fmt:formatDate value="${myDate}" pattern="dd/MM/yyyy"/></p>

            <p class="col-6">Vendeur</p>
            <p class="col-6"><c:out value="${product.seller.username}"/></p>

            <c:if test="${user.id != product.seller.id }">
        </div>
        <form action="${pageContext.request.contextPath}/auction" method="post">
            <div class="row mx-auto">
                <div class="col-4">
                    <label for="price">Ma proposition : </label>
                    <input type="number" id="price" name="auction_price">
                </div>
                <div class="col-6">
                    <input type="hidden" name="product_id" value="${product.id}">
                    <input type="submit" value="encherir">
                </div>
            </div>
        </form>
        </c:if>
    </div>
</main>
<%@include file="/WEB-INF/jsp/_include/footer.jsp" %>
</body>
</html>