package com.anton.gs.model.repository;

import com.anton.gs.model.exception.DaoException;

public interface UserRepository {
    int activateUser(String email);
    String findUserRoleById(Integer userId) throws DaoException;
    Integer findUserIdByEmail(String userEmail);
}
