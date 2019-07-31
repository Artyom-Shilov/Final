package by.shilov.newsSite.service;

import by.shilov.newsSite.exception.ServiceException;

public interface ServiceFactory {
    <T extends Service> T getService(ServiceNames key) throws ServiceException;
}
