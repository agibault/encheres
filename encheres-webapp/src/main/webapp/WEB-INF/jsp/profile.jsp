<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@include file="/WEB-INF/jsp/_include/head.jsp" %>
    <title>Eni-Encheres - Profil</title>
</head>
<body>

<%@include file="/WEB-INF/jsp/_include/header.jsp" %>
<main>

    <div class="container profil">

        <c:if test="${not empty profil.id}">


            <div class="row mx-auto">
                <p class="col-6 ">Pseudo</p>
                <p class="col-6"><c:out value="${profil.username}"/></p>

                <p class="col-6">Nom</p>
                <p class="col-6"><c:out value="${profil.lastName}"/></p>

                <p class="col-6 ">Prenom</p>
                <p class="col-6 "><c:out value="${profil.firstName}"/></p>

                <p class="col-6 ">Email</p>
                <p class="col-6 "><c:out value="${profil.email}"/></p>

                <p class="col-6">Telephone</p>
                <p class="col-6"><c:out value="${profil.phoneNumber}"/></p>

                <p class="col-6">Rue</p>
                <p class="col-6"><c:out value="${profil.street}"/></p>

                <p class="col-6">Code postal</p>
                <p class="col-6"><c:out value="${profil.postalCode}"/></p>

                <p class="col-6">Ville</p>
                <p class="col-6"><c:out value="${profil.city}"/></p>

            </div>

        </c:if>
        <c:if test="${ sessionScope.user.id == profil.id}">
            <div class="row profil_update">
                <div class="col-12 text-center m-4 ">
                    <a href="${pageContext.request.contextPath}/profile/update">Modifier</a>
                </div>

            </div>

        </c:if>

        <c:if test="${empty profil.id}">
            <p>Profil inexistant</p>
        </c:if>
    </div>

</main>
<%@include file="/WEB-INF/jsp/_include/footer.jsp" %>
</body>
</html>