package by.shilov.newsSite.action;

import by.shilov.newsSite.domain.Article;
import by.shilov.newsSite.exception.ActionException;
import by.shilov.newsSite.exception.ServiceException;
import by.shilov.newsSite.service.ArticleService;
import by.shilov.newsSite.service.CommentService;
import by.shilov.newsSite.service.ServiceNames;
import by.shilov.newsSite.service.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReadArticleAction extends Action{

    private final String FORWARDED_PAGE = "/WEB-INF/jsp/article/articleRead.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            int articleId = Integer.valueOf(request.getParameter("id"));
            System.out.println(articleId);
            ArticleService articleService = factory.getService(ServiceNames.ARTICLE_SERVICE);
            UserService userService = factory.getService(ServiceNames.USER_SERVICE);
            CommentService commentService = factory.getService(ServiceNames.COMMENT_SERVICE);
            Article article = articleService.findArticleById(articleId);
            String writerLogin = userService.findUserById(article.getWriterId()).getLogin();
            int commentsNumber = commentService.receiveCommentsNumberByArticleId(articleId);
            System.out.println(commentsNumber);
            request.setAttribute("article", article);
            request.setAttribute("writerLogin", writerLogin);
            request.setAttribute("commentsNumber", String.valueOf(commentsNumber));
            request.getRequestDispatcher(FORWARDED_PAGE).forward(request, response);
        } catch (NumberFormatException | ServiceException | ServletException | IOException e) {
            throw new ActionException(e);
        }
    }
}
