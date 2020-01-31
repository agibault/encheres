package org.eni.encheres.webapp.servlets;

import org.eni.encheres.buisiness.Shop;
import org.eni.encheres.model.Product;
import org.eni.encheres.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products;
        products = getProdutsByTag(request);
        request.setAttribute("products", products);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Product> products = null;
        Shop shop = new Shop();
        User user = (User) request.getAttribute("user");

        if (user != null && user.getId() != null) {
            request.setAttribute("user", user);
        }
        try {
            products = shop.getAllProduct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("products", products);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private List<Product> getProdutsByTag(HttpServletRequest req) {
        List<Product> products = null;
        Shop shop = new Shop();
        String what = req.getParameter("auction_search");
        User user = (User) req.getSession().getAttribute("user");

        try {
            switch (what) {
                case "my_open":
                    products = shop.getOpenAuctionByUser(user);
                    break;
                case "wind":
                    products = shop.getWindAuctionByUser(user);
                    break;
                case "my_products":
                    products = shop.getProductsByUser(user);
                    break;
                default:
                    products = shop.getAllProduct();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
}
