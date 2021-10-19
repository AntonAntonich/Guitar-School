package com.anton.gs.model.dao;

import com.anton.gs.model.entity.contract.Contract;
import com.anton.gs.model.exception.DaoException;

import java.util.List;

/**
 * The interface Student dao.
 */
public interface StudentDao {
    /**
     * Find courses list.
     * select contracts of student having email equal incoming
     * @param email the email
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Contract> findCourses(String email) throws DaoException;

    /**
     * Find students list.
     * select student contract where tutor account name equal incoming
     * @param email the email
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Contract> findStudents(String email) throws DaoException;

    /**
     * Update student boolean.
     * change id of skill student where email equal incoming
     * @param email   the email
     * @param skillId the skill id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateStudent(String email, int skillId) throws DaoException;
}
