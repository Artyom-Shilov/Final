package by.shilov.newsSite.dao.mysql;

import by.shilov.newsSite.dao.UserDao;
import by.shilov.newsSite.domain.Role;
import by.shilov.newsSite.domain.User;
import by.shilov.newsSite.domain.UserStatus;
import by.shilov.newsSite.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);

    private final String READ_ALL = "SELECT `id`, `login`, `password`, `role`, `name`, `surname`, `date_of_birth`, `status`," +
            " `registration_date`, icon_url FROM `users` ORDER BY `login`";

    private final String READ_BY_ROLE = "SELECT `id`, `login`, `password`, `name`, `surname`, `date_of_birth`, `status`," +
            " `registration_date`, icon_url FROM `users` WHERE `role` = ?";

    private final String READ_BY_STATUS = "SELECT `id`, `password`, `role`, `name`, `surname`, `date_of_birth`, `status`," +
            " `registration_date`, icon_url FROM `users` WHERE `login` = ? AND `pssword` = ?";

    private final String READ_BY_LOGIN_AND_PASSWORD = "SELECT `id`, `login`, `password`, `role`, `name`, `surname`, `date_of_birth`, `status`," +
            " `registration_date`, icon_url FROM `users` WHERE `login` = ? AND `password` = ?";

    private final String CREATE = "INSERT INTO `users` (`login`, `password`, `role`, `name`, `surname`, `date_of_birth`, `status`," +
            " `registration_date`, `icon_url`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final String READ_BY_ID = "SELECT `login`, `password`, `role`, `name`, `surname`, `date_of_birth`, `status`," +
            " `registration_date`, icon_url FROM `users` WHERE `id` = ?";


    private final String UPDATE = "UPDATE `users` SET `login` = ?, `password` = ?, `role` = ?, `name` = ?, `surname` = ?, " +
            "`date_of_birth` = ?, `status` = ?, `registration_date` = ?, icon_url = ? WHERE `id` = ?";

    private final String DELETE = "DELETE FROM `users` WHERE `id` = ?";

    public List<User> read() throws DaoException {
        String sql = READ_ALL;
        User user = null;
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.getRoleByString(resultSet.getString("role")));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setDateOfBirth(resultSet.getDate("date_of_birth"));
                user.setStatus(UserStatus.getStatusByString(resultSet.getString("status")));
                user.setRegistrationDate(resultSet.getDate("registration_date"));
                user.setIconUrl(resultSet.getString("icon_url"));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<User> read(Role role) throws DaoException {
        String sql = READ_BY_ROLE;
        User user = null;
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.getName());
            try (ResultSet resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(role);
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setDateOfBirth(resultSet.getDate("date_of_birth"));
                    user.setStatus(UserStatus.getStatusByString(resultSet.getString("status")));
                    user.setRegistrationDate(resultSet.getDate("registration_date"));
                    user.setIconUrl(resultSet.getString("icon_url"));
                    users.add(user);
                }
                return users;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<User> read(UserStatus status) throws DaoException {
        String sql = READ_BY_STATUS;
        User user = null;
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status.getName());
            try (ResultSet resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(Role.getRoleByString(resultSet.getString("role")));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setDateOfBirth(resultSet.getDate("date_of_birth"));
                    user.setStatus(status);
                    user.setRegistrationDate(resultSet.getDate("registration_date"));
                    user.setIconUrl(resultSet.getString("icon_url"));
                    users.add(user);
                }
                return users;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public User read(String login, String password) throws DaoException {
        String sql = READ_BY_LOGIN_AND_PASSWORD;
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setLogin(login);
                    user.setPassword(password);
                    user.setRole(Role.getRoleByString(resultSet.getString("role")));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setDateOfBirth(resultSet.getDate("date_of_birth"));
                    user.setStatus(UserStatus.getStatusByString(resultSet.getString("status")));
                    user.setRegistrationDate(resultSet.getDate("registration_date"));
                    user.setIconUrl(resultSet.getString("icon_url"));
                }
                return user;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Integer create(User user) throws DaoException {
        String sql = CREATE;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().getName());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setDate(6, new Date(user.getDateOfBirth().getTime()));
            statement.setString(7, user.getStatus().getName());
            statement.setDate(8, new Date(user.getRegistrationDate().getTime()));
            statement.setString(9, user.getIconUrl());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys();) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    throw new DaoException("There is no id in table after adding new user");
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public User read(Integer id) throws DaoException {
        String sql = READ_BY_ID;
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(id);
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(Role.getRoleByString(resultSet.getString("role")));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setDateOfBirth(resultSet.getDate("date_of_birth"));
                    user.setStatus(UserStatus.getStatusByString(resultSet.getString("status")));
                    user.setRegistrationDate(resultSet.getDate("registration_date"));
                    user.setIconUrl(resultSet.getString("icon_url"));
                }
                return user;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void update(User user) throws DaoException {
        String sql = UPDATE;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().getName());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setDate(6, new Date(user.getDateOfBirth().getTime()));
            statement.setString(7, user.getStatus().getName());
            statement.setDate(8, new Date(user.getRegistrationDate().getTime()));
            statement.setString(9, user.getIconUrl());
            statement.setInt(10, user.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void delete(Integer id) throws DaoException {
        String sql = DELETE;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
