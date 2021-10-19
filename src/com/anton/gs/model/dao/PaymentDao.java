package com.anton.gs.model.dao;

import com.anton.gs.model.exception.DaoException;

/**
 * The interface Payment dao.
 */
public interface PaymentDao {
    /**
     * Find client by bank account string.
     * select user name account having account number equal incoming
     * @param bankAccount the bank account
     * @return the string
     * @throws DaoException the dao exception
     */
    String findClientByBankAccount(String bankAccount) throws DaoException;

    /**
     * Extract bank account rest int.
     * select rest of bank client having account number incoming
     * @param bankAccountNumber the bank account number
     * @return the int
     * @throws DaoException the dao exception
     */
    int extractBankAccountRest(String bankAccountNumber) throws DaoException;

    /**
     * Transfer money boolean.
     * imitate money transfer
     * @param bankAccountNumber the bank account number
     * @param sum               the sum
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean transferMoney(String bankAccountNumber, int sum) throws DaoException;

    /**
     * Create new order boolean.
     * creating new order
     * @param email the email
     * @param genre the genre
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean createNewOrder(String email, String genre) throws DaoException;

}
