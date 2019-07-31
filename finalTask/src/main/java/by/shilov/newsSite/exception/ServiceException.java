package by.shilov.newsSite.exception;

public class ServiceException extends Exception {

    public ServiceException() {}

    public ServiceException(String message, Throwable e) {
        super(message, e);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable e) {
        super(e);
    }
}
