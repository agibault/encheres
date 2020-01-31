<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group row">
    <label class="col-6 col-lg-3 col-form-label" for="username">Pseudo :</label>
    <input type="text" name="user_username" id="username" class="form-control col-6 col-lg-3"
           value="<c:out value="${user.username}">${param.user_username}</c:out>" required min="1" max="30">

    <label class="col-6 col-lg-3 col-form-label ml-auto text-lg-right " for="last_name">Nom :</label>
    <input type="text" name="user_lastName" id="last_name" class="form-control col-6 col-lg-3"
           value="<c:out value="${user.lastName}">${param.user_lastName}</c:out>" required min="1" max="30">
</div>
<div class="form-group row">
    <label class="col-6 col-lg-3 col-form-label" for="first_name">Prénom :</label>
    <input type="text" name="user_firstName" id="first_name" class="form-control col-6 col-lg-3"
           value="<c:out value="${user.firstName}">${param.user_firstName}</c:out>" required min="1" max="30">

    <label class="col-6 col-lg-3 col-form-label ml-auto text-lg-right" for="email">Email :</label>
    <input type="email" name="user_email" id="email" class="form-control col-6 col-lg-3"
           value="<c:out value="${user.email}">${param.user_email}</c:out>" required max="30">
</div>
<div class="form-group row">

    <label class="col-6 col-lg-3 col-form-label" for="number">Teléphone :</label>
    <input type="tel" name="user_phoneNumber" id="number" class="form-control col-6 col-lg-3"
           value="<c:out value="${user.phoneNumber}">${param.user_phoneNumber}</c:out>" pattern="^0[1-68][0-9]{8}$">

    <label class="col-6 col-lg-3 col-form-label ml-auto text-lg-right" for="street">Rue :</label>
    <input type="text" name="user_street" id="street" class="form-control col-6 col-lg-3"
           value="<c:out value="${user.street}">${param.user_street}</c:out>" required min="1" max="30">
</div>
<div class="form-group row">
    <label class="col-6 col-lg-3 col-form-label" for="postal_code">Code postal :</label>
    <input type="number" name="user_postalCode" id="postal_code" class="form-control col-6 col-lg-3" pattern="[0-9]{5}"
           value="<c:out value="${user.postalCode}">${param.user_postalCode}</c:out>" required>

    <label class="col-6 col-lg-3 col-form-label ml-auto text-lg-right" for="city">Ville :</label>
    <input type="text" name="user_city" id="city" class="form-control col-6 col-lg-3"
           value="<c:out value="${user.city}">${param.user_city}</c:out>" required min="1" max="30">
</div>
<c:if test="${not empty user.id}">
    <div class="form-group row">
        <label class="col-6 col-lg-3 col-form-label" for="actual_password">Mot de passe actuel :</label>
        <input type="password" name="user_actualPassword" id="actual_password" class="form-control col-6 col-lg-3"
               required>
    </div>
    <div class="form-group row">
        <label class="col-6 col-lg-3 col-form-label" for="new_password">Nouveau mot de passe :</label>
        <input type="password" name="user_password" id="new_password" class="form-control col-6 col-lg-3">

        <label class="col-6 col-lg-3 col-form-label ml-auto text-lg-right" for="confirm_password">Confirmation :</label>
        <input type="password" name="user_confirmPassword" id="confirm_password" class="form-control col-6 col-lg-3">
    </div>
</c:if>
<c:if test="${empty user.id}">
    <div class="form-group row">
        <label class="col-6 col-lg-3 col-form-label" for="password">Mot de passe :</label>
        <input type="password" name="user_password" id="password" class="form-control col-6 col-lg-3" required>

        <label class="col-6 col-lg-3 col-form-label ml-auto text-lg-right" for="confirm_password">Confirmation :</label>
        <input type="password" name="user_confirmPassword" id="confirm_password" class="form-control col-6 col-lg-3"
               required>
    </div>

</c:if>

<c:if test="${empty user.id}">
    <div class="row m-4">
        <div class="col-6 text-right">
            <input type="submit" value="Créer">
        </div>
        <div class="col-6 ">
            <button class="btn btn-primary"><a href="${pageContext.request.contextPath}/">Annuler</a></button>
        </div>
    </div>


</c:if>

<c:if test="${not empty user.id}">
    <p>Credit <strong><c:out value="${user.credit}"/></strong></p>
    <div class="row m-4">
        <div class="col-6 text-right">
            <input type="submit" value="Enregistrer">

        </div>
        <div class="col-6">
            <input type="submit" value="Supprimer mon compte" name="deactivate ">
        </div>

    </div>


</c:if>
