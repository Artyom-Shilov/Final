package by.shilov.newsSite.service;

import by.shilov.newsSite.dao.ArticleDao;
import by.shilov.newsSite.dao.DaoNames;
import by.shilov.newsSite.domain.Article;
import by.shilov.newsSite.domain.ArticleCategory;
import by.shilov.newsSite.domain.ArticleStatus;
import by.shilov.newsSite.exception.DaoException;
import by.shilov.newsSite.exception.ServiceException;

import java.util.Date;
import java.util.List;

public class ArticleServiceImpl extends ServiceImpl implements ArticleService {

    @Override
    public List<Article> findApprovedArticlesByCategoryForPagination(ArticleCategory category, Integer currentPage, Integer recordsOnPage) throws ServiceException {
        try {
            transaction.receiveConnection();
            ArticleDao articleDao = transaction.createDao(DaoNames.ARTICLE_DAO);
            List<Article> articles = articleDao.readApprovedByCategoryForPagination(category, currentPage, recordsOnPage);
            transaction.freeConnection();
            return articles;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer receiveNumberOfRowsByCategory(String category) throws ServiceException {
        try {
            transaction.receiveConnection();
            ArticleDao articleDao = transaction.createDao(DaoNames.ARTICLE_DAO);
            Integer rowsNumber = articleDao.receiveRecordsNumberByCategory(category);
            transaction.freeConnection();
            return rowsNumber;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Article> findAll() throws ServiceException {
        try {
            transaction.receiveConnection();
            ArticleDao articleDao = transaction.createDao(DaoNames.ARTICLE_DAO);
            List<Article> articles = articleDao.read();
            transaction.freeConnection();
            return articles;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Article findArticleById(Integer id) throws ServiceException {
        try {
            transaction.receiveConnection();
            ArticleDao articleDao = transaction.createDao(DaoNames.ARTICLE_DAO);
            Article article = articleDao.read(id);
            transaction.freeConnection();
            return article;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Article> findArticlesByDate(Date start, Date end) throws ServiceException {
        try {
            transaction.receiveConnection();
            ArticleDao articleDao = transaction.createDao(DaoNames.ARTICLE_DAO);
            List<Article> articles = articleDao.read(start, end);
            transaction.freeConnection();
            return articles;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Article> findArticlesByTitle(String title) throws ServiceException {
        try {
            transaction.receiveConnection();
            ArticleDao articleDao = transaction.createDao(DaoNames.ARTICLE_DAO);
            List<Article> articles = articleDao.read(title);
            transaction.freeConnection();
            return articles;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Article> findArticlesByStatus(ArticleStatus status) throws ServiceException {
        try {
            transaction.receiveConnection();
            ArticleDao articleDao = transaction.createDao(DaoNames.ARTICLE_DAO);
            List<Article> articles = articleDao.read(status);
            transaction.freeConnection();
            return articles;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Article> findArticlesByCategory(ArticleCategory category) throws ServiceException {
        try {
            transaction.receiveConnection();
            ArticleDao articleDao = transaction.createDao(DaoNames.ARTICLE_DAO);
            List<Article> articles = articleDao.read(category);
            transaction.freeConnection();
            return articles;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Article article) throws ServiceException {
        try {
            System.out.println("save");
            System.out.println();
            transaction.receiveConnection();
            ArticleDao articleDao = transaction.createDao(DaoNames.ARTICLE_DAO);
            if (article.getId() == null) {
                article.setId(articleDao.create(article));
            } else {
                articleDao.update(article);
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
            ArticleDao articleDao = transaction.createDao(DaoNames.ARTICLE_DAO);
            articleDao.delete(id);
            transaction.freeConnection();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
