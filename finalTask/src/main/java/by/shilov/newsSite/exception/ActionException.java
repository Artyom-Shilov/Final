package by.shilov.newsSite.exception;

public class ActionException extends Exception {
    public ActionException() {}

    public ActionException(String message, Throwable e) {
        super(message, e);
    }

    public ActionException(String message) {
        super(message);
    }

    public ActionException(Throwable e) {
        super(e);
    }
}
