package org.eni.encheres.persistence;

import org.eni.encheres.model.Product;
import org.eni.encheres.model.Withdrawal;

public interface  WithdrawalDao {
    void addWithdrawal(Product product) throws PersistenceException;
    Withdrawal getWithdrawalByProduct(Product product) throws PersistenceException;
}
