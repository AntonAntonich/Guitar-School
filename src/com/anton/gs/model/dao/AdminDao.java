package com.anton.gs.model.dao;

import com.anton.gs.model.entity.contract.Contract;
import com.anton.gs.model.entity.order.Order;
import com.anton.gs.model.entity.user.User;
import com.anton.gs.model.exception.DaoException;
import com.anton.gs.model.exception.ServiceException;

import java.sql.Date;
import java.util.List;

/**
 * The interface Admin dao.
 */
public interface  AdminDao {
    /**
     * Refresh user boolean.
     * update user using incoming parameters
     * @param email    the email
     * @param roleId   the role id
     * @param skillId  the skill id
     * @param degreeId the degree id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean refreshUser(String email, int roleId, int skillId, int degreeId) throws DaoException;

    /**
     * Ban user boolean.
     * set blocked value field in user table , having email
     * @param email the email
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean banUser(String email) throws DaoException;

    /**
     * Find banned users list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<String> findBannedUsers() throws DaoException;

    /**
     * Unban user boolean.
     * set false in banned user table where user email equal incoming email
     * @param email the email
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean unbanUser(String email) throws DaoException;

    /**
     * Find orders list.
     * extract orders from database table orders
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findOrders() throws DaoException;

    /**
     * Confirm order boolean.
     * set value true in orders table
     * @param orderId the order id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean confirmOrder(int orderId) throws DaoException;

    /**
     * Find tutors list.
     * extract all tutors
     * @param role the role
     * @return the list
     * @throws DaoException the dao exception
     */
    List<String> findTutors(String role) throws DaoException;

    /**
     * Create contract boolean.
     * create contract using incoming parameters
     * @param studentEmail the student email
     * @param genre        the genre
     * @param tutorEmail   the tutor email
     * @param startDate    the start date
     * @param endDate      the end date
     * @param currentLevel the current level
     * @param targetLevel  the target level
     * @param guitarType   the guitar type
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean createContract(String studentEmail,
                           String genre,
                           String tutorEmail,
                           Date startDate,
                           Date endDate,
                           String currentLevel,
                           String targetLevel,
                           String guitarType) throws DaoException;

    /**
     * Find contracts list.
     * find all contracts
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Contract> findContracts() throws DaoException;
}
