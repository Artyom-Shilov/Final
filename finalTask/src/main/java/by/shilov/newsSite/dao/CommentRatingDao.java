package by.shilov.newsSite.dao;

import by.shilov.newsSite.domain.CommentRating;
import by.shilov.newsSite.exception.DaoException;

public interface CommentRatingDao extends Dao<CommentRating>{

    Integer check(Integer userId, Integer commentId) throws DaoException;

    Integer receivePlusesForComment(Integer commentId) throws DaoException;

    Integer receiveMinusesForComment(Integer commentId) throws DaoException;

    void create(Integer userId, Integer commentId) throws DaoException;

    void putPlus(Integer userId, Integer commentId) throws DaoException;

    void putMinus(Integer userId, Integer commentId) throws DaoException;
}
