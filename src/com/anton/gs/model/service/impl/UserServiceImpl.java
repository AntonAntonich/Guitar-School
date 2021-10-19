package com.anton.gs.model.service.impl;

import com.anton.gs.model.dao.UserDao;
import com.anton.gs.model.dao.impl.UserDaoImpl;
import com.anton.gs.model.entity.user.User;
import com.anton.gs.model.entity.user.repository.UserRepositoryImpl;
import com.anton.gs.model.exception.DaoException;
import com.anton.gs.model.exception.RepositoryException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.UserService;
import com.anton.gs.model.util.impl.PasswordCoderImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public  class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger();

    private static final int DEFAULT_DEGREE_VALUE = 1;
    private static final int DEFAULT_SKILL_VALUE = 1;
    private UserDao dao;

    private static UserServiceImpl userService;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    private UserServiceImpl() {
        dao = UserDaoImpl.getInstance();
    }

    public static UserServiceImpl getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public void addToAvatarTable(String userEmail) throws ServiceException {
        try {
            dao.addToAvatarTable(userEmail);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "adding to avatar table  student: " + userEmail, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public String findPhotoName(String email) throws ServiceException {
        String photoName;
        try {
            photoName = dao.findAvatarName(email);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "finding photo name failed, email: " + email, e);
            throw new ServiceException(e);
        }
        return photoName;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        List<User> users;
        try {
            users = dao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "finding users failed", e);
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public boolean activateUser(String email) throws ServiceException {
        boolean result = false;
        try {
            int success = dao.activateUser(email);
            if (success == 1) {
                result = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "user activation failed, user: " + email, e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean isUserExist(String email) throws ServiceException {
        String emailFromDB = null;
        try {
            emailFromDB = dao.findUserMail(email);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "checking user presence failed, email: " + email, e);
            throw new ServiceException(e);
        }
        return email.equals(emailFromDB);
    }

    @Override
    public boolean isUserEmailPasswordEquals(String email, String password) throws ServiceException {

        String emailFromDB = null;
        String passwordFromDB = null;
        try {
            emailFromDB = dao.findUserMail(email);
            passwordFromDB = dao.findUserPasswordByEmail(emailFromDB);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "getting from db, email: " + email, e);
            throw new ServiceException(e);
        }

        password = PasswordCoderImpl.getInstance().codePassword(password);
        return password.equals(passwordFromDB);
    }

    @Override
    public String findUserRoleByEmail(String email) throws ServiceException {

        Integer userId = null;
        String userRole = null;
        try {
            userId = dao.findUserIdByEmail(email);
            userRole = dao.findUserRoleById(userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "detecting role failed, user: " + email, e);
            throw new ServiceException(e);
        }

        String role = null;
        try {
            role = UserRepositoryImpl.getInstance().findValue(UserRepositoryImpl.ROLE, userRole);
        } catch (RepositoryException e) {
            logger.log(Level.ERROR, "detecting role in rep  failed, role: " + userRole, e);
            throw new ServiceException(e);
        }
        return role;
    }

    @Override
    public boolean registerNewUser(String email,
                                   String password,
                                   String nickname,
                                   String role) throws ServiceException {
        boolean result = false;

        try {
            dao.addUser(email,
                    PasswordCoderImpl.getInstance().codePassword(password),
                    nickname,
                    UserRepositoryImpl.getInstance().findValueId(UserRepositoryImpl.ROLE, role),
                    DEFAULT_DEGREE_VALUE,
                    DEFAULT_SKILL_VALUE);
            result = true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "registration new user failed, email: " + email, e);
            throw new ServiceException();
        } catch (RepositoryException e) {
            logger.log(Level.ERROR, "detecting role in rep  failed, role: " + email, e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public User findUser(String userMail) throws ServiceException {
        User user = null;

        try {
            user = dao.createUser(userMail);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "finding user in rep, email: " + userMail, e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean isUserActive(String email) throws ServiceException {
        boolean result;
        try {
            result = dao.isUserActive(email);

        } catch (DaoException e) {
            logger.log(Level.ERROR, "checking user activity failed, email: " + email, e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean isUserBlocked(String email) throws ServiceException {
        boolean result;
        try {
            result = dao.isUserBlocked(email);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "checking user banned failed, email: " + email, e);
            throw new ServiceException(e);
        }
        return result;
    }
}
