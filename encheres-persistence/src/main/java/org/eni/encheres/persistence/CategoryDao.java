package org.eni.encheres.persistence;

import org.eni.encheres.model.Category;

public interface CategoryDao {

    Category getById (Integer id) throws PersistenceException;
}
