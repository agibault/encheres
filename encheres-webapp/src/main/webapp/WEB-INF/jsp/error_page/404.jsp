<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="author" content="">
    <meta name="description" content="">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Erreur 404</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/_include/header.jsp" %>
<main>
    <div class="container">
        <h1 class="text-center m-3">Page introuvable</h1>
        <div class="row justify-content-center">
            <div class="col-6">
                <div class="logo-error text-center p-5">
                    <img src="${pageContext.request.contextPath}/images/logo/logo-500.png" alt="" width="500" height="526">
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="/WEB-INF/jsp/_include/footer.jsp" %>
</body>
</html>