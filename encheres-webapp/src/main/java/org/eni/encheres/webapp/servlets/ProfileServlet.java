package org.eni.encheres.webapp.servlets;

import org.eni.encheres.buisiness.user.UserManager;
import org.eni.encheres.model.User;
import org.eni.encheres.persistence.PersistenceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User sessionUser = (User) request.getSession().getAttribute("user");
        User user = null;
        String paramUserId = request.getParameter("id");
        int userId;
        UserManager userManager = new UserManager();
        if (paramUserId != null) {
            try {
                userId = Integer.parseInt(paramUserId);
                if (userId == sessionUser.getId()) {
                    request.setAttribute("profil", sessionUser);
                    response.sendRedirect(request.getContextPath() + "/profile");
                } else if (sessionUser.getId() != null && sessionUser.getId() != userId) {
                    user = userManager.getUserById(userId);
                    request.setAttribute("profil", user);
                    getServletContext().getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
                }
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("profil", sessionUser);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
        }
    }
}
