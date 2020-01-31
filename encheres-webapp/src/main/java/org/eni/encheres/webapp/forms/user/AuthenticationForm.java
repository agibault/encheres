package org.eni.encheres.webapp.forms.user;

import org.eni.encheres.webapp.forms.Form;
import javax.servlet.http.HttpServletRequest;

public class AuthenticationForm extends Form {

    private String login;
    private String password;

    public AuthenticationForm(HttpServletRequest request) {
        super(request);
        hydrate();
    }

    private void hydrate() {
        login = getParam("user_username");
        password = getParam("user_password");
    }

    public void validate() {
        if (isBlank(login) || isBlank(password)) {
            addError("not_blank", "un login doit être renseigné. ");
        }
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
