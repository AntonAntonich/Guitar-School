package com.anton.gs.model.service;

import com.anton.gs.model.entity.contract.Contract;
import com.anton.gs.model.exception.ServiceException;

import java.util.List;

/**
 * The interface Student service.
 * all methods call appropriated methods id dao layer
 */
public interface StudentService {
    /**
     * Show courses list.
     *
     * @param email the email
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Contract> showCourses(String email) throws ServiceException;

    /**
     * Show students list.
     *
     * @param email the email
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Contract> showStudents(String email) throws ServiceException;

    /**
     * Update student boolean.
     *
     * @param email the email
     * @param skill the skill
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateStudent(String email, String skill) throws ServiceException;

}
