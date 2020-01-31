<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@include file="/WEB-INF/jsp/_include/head.jsp" %>
    <title>Eni-Encheres - Vendre</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/_include/header.jsp" %>
<h1 class="text-center m-3">Nouvelle vente</h1>
<main>
    <div class="container">
        <c:if test="${not empty errors}">
            <div class="errors">
                <div class="alert alert-danger" role="alert">
                        ${errors}
                </div>
            </div>

        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/sell">
            <jsp:include page="form/add_product.jsp">
                <jsp:param name="user" value="${user}"/>
            </jsp:include>
        </form>
    </div>
</main>
<%@include file="/WEB-INF/jsp/_include/footer.jsp" %>
</body>
</html>

