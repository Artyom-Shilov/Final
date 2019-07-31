package by.shilov.newsSite.action;

import by.shilov.newsSite.domain.Role;
import by.shilov.newsSite.domain.User;
import by.shilov.newsSite.exception.ActionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToPersonalCabinetAction extends Action {

    private final String FORWARDED_PAGE = "/WEB-INF/jsp/user/personalCabinet.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            User user = (User) request.getSession(false).getAttribute("user");
            if (user == null) {
                request.setAttribute("message", "You must log in");
                request.getRequestDispatcher(ERROR_PAGE_FILE).forward(request, response);
            }
            request.getRequestDispatcher(FORWARDED_PAGE).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new ActionException(e);
        }
    }
}

