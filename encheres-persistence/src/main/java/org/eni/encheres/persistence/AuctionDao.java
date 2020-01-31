package org.eni.encheres.persistence;

import org.eni.encheres.model.Auction;

public interface AuctionDao {
    void makeBidOnUpdate(Auction auction) throws PersistenceException;

    void makeBid(Auction auction) throws PersistenceException;

    Integer getBidPrice(int id) throws PersistenceException;

    void updateBid(Auction id) throws PersistenceException;

    Integer getBuyerId(int productId) throws PersistenceException;
}
