package org.eni.encheres.persistence.jdbc.table;

import org.eni.encheres.model.Auction;
import org.eni.encheres.model.Product;
import org.eni.encheres.persistence.Factory;
import org.eni.encheres.persistence.AuctionDao;
import org.eni.encheres.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuctionTable implements AuctionDao {


    private static final String QUERY_ADD_BID = "INSERT INTO encheres"
            + "(no_utilisateur,no_article,date_enchere,montant_enchere)"
            + "VALUES(?,?,?,?)";

    private static final String QUERY_SELECT_BID = "SELECT * FROM encheres WHERE no_article = ?";
    private static final String QUERY_UPDATE_CREDIT = "UPDATE utilisateurs SET credit = ? WHERE no_utilisateur = ?";
    private static final String QUERY_SELECT_BID_PRICE = "SELECT montant_enchere FROM encheres WHERE no_article = ?";
    private static final String QUERY_SELECT_USER_BY_BID = "SELECT no_utilisateur FROM encheres WHERE no_article = ?";
    private static final String QUERY_UPDATE_BID = "UPDATE encheres SET no_utilisateur = ?, date_enchere = ?,montant_enchere= ? WHERE no_article = ?";

    @Override
    public void makeBid(Auction auction) throws PersistenceException {
        try (Connection connection = Factory.getConnection()) {
            try {
                connection.setAutoCommit(false);
                addBid(connection, auction);
                updateCredit(connection, auction.getBuyer().getId(), auction.getNewCreditNewBuyer());
                connection.commit();
            } catch (PersistenceException | SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC");
        }
    }

    @Override
    public void makeBidOnUpdate(Auction auction) throws PersistenceException {
        Product product = auction.getProduct();
        try (Connection connection = Factory.getConnection()) {
            try {
                connection.setAutoCommit(false);
                updateBidCommit(connection, auction);
                updateCredit(connection, product.getBuyer().getId(), auction.getNewCreditLastBuyer());
                updateCredit(connection, auction.getBuyer().getId(), auction.getNewCreditNewBuyer());
                connection.commit();
            } catch (PersistenceException | SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC");
        }
    }

    public void updateCredit(Connection connection, int userId, int newCredit) throws PersistenceException {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_UPDATE_CREDIT)) {
            ps.setInt(1, newCredit);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC");
        }
    }

    public void updateBidCommit(Connection connection, Auction auction) throws PersistenceException {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_UPDATE_BID)) {
            ps.setInt(1, auction.getBuyer().getId());
            ps.setDate(2, Date.valueOf(auction.getDate()));
            ps.setInt(3, auction.getNewPrice());
            ps.setInt(4, auction.getProduct().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC");
        }

    }

    @Override
    public void updateBid(Auction auction) throws PersistenceException {
        try (Connection connection = Factory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY_UPDATE_BID)) {
                ps.setInt(1, auction.getBuyer().getId());
                ps.setDate(2, Date.valueOf(auction.getDate()));
                ps.setInt(3, auction.getNewPrice());
                ps.setInt(4, auction.getProduct().getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC");
        }

    }

    private void addBid(Connection connection, Auction auction) throws PersistenceException {

        try (PreparedStatement ps = connection.prepareStatement(QUERY_ADD_BID)) {
            ps.setInt(1, auction.getBuyer().getId());
            ps.setInt(2, auction.getProduct().getId());
            ps.setDate(3, Date.valueOf(auction.getDate()));
            ps.setInt(4, auction.getNewPrice());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC");
        }
    }

    @Override
    public Integer getBidPrice(int id) throws PersistenceException {
        Integer price = null;
        try (Connection connection = Factory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(QUERY_SELECT_BID_PRICE);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    price = rs.getInt(1);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC");
        }
        return price;
    }

    @Override
    public Integer getBuyerId(int productId) throws PersistenceException {
        Integer buyerId = null;
        try (Connection connection = Factory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(QUERY_SELECT_USER_BY_BID);
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    buyerId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC");
        }
        return buyerId;
    }
}
