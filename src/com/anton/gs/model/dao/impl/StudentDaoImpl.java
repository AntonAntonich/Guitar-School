package com.anton.gs.model.dao.impl;

import com.anton.gs.model.dao.SqlQueries;
import com.anton.gs.model.dao.StudentDao;
import com.anton.gs.model.dao.table.ContractTable;
import com.anton.gs.model.dao.table.UserTable;
import com.anton.gs.model.entity.contract.Contract;
import com.anton.gs.model.entity.contract.ContractCreator;
import com.anton.gs.model.exception.DaoException;
import com.anton.gs.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.anton.gs.model.dao.SqlQueries.*;

public final class StudentDaoImpl implements StudentDao {
    private static Logger logger = LogManager.getLogger();

    private static StudentDaoImpl studentDao;

    private StudentDaoImpl() {
    }

    public static StudentDaoImpl getInstance() {
        if (studentDao == null) {
            studentDao = new StudentDaoImpl();
        }
        return studentDao;
    }

    @Override
    public boolean updateStudent(String email, int skillId) throws DaoException {
        int result;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_ID);
             PreparedStatement statementUpd = connection.prepareStatement(SQL_UPDATE_STUDENT)) {
            statement.setString(1, email);
            int studentId = 0;

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                studentId = set.getInt(UserTable.USER_BASIC_INFO_ID);
            }
            statementUpd.setInt(1, skillId);
            statementUpd.setInt(2, studentId);
            result = statementUpd.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "troubles with sql process", e);
            throw new DaoException(e);
        }
        return result == 1;
    }

    @Override
    public List<Contract> findCourses(String email) throws DaoException {
        List<Contract> contractList = new ArrayList<>();

        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COURSES)) {
            Contract contract;
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
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
    public List<Contract> findStudents(String email) throws DaoException {
        List<Contract> contractList = new ArrayList<>();

        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_STUDENTS)) {
            Contract contract;
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
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
}
