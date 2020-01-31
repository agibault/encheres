package org.eni.encheres.webapp.servlets;

import org.eni.encheres.buisiness.user.UserManager;
import org.eni.encheres.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeactivateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManager userManager = new UserManager();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            userManager.deacticateAccount(user);
            session.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath());
    }
}
