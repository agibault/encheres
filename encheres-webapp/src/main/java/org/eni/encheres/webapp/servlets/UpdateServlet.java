package org.eni.encheres.webapp.servlets;

import org.eni.encheres.buisiness.user.UserManager;
import org.eni.encheres.webapp.forms.user.UpdateForm;
import org.eni.encheres.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdateForm form = null;
        UserManager userManager;
        String isDeactivate  = request.getParameter("deactivate");
        if (isDeactivate != null){
            response.sendRedirect(request.getContextPath() + "/deactivate");
            return;
        }
        try {
            User user = (User) request.getSession().getAttribute("user");
            form = new UpdateForm(request, user);
            form.validate();
            userManager = new UserManager();
            if (form.isValid()) {
                userManager.updateUser(user);
                response.sendRedirect(request.getContextPath());
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("errors", form.getErrors());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/update_profile.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("us","ok");
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/update_profile.jsp").forward(request, response);
    }
}
