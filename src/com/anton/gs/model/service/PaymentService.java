package com.anton.gs.model.service;

import com.anton.gs.model.exception.ServiceException;

/**
 * The interface Payment service.
 * all methods call appropriated methods id dao layer
 */
public interface PaymentService {
    /**
     * Find client by account string.
     *
     * @param bankAccount the bank account
     * @return the string
     * @throws ServiceException the service exception
     */
    String findClientByAccount(String bankAccount) throws ServiceException;

    /**
     * Find rest by account int.
     *
     * @param bankAccount the bank account
     * @return the int
     * @throws ServiceException the service exception
     */
    int findRestByAccount(String bankAccount) throws ServiceException;

    /**
     * Transfer string.
     *
     * @param bankAccount the bank account
     * @param sum         the sum
     * @return the string
     * @throws ServiceException the service exception
     */
    String transfer(String bankAccount, int sum) throws ServiceException;

    /**
     * Create new order boolean.
     *
     * @param email the email
     * @param genre the genre
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createNewOrder(String email, String genre) throws ServiceException;
}
