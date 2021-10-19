package com.anton.gs.model.service.impl;

import com.anton.gs.model.dao.StudentDao;
import com.anton.gs.model.dao.impl.StudentDaoImpl;
import com.anton.gs.model.entity.contract.Contract;
import com.anton.gs.model.entity.user.repository.UserRepositoryImpl;
import com.anton.gs.model.exception.DaoException;
import com.anton.gs.model.exception.RepositoryException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.StudentService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public final class StudentServiceImpl implements StudentService {
    private static Logger logger = LogManager.getLogger();


    private StudentDao dao;
    private static StudentServiceImpl studentService;

    private StudentServiceImpl() {
        dao= StudentDaoImpl.getInstance();
    }

    public static StudentServiceImpl getInstance() {
        if(studentService == null) {
            studentService = new StudentServiceImpl();
        }
        return studentService;
    }

    @Override
    public List<Contract> showCourses(String email) throws ServiceException {
        List<Contract> contractList;
        try{
            contractList = dao.findCourses(email);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "showing courses failed, student: " + email, e);
            throw new ServiceException(e);
        }
        return contractList;
    }

    @Override
    public boolean updateStudent(String email, String skill) throws ServiceException {
        boolean result;

        try {
            int skillId = UserRepositoryImpl.getInstance().findValueId(UserRepositoryImpl.SKILL, skill);
            result = dao.updateStudent(email, skillId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "update student failed, student: " + email, e);
            throw new ServiceException(e);
        } catch (RepositoryException e) {
            logger.log(Level.ERROR, "finding skill id  failed, skill: " + skill, e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Contract> showStudents(String email) throws ServiceException {
        List<Contract> contractList;

        try {
            contractList = dao.findStudents(email);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "showing students failed, student: " + email, e);
            throw new ServiceException(e);
        }
        return contractList;
    }
}
