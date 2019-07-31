package by.shilov.newsSite.dao;

import by.shilov.newsSite.domain.Article;
import by.shilov.newsSite.domain.ArticleCategory;
import by.shilov.newsSite.domain.Comment;
import by.shilov.newsSite.exception.DaoException;

import java.util.List;

public interface CommentDao extends Dao<Comment>{

    List<Comment> readByArticleIdForPagination(Integer articleId, Integer currentPage, Integer recordsOnPage) throws DaoException;
    List<Comment> readByArticleId(Integer articleId) throws DaoException;
    int receiveCountByArticleId(Integer articleId) throws DaoException;
    List<Comment> readByUserId(Integer userId) throws DaoException;
}
