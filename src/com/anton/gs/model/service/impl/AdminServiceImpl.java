package com.anton.gs.model.service.impl;

import com.anton.gs.model.dao.AdminDao;
import com.anton.gs.model.dao.impl.AdminDaoImpl;
import com.anton.gs.model.entity.contract.Contract;
import com.anton.gs.model.entity.order.Order;
import com.anton.gs.model.entity.user.repository.UserRepository;
import com.anton.gs.model.entity.user.repository.UserRepositoryImpl;
import com.anton.gs.model.exception.DaoException;
import com.anton.gs.model.exception.RepositoryException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.AdminService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class AdminServiceImpl implements AdminService {
    private static Logger logger = LogManager.getLogger();

    private static AdminServiceImpl adminService;
    private final AdminDao dao;

    private AdminServiceImpl(){
        dao =  AdminDaoImpl.getInstance();
    }

    public static AdminServiceImpl getInstance(){
        if(adminService == null) {
            adminService = new AdminServiceImpl();
        }
        return adminService;
    }



    @Override
    public List<Contract> showAllContracts() throws ServiceException {
        List<Contract> contractList;
        try{
            contractList = dao.findContracts();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "trying to extract contracts from db failed", e);
            throw new ServiceException(e);
        }
        return contractList;
    }

    @Override
    public boolean createContract(String studentEmail, String genre, String tutorEmail, String startDate, String endDate, String currentLevel, String targetLevel, String guitarType) throws ServiceException {
        boolean result;
        Date startDateDb = Date.valueOf(LocalDate.parse(startDate));
        Date endDateDb = Date.valueOf(LocalDate.parse(endDate));
        try {
            result = dao.createContract(studentEmail, genre, tutorEmail, startDateDb, endDateDb, currentLevel, targetLevel, guitarType);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "creating contract failed following to db from servicec with data student email, " +
                    "genere, tutor email, start date, finish date, current level, target level:"
            + studentEmail + " " + tutorEmail + " " + startDate + " " + endDate + " "
            + currentLevel + " " + targetLevel, e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<String> findTutors(String role) throws ServiceException {
        List<String> tutors = null;
        try{
           tutors =  dao.findTutors(role);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "finding tutors failed ", e);
            throw new ServiceException(e);
        }
        return tutors;
    }

    @Override
    public boolean confirmOrder(int orderId) throws ServiceException {
        boolean result;
        try{
            result = dao.confirmOrder(orderId);
         } catch (DaoException e) {
            logger.log(Level.ERROR, "confirming order failed, order id = " + orderId, e);
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public List<Order> showOrders() throws ServiceException {
        List<Order> orders = null;
        try {
            orders = dao.findOrders();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "finding orders failed ", e);
            throw new ServiceException(e);
        }
        return orders;
    }

    public boolean refreshUser(String email, String role, String skill, String degree) throws ServiceException {
        boolean result;
        int roleId;
        int skillId;
        int degreeId;
        UserRepository<Integer, String> userRepository =  UserRepositoryImpl.getInstance();
        try {
            roleId = userRepository.findValueId(UserRepositoryImpl.ROLE, role);
            skillId = userRepository.findValueId(UserRepositoryImpl.SKILL, skill);
            degreeId = userRepository.findValueId(UserRepositoryImpl.DEGREE, degree);
            result = dao.refreshUser(email, roleId, skillId, degreeId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "refresh users failed " + email, e);
            throw new ServiceException(e);
        } catch (RepositoryException e) {
            logger.log(Level.ERROR, "failed detecting role id, skill id, degree id " + role +
                    " " + skill + " " + degree, e);
            throw new ServiceException(e);
        }
        return result;
    }

    public boolean banUser(String email) throws ServiceException {
        boolean result;
        try {
            result = dao.banUser(email);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "blocking user  failed " + email, e);
            throw new ServiceException(e);
        }
        return result;
    }

    public List<String> findBannedUsers() throws ServiceException {
        List <String> blockedUserEmailList = new ArrayList<>();
        try {
            blockedUserEmailList = dao.findBannedUsers();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "getting banned users failed ", e);
            throw new ServiceException(e);
        }
        return blockedUserEmailList;
    }

    @Override
    public boolean unbanUser(String email) throws ServiceException {
        boolean result;
        try {
            result = dao.unbanUser(email);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "unban user user failed " + email, e);
            throw new ServiceException(e);
        }
        return result;
    }
}
