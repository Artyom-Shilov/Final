package by.shilov.newsSite.service;

import by.shilov.newsSite.dao.DaoNames;
import by.shilov.newsSite.dao.UserDao;
import by.shilov.newsSite.domain.Role;
import by.shilov.newsSite.domain.User;
import by.shilov.newsSite.domain.UserStatus;
import by.shilov.newsSite.exception.DaoException;
import by.shilov.newsSite.exception.ServiceException;

import java.util.List;

public class UserServiceImpl extends ServiceImpl implements UserService {

    @Override
    public User findUserById(Integer id) throws ServiceException {
        try {
            transaction.receiveConnection();
            UserDao userDao = transaction.createDao(DaoNames.USER_DAO);
            User user  = userDao.read(id);
            transaction.freeConnection();
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            transaction.receiveConnection();
            UserDao userDao = transaction.createDao(DaoNames.USER_DAO);
            List<User> users = userDao.read();
            transaction.freeConnection();
            return users;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserByLoginPassword(String login, String password) throws ServiceException {
        try {
            transaction.receiveConnection();
            UserDao userDao = transaction.createDao(DaoNames.USER_DAO);
            User user = userDao.read(login, password);
            transaction.freeConnection();
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUsersByRole(Role role) throws ServiceException {
        try {
            transaction.receiveConnection();
            UserDao userDao = transaction.createDao(DaoNames.USER_DAO);
            List<User> users = userDao.read(role);
            transaction.freeConnection();
            return users;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUserByStatus(UserStatus userStatus) throws ServiceException {
        try {
            transaction.receiveConnection();
            UserDao userDao = transaction.createDao(DaoNames.USER_DAO);
            List<User> users = userDao.read(userStatus);
            transaction.freeConnection();
            return users;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            transaction.receiveConnection();
            UserDao userDao = transaction.createDao(DaoNames.USER_DAO);
            if (user.getId() == null) {
                user.setId(userDao.create(user));
            } else {
                userDao.update(user);
            }
            transaction.freeConnection();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            transaction.receiveConnection();
            UserDao userDao = transaction.createDao(DaoNames.USER_DAO);
            userDao.delete(id);
            transaction.freeConnection();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
