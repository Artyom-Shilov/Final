package by.shilov.newsSite.dao;

import by.shilov.newsSite.domain.Mark;
import by.shilov.newsSite.exception.DaoException;

import java.util.List;

public interface MarkDao extends Dao<Mark> {
    List<Mark> read () throws DaoException;
}
