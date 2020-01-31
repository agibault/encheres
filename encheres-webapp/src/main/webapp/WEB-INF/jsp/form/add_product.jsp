<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group row">

    <label class="col-6 col-lg-3 col-form-label" for="name">Article :</label>
    <input type="text" name="product_name" id="name" class="form-control col-6 col-lg-3"
           value="<c:out value="${param.product.name}"/>" required min="1" max="30">

</div>

<div class="form-group row">
    <label class="col-6 col-lg-3 col-form-label" for="description">Description :</label>
    <textarea name="product_description" id="description" cols="30" rows="10" class="form-control col-6 col-lg-3"><c:out
            value="${param.product.description}"/></textarea>

</div>
<div class="form-group row">
    <label class="col-6 col-lg-3 col-form-label" for="price">Mise a prix :</label>
    <input type="number" name="product_initPrice" id="price" class="form-control col-6 col-lg-3"
           value="<c:out value="${param.product.initPrice}"/>" required>
</div>

<div class="form-group row">
    <label class="col-6 col-lg-3 col-form-label" for="start_date">Debut de l'enchere :</label>
    <input type="date" name="product_startBid" id="start_date" class="form-control col-6 col-lg-3"
           value="<c:out value="${param.product_startBid}"/>" required>
</div>

<div class="form-group row">
    <label class="col-6 col-lg-3 col-form-label" for="end_date">Fin de l'enchere :</label>
    <input type="date" name="product_endBid" id="end_date" class="form-control col-6 col-lg-3"
           value="<c:out value="${param.product_endBid}"/>" required>
</div>

<fieldset>
    <legend>Retrait</legend>
    <div class="form-group row">
        <label class="col-6 col-lg-3 col-form-label" for="withdrawal_street">Rue :</label>
        <input type="text" name="withdrawal_street" id="withdrawal_street" class="form-control col-6 col-lg-3"
               value="<c:out value="${param.withdrawal_street}">${user.street}</c:out>" required>
    </div>
    <div class="form-group row">
        <label class="col-6 col-lg-3 col-form-label" for="withdrawal_postalCode">Code Postal :</label>
        <input type="text" name="withdrawal_postalCode" id="withdrawal_postalCode" class="form-control col-6 col-lg-3"
               value="<c:out value="${param.withdrawal_postalCode}">${user.postalCode}</c:out>" required>
    </div>
    <div class="form-group row">
        <label class="col-6 col-lg-3 col-form-label" for="withdrawal_city">Ville :</label>
        <input type="text" name="withdrawal_city" id="withdrawal_city" class="form-control col-6 col-lg-3"
               value="<c:out value="${param.withdrawal_city}">${user.city}</c:out>" required>
    </div>

</fieldset>

<input type="submit" value="Enregister">
