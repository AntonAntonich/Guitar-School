package com.anton.gs.model.dao.impl;

import com.anton.gs.model.dao.UserDao;
import com.anton.gs.model.dao.table.AvatarFileTable;
import com.anton.gs.model.entity.user.User;

import com.anton.gs.model.entity.user.UserCreator;
import com.anton.gs.model.exception.DaoException;
import com.anton.gs.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.anton.gs.model.dao.table.UserTable.*;
import static com.anton.gs.model.dao.SqlQueries.*;


public final class UserDaoImpl implements UserDao {
    private static Logger logger = LogManager.getLogger();

    private static UserCreator userCreator;
    private static UserDaoImpl userDao;

    private UserDaoImpl() {
        userCreator = UserCreator.getInstance();
    }

    public static UserDaoImpl getInstance() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    @Override
    public boolean updateAvatarFileName(String email, String fileName) throws DaoException {
        int i = 0;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_FILE_NAME)) {
            statement.setString(1, fileName);
            statement.setString(2, email);
            i = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return i == 1;
    }

    @Override
    public String findAvatarName(String email) throws DaoException {
        String fileName = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_FILE_NAME)) {
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                fileName = set.getString(AvatarFileTable.FILE_NAME);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return fileName;
    }

    @Override
    public void addToAvatarTable(String userEmail) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_EMAIL_INTO_AVATAR_TABLE)) {
            statement.setString(1, userEmail);
            statement.execute();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = CustomConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USERS)) {
            User user;
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                user = extractUser(set);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return users;
    }


    @Override
    public int activateUser(String email) throws DaoException {
        int result;

        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ACTIVATE_USER)) {
            statement.setString(1, email);
            result = statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public void addUser(String email, String password, String nickName, int roleId, int degree, int level) throws DaoException {

        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, nickName);
            statement.setInt(4, roleId);
            statement.setInt(5, degree);
            statement.setInt(6, level);
            statement.execute();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
    }


    @Override
    public Integer findUserIdByEmail(String userEmail) throws DaoException {
        Integer userId = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ID_BY_EMAIL)) {
            statement.setString(1, userEmail);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                userId = set.getInt(USER_BASIC_INFO_ID);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return userId;
    }


    @Override
    public String findUserMail(String email) throws DaoException {
        String emailFromDb = null;

        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_EMAIL)) {
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                emailFromDb = set.getString(USER_EMAIL);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return emailFromDb;
    }

    @Override
    public String findUserPasswordByEmail(String emailFromDB) throws DaoException {
        String passwordFromDB = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_PASSWORD_BY_EMAIL);) {
            statement.setString(1, emailFromDB);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                passwordFromDB = set.getString(USER_PASSWORD);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return passwordFromDB;
    }


    @Override
    public String findUserRoleById(Integer userId) throws DaoException {
        String userRole = null;

        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_ROLE_BY_ID)) {
            statement.setInt(1, userId);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                userRole = set.getString(USER_ROLE);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return userRole;
    }

    @Override
    public User createUser(String userMail) throws DaoException {
        User user = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL)) {
            String pattern = userMail;
            statement.setString(1, pattern);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public boolean isUserActive(String userMail) throws DaoException {

        boolean isActive = false;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_IS_USER_ACTIVE)) {
            statement.setString(1, userMail);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                isActive = set.getBoolean(USER_IS_ACTIVE);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return isActive;
    }

    @Override
    public boolean isUserBlocked(String email) throws DaoException {
        boolean result;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_IS_USER_BANNED)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            String emailFromDb = null;
            while (resultSet.next()) {
                emailFromDb = resultSet.getString(USER_EMAIL);
            }
            result = email.equals(emailFromDb);

        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return result;
    }

    private User extractUser(ResultSet set) throws SQLException {
        User user;
        int userId = set.getInt(USER_ID);
        String email = set.getString(USER_EMAIL);
        String nickName = set.getString(USER_NICKNAME);
        String userRole = set.getString(USER_ROLE);
        String userSkillLevel = set.getString(STUDENT_SKILL);
        String userDegree = set.getString(TUTOR_DEGREE);
        Boolean isActive = set.getBoolean(USER_IS_ACTIVE);
        user = userCreator.createUser(userId, email, nickName, userRole, userDegree, userSkillLevel, isActive);
        return user;
    }
}
