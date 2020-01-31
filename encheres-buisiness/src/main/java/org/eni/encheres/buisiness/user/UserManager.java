package org.eni.encheres.buisiness.user;

import org.eni.encheres.buisiness.exceptions.RegistrationException;
import org.eni.encheres.model.User;
import org.eni.encheres.persistence.Factory;
import org.eni.encheres.persistence.UserDao;
import org.eni.encheres.persistence.PersistenceException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserManager {

    private UserDao userDao;

    public UserManager() {
        userDao = Factory.getUserDao();
    }

    public User getUserByUsername(String username, String password) throws Exception {
        return userDao.getUserByUsername(username);
    }

    public User getUserByEmail(String email, String password) throws Exception {
        return userDao.getUserByEmail(email);
    }

    public void addUser(User user) throws RegistrationException {
      Registration registration = new Registration();
            registration.addUser(user);
    }

    public User getUserByLogin(String login, String password) throws  AuthenticationException {
        Authentication authentication = new Authentication();
        return authentication.retrieveUser(login, password);
    }
    public User getUserById(int id) throws  PersistenceException {
        return userDao.getUserById(id);
    }

    public void updateUser(User user) throws  PersistenceException {
        user.setPassword(user.getPassword());
        userDao.updateUser(user);
    }

    public void deacticateAccount(User user) throws PersistenceException {
        if (user != null) {
            try {
                userDao.deactivateAccount(user.getId());
            }catch (PersistenceException e){

            }

        }
        ;
    }


}
