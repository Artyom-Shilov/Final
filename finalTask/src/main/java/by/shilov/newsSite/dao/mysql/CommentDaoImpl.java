package by.shilov.newsSite.dao.mysql;

import by.shilov.newsSite.dao.CommentDao;
import by.shilov.newsSite.domain.Article;
import by.shilov.newsSite.domain.Comment;
import by.shilov.newsSite.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDaoImpl extends BaseDaoImpl implements CommentDao {

    private static Logger logger = Logger.getLogger(CommentDaoImpl.class);

    private final String READ_BY_ARTICLE_ID = "SELECT `id`, `text`, `creation_date`, `commentator_id`,`quoted_comment_id` FROM `comments` WHERE `article_id` = ? ORDER BY `creation_date`";

    private final String READ_BY_USER_ID = "SELECT `id`, `text`, `creation_date`, `article_id`,`quoted_comment_id` FROM `comments` WHERE `commentator_id` = ?";

    private final String CREATE = "INSERT INTO `comments` (`text`, `creation_date`, `commentator_id`, `article_id`, " +
            "`quoted_comment_id`) VALUES (?, ?, ?, ?, ?)";

    private final String READ_BY_ID = "SELECT `text`, `creation_date`, `commentator_id`, `article_id`,`quoted_comment_id` FROM `comments` WHERE `id` = ?";

    private final String UPDATE = "UPDATE `comments` SET `text` = ?, `creation_date` = ?, `commentator_id` = ?, `article_id` = ?, " +
            "`quoted_comment_id` = ? WHERE `id` = ?";

    private final String RECEIVE_COUNT_BY_ARTICLE_ID = "SELECT COUNT(id) FROM `comments` WHERE `article_id` = ?";

    private final String DELETE = "DELETE FROM `comments` WHERE `id` = ?";

    private final String READ_BY_ARTICLE_ID_FOR_PAGINATION = "SELECT `id`, `text`, `creation_date`, `commentator_id`,`quoted_comment_id` FROM `comments` WHERE `article_id` = ? ORDER BY `id` DESC LIMIT ?, ?";

    @Override
    public List<Comment> readByArticleIdForPagination(Integer articleId, Integer currentPage, Integer recordsOnPage) throws DaoException {
        String sql = READ_BY_ARTICLE_ID_FOR_PAGINATION;
        System.out.println(articleId);
        System.out.println(currentPage);
        System.out.println(recordsOnPage);
        Comment comment = null;
        int start = currentPage * recordsOnPage - recordsOnPage;
        List<Comment> comments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, articleId);
            statement.setInt(2,start);
            statement.setInt(3, recordsOnPage);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    comment = new Comment();
                    comment.setId(resultSet.getInt("id"));
                    comment.setText(resultSet.getString("text"));
                    comment.setCreationDate(new Date(resultSet.getTimestamp("creation_date").getTime()));
                    comment.setCommentatorId(resultSet.getInt("commentator_id"));
                    comment.setArticleId(articleId);
                    comment.setQuotedCommentId(resultSet.getInt("quoted_comment_id"));
                    comments.add(comment);
                }
                return comments;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
    }

    @Override
    public int receiveCountByArticleId(Integer articleId) throws DaoException {
        String sql = RECEIVE_COUNT_BY_ARTICLE_ID;
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, articleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    throw new DaoException("Cant receive number of records");
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Comment> readByArticleId(Integer articleId) throws DaoException {
        String sql = READ_BY_ARTICLE_ID;
        Comment comment = null;
        List<Comment> comments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, articleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    comment = new Comment();
                    comment.setId(resultSet.getInt("id"));
                    comment.setText(resultSet.getString("text"));
                    comment.setCreationDate(resultSet.getDate("creation_date"));
                    comment.setCommentatorId(resultSet.getInt("commentator_id"));
                    comment.setArticleId(resultSet.getInt(articleId));
                    comment.setQuotedCommentId(resultSet.getInt("quoted_comment_id"));
                    comments.add(comment);
                }
                return comments;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Comment> readByUserId(Integer userId) throws DaoException {
        String sql = READ_BY_USER_ID;
        Comment comment = null;
        List<Comment> comments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    comment = new Comment();
                    comment.setId(resultSet.getInt("id"));
                    comment.setText(resultSet.getString("text"));
                    comment.setCreationDate(resultSet.getDate("creation_date"));
                    comment.setCommentatorId(userId);
                    comment.setArticleId(resultSet.getInt("article_id"));
                    comment.setQuotedCommentId(resultSet.getInt("quoted_comment_id"));
                    comments.add(comment);
                }
                return comments;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Integer create(Comment comment) throws DaoException {
        String sql = CREATE;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, comment.getText());
            statement.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comment.getCreationDate()));
            statement.setInt(3, comment.getCommentatorId());
            statement.setInt(4, comment.getArticleId());
            statement.setInt(5, comment.getQuotedCommentId());
            System.out.println();
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    logger.error("There is no id in table after adding new comment");
                    throw new DaoException("There is no id in table after adding new comment");
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Comment read(Integer id) throws DaoException {
        String sql = READ_BY_ID;
        Comment comment = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    comment = new Comment();
                    comment.setId(id);
                    comment.setText(resultSet.getString("text"));
                    comment.setCreationDate(new Date(resultSet.getTimestamp("creation_date").getTime()));
                    comment.setCommentatorId(resultSet.getInt("commentator_id"));
                    comment.setArticleId(resultSet.getInt("article_id"));
                    comment.setQuotedCommentId(resultSet.getInt("quoted_comment_id"));
                }
                return comment;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void update(Comment comment) throws DaoException {
        String sql = UPDATE;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, comment.getText());
            statement.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comment.getCreationDate()));
            statement.setInt(3, comment.getCommentatorId());
            statement.setInt(4, comment.getArticleId());
            statement.setInt(5, comment.getQuotedCommentId());
            statement.setInt(6, comment.getId());

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
