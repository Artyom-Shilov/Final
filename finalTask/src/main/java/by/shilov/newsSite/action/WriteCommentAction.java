package by.shilov.newsSite.action;

import by.shilov.newsSite.domain.Comment;
import by.shilov.newsSite.domain.User;
import by.shilov.newsSite.exception.ActionException;
import by.shilov.newsSite.exception.ServiceException;
import by.shilov.newsSite.service.CommentService;
import by.shilov.newsSite.service.ServiceNames;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class WriteCommentAction extends Action {

    private final String FORWARDED_PAGE = "/WEB-INF/jsp/comment/commentWrite.jsp";
    private final String REDIRECTED_PAGE_1 = "/read_article_comments.html";
    private final String REDIRECTED_PAGE_2 = "/mainPage.html";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        int articleId = 0;
        int qCommentId = 0;
        try {
            if (request.getSession(false).getAttribute("user") == null) {
                request.setAttribute("message", "You must log in");
                request.getRequestDispatcher(ERROR_PAGE_FILE).forward(request, response);
            }
            if (request.getMethod().equals("GET")){
                articleId = Integer.valueOf(request.getParameter("article"));
                request.getSession(false).setAttribute("article", articleId);
                if (request.getParameter("qComment")!= null) {
                    qCommentId = Integer.valueOf(request.getParameter("qComment"));
                }
                request.getSession(false).setAttribute("qComment", qCommentId);
                request.getRequestDispatcher(FORWARDED_PAGE).forward(request, response);
            }

            if (request.getMethod().equals("POST")){
                System.out.println(request.getSession(false).getAttribute("article"));
                if (request.getSession(false).getAttribute("article") == null){
                    response.sendRedirect(request.getContextPath() + REDIRECTED_PAGE_2);
                    return;
                }
                String commentText = request.getParameter("commentText");
                CommentService service = factory.getService(ServiceNames.COMMENT_SERVICE);
                Comment comment = new Comment();
                comment.setCommentatorId(((User)request.getSession(false).getAttribute("user")).getId());
                comment.setText(commentText);
                comment.setCreationDate(new Date());
                comment.setQuotedCommentId((Integer)request.getSession(false).getAttribute("qComment"));
                comment.setArticleId((Integer)request.getSession(false).getAttribute("article"));
                service.save(comment);
                response.sendRedirect(request.getContextPath() + REDIRECTED_PAGE_1);
            }
        }catch (NumberFormatException | ServiceException |ServletException | IOException e){
            throw new ActionException(e);
        }
    }
}
