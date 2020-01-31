package org.eni.encheres.persistence;

import org.eni.encheres.model.Product;
import org.eni.encheres.model.User;

import java.util.List;

public interface ProductDao {

    void addProduct(Product product) throws PersistenceException;

    List<Product> getAllProduct() throws PersistenceException;

    Product getProduct(int id) throws PersistenceException;

    List<Product> getProductsByTag(String tag) throws PersistenceException;

    List<Product> getOpenAuctionByUser(User user) throws PersistenceException;

    List<Product> getWindAuctionByUser(User user) throws PersistenceException;

    List<Product> getProductsOpenByUser(User user) throws PersistenceException;

}
