package org.eni.encheres.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Auction {
    private User buyer;
    private Product product;
    private LocalDate date;
    private Integer newPrice;
    private Integer newCreditNewBuyer;
    private Integer newCreditLastBuyer;


    @NotNull
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @NotNull
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @NotNull
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @NotNull
    public Integer getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Integer newPrice) {
        this.newPrice = newPrice;
    }
    @NotNull
    public Integer getNewCreditNewBuyer() {
        return newCreditNewBuyer;
    }

    public void setNewCreditNewBuyer(Integer newCreditNewBuyer) {
        this.newCreditNewBuyer = newCreditNewBuyer;
    }

    public Integer getNewCreditLastBuyer() {
        return newCreditLastBuyer;
    }

    public void setNewCreditLastBuyer(Integer newCreditLastBuyer) {
        this.newCreditLastBuyer = newCreditLastBuyer;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "buyer=" + buyer +
                ", product=" + product +
                ", date=" + date +
                ", price=" + newPrice +
                '}';
    }
}
