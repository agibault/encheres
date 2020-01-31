package org.eni.encheres.buisiness.user;

import org.eni.encheres.buisiness.Factory;
import org.eni.encheres.buisiness.exceptions.RegistrationException;
import org.eni.encheres.model.User;
import org.eni.encheres.persistence.PersistenceException;
import org.eni.encheres.persistence.UserDao;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


public class Registration {

    private User user;
    private UserDao userDao;

    public Registration() {
        userDao = org.eni.encheres.persistence.Factory.getUserDao();
    }


    public void addUser(User user) throws RegistrationException {
        setUser(user);
        validate();
        encodePassword(user.getPassword());
        try {
            userDao.addUser(user);
        } catch (PersistenceException e) {
            throw new RegistrationException("unable to add user");
        }
    }
    private void encodePassword(String plainPassword) {
        PasswordEncoder passwordEncoder = Factory.getPasswordEncoder();
        if (plainPassword != null) {
            user.setPassword(passwordEncoder.encode(plainPassword));
        }
    }

    private void validate() throws RegistrationException {
        validateUniqueUsername();
        validateUniqueEmail();
    }

    private void validateUniqueEmail() throws RegistrationException {
        try {
            boolean isUniqueEmail = userDao.isUniqueEmail(user.getEmail());
            if (!isUniqueEmail) {
                throw new RegistrationException("email already used");
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new RegistrationException("unable to access the database");
        }

    }

    public boolean isUniqueUsername(String username) throws RegistrationException {
        try {
            userDao = org.eni.encheres.persistence.Factory.getUserDao();
            return  userDao.isUniqueUsername(username);
        } catch (PersistenceException e) {
            throw new RegistrationException("unable to access the database");
        }
    }

    public boolean isUniqueEmail(String email) throws RegistrationException {
        try {
            userDao = org.eni.encheres.persistence.Factory.getUserDao();
            return  userDao.isUniqueEmail(email);
        } catch (PersistenceException e) {
            throw new RegistrationException("unable to access the database");
        }
    }

    private void validateUniqueUsername() throws RegistrationException {
        try {
            boolean isUniqueUsername = userDao.isUniqueUsername(user.getUsername());
            if (!isUniqueUsername) {
                throw new RegistrationException("username already used");
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new RegistrationException("unable to access the database");
        }
    }

    private void setUser(User user) throws RegistrationException {
        if (user != null && isValidUser(user)) {
            this.user = user;
        } else {
            throw new RegistrationException("user not valid");
        }
    }

    private boolean isValidUser(User user) {
        Validator validator = Factory.getValidator();
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        return constraintViolations.isEmpty();
    }
}
