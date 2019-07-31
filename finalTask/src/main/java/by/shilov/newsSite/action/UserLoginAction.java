package by.shilov.newsSite.action;

import by.shilov.newsSite.domain.User;
import by.shilov.newsSite.exception.ActionException;
import by.shilov.newsSite.exception.ServiceException;
import by.shilov.newsSite.service.ServiceNames;
import by.shilov.newsSite.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserLoginAction extends Action {

    private final String FORWARDED_PAGE = "/WEB-INF/jsp/user/login.jsp";
    private final String REDIRECTED_PAGE = "/mainPage.html";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            if (request.getMethod().equals("POST")) {
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                if (login != null & password != null) {
                    UserService service = factory.getService(ServiceNames.USER_SERVICE);
                    User user = service.findUserByLoginPassword(login, password);
                    if (user != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", user);
                        response.sendRedirect(request.getContextPath() + REDIRECTED_PAGE);
                    } else {
                        HttpSession session = request.getSession();
                        session.setAttribute("message", "User with this login and password not found");
                        response.sendRedirect(request.getContextPath() + ERROR_PAGE);
                    }
                }
            } if (request.getMethod().equals("GET")){
                request.getRequestDispatcher(FORWARDED_PAGE).forward(request, response);
            }
        } catch (ServiceException | ServletException | IOException e){
            throw new ActionException(e);
        }
    }
}
