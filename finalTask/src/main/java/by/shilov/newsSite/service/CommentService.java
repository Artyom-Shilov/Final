package by.shilov.newsSite.service;

import by.shilov.newsSite.dao.pool.ConnectionPool;
import by.shilov.newsSite.domain.Article;
import by.shilov.newsSite.domain.ArticleCategory;
import by.shilov.newsSite.domain.Comment;
import by.shilov.newsSite.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface CommentService extends Service {

    Map<Comment, List<Comment>> receiveCommentsMap(Integer articleId, Integer currentPage, Integer recordsOnPage) throws ServiceException;

    List<Comment> findCommentsByArticleIdForPagination(Integer articleId, Integer currentPage, Integer recordsOnPage) throws ServiceException;

    int receiveCommentsNumberByArticleId(Integer articleId) throws ServiceException;

    List<Comment> findAll() throws ServiceException;

    List<Comment> findCommentsByArticleId (Integer articleId) throws ServiceException;

    Comment findCommentById (Integer commentId) throws ServiceException;

    int receivePlusesForComment(Integer commentId) throws ServiceException;

    int receiveMinusesForComment(Integer commentId) throws ServiceException;

    void save (Comment comment) throws ServiceException;

    void putPlus(Integer userId, Integer commentId) throws ServiceException;

    void putMinus(Integer userId, Integer commentId) throws ServiceException;

    Comment receiveQuotedComment(Comment comment) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
