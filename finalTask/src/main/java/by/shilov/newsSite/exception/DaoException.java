package by.shilov.newsSite.exception;

public class DaoException extends Exception {
    public DaoException() {}

    public DaoException(String message, Throwable e) {
        super(message, e);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable e) {
        super(e);
    }
}
