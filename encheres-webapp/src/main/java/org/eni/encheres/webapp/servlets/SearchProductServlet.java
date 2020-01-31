package org.eni.encheres.webapp.servlets;

import org.eni.encheres.buisiness.Shop;
import org.eni.encheres.model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Shop shop = new Shop();
        List<Product> products;
        String tag = request.getParameter("tag");

        if (tag != null){
            try {
                products = shop.getProductsByTag(tag);
                request.setAttribute("products", products);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
