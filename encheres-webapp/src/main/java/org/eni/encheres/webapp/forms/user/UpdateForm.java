package org.eni.encheres.webapp.forms.user;


import org.eni.encheres.buisiness.exceptions.RegistrationException;
import org.eni.encheres.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;

public class UpdateForm extends UserForm {


    private String oldUsername;
    private String oldEmail;

    public UpdateForm(HttpServletRequest request, User user) {
        super(request, user);
        oldUsername = user.getUsername();
        oldEmail = user.getEmail();
        hydrate();
        setPassword();
    }


    @Override
    public void validate() {
        validateBean(user);
        validatePasswordConfirm();
        validatePassword();
        if (isNewEmail()) {
            try {
                validateUniqueEmail();
            } catch (RegistrationException e) {
                e.printStackTrace();
            }
        }
        if (isNewUsername()) {
            try {
                validateUniqueUsername();
            } catch (RegistrationException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isNewEmail() {
        if (oldEmail != null && user.getEmail() != null) {
            return !oldEmail.equals(user.getEmail());
        }
        return true;
    }

    private boolean isNewUsername() {
        if (oldUsername != null && user.getUsername() != null) {
            return !oldUsername.equals(user.getUsername());
        }
        return true;
    }

    private void setPassword() {
        String newPassword = getParam("user_password");
        PasswordEncoder encoder = org.eni.encheres.buisiness.Factory.getPasswordEncoder();
        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(encoder.encode(newPassword));
        }
    }

}
