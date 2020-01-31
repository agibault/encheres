package org.eni.encheres.model;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class Product {
    private Integer id;
    private String name;
    private String description;
    private LocalDate dateStartBid;
    private LocalDate dateEndBid;
    private Integer startingPrice;
    private Integer sellingPrice;
    private State state;
    private Category category;
    private Withdrawal withdrawal;
    private User seller;
    private User buyer;
    private boolean isValid = true;
    private Integer actualPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    @Size(max = 300)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @FutureOrPresent
    public LocalDate getDateStartBid() {
        return dateStartBid;
    }

    public void setDateStartBid(LocalDate dateStartBid) {
        this.dateStartBid = dateStartBid;
    }

    @Future // TODO: 18/01/2020 create constraint for end > start
    public LocalDate getDateEndBid() {
        return dateEndBid;
    }

    public void setDateEndBid(LocalDate dateEndBid) {
        this.dateEndBid = dateEndBid;
    }

    @Min(1)
    public int getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
    }

    @Min(1) // TODO: 18/01/2020 create constraint for selling price >=  init price && actual price
    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @NotNull
    public State getState() {
        int compare = dateStartBid.compareTo(dateEndBid);
        if (compare < 0) {
            this.state = State.NOT_OPEN;
        } else if (compare > 0) {
            this.state = State.OPEN;
        }
        return state;
    }


    @NotNull
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @NotNull
    public Withdrawal getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Withdrawal withdrawal) {
        this.withdrawal = withdrawal;
    }

    @NotNull
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Integer getActualPrice() {
        if (actualPrice == null) {
            actualPrice = getStartingPrice();
        }
        return actualPrice;
    }

    public void setActualPrice(Integer actualPrice) {
        this.actualPrice = actualPrice;
    }

    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", name='"
                + name
                + '\''
                + ", description='"
                + description
                + '\''
                + ", dateStartBid="
                + dateStartBid
                + ", dateEndBid="
                + dateEndBid
                + ", initPrice="
                + startingPrice
                + ", sellingPrice="
                + sellingPrice
                + ", sellState="
                + state + ", category=" + category +
                '}';
    }
}
