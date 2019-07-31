package by.shilov.newsSite.dao;

import by.shilov.newsSite.domain.ArticleUserTag;
import by.shilov.newsSite.domain.Mark;
import by.shilov.newsSite.exception.DaoException;

import java.util.List;

public interface ArticleUserTagDao extends Dao<ArticleUserTag> {
    List<ArticleUserTag> readByUserTag (Mark mark) throws DaoException;

    void create(Integer articleId, Integer userTagId) throws DaoException;
}
