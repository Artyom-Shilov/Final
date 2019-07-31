package by.shilov.newsSite.action;

import by.shilov.newsSite.exception.ActionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToMainPageAction extends Action  {
    private final String FORWARDED_PAGE = "index.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            request.getRequestDispatcher(FORWARDED_PAGE).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new ActionException(e);
        }
    }
}

