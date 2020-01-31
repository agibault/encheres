package org.eni.encheres.persistence.jdbc.table;

import org.eni.encheres.model.Category;
import org.eni.encheres.model.Product;
import org.eni.encheres.model.User;
import org.eni.encheres.model.Withdrawal;
import org.eni.encheres.persistence.Factory;
import org.eni.encheres.persistence.ProductDao;
import org.eni.encheres.persistence.PersistenceException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductTable implements ProductDao {
    private static final String QUERY_SELECT_ALL_SOLD = "SELECT * FROM articles_vendus WHERE date_debut_encheres <= GETDATE() AND date_fin_encheres >= GETDATE()";
    private static final String QUERY_PRODUCTS_OPEN_BY_USER = "SELECT * FROM articles_vendus  WHERE no_utilisateur = ?";
    private static final String QUERY_WIND_AUCTION_BY_USER =
            "SELECT * FROM articles_vendus  \n" +
            "INNER JOIN encheres ON articles_vendus.no_article = encheres.no_article \n" +
            "WHERE  GETDATE() >  date_fin_encheres \n" +
            "AND encheres.no_utilisateur = ?";
    private static final String QUERY_OPEN_AUCTION_BY_USER = "SELECT * FROM ARTICLES_VENDUS  INNER JOIN encheres  ON   encheres.no_article = ARTICLES_VENDUS.no_article WHERE encheres.no_utilisateur = ? AND date_fin_encheres >= GETDATE()";
    private static final String QUERY_SELECT_PRODUCT = "SELECT * FROM articles_vendus WHERE no_article = ?";
    private static final String QUERY_SEARCH_BY_TAG = "SELECT * FROM articles_vendus WHERE nom_article like ?";
    private static final String QUERY_ADD_PRODUCT = "INSERT INTO articles_vendus"
            + "(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie)"
            + "VALUES(?,?,?,?,?,?,?)";

    @Override
    public void addProduct(Product product) throws PersistenceException {
        try (Connection connection = Factory.getConnection()) {
            try {
                PreparedStatement ps = connection.prepareStatement(QUERY_ADD_PRODUCT, PreparedStatement.RETURN_GENERATED_KEYS);
                connection.setAutoCommit(false);
                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
                ps.setDate(3, Date.valueOf(product.getDateStartBid()));
                ps.setDate(4, Date.valueOf(product.getDateEndBid()));
                ps.setInt(5, product.getStartingPrice());
                ps.setInt(6, product.getSeller().getId());
                // TODO: 18/01/2020 add category
                ps.setInt(7, 1);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        product.setId(rs.getInt(1));
                    }
                    addWithdrawal(product);
                    connection.commit();
                }
            } catch (PersistenceException | SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC");
        }
    }

    @Override
    public List<Product> getAllProduct() throws PersistenceException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = Factory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(QUERY_SELECT_ALL_SOLD);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    setDataProduct(rs, product);
                    products.add(product);
                }
            }
        } catch (SQLException | PersistenceException e) {
            throw new PersistenceException("JDBC");
        }
        return products;
    }

    @Override
    public List<Product> getProductsByTag(String tag) throws PersistenceException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = Factory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(QUERY_SEARCH_BY_TAG);
            String t = "%" + tag + "%";
            ps.setString(1,t);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    setDataProduct(rs, product);
                    products.add(product);
                }
            }
        } catch (SQLException | PersistenceException e) {
            throw new PersistenceException("JDBC");
        }
        return products;
    }

    @Override
    public List<Product> getOpenAuctionByUser(User user) throws PersistenceException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = Factory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(QUERY_OPEN_AUCTION_BY_USER);
            ps.setInt(1, user.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    setDataProduct(rs, product);
                    products.add(product);
                }
            }
        } catch (SQLException | PersistenceException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC");
        }
        return products;
    }

    private void setDataProduct(ResultSet rs, Product product) throws SQLException, PersistenceException {
        product.setId(rs.getInt(1));
        product.setName(rs.getString(2));
        product.setDescription(rs.getString(3));
        product.setDateStartBid(rs.getDate(4).toLocalDate());
        product.setDateEndBid(rs.getDate(5).toLocalDate());
        product.setStartingPrice(rs.getInt(6));
        product.setSellingPrice(rs.getInt(7));
        product.setSeller(retrieveUser(rs.getInt(8)));
        product.setCategory(retrieveCategory(rs.getInt(9)));
        product.setWithdrawal(retrieveWithdrawal(product));
        product.setBuyer(retrieveBuyerWiThBid(product.getId()));
        product.setActualPrice(retrieveActualPrice(product));
    }

    @Override
    public Product getProduct(int id) throws PersistenceException {
        Product product = new Product();
        try (Connection connection = Factory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(QUERY_SELECT_PRODUCT);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    setDataProduct(rs, product);
                }
            }
        } catch (SQLException | PersistenceException e) {
            throw new PersistenceException("JDBC");
        }
        return product;
    }

    @Override
    public List<Product> getWindAuctionByUser(User user) throws PersistenceException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = Factory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(QUERY_WIND_AUCTION_BY_USER);
            ps.setInt(1, user.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    setDataProduct(rs, product);
                    // TODO: 23/01/2020
                    products.add(product);
                }
            }
        } catch (SQLException | PersistenceException e) {
            throw new PersistenceException("JDBC");
        }
        return products;
    }
    @Override
    public List<Product> getProductsOpenByUser(User user) throws PersistenceException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = Factory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(QUERY_PRODUCTS_OPEN_BY_USER);
            ps.setInt(1, user.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    setDataProduct(rs, product);
                    products.add(product);
                }
            }
        } catch (SQLException | PersistenceException e) {
            throw new PersistenceException("JDBC");
        }
        return products;
    }
    private User retrieveUser(int userId) throws PersistenceException {
        return Factory.getUserDao().getUserById(userId);
    }

    private User retrieveBuyerWiThBid(int productId) throws PersistenceException {
        Integer userId = Factory.getAuctionDao().getBuyerId(productId);
        if (userId != null) {
            return Factory.getUserDao().getUserById(userId);
        }
        return null;
    }

    private Category retrieveCategory(int id) throws PersistenceException {
        return Factory.getCategoryDao().getById(id);
    }

    private Withdrawal retrieveWithdrawal(Product product) throws PersistenceException {
        return Factory.getWithdrawalTable().getWithdrawalByProduct(product);
    }

    private void addWithdrawal(Product product) throws PersistenceException {
        Factory.getWithdrawalTable().addWithdrawal(product);
    }


    private Integer retrieveActualPrice(Product product) throws PersistenceException {
        return Factory.getAuctionDao().getBidPrice(product.getId());
    }

}
