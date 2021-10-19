package com.anton.gs.model.entity.user.repository;

import com.anton.gs.model.exception.RepositoryException;

public interface UserRepository<K, T> {

    T findValue(T searchType, T value) throws RepositoryException;
    T findValueById(T searchType, K valueId) throws RepositoryException;
    K findValueId(T searchType, T value) throws RepositoryException;

}
