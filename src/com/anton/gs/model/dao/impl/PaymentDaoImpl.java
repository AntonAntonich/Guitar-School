package com.anton.gs.model.dao.impl;

import com.anton.gs.model.dao.table.BankTable;
import com.anton.gs.model.dao.PaymentDao;
import com.anton.gs.model.exception.DaoException;
import com.anton.gs.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.anton.gs.model.dao.SqlQueries.*;

public final class PaymentDaoImpl implements PaymentDao {
    private static Logger logger = LogManager.getLogger();

    private static PaymentDaoImpl paymentDao;

    private PaymentDaoImpl() {
    }

    public static PaymentDaoImpl getInstance() {
        if (paymentDao == null) {
            paymentDao = new PaymentDaoImpl();
        }
        return paymentDao;
    }

    @Override
    public String findClientByBankAccount(String bankAccount) throws DaoException {

        String email = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BANK_CLIENT_EMAIL)) {
            statement.setString(1, bankAccount);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                email = set.getString(BankTable.EMAIL);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return email;
    }

    @Override
    public int extractBankAccountRest(String bankAccountNumber) throws DaoException {

        int rest = 0;
        try ( Connection connection = CustomConnectionPool.getInstance().takeConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_REST)) {
            statement.setString(1, bankAccountNumber);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                rest = set.getInt(BankTable.REST);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return rest;
    }

    @Override
    public boolean transferMoney(String bankAccountNumber, int sum) throws DaoException {

        int result;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_REST)) {
            statement.setInt(1, sum);
            statement.setString(2, bankAccountNumber);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return result == 1;
    }

    @Override
    public boolean createNewOrder(String email, String genre) throws DaoException {
        int i = 0;

        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ORDER)) {
            statement.setString(1, email);
            statement.setString(2, genre);
            i = statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return i == 1;
    }
}
