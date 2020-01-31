package org.eni.encheres.webapp.servlets;

import org.eni.encheres.buisiness.Shop;
import org.eni.encheres.model.Product;
import org.eni.encheres.model.User;
import org.eni.encheres.webapp.forms.SellProductForm;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SellProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        SellProductForm addProductForm = new SellProductForm(request, product);
        User user = (User) request.getSession().getAttribute("user");
        try {
            addProductForm.hydrate(product);
            product.setSeller(user);
            addProductForm.validate();

            if (addProductForm.isValid()) {
                Shop shop = new Shop();
                shop.addProduct(product);
                request.setAttribute("errros", "erreur");
                response.sendRedirect(request.getContextPath());
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("errors", "erreur");
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/add_product.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/add_product.jsp").forward(request, response);
    }
}
