package org.eni.encheres.persistence;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcConnection {

    public Connection getConnetion() {
        try (java.sql.Connection connection = Factory.getConnection()) {
            return connection;
        } catch (PersistenceException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
