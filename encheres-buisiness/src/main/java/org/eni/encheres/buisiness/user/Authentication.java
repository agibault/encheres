package org.eni.encheres.buisiness.user;

import org.eni.encheres.model.User;
import org.eni.encheres.persistence.Factory;
import org.eni.encheres.persistence.UserDao;

public class Authentication {

    private User user = new User();

    public User retrieveUser(String login, String plainPassword) throws AuthenticationException {

        try {
            tryWithEmailOrUsername(login);
            if (user.getId() != null) {
                if (passwordMatches(plainPassword, user.getPassword()) && user.isValid()) {
                    return user;
                }
            }
        } catch (Exception e) {
            throw new AuthenticationException("Authentication - empty user");
        }
        return null;
    }

    private void tryWithEmailOrUsername(String field) throws AuthenticationException {
        User loadUser;
        try {
            UserDao userDao = Factory.getUserDao();
            loadUser = userDao.getUserByEmail(field);

            if (loadUser.getId() == null) {
                loadUser = userDao.getUserByUsername(field);
            }
            if (loadUser != null) {
                user = loadUser;
            }
        } catch (Exception e) {
            throw new AuthenticationException("f");
        }
    }

    private boolean passwordMatches(String raw, String hash) throws AuthenticationException {
        if (raw == null || hash == null) {
           throw new AuthenticationException("Authentication - password cannot be empty");
        }
        return org.eni.encheres.buisiness.Factory.getPasswordEncoder().matches(raw, hash);
    }



}
