package com.anton.gs.model.service;

import com.anton.gs.model.entity.user.User;
import com.anton.gs.model.exception.ServiceException;

import java.util.List;

/**
 * The interface User service.
 * all methods call appropriated methods id dao layer
 */
public interface UserService {
     /**
      * Find all users list.
      *
      * @return the list
      * @throws ServiceException the service exception
      */
     List<User> findAllUsers () throws ServiceException;

     /**
      * Activate user boolean.
      *
      * @param email the email
      * @return the boolean
      * @throws ServiceException the service exception
      */
     boolean activateUser(String email) throws ServiceException;

     /**
      * Is user exist boolean.
      *
      * @param email the email
      * @return the boolean
      * @throws ServiceException the service exception
      */
     boolean isUserExist(String email) throws ServiceException;

     /**
      * Is user email password equals boolean.
      *
      * @param email    the email
      * @param password the password
      * @return the boolean
      * @throws ServiceException the service exception
      */
     boolean isUserEmailPasswordEquals(String email, String password) throws ServiceException;

     /**
      * Find user role by email string.
      *
      * @param email the email
      * @return the string
      * @throws ServiceException the service exception
      */
     String findUserRoleByEmail(String email) throws ServiceException;

     /**
      * Register new user boolean.
      *
      * @param email    the email
      * @param password the password
      * @param nickname the nickname
      * @param role     the role
      * @return the boolean
      * @throws ServiceException the service exception
      */
     boolean registerNewUser(String email, String password, String nickname, String role) throws ServiceException;

     /**
      * Find user user.
      *
      * @param userMail the user mail
      * @return the user
      * @throws ServiceException the service exception
      */
     User findUser(String userMail) throws ServiceException;

     /**
      * Is user active boolean.
      *
      * @param email the email
      * @return the boolean
      * @throws ServiceException the service exception
      */
     boolean isUserActive(String email) throws ServiceException;

     /**
      * Is user blocked boolean.
      *
      * @param email the email
      * @return the boolean
      * @throws ServiceException the service exception
      */
     boolean isUserBlocked(String email) throws ServiceException;

     /**
      * Find photo name string.
      *
      * @param email the email
      * @return the string
      * @throws ServiceException the service exception
      */
     String findPhotoName(String email) throws ServiceException;

     /**
      * Add to avatar table.
      *
      * @param userEmail the user email
      * @throws ServiceException the service exception
      */
     void addToAvatarTable(String userEmail) throws ServiceException;
}
