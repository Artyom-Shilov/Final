package by.shilov.newsSite.action;

import by.shilov.newsSite.domain.Comment;
import by.shilov.newsSite.exception.ActionException;
import by.shilov.newsSite.exception.ServiceException;
import by.shilov.newsSite.service.ArticleService;
import by.shilov.newsSite.service.CommentService;
import by.shilov.newsSite.service.ServiceNames;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReadArticleCommentsAction extends Action {

    private final int RECORDS_ON_PAGE = 4;
    private final String FORWARDED_PAGE = "/WEB-INF/jsp/comment/commentsRead.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            Integer currentPage = null;
            Integer id = null;
            int flag = 0;

            System.out.println(0);
            if (request.getSession(false).getAttribute("article") != null) {
                System.out.println(1);
                id = (Integer) request.getSession(false).getAttribute("article");
                currentPage = 1;
                Cookie articleCookie = new Cookie("article", id.toString());
                response.addCookie(articleCookie);
                request.getSession(false).removeAttribute("article");
                request.getSession(false).removeAttribute("qComment");
            } else {
                System.out.println(2);
                if (request.getParameter("currentPage") != null && request.getParameter("articleId") != null) {
                    System.out.println(3);
                    currentPage = Integer.valueOf(request.getParameter("currentPage"));
                    id = Integer.valueOf(request.getParameter("articleId"));
                    System.out.println(id);
                    System.out.println(currentPage);
                } else {
                    System.out.println(4);
                    if (request.getCookies() != null) {
                        for (Cookie cookie : request.getCookies()) {
                            if (cookie.getName().equals("article") && cookie != null) {
                                id = Integer.valueOf(cookie.getValue());
                                currentPage = 1;
                                flag++;
                            }
                        }
                    }
                    if (flag == 0) {
                        request.setAttribute("message", "Page not found");
                        request.getRequestDispatcher(ERROR_PAGE_FILE).forward(request, response);
                    }
                }
            }
            CommentService commentService = factory.getService(ServiceNames.COMMENT_SERVICE);
            ArticleService articleService = factory.getService(ServiceNames.ARTICLE_SERVICE);
            String title = articleService.findArticleById(id).getTitle();
            int rows = commentService.receiveCommentsNumberByArticleId(id);
            System.out.println("rows " + rows);
            int numberOfPages = (int) Math.ceil((double) rows / RECORDS_ON_PAGE);
            System.out.println("numberOfPages" + numberOfPages);
            System.out.println(id + " " + currentPage + " " + RECORDS_ON_PAGE);
            Map<Comment, List<Comment>> comments = commentService.receiveCommentsMap(id, currentPage, RECORDS_ON_PAGE);
            request.setAttribute("comments", comments);
            System.out.println(comments.size());
            request.setAttribute("articleId", id);
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", RECORDS_ON_PAGE);
            request.setAttribute("title", title);
            request.getRequestDispatcher(FORWARDED_PAGE).forward(request, response);

        } catch (NumberFormatException | ServiceException | ServletException | IOException e) {
            throw new ActionException(e);
        }
    }
}
