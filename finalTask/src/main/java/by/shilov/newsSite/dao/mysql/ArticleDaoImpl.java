package by.shilov.newsSite.dao.mysql;

import by.shilov.newsSite.dao.ArticleDao;
import by.shilov.newsSite.domain.Article;
import by.shilov.newsSite.domain.ArticleCategory;
import by.shilov.newsSite.domain.ArticleStatus;
import by.shilov.newsSite.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleDaoImpl extends BaseDaoImpl implements ArticleDao {
    private static Logger logger = Logger.getLogger(ArticleDaoImpl.class);

    private final String READ_ALL = "SELECT `id`, `title`, `text`, `writer_id`, `status`, `creation_date`, `category`, " +
            "`image_url` FROM `articles`";

    private final String READ_BY_TITLE = "SELECT `id`, `title`, `text`, `writer_id`, `status`, `creation_date`, `category`" +
            "`image_url` FROM `articles` WHERE `title` LIKE ? ";

    private final String READ_BY_DATE = "SELECT `id`, `title`, `text`, `writer_id`, `status`, `creation_date`, `category`" +
            "`image_url` FROM `articles` WHERE `cration_date` BETWEEN ? AND ?";

    private final String READ_BY_CATEGORY = "SELECT `id`, `title`, `text`, `writer_id`, `status`, `creation_date`, " +
            "`image_url` FROM `articles` WHERE `category` = ?";

    private final String READ_BY_STATUS = "SELECT `id`, `title`, `text`, `writer_id`, `status`, `creation_date`, `category`, " +
            "`image_url` FROM `articles` WHERE `status` = ?";

    private final String READ_BY_ID = "SELECT `title`, `text`, `writer_id`, `status`, `creation_date`, `category`, " +
            "`image_url` FROM `articles` WHERE `id` = ?";

    private final String CREATE = "INSERT INTO `articles` (`title`, `text`, `writer_id`, `status`, `creation_date`, " +
            "`category`, `image_url`) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private final String UPDATE = "UPDATE `articles` SET `title` = ?, `text` = ?, `writer_id` = ?, `status` = ?, `creation_date` = ?, " +
            "`category` = ?, `image_url` = ?  WHERE `id` = ?";

    private final String DELETE = "DELETE FROM `articles` WHERE `id` = ?";

    private final String READ_BY_MARK_ID = "SELECT `article_id`  FROM `m2m_articles_marks` WHERE mark_id = ?";

    private final String CREATE_RECORD_IN_ARTICLES_MARKS_TABLE = "INSERT INTO `m2m_articles_marks` (`article_id`, `usertag_id`) VALUES (?, ?)";

    private final String RECEIVE_RECORDS_NUMBER_BY_CATEGORY = "SELECT COUNT(id) FROM articles WHERE `status` = 'approved' AND `category` = ?";

    private final String READ_APPROVED_BY_CATEGORY_FOR_PAGINATION = "SELECT `id`, `title`, `text`, `writer_id`, `status`, `creation_date`, " +
            "`image_url` FROM `articles` WHERE `status` = 'approved' AND `category` = ? ORDER BY `id` DESC LIMIT ?, ?";

    @Override
    public List<Article> readApprovedByCategoryForPagination(ArticleCategory category, Integer currentPage, Integer recordsOnPage) throws DaoException {
        String sql = READ_APPROVED_BY_CATEGORY_FOR_PAGINATION;
        int start = currentPage * recordsOnPage - recordsOnPage;
        Article article = null;
        List<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getName());
            statement.setInt(2,start);
            statement.setInt(3, recordsOnPage);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    article = new Article();
                    article.setId(resultSet.getInt("id"));
                    article.setTitle(resultSet.getString("title"));
                    article.setText(resultSet.getString("text"));
                    article.setWriterId(resultSet.getInt("writer_id"));
                    article.setStatus(ArticleStatus.getStatusByString(resultSet.getString("status")));
                    article.setCreationDate(resultSet.getDate("creation_date"));
                    article.setCategory(category);
                    article.setImageUrl(resultSet.getString("image_url"));
                    articles.add(article);
                }
            }
            return articles;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Integer receiveRecordsNumberByCategory(String category) throws DaoException {
        String sql = RECEIVE_RECORDS_NUMBER_BY_CATEGORY;
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, category);
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

    public void createRecordInArticlesMarksTable(Integer articleId, Integer userTagId) throws DaoException {
        String sql = CREATE_RECORD_IN_ARTICLES_MARKS_TABLE;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, articleId);
            statement.setInt(2, userTagId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Article> readByMarkId(Integer markId) throws DaoException {
        String sql = READ_BY_MARK_ID;
        Article article = null;
        List<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, markId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    article = new Article();
                    article.setId(resultSet.getInt("id"));
                    articles.add(article);
                }
                return articles;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public List<Article> read() throws DaoException {
        String sql = READ_ALL;
        Article article = null;
        List<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setText(resultSet.getString("text"));
                article.setWriterId(resultSet.getInt("writer_id"));
                article.setStatus(ArticleStatus.getStatusByString(resultSet.getString("status")));
                article.setCreationDate(resultSet.getDate("creation_date"));
                article.setCategory(ArticleCategory.getCategoryByString(resultSet.getString("category")));
                article.setImageUrl(resultSet.getString("image_url"));
                articles.add(article);
            }
            return articles;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Article> read(String searchTitle) throws DaoException {
        String forSql = "%" + searchTitle + "%";
        String sql = READ_BY_TITLE;
        Article article = null;
        List<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, forSql);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    article = new Article();
                    article.setId(resultSet.getInt("id"));
                    article.setTitle(resultSet.getString("title"));
                    article.setText(resultSet.getString("text"));
                    article.setWriterId(resultSet.getInt("writer_id"));
                    article.setStatus(ArticleStatus.getStatusByString(resultSet.getString("status")));
                    article.setCreationDate(resultSet.getDate("creation_date"));
                    article.setCategory(ArticleCategory.getCategoryByString(resultSet.getString("category")));
                    article.setImageUrl(resultSet.getString("image_url"));
                    articles.add(article);
                }
                return articles;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Article> read(Date searchDateStart, Date searchDateEnd) throws DaoException {
        String sql = READ_BY_DATE;
        Article article = null;
        List<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(searchDateStart.getTime()));
            statement.setDate(2, new java.sql.Date(searchDateStart.getTime()));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    article = new Article();
                    article.setId(resultSet.getInt("id"));
                    article.setTitle(resultSet.getString("title"));
                    article.setText(resultSet.getString("text"));
                    article.setWriterId(resultSet.getInt("writer_id"));
                    article.setStatus(ArticleStatus.getStatusByString(resultSet.getString("status")));
                    article.setCreationDate(resultSet.getDate("creation_date"));
                    article.setCategory(ArticleCategory.getCategoryByString(resultSet.getString("category")));
                    article.setImageUrl(resultSet.getString("image_url"));
                    articles.add(article);
                }
                return articles;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Article> read(ArticleCategory category) throws DaoException {
        String sql = READ_BY_CATEGORY;
        Article article = null;
        List<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getName());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    article = new Article();
                    article.setId(resultSet.getInt("id"));
                    article.setTitle(resultSet.getString("title"));
                    article.setText(resultSet.getString("text"));
                    article.setWriterId(resultSet.getInt("writer_id"));
                    article.setStatus(ArticleStatus.getStatusByString(resultSet.getString("status")));
                    article.setCreationDate(resultSet.getDate("creation_date"));
                    article.setCategory(category);
                    article.setImageUrl(resultSet.getString("image_url"));
                    articles.add(article);
                }
            }
            return articles;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Article> read(ArticleStatus status) throws DaoException {
        String sql = READ_BY_STATUS;
        Article article = null;
        List<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status.getName());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    article = new Article();
                    article.setId(resultSet.getInt("id"));
                    article.setTitle(resultSet.getString("title"));
                    article.setText(resultSet.getString("text"));
                    article.setWriterId(resultSet.getInt("writer_id"));
                    article.setStatus(status);
                    article.setCreationDate(resultSet.getDate("creation_date"));
                    article.setCategory(ArticleCategory.getCategoryByString(resultSet.getString("category")));
                    article.setImageUrl(resultSet.getString("image_url"));
                    articles.add(article);
                }
            }
            return articles;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Integer create(Article article) throws DaoException {
        String sql = CREATE;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getText());
            statement.setInt(3, article.getWriterId());
            statement.setString(4, article.getStatus().getName());
            statement.setString(5, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(article.getCreationDate()));
            statement.setString(6, article.getCategory().toString());
            statement.setString(7, article.getImageUrl());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    logger.error("There is no id in table after adding new article");
                    throw new DaoException("There is no id in table after adding new article");
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Article read(Integer id) throws DaoException {
        String sql = READ_BY_ID;
        Article article = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    article = new Article();
                    article.setId(id);
                    article.setTitle(resultSet.getString("title"));
                    article.setText(resultSet.getString("text"));
                    article.setWriterId(resultSet.getInt("writer_id"));
                    article.setStatus(ArticleStatus.getStatusByString(resultSet.getString("status")));
                    article.setCreationDate(resultSet.getDate("creation_date"));
                    article.setCategory(ArticleCategory.getCategoryByString(resultSet.getString("category")));
                    article.setImageUrl(resultSet.getString("image_url"));
                }
                return article;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void update(Article article) throws DaoException {
        String sql = UPDATE;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getText());
            statement.setInt(3, article.getWriterId());
            statement.setString(4, article.getStatus().getName());
            statement.setString(5, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(article.getCreationDate()));
            statement.setString(6, article.getCategory().toString());
            statement.setString(7, article.getImageUrl());
            statement.setInt(8, article.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void delete(Integer id) throws DaoException {
        String sql = DELETE;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new DaoException(e);
        }
    }
}
