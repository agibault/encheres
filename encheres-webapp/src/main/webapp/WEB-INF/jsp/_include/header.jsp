<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
        <img src="${pageContext.request.contextPath}/images/logo/logo-60.png" width="60" height="60" class="d-inline-block align-top" alt="">
        ENI-Encheres
    </a>
    <div class="collapse navbar-collapse " id="navbarNav">

        <ul class="navbar-nav ml-auto">
            <c:if test="${not empty user.id}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/sell"><span class="link">Vendre un article</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/profile"><span class="link">Mon profil</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/logout">Déconnexion</a>
                </li>
            </c:if>
            <c:if test="${empty user.id}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/registration">S'inscrire</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/login">Se connecter</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>