package by.shilov.newsSite.service;

import by.shilov.newsSite.dao.DaoNames;
import by.shilov.newsSite.dao.MarkDao;
import by.shilov.newsSite.domain.Mark;
import by.shilov.newsSite.exception.DaoException;
import by.shilov.newsSite.exception.ServiceException;

import java.util.List;

public class MarkServiceImpl extends ServiceImpl implements MarkService {

    @Override
    public List<Mark> findAll() throws ServiceException {
        try {
            MarkDao markDao = transaction.createDao(DaoNames.MARK_DAO);
            List<Mark> marks = markDao.read();
            transaction.commit();
            transaction.freeConnection();
            return marks;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException(e);
        }
    }

    @Override
    public Mark findById(Integer id) throws ServiceException {
        try {
            MarkDao markDao = transaction.createDao(DaoNames.MARK_DAO);
            Mark mark = markDao.read(id);
            transaction.commit();
            transaction.freeConnection();
            return mark;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Mark mark) throws ServiceException {
        try {
            MarkDao markDao = transaction.createDao(DaoNames.MARK_DAO);
            if (mark.getId() == null) {
                mark.setId(markDao.create(mark));
            } else {
                markDao.update(mark);
            }
            transaction.commit();
            transaction.freeConnection();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            MarkDao markDao = transaction.createDao(DaoNames.MARK_DAO);
            markDao.delete(id);
            transaction.commit();
            transaction.freeConnection();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1);
            }
            e.printStackTrace();
        }
    }
}
