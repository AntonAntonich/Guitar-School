package com.anton.gs.model.dao;


import com.anton.gs.model.entity.user.User;
import com.anton.gs.model.exception.DaoException;

import java.util.List;

/**
 * The interface User dao.
 */
public interface   UserDao extends BaseDao<Integer, User> {

    /**
     * Activate user int.
     * set value true in is active in  user table
     * @param email the email
     * @return the int
     * @throws DaoException the dao exception
     */
    int activateUser(String email) throws DaoException;

    /**
     * Add user.
     * insert user in database using incoming parameters
     * @param email    the email
     * @param password the password
     * @param nickName the nick name
     * @param roleId   the role id
     * @param degree   the degree
     * @param level    the level
     * @throws DaoException the dao exception
     */
    void addUser(String email, String password, String nickName, int roleId, int degree, int level) throws DaoException;

    /**
     * Find user mail string.
     * select user email having email equals incoming email
     * @param email the email
     * @return the string
     * @throws DaoException the dao exception
     */
    String findUserMail(String email) throws DaoException;

    /**
     * Find user password by email string.
     * select encrypted user password
     * @param emailFromDB the email from db
     * @return the string
     * @throws DaoException the dao exception
     */
    String findUserPasswordByEmail(String emailFromDB) throws DaoException;

    /**
     * Find user id by email integer.
     * select user having email equal incoming email
     * @param userEmail the user email
     * @return the integer
     * @throws DaoException the dao exception
     */
    Integer findUserIdByEmail(String userEmail) throws DaoException;

    /**
     * Find user role by id string.
     * select user role where id equal incoming parameter
     * @param userId the user id
     * @return the string
     * @throws DaoException the dao exception
     */
    String findUserRoleById(Integer userId) throws DaoException;

    /**
     * Create user user.
     *
     * @param userMail the user mail
     * @return the user
     * @throws DaoException the dao exception
     */
    User createUser(String userMail) throws DaoException;

    /**
     * Is user active boolean.
     * select value of column is user active where email equal incoming
     * @param userMail the user mail
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isUserActive(String userMail) throws DaoException;

    /**
     * Is user blocked boolean.
     * select value of column is user blocked where email equal incoming
     * @param email the email
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isUserBlocked(String email) throws DaoException;

    /**
     * Find avatar name string.
     * select name of picture file on server having email eula incoming
     * @param email the email
     * @return the string
     * @throws DaoException the dao exception
     */
    String findAvatarName(String email) throws DaoException;

    /**
     * Add to avatar table.
     * insert picture file name in table user avatar
     * @param userEmail the user email
     * @throws DaoException the dao exception
     */
    void addToAvatarTable(String userEmail) throws DaoException;

    /**
     * Update avatar file name boolean.
     * change name of atr file on incoming file name where email equal incoming
     * @param email    the email
     * @param fileName the file name
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateAvatarFileName(String email, String fileName) throws DaoException;

}
