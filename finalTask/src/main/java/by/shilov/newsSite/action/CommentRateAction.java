package by.shilov.newsSite.action;

import by.shilov.newsSite.domain.User;
import by.shilov.newsSite.exception.ActionException;
import by.shilov.newsSite.exception.DaoException;
import by.shilov.newsSite.exception.ServiceException;
import by.shilov.newsSite.service.CommentService;
import by.shilov.newsSite.service.ServiceNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentRateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            User user = (User)request.getSession(false).getAttribute("user");
            if (user == null) {
                request.setAttribute("message", "You must log in");
                request.getRequestDispatcher(ERROR_PAGE_FILE).forward(request, response);
            }
            String character = request.getParameter("character");
            int commentId = Integer.valueOf(request.getParameter("rComment"));
            CommentService service = factory.getService(ServiceNames.COMMENT_SERVICE);
            if (character != null){
                switch (character){
                    case "plus":
                        service.putPlus(user.getId(), commentId);
                        break;
                    case "minus":
                        service.putMinus(user.getId(), commentId);
                        break;
                        default:
                }
            }
        } catch (NumberFormatException | ServiceException | ServletException | IOException e) {
            throw new ActionException(e);
        }
    }
}
