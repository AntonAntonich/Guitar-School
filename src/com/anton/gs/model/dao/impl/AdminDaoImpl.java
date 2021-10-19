package com.anton.gs.model.dao.impl;

import com.anton.gs.model.dao.AdminDao;
import com.anton.gs.model.dao.table.ContractTable;
import com.anton.gs.model.dao.table.OrderTable;
import com.anton.gs.model.dao.table.UserTable;
import com.anton.gs.model.entity.contract.Contract;
import com.anton.gs.model.entity.contract.ContractCreator;
import com.anton.gs.model.entity.order.Order;
import com.anton.gs.model.entity.order.OrderCreator;
import com.anton.gs.model.exception.DaoException;
import com.anton.gs.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.anton.gs.model.dao.SqlQueries.*;
import static com.anton.gs.model.dao.table.UserTable.*;


public final class AdminDaoImpl implements AdminDao {
    private static Logger logger = LogManager.getLogger();
    private static AdminDaoImpl adminDao;
    private OrderCreator orderCreator;

    private AdminDaoImpl() {
        orderCreator = OrderCreator.getInstance();
    }

    public static AdminDaoImpl getInstance() {
        if (adminDao == null) {
            adminDao = new AdminDaoImpl();
        }
        return adminDao;
    }

    @Override
    public List<Contract> findContracts() throws DaoException {
        List<Contract> contractList = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             Statement statement = connection.createStatement()) {
            Contract contract;
            ResultSet set = statement.executeQuery(SQL_SELECT_CONTRACTS);
            while (set.next()) {
                contract = ContractBuilder.buildContract(set);
                contractList.add(contract);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return contractList;
    }

    @Override
    public boolean createContract(String studentEmail,
                                  String genre,
                                  String tutorEmail,
                                  Date startDate,
                                  Date endDate,
                                  String currentLevel,
                                  String targetLevel,
                                  String guitarType) throws DaoException {

        int result = 0;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statementTutor = connection.prepareStatement(SQL_FIND_ID_BY_EMAIL);
             PreparedStatement statementStudent = connection.prepareStatement(SQL_FIND_ID_BY_EMAIL);
             PreparedStatement statementSkill = connection.prepareStatement(SQL_SELECT_SKILL_LEVEL_ID);
             PreparedStatement statementTarget = connection.prepareStatement(SQL_SELECT_SKILL_LEVEL_ID);
             PreparedStatement statementGuitar = connection.prepareStatement(SQL_SELECT_GUITAR_ID);
             PreparedStatement statementContract = connection.prepareStatement(SQL_INSERT_CONTRACT);
             PreparedStatement statementGenre = connection.prepareStatement(SQL_SELECT_GENRE_ID)
        ) {
            int studentId = 0;
            int tutorId = 0;
            int currentSkillLevelId = 0;
            int targetSkillLevelId = 0;
            int guitarId = 0;
            int genreId = 0;

            statementStudent.setString(1, studentEmail);
            ResultSet set = statementStudent.executeQuery();
            while (set.next()) {
                studentId = set.getInt(USER_BASIC_INFO_ID);
            }

            statementTutor.setString(1, tutorEmail);
            set = statementTutor.executeQuery();
            while (set.next()) {
                tutorId = set.getInt(USER_BASIC_INFO_ID);
            }

            statementSkill.setString(1, currentLevel);
            set = statementSkill.executeQuery();
            while (set.next()) {
                currentSkillLevelId = set.getInt(ContractTable.SKILL_ID);
            }

            statementTarget.setString(1, targetLevel);
            set = statementTarget.executeQuery();
            while (set.next()) {
                targetSkillLevelId = set.getInt(ContractTable.SKILL_ID);
            }
            statementGuitar.setString(1, guitarType);
            set = statementGuitar.executeQuery();
            while (set.next()) {
                guitarId = set.getInt(ContractTable.GUITAR_ID);
            }
            statementGenre.setString(1, genre);
            set = statementGenre.executeQuery();
            while (set.next()) {
                genreId = set.getInt(ContractTable.GENRE_ID);
            }
            statementContract.setDate(1, startDate);
            statementContract.setDate(2, endDate);
            statementContract.setInt(3, guitarId);
            statementContract.setInt(4, genreId);
            statementContract.setInt(5, currentSkillLevelId);
            statementContract.setInt(6, targetSkillLevelId);
            statementContract.setInt(7, tutorId);
            statementContract.setInt(8, studentId);

            result = statementContract.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return result == 1;
    }

    @Override
    public List<String> findTutors(String role) throws DaoException {
        List<String> tutorEmailList = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_WITH_ROLE)) {
            statement.setString(1, role);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                String email = set.getString(USER_EMAIL);
                tutorEmailList.add(email);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return tutorEmailList;
    }

    @Override
    public boolean confirmOrder(int orderId) throws DaoException {
        int i = 0;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER)) {
            statement.setInt(1, orderId);
            i = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return i == 1;
    }

    @Override
    public List<Order> findOrders() throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ORDERS)) {
            Order order;
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                order = extractOrder(set);
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public boolean refreshUser(String email, int roleId, int skillId, int degreeId) throws DaoException {
        int result = 0;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_ID);
             PreparedStatement statementUser = connection.prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt(ID);
            }
            statementUser.setInt(1, roleId);
            statementUser.setInt(2, degreeId);
            statementUser.setInt(3, skillId);
            statementUser.setInt(4, id);
            result = statementUser.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return result == 1;
    }

    public boolean banUser(String email) throws DaoException {
        return extractConnection(email, SQL_BAN_USER);
    }

    public boolean unbanUser(String email) throws DaoException {
        return extractConnection(email, SQL_UNBAN_USER);
    }

    private boolean extractConnection(String email, String sql) throws DaoException {

        int i;
        try {
            try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                i = statement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return i == 1;
    }

    @Override
    public List<String> findBannedUsers() throws DaoException {
        List<String> emailList = new ArrayList<>();

        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BANNED_USER)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                emailList.add(set.getString(UserTable.USER_EMAIL));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return emailList;
    }

    private Order extractOrder(ResultSet set) throws SQLException {
        Order order;
        int orderId = set.getInt(OrderTable.ID);
        String email = set.getString(OrderTable.EMAIL);
        String genre = set.getString(OrderTable.GENRE);
        boolean isPaid = set.getBoolean(OrderTable.IS_PAID);
        boolean isConfirmed = set.getBoolean(OrderTable.IS_CONFIRMED);
        order = orderCreator.creteOrder(orderId, email, genre, isPaid, isConfirmed);
        return order;
    }
}
