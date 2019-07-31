package by.shilov.newsSite.dao;

import by.shilov.newsSite.exception.DaoException;

public interface Transaction {
    <T extends Dao<?>> T createDao(DaoNames key) throws DaoException;

    public void freeConnection() throws DaoException;

    public void receiveConnection() throws DaoException;

    void commit() throws DaoException;

    void rollback() throws DaoException;
}
