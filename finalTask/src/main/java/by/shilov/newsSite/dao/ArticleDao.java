package by.shilov.newsSite.dao;



import by.shilov.newsSite.domain.Article;
import by.shilov.newsSite.domain.ArticleCategory;
import by.shilov.newsSite.domain.ArticleStatus;
import by.shilov.newsSite.exception.DaoException;


import java.util.Date;
import java.util.List;

public interface ArticleDao extends Dao<Article> {

    List<Article> readApprovedByCategoryForPagination(ArticleCategory category, Integer currentPage, Integer recordsOnPage) throws DaoException;

    Integer receiveRecordsNumberByCategory(String category) throws DaoException;

    List<Article> readByMarkId(Integer markId) throws DaoException;

    void createRecordInArticlesMarksTable (Integer articleId, Integer markId) throws DaoException;

    List<Article> read() throws DaoException;

    List<Article> read (ArticleCategory category) throws DaoException;

    List<Article> read (ArticleStatus status) throws DaoException;

    List<Article> read (String searchTitle) throws DaoException;

    List<Article> read (Date searchDateStart, Date searchDateEnd) throws DaoException;
}
