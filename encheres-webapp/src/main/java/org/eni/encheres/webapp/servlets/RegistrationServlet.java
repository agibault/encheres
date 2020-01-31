package org.eni.encheres.webapp.servlets;

import com.sun.xml.internal.bind.v2.TODO;
import org.eni.encheres.buisiness.user.UserManager;
import org.eni.encheres.webapp.forms.user.RegistrationForm;
import org.eni.encheres.buisiness.user.Registration;
import org.eni.encheres.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        UserManager userManager = new UserManager();
        RegistrationForm userForm;
        HttpSession session;
        userForm = new RegistrationForm(request, user);
        userForm.validate();
        try {

            if (userForm.isValid()) {
                userManager.addUser(user);
                session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath());
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("errors", userForm.getErrors());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
    }
}
