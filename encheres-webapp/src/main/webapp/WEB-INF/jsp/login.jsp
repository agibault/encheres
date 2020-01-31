<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@include file="/WEB-INF/jsp/_include/head.jsp" %>
    <title>Eni-Encheres - Connexion</title>
</head>
<body>

<%@include file="/WEB-INF/jsp/_include/header.jsp" %>

<c:if test="${not empty errors}">
    <div class="errors">
        <div class="alert alert-danger" role="alert">
                ${errors}
        </div>
    </div>
</c:if>

<div class="row justify-content-center dc">
    <div class="form_login col-6 ">
        <%@include file="/WEB-INF/jsp/form/authentification.jsp" %>
    </div>
</div>
<%@include file="/WEB-INF/jsp/_include/footer.jsp" %>
</body>
</html>