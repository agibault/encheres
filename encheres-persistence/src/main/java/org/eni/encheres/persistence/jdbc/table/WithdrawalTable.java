package org.eni.encheres.persistence.jdbc.table;

import org.eni.encheres.model.Product;
import org.eni.encheres.model.Withdrawal;
import org.eni.encheres.persistence.Factory;
import org.eni.encheres.persistence.WithdrawalDao;
import org.eni.encheres.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WithdrawalTable implements WithdrawalDao {

    private static final String QUERY_ADD_WITHDRAWAL = "INSERT INTO retraits "
            + "(no_article,rue,code_postal,ville)"
            + "VALUES(?,?,?,?)";
    private static final String QUERY_GET_WITHDRAWAL_BY_PRODUCT = "SELECT * FROM retraits WHERE no_article = ?";

    public void addWithdrawal(Product product) throws PersistenceException {
        Withdrawal withdrawal = product.getWithdrawal();
        try (Connection connection = Factory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY_ADD_WITHDRAWAL)) {
                ps.setInt(1, product.getId());
                ps.setString(2, withdrawal.getCity());
                ps.setString(3, withdrawal.getPostalCode());
                ps.setString(4, withdrawal.getStreet());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC");
        }
    }

    public Withdrawal getWithdrawalByProduct(Product product) throws PersistenceException {
        Withdrawal withdrawal = new Withdrawal();
        try (Connection connection = Factory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(QUERY_GET_WITHDRAWAL_BY_PRODUCT);
            ps.setInt(1, product.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    withdrawal.setStreet(rs.getString(1));
                    withdrawal.setPostalCode(rs.getString(2));
                    withdrawal.setCity(rs.getString(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC");
        }
        return withdrawal;
    }
}
