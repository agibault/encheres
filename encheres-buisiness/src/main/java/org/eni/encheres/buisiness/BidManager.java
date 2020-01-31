package org.eni.encheres.buisiness;

import org.eni.encheres.buisiness.exceptions.BidException;
import org.eni.encheres.model.*;
import org.eni.encheres.persistence.Factory;
import org.eni.encheres.persistence.PersistenceException;
import javax.validation.Validator;

public class BidManager {

    private Auction auction;
    private Product product;
    private User buyer;

    public BidManager(Auction auction) throws Exception {
        setAuction(auction);
        setProduct(auction.getProduct());
        setBuyer(auction.getBuyer());
    }


    public void buyProduct() throws BidException {

        User lastBuyer = auction.getProduct().getBuyer();
        User newBuyer = auction.getBuyer();
        if (userHasEnoughCredit() && isSupThanOlderBid()) {
            try {
                chargeNewBuyer();
                if (isFirstBid()) {
                    Factory.getAuctionDao().makeBid(auction);
                    newBuyer.setCredit(auction.getNewCreditNewBuyer());
                } else {
                    credit();
                    Factory.getAuctionDao().makeBidOnUpdate(auction);
                    lastBuyer.setCredit(auction.getNewCreditLastBuyer());
                    newBuyer.setCredit(auction.getNewCreditNewBuyer());
                }
                newBuyer.addProductPurchased(product);
            } catch (PersistenceException e) {
                e.printStackTrace();
                throw new BidException("problem with purchase");
            }
        }

    }

    private boolean userHasEnoughCredit() {
        return buyer.getCredit() >= auction.getNewPrice();
    }

    private boolean isFirstBid() {
        return product.getBuyer() == null;
    }

    private void chargeNewBuyer() {
        int actualCredit = buyer.getCredit();
        auction.setNewCreditNewBuyer(actualCredit - auction.getNewPrice());
    }

    private boolean isSupThanOlderBid() throws BidException {
        if (product.getActualPrice() != null) {
            if (auction.getNewPrice() > product.getActualPrice()) {
                return true;
            } else {
                throw new BidException("must be sup than older");
            }
        }
        throw new BidException("price null");
    }

    private void credit() {
        User lastBuyer = product.getBuyer();
        Integer oldPriceProduct = product.getActualPrice();

        if (lastBuyer != null && lastBuyer.getId() != null) {
            int newCredit = product.getBuyer().getCredit();
            auction.setNewCreditLastBuyer(newCredit + oldPriceProduct);
        }

    }

    private void setBuyer(User buyer) throws Exception {
        if (buyer == null || buyer.getId() == null) {
            throw new BidException("buyer cannot be empty");
        }
        this.buyer = buyer;
    }

    private void setProduct(Product product) throws Exception {
        if (product == null || product.getId() == null) {
            throw new BidException("product cannot be empty");
        }
        this.product = product;
    }

    private void setAuction(Auction auction) throws BidException {
        check(auction);
        this.auction = auction;
    }

    public void check(Auction auction) throws BidException {
        Validator validator = org.eni.encheres.buisiness.Factory.getValidator();
        boolean errors = !validator.validate(auction).isEmpty();
        if (errors) {
            throw new BidException("bean bid not ");
        }
    }
}
