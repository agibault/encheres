<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@include file="/WEB-INF/jsp/_include/head.jsp" %>
    <title>Eni-Modification du profil - Inscription</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/_include/header.jsp" %>
<h1 class="text-center m-5">Mon profil</h1>
<main>

    <c:if test="${not empty errors}">
        <div class="errors">
            <div class="alert alert-danger" role="alert">

                <c:forEach var="error" items="${errors}">
                    <li>
                        <ul>${error.value}</ul>
                    </li>

                </c:forEach>
            </div>
        </div>
    </c:if>
    <div class="row justify-content-center m-4 dc">
        <div class="form_login col-6 ">
            <form action="${pageContext.request.contextPath}/profile/update" method="post">
                <jsp:include page="form/user_profile.jsp"/>

            </form>

        </div>
    </div>


</main>
<%@include file="/WEB-INF/jsp/_include/footer.jsp" %>
</body>
</html>