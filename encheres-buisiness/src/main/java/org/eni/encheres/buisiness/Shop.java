package org.eni.encheres.buisiness;

import org.eni.encheres.buisiness.exceptions.ShopExeption;
import org.eni.encheres.buisiness.validator.ProductValidator;
import org.eni.encheres.model.*;
import org.eni.encheres.persistence.Factory;
import org.eni.encheres.persistence.ProductDao;
import java.time.LocalDate;
import java.util.List;

public class Shop {


    public boolean makeABid(Product productPurchased, User buyer, int price) throws ShopExeption {
        try {
            Auction auction = new Auction();
            auction.setBuyer(buyer);
            auction.setProduct(productPurchased);
            auction.setDate(LocalDate.now());
            auction.setNewPrice(price);
            BidManager bid1 = new BidManager(auction);
            bid1.buyProduct();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ShopExeption("error during the sale");
        }
        return true;

    }


    public void addProduct(Product productSold) throws ShopExeption {
        try {
            if (isValidProduct(productSold) && productSold.getSeller().getId() != null) {
                ProductDao productDao = Factory.getProductDao();
                productDao.addProduct(productSold);
            }

        } catch (Exception e) {
            throw new ShopExeption("error during the sale");
        }

    }

    public boolean isValidProduct(Product productSold) {
        ProductValidator productValidator = new ProductValidator(productSold);
        return productValidator.isValid();
    }


    public List<Product> getAllProduct() throws Exception {
        List<Product> products;
        try {
            ProductDao productDao = Factory.getProductDao();
            products = productDao.getAllProduct();
        } catch (Exception e) {
            throw new ShopExeption("error during the sale");
        }
        return products;
    }

    public Product getProduct(int id) throws Exception {
        Product product;
        try {
            ProductDao productDao = Factory.getProductDao();
            product = productDao.getProduct(id);
        } catch (Exception e) {
            throw new ShopExeption("error during the sale");
        }
        return product;
    }

    public List<Product> getProductsByTag(String tag) throws Exception {
        List<Product> products = null;
        if (tag != null && !tag.isEmpty()) {
            try {
                ProductDao productDao = Factory.getProductDao();
                products = productDao.getProductsByTag(tag);
            } catch (Exception e) {
                throw new ShopExeption("error during the sale");
            }
        }
        return products;
    }

    public List<Product> getOpenAuctionByUser(User user) throws Exception {
        List<Product> products = null;
        if (user != null && user.getId() != null) {
            try {
                ProductDao productDao = Factory.getProductDao();
                products = productDao.getOpenAuctionByUser(user);
            } catch (Exception e) {
                throw new ShopExeption("error during the sale");
            }
        }
        return products;
    }

    public List<Product> getWindAuctionByUser(User user) throws Exception {
        List<Product> products = null;
        if (user != null && user.getId() != null) {
            try {
                ProductDao productDao = Factory.getProductDao();
                products = productDao.getWindAuctionByUser(user);
            } catch (Exception e) {
                throw new ShopExeption("error during the sale");
            }
        }
        return products;
    }

    public List<Product> getProductsByUser(User user) throws Exception {
        List<Product> products = null;
        if (user != null && user.getId() != null) {
            try {
                ProductDao productDao = Factory.getProductDao();
                products = productDao.getProductsOpenByUser(user);
            } catch (Exception e) {
                throw new ShopExeption("error during the sale");
            }
        }
        return products;
    }


}
