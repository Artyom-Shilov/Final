package by.shilov.newsSite.dao;

import by.shilov.newsSite.domain.Role;
import by.shilov.newsSite.domain.User;
import by.shilov.newsSite.domain.UserStatus;
import by.shilov.newsSite.exception.DaoException;

import java.util.List;

public interface UserDao extends Dao<User> {

    List<User> read () throws DaoException;

    List<User> read (Role role) throws DaoException;

    List<User> read (UserStatus status) throws DaoException;

    User read(String login, String password) throws DaoException;


}
