package by.shilov.newsSite.exception;

import javax.xml.validation.Validator;

public class ValidatorException extends Exception {
    public ValidatorException() {}

    public ValidatorException(String message, Throwable e) {
        super(message, e);
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(Throwable e) {
        super(e);
    }
}

