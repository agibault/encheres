package org.eni.encheres.persistence;


import org.eni.encheres.model.User;

import java.sql.SQLException;

public interface UserDao {

    User getUserByUsername(String username) throws PersistenceException;

    User getUserByEmail(String email) throws PersistenceException;

    User getUserById(int id) throws PersistenceException;

    void addUser(User user) throws PersistenceException;

    boolean isUniqueEmail(String email) throws PersistenceException;

    boolean isUniqueUsername(String email) throws PersistenceException;

    void updateUser(User user) throws PersistenceException;

    void updateCredit(int userId, int newCredit) throws PersistenceException;

    String getUserPassword(int userId) throws PersistenceException;

    void deactivateAccount(int userId) throws PersistenceException;


}
