package org.eni.encheres.webapp.servlets;

import org.eni.encheres.buisiness.ManagerFactory;
import org.eni.encheres.buisiness.user.AuthenticationException;
import org.eni.encheres.buisiness.user.UserManager;
import org.eni.encheres.model.User;
import org.eni.encheres.webapp.forms.user.AuthenticationForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManager userManager = new UserManager();
        AuthenticationForm form = new AuthenticationForm(request);
        User user = null;
        form.validate();
        try {
            if (form.isValid()) {
                user = userManager.getUserByLogin(form.getLogin(), form.getPassword());
            } else {
                request.setAttribute("form_errors", form.getErrors());
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath());
        } else {
            request.setAttribute("errors", "Identifiants Invalide");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
}