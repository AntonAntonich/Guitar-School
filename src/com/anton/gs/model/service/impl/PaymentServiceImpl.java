package com.anton.gs.model.service.impl;

import com.anton.gs.model.dao.PaymentDao;
import com.anton.gs.model.dao.impl.PaymentDaoImpl;
import com.anton.gs.model.exception.DaoException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.PaymentService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PaymentServiceImpl implements PaymentService {
    private static Logger logger = LogManager.getLogger();

    private static final String NOT_ENOUGH = "NOT_ENOUGH";
    private static final String SUCCESSFUL = "SUCCESSFUL";
    private static final String UNSUCCESSFULLY = "UNSUCCESSFULLY";

    private static PaymentServiceImpl paymentService;
    private PaymentDao dao;

    private PaymentServiceImpl () {
        dao = PaymentDaoImpl.getInstance();
    }



    public static PaymentServiceImpl getInstance() {
        if(paymentService == null) {
            paymentService = new PaymentServiceImpl();
        }
        return paymentService;
    }

    @Override
    public String findClientByAccount(String bankAccount) throws ServiceException {
        String email = null;
        try {
            email = dao.findClientByBankAccount(bankAccount);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "couldn't find client account " + bankAccount, e);
            throw new ServiceException(e);
        }
        return email;
    }

    @Override
    public int findRestByAccount(String bankAccount) throws ServiceException {
        int rest = 0;
        try {
            rest = dao.extractBankAccountRest(bankAccount);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "couldn't find client account " + bankAccount, e);
            throw new ServiceException(e);
        }
        return rest;
    }

    @Override
    public String transfer(String bankAccount, int price) throws ServiceException {
        int currentRest = findRestByAccount(bankAccount);
        String message = null;
        if(currentRest < price) {
            message = NOT_ENOUGH;
            return message;
        }

        try {
            dao.transferMoney(bankAccount, price);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "money transfer failed: account, price"
                    + bankAccount + " " + price, e);
            throw new ServiceException(e);
        }
        int restAfterTransfer = findRestByAccount(bankAccount);
        if((currentRest - restAfterTransfer) == price) {
            message = SUCCESSFUL;
        }
        return message;
    }

    @Override
    public boolean createNewOrder(String email, String genre) throws ServiceException {
        boolean result;
        try {
            result = dao.createNewOrder(email, genre);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "order creating find failed  " + email, e);
            throw new ServiceException(e);
        }
        return result;
    }
}
