package com.anton.gs.model.dao;

import com.anton.gs.model.entity.Entity;
import com.anton.gs.model.exception.DaoException;

import java.util.List;

/**
 * The interface Base dao.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 */
public interface BaseDao<K,T extends Entity> {
    /**
     * Find all list.
     * select all entities
     * @return the list
     * @throws DaoException the dao exception
     */
    List<T> findAll() throws DaoException;
}
