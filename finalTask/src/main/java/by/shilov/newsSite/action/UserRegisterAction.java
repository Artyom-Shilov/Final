package by.shilov.newsSite.action;

import by.shilov.newsSite.domain.Role;
import by.shilov.newsSite.domain.User;
import by.shilov.newsSite.exception.ActionException;
import by.shilov.newsSite.exception.ServiceException;
import by.shilov.newsSite.exception.ValidatorException;
import by.shilov.newsSite.service.ServiceNames;
import by.shilov.newsSite.service.UserService;
import by.shilov.newsSite.validator.UserValidator;
import org.apache.taglibs.standard.extra.spath.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserRegisterAction extends Action {

    private final String FORWARDED_PAGE = "/WEB-INF/jsp/user/register.jsp";
    private final String REDIRECTED_PAGE = "/mainPage.html";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            if (request.getMethod().equals("POST")) {
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date birthday = format.parse(request.getParameter("birthday"));
                UserValidator validator = new UserValidator();
                try {
                    validator.validate(login, password, name, surname, birthday);
                } catch (ValidatorException e) {
                    HttpSession session = request.getSession();
                    session.setAttribute("message", e.getMessage());
                    response.sendRedirect(request.getContextPath() + ERROR_PAGE);
                }
                UserService service = factory.getService(ServiceNames.USER_SERVICE);
                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setName(name);
                user.setSurname(surname);
                user.setRole(Role.VISITOR);
                user.setRegistrationDate(new Date());
                user.setDateOfBirth(birthday);
                service.save(user);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + REDIRECTED_PAGE);
            }
            if (request.getMethod().equals("GET")) {
                request.getRequestDispatcher(FORWARDED_PAGE).forward(request, response);
            }
        } catch (ServiceException | IOException | ServletException | java.text.ParseException e) {
            throw new ActionException(e);
        }
    }
}

