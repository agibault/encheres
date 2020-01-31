<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@include file="/WEB-INF/jsp/_include/head.jsp" %>
    <title>Eni-Encheres - Inscription</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/_include/header.jsp" %>
<h1 class="text-center m-5">Mon profil</h1>
<main>
    ${user_username}
    <c:if test="${not empty errors}">
        <div class="errors">
            <div class="alert alert-danger" role="alert">

                <c:forEach var="error" items="${errors}">
                    <li>
                        <ul>${error.key} ${error.value}</ul>
                    </li>

                </c:forEach>
                <c:forEach var="errror" items="${errorts}">
                    <li>
                        <ul>${errror.key} ${errror.value}</ul>
                    </li>

                </c:forEach>
            </div>
        </div>
    </c:if>
    <div class="row justify-content-center dc">
        <div class="form_login col-6 ">
            <form action="${pageContext.request.contextPath}/registration" method="post">
                <%@include file="/WEB-INF/jsp/form/user_profile.jsp" %>

            </form>
        </div>
    </div>
</main>
<%@include file="/WEB-INF/jsp/_include/footer.jsp" %>
</body>
</html>