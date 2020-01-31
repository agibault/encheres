<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form id="search" action="${pageContext.request.contextPath}/" method="post" class="m-3">

    <div>
        <label for="auction_search">Enchere Ouverte</label>
        <input type="radio" id="auction_search" name="auction_search" value="open"
               onclick="document.getElementById('search').submit();">
    </div>
    <div>
        <label for="auction_my_bid">Mes encheres en cours</label>
        <input type="radio" id="auction_my_bid" name="auction_search" value="my_open"
               onclick="document.getElementById('search').submit();">
    </div>
    <div>
        <label for="auction_wind">Mes encheres remportes</label>
        <input type="radio" id="auction_wind" name="auction_search" value="wind"
               onclick="document.getElementById('search').submit();">
    </div>
    <div>
        <label for="products">Mes ventes en cours</label>
        <input type="radio" id="products" name="auction_search" value="my_products"
               onclick="document.getElementById('search').submit();">
    </div>

</form>