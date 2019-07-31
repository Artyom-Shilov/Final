package by.shilov.newsSite.dao.mysql;

import by.shilov.newsSite.dao.MarkDao;
import by.shilov.newsSite.domain.Mark;
import by.shilov.newsSite.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarkDaoImpl extends BaseDaoImpl implements MarkDao {
    private static Logger logger = Logger.getLogger(MarkDaoImpl.class);

    private final String READ_ALL = "SELECT `id`, `name`  FROM `marks`";

    private final String CREATE = "INSERT INTO `marks` (`name`) VALUES (?)";

    private final String READ_BY_ID = "SELECT `name` FROM `marks` WHERE `id` = ?";

    private final String UPDATE = "UPDATE `marks` SET `name` = ? WHERE `id` = ?";

    private final String DELETE = "DELETE FROM `marks` WHERE `id` = ?";

    @Override
    public List<Mark> read() throws DaoException {
        String sql = READ_ALL;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Mark mark = null;
        List<Mark> marks = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                mark = new Mark();
                mark.setName(resultSet.getString("name"));
                mark.setId(resultSet.getInt("id"));
                marks.add(mark);
            }
            return marks;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public Integer create(Mark mark) throws DaoException {
        String sql = CREATE;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, mark.getName());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no id in table after adding new mark");
                throw new DaoException("There is no id in table after adding new mark");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public Mark read(Integer id) throws DaoException {
        String sql = READ_BY_ID;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Mark mark = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                mark = new Mark();
                mark.setId(id);
                mark.setName(resultSet.getString("name"));
            }
            return mark;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException e) {
            throw new DaoException(e);
            }
        }
    }

    @Override
    public void update(Mark mark) throws DaoException {
        String sql = UPDATE;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, mark.getName());
            statement.setInt(2, mark.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                throw new DaoException();
            }
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sql = DELETE;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                throw new DaoException(e);
            }
        }
    }
}
