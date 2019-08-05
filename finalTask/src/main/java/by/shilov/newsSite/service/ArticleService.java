package by.shilov.newsSite.service;

import by.shilov.newsSite.domain.Article;
import by.shilov.newsSite.domain.ArticleCategory;
import by.shilov.newsSite.domain.ArticleStatus;
import by.shilov.newsSite.domain.Mark;
import by.shilov.newsSite.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface ArticleService extends Service {
    List<Article> findApprovedArticlesByCategoryForPagination(ArticleCategory category, Integer currentPage, Integer recordsOnPage) throws ServiceException;

    Integer receiveNumberOfRowsByCategory(String category) throws ServiceException;

    public List<String> receiveCategoriesList();

    List<Article> findAll() throws ServiceException;

    Article findArticleById(Integer id) throws ServiceException;

    List<Article> findArticlesByDate(Date start, Date end) throws ServiceException;

    List<Article> findArticlesByTitle(String title) throws ServiceException;

    List<Article> findArticlesByStatus(ArticleStatus status) throws ServiceException;

    List<Article> findArticlesByCategory(ArticleCategory category) throws ServiceException;

    /*List<Article> findArticlesByWriter(Integer writerId) throws ServiceException;*/

    /*List<Article> findArticlesByMark(String markName) throws ServiceException;

    void addMarkToArticle(String markName) throws ServiceException;*/

    void save (Article article) throws ServiceException;

    void delete (Integer id) throws ServiceException;

}
