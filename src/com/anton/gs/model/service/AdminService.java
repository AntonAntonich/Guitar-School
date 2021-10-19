package com.anton.gs.model.service;

import com.anton.gs.model.entity.contract.Contract;
import com.anton.gs.model.entity.order.Order;
import com.anton.gs.model.entity.user.User;
import com.anton.gs.model.exception.ServiceException;

import java.sql.Date;
import java.util.List;

/**
 * The interface Admin service.
 * all methods call appropriated methods id dao layer
 */
public interface AdminService {
    /**
     * Refresh user boolean.
     *
     * @param email  the email
     * @param role   the role
     * @param skill  the skill
     * @param degree the degree
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean refreshUser(String email, String role, String skill, String degree) throws ServiceException;

    /**
     * Ban user boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean banUser(String email) throws ServiceException;

    /**
     * Find banned users list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<String> findBannedUsers() throws ServiceException;

    /**
     * Unban user boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean unbanUser(String email) throws ServiceException;

    /**
     * Show orders list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> showOrders() throws ServiceException;

    /**
     * Confirm order boolean.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean confirmOrder(int orderId) throws ServiceException;

    /**
     * Find tutors list.
     *
     * @param role the role
     * @return the list
     * @throws ServiceException the service exception
     */
    List<String> findTutors(String role) throws ServiceException;

    /**
     * Create contract boolean.
     *
     * @param studentEmail the student email
     * @param genre        the genre
     * @param tutorEmail   the tutor email
     * @param startDate    the start date
     * @param endDate      the end date
     * @param currentLevel the current level
     * @param targetLevel  the target level
     * @param guitarType   the guitar type
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createContract(String studentEmail,
                           String genre,
                           String tutorEmail,
                           String startDate,
                           String endDate,
                           String currentLevel,
                           String targetLevel,
                           String guitarType) throws ServiceException;

    /**
     * Show all contracts list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Contract> showAllContracts() throws ServiceException;
}
