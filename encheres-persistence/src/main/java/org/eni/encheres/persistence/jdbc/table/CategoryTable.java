package org.eni.encheres.persistence.jdbc.table;

import org.eni.encheres.model.Category;
import org.eni.encheres.persistence.CategoryDao;
import org.eni.encheres.persistence.Factory;
import org.eni.encheres.persistence.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryTable implements CategoryDao {

    private static final String QUERY_SELECT_CATEGORY = "SELECT * FROM articles_vendus WHERE no_article = ?";

    @Override
    public Category getById(Integer id) throws PersistenceException {
        Category category = new Category();
        try (Connection connection = Factory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(QUERY_SELECT_CATEGORY);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    category.setName(rs.getString("libelle"));
                }
            }
        } catch (SQLException | PersistenceException e) {
            throw new PersistenceException("JDBC");
        }
        return category;
    }
}
