package org.eni.encheres.persistence;

import org.eni.encheres.persistence.jdbc.PollConnection;
import org.eni.encheres.persistence.jdbc.table.*;

import java.sql.Connection;

public class Factory {

    public static UserDao getUserDao() {
        return new UserTable();
    }

    public static ProductDao getProductDao() throws PersistenceException {
        return new ProductTable();
    }

    public static CategoryTable getCategoryDao() throws PersistenceException {
        return new CategoryTable();
    }

    public static WithdrawalDao getWithdrawalTable() throws PersistenceException {
        return new WithdrawalTable();
    }

    public static AuctionDao getAuctionDao() {
        return new AuctionTable();
    }

    public static Connection getConnection() throws PersistenceException {
        return PollConnection.getConnection();
    }
}