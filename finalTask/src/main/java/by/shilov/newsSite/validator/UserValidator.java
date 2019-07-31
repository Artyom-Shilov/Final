package by.shilov.newsSite.validator;

import by.shilov.newsSite.domain.User;
import by.shilov.newsSite.exception.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class UserValidator{

        public void validate(String login, String password, String name, String surname, Date birthday) throws ValidatorException {
            if (login == null || login.isEmpty()){
                throw new ValidatorException("empty login");
            }
            if (password == null || password.isEmpty()){
                throw new ValidatorException("empty password");
            }
            if (name == null || name.isEmpty()){
                throw new ValidatorException("empty name");
            }
            if (surname == null || surname.isEmpty()){
                throw new ValidatorException("empty surname");
            }
            if (birthday == null){
                throw new ValidatorException("empty birthday");
            }
    }
}
