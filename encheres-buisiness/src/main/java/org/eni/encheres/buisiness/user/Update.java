package org.eni.encheres.buisiness.user;

import org.eni.encheres.buisiness.Factory;
import org.eni.encheres.buisiness.exceptions.UpdateException;
import org.eni.encheres.model.User;
import org.eni.encheres.persistence.PersistenceException;
import org.eni.encheres.persistence.UserDao;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

public class Update {

    private User oldUser;
    private User newUser;
    private UserDao userDao;
    private Map<String, String> errors = new HashMap<>();

    public Update() {
        userDao = org.eni.encheres.persistence.Factory.getUserDao();
    }

    public void updateUser(User user) {
        this.newUser = user;
        try {
            validate();
            if (errors.isEmpty() && isValidUser()) {
                userDao.updateUser(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void encodePassword(String plainPassword) {
        PasswordEncoder passwordEncoder = Factory.getPasswordEncoder();
        if (plainPassword != null) {
            newUser.setPassword(passwordEncoder.encode(plainPassword));
        }
    }

    private boolean isValidUser() {
        Validator validator = Factory.getValidator();
        return validator.validate(newUser).isEmpty();
    }

    protected void validateUniqueEmail() throws Exception {
        boolean isUniqueEmail = userDao.isUniqueEmail(newUser.getEmail());

        if (!isUniqueEmail) {
            errors.put("email", "Email deja existant");
        }
    }

    protected void validateUniqueUsername() throws Exception {

        if (newUser == null) {
            throw new Exception();
        }
        boolean isUniqueUsername = userDao.isUniqueUsername(newUser.getUsername());
        if (!isUniqueUsername) {
            errors.put("username", "Pseudo deja existant");
        }
    }

    private void validate() throws Exception {
        validateOldPassword();
        if (isNewUsername()) {
            validateUniqueUsername();
        }
        if (isNewEmail()) {
            validateUniqueEmail();
        }
    }


    private void validateOldPassword() throws Exception {
        PasswordEncoder encoder = Factory.getPasswordEncoder();
        if (!encoder.matches(newUser.getPassword(), oldUser.getPassword())) {
            errors.put("username", "Pseudo deja existant");
        }
    }

    private void isNewPassword() throws Exception {
        PasswordEncoder encoder = Factory.getPasswordEncoder();
        if (!encoder.matches(newUser.getPassword(), oldUser.getPassword())) {
            errors.put("username", "Pseudo deja existant");
        }
    }

    private boolean isNewEmail() {
        return !oldUser.getEmail().equals(newUser.getEmail());
    }

    private boolean isNewUsername() {
        return !oldUser.getUsername().equals(newUser.getUsername());
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public boolean isValid() throws Exception {
        return errors.isEmpty();
    }


    public boolean isGoodPassword(User user, String password) throws UpdateException {
        PasswordEncoder passwordEncoder = Factory.getPasswordEncoder();
        boolean result = false;
        try {
            if (user.getId() != null){
                String hashPassword = userDao.getUserPassword(user.getId());
                if (hashPassword != null && password != null) {
                    result = passwordEncoder.matches(password, hashPassword);
                }
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return result;
    }


}
