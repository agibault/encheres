package org.eni.encheres;

import org.eni.encheres.buisiness.BidManager;
import org.eni.encheres.model.Auction;
import org.eni.encheres.model.Product;
import org.eni.encheres.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class BidTest {


    Auction auction;

    @Before
    public void setUp() throws Exception {
        User buyer = new User();
        buyer.setId(1);
        buyer.setCredit(200);

        Product product = new Product();
        product.setId(1);
        product.setActualPrice(20);


        auction = new Auction();
        auction.setDate(LocalDate.now());
        auction.setNewPrice(150);
        auction.setProduct(product);
        auction.setBuyer(buyer);
    }

    @Test
    public void TestCreditBuyer() throws Exception {
        BidManager bidProce = new BidManager(auction);
        bidProce.buyProduct();
        Assert.assertEquals(50, auction.getBuyer().getCredit());

    }

    @Test
    public void TestNewPrice() throws Exception {
        int newPrice = auction.getProduct().getActualPrice();
        Assert.assertEquals(150, newPrice);

    }
}
