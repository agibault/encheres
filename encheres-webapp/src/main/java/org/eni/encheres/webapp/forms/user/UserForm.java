package org.eni.encheres.webapp.forms.user;

import org.eni.encheres.buisiness.exceptions.RegistrationException;
import org.eni.encheres.buisiness.exceptions.UpdateException;
import org.eni.encheres.buisiness.user.Registration;
import org.eni.encheres.buisiness.user.Update;
import org.eni.encheres.model.User;
import org.eni.encheres.webapp.forms.Form;
import javax.servlet.http.HttpServletRequest;

abstract class UserForm  extends Form {

    User user;
    Registration registration;
    Update update;

    public UserForm(HttpServletRequest request, User user) {
        super(request);
        update = new Update();
        registration = new Registration();
        this.user = user;
    }

    protected void validatePasswordConfirm() {
        String password = getParam("user_password");
        String confirmPassword = getParam("user_confirmPassword");
        if (!passwordAreEquals(password, confirmPassword)) {
            addError("password_confirm", "Les mots de passe de corespondent pas");
        }
    }

    protected void validateUniqueEmail() throws RegistrationException {
        String email = user.getEmail();
        if (email != null) {
            if (!registration.isUniqueEmail(email)) {
                addError("email", "email deja existant");
            }
        }

    }

    protected void validateUniqueUsername() throws RegistrationException {
        String username = user.getUsername();
        if (username != null) {
            if (!registration.isUniqueUsername(username)) {
                addError("username", "pseudo deja existant");
            }
        }
    }

    protected void validatePassword() {

        String suggestPassword = getParam("user_actualPassword");
        try {
            if (!update.isGoodPassword(user, suggestPassword)) {
                addError("password", "wrong password");
            }
        } catch (UpdateException e) {
            e.printStackTrace();
        }
    }

    private boolean passwordAreEquals(String password, String passwordToCompare) {
        if (password != null && passwordToCompare != null) {
            return password.equals(passwordToCompare);
        }
        return false;
    }
    public void hydrate() {
        user.setUsername(getParam("user_username"));
        user.setFirstName(getParam("user_firstName"));
        user.setLastName(getParam("user_lastName"));
        user.setEmail(getParam("user_email"));
        user.setPhoneNumber(getParam("user_phoneNumber"));
        user.setStreet(getParam("user_street"));
        user.setPostalCode(getParam("user_postalCode"));
        user.setCity(getParam("user_city"));

    }
}
