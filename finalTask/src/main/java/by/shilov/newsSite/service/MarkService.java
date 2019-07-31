package by.shilov.newsSite.service;

import by.shilov.newsSite.domain.Mark;
import by.shilov.newsSite.exception.ServiceException;

import java.util.List;

public interface MarkService extends Service {

    List<Mark> findAll() throws ServiceException;

    Mark findById(Integer id) throws ServiceException;

    void save(Mark mark) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
