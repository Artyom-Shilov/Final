package by.shilov.newsSite.service;

import by.shilov.newsSite.dao.Transaction;
import by.shilov.newsSite.dao.TransactionFactory;
import by.shilov.newsSite.exception.DaoException;
import by.shilov.newsSite.exception.ServiceException;
import org.apache.log4j.Logger;


public class ServiceFactoryImpl implements ServiceFactory {

    private static Logger logger = Logger.getLogger(ServiceFactoryImpl.class);

    private TransactionFactory transactionFactory;

    public ServiceFactoryImpl() {}

    public ServiceFactoryImpl(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    public void setTransactionFactory(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    public TransactionFactory getTransactionFactory() {
        return transactionFactory;
    }

    @Override
    public <T extends Service> T getService(ServiceNames name) throws ServiceException {
        try {
            ServiceImpl service = null;
            switch (name){
                case USER_SERVICE:
                    service = new UserServiceImpl();
                    break;
                case ARTICLE_SERVICE:
                    service = new ArticleServiceImpl();
                    break;
                case COMMENT_SERVICE:
                    service = new CommentServiceImpl();
                    break;
                default:
                    logger.error("cant't recognize service name");
                    throw new ServiceException();
            }
        Transaction transaction = transactionFactory.createTransaction();
        service.setTransaction(transaction);
        return (T)service;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
