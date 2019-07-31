package by.shilov.newsSite.dao;

import by.shilov.newsSite.exception.DaoException;

public interface TransactionFactory {
    Transaction createTransaction() throws DaoException;
}
