package by.shilov.newsSite.action;

import by.shilov.newsSite.domain.User;
import by.shilov.newsSite.exception.ActionException;
import by.shilov.newsSite.exception.DaoException;
import by.shilov.newsSite.exception.ServiceException;
import by.shilov.newsSite.service.ServiceFactoryImpl;
import by.shilov.newsSite.service.ServiceNames;
import by.shilov.newsSite.service.UserService;
import by.shilov.newsSite.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserListAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ActionException{
        try {
            if(request.getMethod().equals("GET")) {
                UserService service = factory.getService(ServiceNames.USER_SERVICE);
                List<User> users = service.findAll();
                request.setAttribute("users", users);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                throw new ActionException("wrong Method for action");
            }
            } catch (ServiceException | ServletException | IOException e) {
                throw new ActionException(e);
        }
    }
}
