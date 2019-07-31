package by.shilov.newsSite.service;

import by.shilov.newsSite.dao.CommentDao;
import by.shilov.newsSite.dao.CommentRatingDao;
import by.shilov.newsSite.dao.DaoNames;
import by.shilov.newsSite.dao.UserDao;
import by.shilov.newsSite.domain.Article;
import by.shilov.newsSite.domain.ArticleCategory;
import by.shilov.newsSite.domain.Comment;
import by.shilov.newsSite.domain.User;
import by.shilov.newsSite.exception.DaoException;
import by.shilov.newsSite.exception.ServiceException;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.*;


public class CommentServiceImpl extends ServiceImpl implements CommentService {

    //Transaction
    public Map<Comment, List<Comment>> receiveCommentsMap(Integer articleId, Integer currentPage, Integer recordsOnPage) throws ServiceException {
        try {
            Map<Comment, List<Comment>> commentsMap = new TreeMap<>(new Comparator<Comment>() {
                @Override
                public int compare(Comment o1, Comment o2) {
                    return o2.getId() - o1.getId();
                }
            });
            Comment com;
            User user;
            transaction.receiveConnection();
            CommentDao commentDao = transaction.createDao(DaoNames.COMMENT_DAO);
            UserDao userDao = transaction.createDao(DaoNames.USER_DAO);
            CommentRatingDao commentRatingDao = transaction.createDao(DaoNames.COMMENTRATING_DAO);
            int quotedCommentId;
            List<Comment> comments = commentDao.readByArticleIdForPagination(articleId, currentPage, recordsOnPage);
            List<Comment> commentMapList = new ArrayList<>();
            for (Comment comment : comments){
                user = userDao.read(comment.getCommentatorId());
                Integer pluses = commentRatingDao.receivePlusesForComment(comment.getId());
                Integer minuses = commentRatingDao.receiveMinusesForComment(comment.getId());
                comment.setCommentatorLogin(user.getLogin());
                comment.setPlusNumber(pluses);
                comment.setMinusNumber(minuses);
                quotedCommentId = comment.getQuotedCommentId();
                if (quotedCommentId == 0){
                    commentsMap.put(comment, null);
                    continue;
                }
                while (quotedCommentId != 0) {
                    com = commentDao.read(quotedCommentId);
                    user = userDao.read(com.getCommentatorId());
                    com.setCommentatorLogin(user.getLogin());
                    commentMapList.add(com);
                    quotedCommentId = com.getQuotedCommentId();
                }
                commentsMap.put(comment, commentMapList);
                commentMapList = new ArrayList<>();
            }
            return commentsMap;
        } catch (DaoException e){
            throw new ServiceException();
        }
    }


    @Override
    public List<Comment> findCommentsByArticleIdForPagination(Integer articleId, Integer currentPage, Integer recordsOnPage) throws ServiceException {
        try {
            transaction.receiveConnection();
            CommentDao commentDao = transaction.createDao(DaoNames.COMMENT_DAO);
            List<Comment> comments = commentDao.readByArticleIdForPagination(articleId, currentPage, recordsOnPage);
            transaction.freeConnection();
            return comments;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public int receiveCommentsNumberByArticleId(Integer articleId) throws ServiceException {
        try {
            transaction.receiveConnection();
            CommentDao commentDao = transaction.createDao(DaoNames.COMMENT_DAO);
            int count = commentDao.receiveCountByArticleId(articleId);
            transaction.freeConnection();
            return count;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Comment> findAll() throws ServiceException {
        return null;
    }

    @Override
    public List<Comment> findCommentsByArticleId(Integer articleId) throws ServiceException {
        try {
            transaction.receiveConnection();
            CommentDao commentDao = transaction.createDao(DaoNames.COMMENT_DAO);
            List<Comment> comments = commentDao.readByArticleId(articleId);
            transaction.freeConnection();
            return comments;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment findCommentById(Integer commentId) throws ServiceException {
        try {
            transaction.receiveConnection();
            CommentDao commentDao = transaction.createDao(DaoNames.COMMENT_DAO);
            Comment comment = commentDao.read(commentId);
            transaction.freeConnection();
            return comment;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int receivePlusesForComment(Integer commentId) throws ServiceException {
        return 0;
    }

    @Override
    public int receiveMinusesForComment(Integer commentId) throws ServiceException {
        return 0;
    }

    @Override
    public void save(Comment comment) throws ServiceException {
        try {
            transaction.receiveConnection();
            CommentDao commentDao = transaction.createDao(DaoNames.COMMENT_DAO);
            if (comment.getId() == null) {
                comment.setId(commentDao.create(comment));
            } else {
                commentDao.update(comment);
            }
            transaction.freeConnection();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void putPlus(Integer userId, Integer commentId) throws ServiceException {
        try {
            transaction.receiveConnection();
            CommentRatingDao commentRatingDao = transaction.createDao(DaoNames.COMMENTRATING_DAO);
            int rowsNumber = commentRatingDao.check(userId, commentId);
            if (rowsNumber == 0) {
                commentRatingDao.create(userId, commentId);
            }
            commentRatingDao.putPlus(userId, commentId);
            transaction.freeConnection();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void putMinus(Integer userId, Integer commentId) throws ServiceException {
        try {
            transaction.receiveConnection();
            CommentRatingDao commentRatingDao = transaction.createDao(DaoNames.COMMENTRATING_DAO);
            int rowsNumber = commentRatingDao.check(userId, commentId);
            System.out.println("check " + rowsNumber);
            if (rowsNumber == 0) {
                System.out.println("create");
                commentRatingDao.create(userId, commentId);
            }
            System.out.println("put");
            commentRatingDao.putMinus(userId, commentId);
            transaction.freeConnection();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment receiveQuotedComment(Comment comment) throws ServiceException {
        return null;
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            transaction.receiveConnection();
            CommentDao commentDao = transaction.createDao(DaoNames.COMMENT_DAO);
            commentDao.delete(id);
            transaction.freeConnection();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
