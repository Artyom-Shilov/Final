package by.shilov.newsSite.service;

import by.shilov.newsSite.domain.Role;
import by.shilov.newsSite.domain.User;
import by.shilov.newsSite.domain.UserStatus;
import by.shilov.newsSite.exception.ServiceException;

import java.util.List;

public interface UserService extends Service {

        List<User> findAll() throws ServiceException;

        User findUserByLoginPassword(String login, String password) throws ServiceException;

        User findUserById(Integer id) throws ServiceException;

        List<User> findUsersByRole(Role role) throws ServiceException;

        List<User> findUserByStatus(UserStatus userStatus) throws ServiceException;

        void save(User user) throws ServiceException;

        void delete(Integer id) throws ServiceException;
}
