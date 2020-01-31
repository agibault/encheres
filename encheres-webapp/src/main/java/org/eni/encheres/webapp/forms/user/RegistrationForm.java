package org.eni.encheres.webapp.forms.user;

import org.eni.encheres.buisiness.exceptions.RegistrationException;
import org.eni.encheres.model.User;


import javax.servlet.http.HttpServletRequest;

public class RegistrationForm extends UserForm {


    public RegistrationForm(HttpServletRequest request, User user) {
        super(request, user);
        hydrate();
        user.setPassword(getParam("user_password"));
    }

    public void validate() {
        try {
            validateBean(user);
            validatePasswordConfirm();
            validateUniqueEmail();
            validateUniqueUsername();
        } catch (RegistrationException e) {
            e.printStackTrace();
        }
    }
}