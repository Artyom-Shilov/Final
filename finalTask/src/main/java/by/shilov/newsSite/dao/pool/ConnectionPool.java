package by.shilov.newsSite.dao.pool;

import by.shilov.newsSite.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static Logger logger = Logger.getLogger(ConnectionPool.class);

    private final int CONNECTIONS_LIMIT = 30;


    private String host;
    private String login;
    private String password;
    private int maxSize;
    private ReentrantLock locker = new ReentrantLock();

    private ReentrantLock lockerForRelease = new ReentrantLock();


    private BlockingQueue<Connection> availableConnections = new LinkedBlockingQueue<Connection>();
    private Set<Connection> takenConnections = new HashSet<>();


    public Connection getConnection() throws DaoException {
        try {
            locker.lock();
            Connection connection = null;
            if (!availableConnections.isEmpty()) {
                connection = availableConnections.take();
                takenConnections.add(connection);
            } else {
                if (takenConnections.size() < maxSize) {
                    connection = DriverManager.getConnection(host, login, password);
                    takenConnections.add(connection);
                }
                else {
                    connection = availableConnections.take();
                    takenConnections.add(connection);
                }
            }
            logger.info("Connection was taken  " + connection + " " + connection.getNetworkTimeout());
            return connection;
        } catch (InterruptedException | SQLException e) {
            logger.info("can't get connection from pool");
            throw new DaoException(e);
        } finally {
            locker.unlock();
        }
    }

    public void releaseConnection (Connection connection) throws DaoException{
        try {
            lockerForRelease.lock();
            connection.setAutoCommit(true);
            takenConnections.remove(connection);
            availableConnections.put(connection);
            logger.info("Connection was released  " + connection + " " + connection.getNetworkTimeout());
        } catch (SQLException | InterruptedException e) {
            logger.error("can't return connection into pool", e);
            throw new DaoException(e);
        }finally {
            lockerForRelease.unlock();
        }
    }


    public void init(String driverClass, String host, String login, String password, int startSize, int maxSize) throws DaoException {
        try {
            locker.lock();
            destroy();
            if (maxSize > CONNECTIONS_LIMIT){
                logger.error("max number for connections is more than database limit");
                throw new DaoException();
            }
            destroy();
            Class.forName(driverClass);
            this.host = host;
            this.login = login;
            this.password = password;
            this.maxSize = maxSize;
            for(int i = 0; i < startSize; i++) {
                availableConnections.put(DriverManager.getConnection(host, login, password));
            }
        } catch(ClassNotFoundException | SQLException | InterruptedException e) {
            logger.error("can't to initialize connection pool", e);
            throw new DaoException(e);
        } finally {
            locker.unlock();
        }
    }

    public void destroy() throws SQLException {
        for (Connection connection : availableConnections) {
            connection.close();
        }
        availableConnections.clear();
        for (Connection connection : takenConnections) {
            connection.close();
        }
        takenConnections.clear();
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }

    public static class ConnectionPoolHolder {
        public static final ConnectionPool instance = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.instance;
    }


}