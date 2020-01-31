<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="${pageContext.request.contextPath}/login" method="post" name="form_login">
    <div class="form-group row">
        <label class="col-6 col-lg-3 col-form-label" for="username">Identifiant :</label>
        <input type="text" name="user_username" id="username" class="form-control col-5 col-lg-5" value="<c:out value="${param.user_username}"/>" required>
    </div>
    <div class=" form-group row">
        <label class="col-6 col-lg-3 col-form-label" for="password">Mot de passe :</label>
        <input type="password" name="user_password" id="password" class="form-control col-5 col-lg-5" required>
    </div>

    <div class="row">
        <div class="col-12 text-center m-4">
            <input type="submit" value="Connexion">
        </div>

    </div>

</form>