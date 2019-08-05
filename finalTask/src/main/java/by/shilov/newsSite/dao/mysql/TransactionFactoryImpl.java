package by.shilov.newsSite.dao.mysql;

import by.shilov.newsSite.dao.Transaction;
import by.shilov.newsSite.dao.TransactionFactory;
import by.shilov.newsSite.dao.pool.ConnectionPool;
import by.shilov.newsSite.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {

    private static Logger logger = Logger.getLogger(TransactionFactoryImpl.class);
    private Connection connection;

    public TransactionFactoryImpl()  {}

    @Override
    public Transaction createTransaction() throws DaoException {
        return new TransactionImpl();

    }
}
