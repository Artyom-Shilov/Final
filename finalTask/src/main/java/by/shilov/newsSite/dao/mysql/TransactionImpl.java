package by.shilov.newsSite.dao.mysql;

import by.shilov.newsSite.dao.Dao;
import by.shilov.newsSite.dao.DaoNames;
import by.shilov.newsSite.dao.Transaction;
import by.shilov.newsSite.dao.pool.ConnectionPool;
import by.shilov.newsSite.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;


public class TransactionImpl implements Transaction {

    private static Logger logger = Logger.getLogger(TransactionImpl.class);

    private Connection connection;

    public TransactionImpl() {}

    public void receiveConnection() throws DaoException{
        this.connection = ConnectionPool.ConnectionPoolHolder.instance.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T extends Dao<?>> T createDao(DaoNames name) throws DaoException {
        BaseDaoImpl dao = null;
        switch (name){
            case USER_DAO:
                dao = new UserDaoImpl();
                break;
            case ARTICLE_DAO:
                dao = new ArticleDaoImpl();
                break;
            case COMMENT_DAO:
                dao = new CommentDaoImpl();
                break;
            case MARK_DAO:
                dao = new MarkDaoImpl();
                break;
            case COMMENTRATING_DAO:
                dao = new CommentRatingDaoImpl();
                break;
            default:
                logger.error("can't recognize Dao name");
                throw new DaoException();
        }
        dao.setConnection(connection);
        return (T)dao;
        }

    public void freeConnection() throws DaoException {
        ConnectionPool.ConnectionPoolHolder.instance.releaseConnection(connection);
    }

    @Override
    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("can't commit transaction", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("can't rollback transaction", e);
            throw new DaoException(e);
        }
    }
}
