package by.shilov.newsSite.dao.mysql;

import by.shilov.newsSite.dao.CommentRatingDao;
import by.shilov.newsSite.domain.CommentRating;
import by.shilov.newsSite.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRatingDaoImpl extends BaseDaoImpl implements CommentRatingDao {
    private static Logger logger = Logger.getLogger(CommentRatingDaoImpl.class);

    private final String RECEIVE_PLUSES_BY_COMMENT_ID = "SELECT  COUNT(`plus`) FROM `comments_rating` WHERE `evaluated_comment_id` = ? AND `plus` IS NOT FALSE";

    private final String RECEIVE_MINUSES_BY_COMMENT_ID = "SELECT  COUNT(`minus`) FROM `comments_rating` WHERE `evaluated_comment_id` = ? AND `minus` IS NOT FALSE";

    private final String CREATE_BY_USER_ID_AND_COMMENT_ID = "INSERT INTO `comments_rating` (`evaluator_id`, `evaluated_comment_id`, `plus`, `minus`) VALUES (?, ?, FALSE, FALSE)";

    private final String PUT_PLUS = "UPDATE `comments_rating` SET `plus` = TRUE  WHERE `evaluator_id` = ? AND `evaluated_comment_id` = ?";

    private final String PUT_MINUS = "UPDATE `comments_rating` SET `minus` = TRUE  WHERE `evaluator_id` = ? AND `evaluated_comment_id` = ?";

    private final String CHECK = "SELECT  COUNT(`evaluator_id`) FROM `comments_rating` WHERE `evaluator_id` = ? AND `evaluated_comment_id` = ?";

    @Override
    public Integer check(Integer userId, Integer commentId) throws DaoException {
        String sql = CHECK;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, commentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    logger.error("There is no comment_id in comment rating table");
                    throw new DaoException();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Integer receivePlusesForComment(Integer commentId) throws DaoException {
        String sql = RECEIVE_PLUSES_BY_COMMENT_ID;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, commentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    logger.error("There is no comment_id in comment rating table");
                    throw new DaoException();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public Integer receiveMinusesForComment(Integer commentId) throws DaoException {
        String sql = RECEIVE_MINUSES_BY_COMMENT_ID;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, commentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    logger.error("There is no comment_id in comment rating table");
                    throw new DaoException();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void create(Integer userId, Integer commentId) throws DaoException {
        String sql = CREATE_BY_USER_ID_AND_COMMENT_ID;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, commentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void putPlus(Integer userId, Integer commentId) throws DaoException {
        String sql = PUT_PLUS;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, commentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void putMinus(Integer userId, Integer commentId) throws DaoException {
        String sql = PUT_MINUS;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, commentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Integer create(CommentRating entity) throws DaoException {
        return null;
    }

    @Override
    public CommentRating read(Integer identity) throws DaoException {
        return null;
    }

    @Override
    public void update(CommentRating entity) throws DaoException {

    }

    @Override
    public void delete(Integer id) throws DaoException {

    }
}
