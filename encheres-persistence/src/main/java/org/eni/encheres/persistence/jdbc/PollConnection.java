package org.eni.encheres.persistence.jdbc;

import org.eni.encheres.persistence.PersistenceException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

abstract public class PollConnection {

    private static DataSource dataSource;

    static {
        Context context;
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException("Impossible d'accéder à la base de données");
        }
    }

    public static Connection getConnection() throws PersistenceException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("Impossible d'accéder à la base de données");
        }
    }
}