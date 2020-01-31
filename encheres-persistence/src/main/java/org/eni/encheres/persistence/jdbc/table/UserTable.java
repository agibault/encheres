package org.eni.encheres.persistence.jdbc.table;

import org.eni.encheres.persistence.Factory;
import org.eni.encheres.persistence.UserDao;
import org.eni.encheres.persistence.PersistenceException;
import org.eni.encheres.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTable implements UserDao {

    private static final String QUERY_SELECT_BY_USERNAME = "SELECT * FROM utilisateurs WHERE pseudo = ?";
    private static final String QUERY_DEACTIVATE_ACCOUNT = "UPDATE utilisateurs SET valid = 0 WHERE no_utilisateur = ?";
    private static final String QUERY_SELECT_PASSWORD = "SELECT mot_de_passe FROM utilisateurs WHERE no_utilisateur = ?";
    private static final String QUERY_SELECT_BY_EMAIL = "SELECT * FROM utilisateurs WHERE email = ?";
    private static final String QUERY_IS_UNIQUE_EMAIL = "SELECT email FROM utilisateurs WHERE email = ?";
    private static final String QUERY_IS_UNIQUE_USERNAME = "SELECT pseudo FROM utilisateurs WHERE pseudo = ?";
    private static final String QUERY_SELECT_BY_ID = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";
    private static final String QUERY_INSERT_USER = "INSERT INTO utilisateurs "
            + "(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)"
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String QUERY_UPDATE_USER = "UPDATE utilisateurs SET pseudo = ?, nom = ?, prenom = ?,email = ?,telephone= ?, rue =?,code_postal = ?, ville =?,mot_de_passe = ?  WHERE no_utilisateur = ?";
    private static final String QUERY_UPDATE_CREDIT = "UPDATE utilisateurs SET credit = ? WHERE no_utilisateur = ?";


    @Override
    public void addUser(User user) throws PersistenceException {
        try (Connection connection = Factory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY_INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
                e(user, ps);
                ps.setInt(10, user.getCredit());
                ps.setBoolean(11, user.isAdmin());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCredit(int userId, int newCredit) throws PersistenceException {
        try (Connection connection = Factory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY_UPDATE_CREDIT)) {
                ps.setInt(1, newCredit);
                // TODO: 21/01/2020 commit
                //ps.setInt(2, userId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC");
        }
    }

    @Override
    public void updateUser(User user) throws PersistenceException {
        try (Connection connection = Factory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY_UPDATE_USER)) {
                e(user, ps);
                ps.setInt(10, user.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void e(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getFirstName());
        ps.setString(4, user.getEmail());
        ps.setString(5, user.getPhoneNumber());
        ps.setString(6, user.getStreet());
        ps.setString(7, user.getPostalCode());
        ps.setString(8, user.getCity());
        ps.setString(9, user.getPassword());
    }

    private boolean isUniqueField(String field, String query) throws PersistenceException {
        try (Connection connection = Factory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, field);
                // TODO: 23/01/2020
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC-" + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean isUniqueEmail(String email) throws PersistenceException {
        return isUniqueField(email, QUERY_IS_UNIQUE_EMAIL);
    }

    @Override
    public boolean isUniqueUsername(String username) throws PersistenceException {
        return isUniqueField(username, QUERY_IS_UNIQUE_USERNAME);
    }

    @Override
    public User getUserByUsername(String username) throws PersistenceException {
        return getUserByLogin(username, QUERY_SELECT_BY_USERNAME);
    }

    @Override
    public User getUserByEmail(String firstName) throws PersistenceException {
        return getUserByLogin(firstName, QUERY_SELECT_BY_EMAIL);
    }

    private User getUserByLogin(String firstName, String querySelectByEmail) throws PersistenceException {
        User user = new User();
        try (Connection connection = Factory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(querySelectByEmail)) {
                ps.setString(1, firstName);
                mapUser(ps, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC-" + e.getMessage());
        }
        return user;
    }

    @Override
    public String getUserPassword(int userId) throws PersistenceException {
        String password = null;
        try (Connection connection = Factory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(QUERY_SELECT_PASSWORD);
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    password = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC-" + e.getMessage());
        }
        return password;
    }

    @Override
    public User getUserById(int id) throws PersistenceException {
        User user = new User();
        try (Connection connection = Factory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY_SELECT_BY_ID)) {
                ps.setInt(1, id);
                mapUser(ps, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC-" + e.getMessage());
        }
        return user;
    }

    private void mapUser(PreparedStatement ps, User user) throws PersistenceException {
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                user.setId(rs.getInt("no_utilisateur"));
                user.setUsername(rs.getString("pseudo"));
                user.setLastName(rs.getString("nom"));
                user.setFirstName(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("telephone"));
                user.setStreet(rs.getString("rue"));
                user.setPostalCode(rs.getString("code_postal"));
                user.setCity(rs.getString("ville"));
                user.setPassword(rs.getString("mot_de_passe"));
                user.setCredit(rs.getInt("credit"));
                user.setAdmin(rs.getBoolean("administrateur"));
                user.setValid(rs.getBoolean("valid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC-" + e.getMessage());
        }
    }

    @Override
    public void deactivateAccount(int userId) throws PersistenceException {
        try (Connection connection = Factory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY_DEACTIVATE_ACCOUNT)) {
                ps.setInt(1, userId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("JDBC-" + e.getMessage());
        }
    }
}