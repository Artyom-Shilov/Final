package by.shilov.newsSite.dao;

import by.shilov.newsSite.domain.Entity;
import by.shilov.newsSite.exception.DaoException;

public interface Dao <T extends Entity> {

    Integer create(T entity) throws DaoException;

    T read(Integer identity) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(Integer id) throws DaoException;
}
