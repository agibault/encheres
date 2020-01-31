package org.eni.encheres.webapp.servlets;

import org.eni.encheres.buisiness.Shop;
import org.eni.encheres.model.Product;
import org.eni.encheres.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        Product product;
        String paramProductId = null;
        int productId;
        int productPrice;
        user = (User) request.getSession().getAttribute("user");
        paramProductId = request.getParameter("product_id");
        String paramPrice = request.getParameter("auction_price");


        try {
            if (paramProductId != null) {
                productId = Integer.parseInt(paramProductId);
                productPrice = Integer.parseInt(paramPrice);
                Shop shop = new Shop();
                product = shop.getProduct(productId);
                shop.makeABid(product, user, productPrice);
            }
        } catch (Exception e) {
            request.setAttribute("errors_bid", 'v');
            request.setAttribute("id", paramProductId);
            e.printStackTrace();
            response.sendRedirect(request.getContextPath()+"/auction?id="+paramProductId);
            return;
        }
        request.setAttribute("bid_succes", paramProductId);
        response.sendRedirect(request.getContextPath());
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Shop shop = new Shop();
        Product product = null;
        Integer productId = null;
        String paramIdProduct = request.getParameter("id");

        try {

            if (paramIdProduct != null) {
                productId = Integer.parseInt(paramIdProduct);
                product = shop.getProduct(productId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (product != null && product.getId() != null) {
            request.setAttribute("product", product);
        } else {
            request.setAttribute("error", "erreur");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/auction_details.jsp").forward(request, response);
    }
}
