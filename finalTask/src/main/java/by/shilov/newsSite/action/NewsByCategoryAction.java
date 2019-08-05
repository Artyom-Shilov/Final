package by.shilov.newsSite.action;

import by.shilov.newsSite.domain.Article;
import by.shilov.newsSite.domain.ArticleCategory;
import by.shilov.newsSite.exception.ActionException;
import by.shilov.newsSite.exception.ServiceException;
import by.shilov.newsSite.service.ArticleService;
import by.shilov.newsSite.service.Service;
import by.shilov.newsSite.service.ServiceNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class NewsByCategoryAction extends Action {
    private final String FORWARDED_PAGE = "/WEB-INF/jsp/article/articlesByCategory.jsp";

    private final int RECORDS_ON_PAGE = 4;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            List<Article> articles = null;
            String category = request.getParameter("category");
            ArticleService service = factory.getService(ServiceNames.ARTICLE_SERVICE);
            int currentPage = Integer.valueOf(request.getParameter("currentPage"));
            int rows = service.receiveNumberOfRowsByCategory(category);
            System.out.println(rows);
            int numberOfPages = (int)Math.ceil((double) rows/RECORDS_ON_PAGE);
            switch (category) {
                case "world":
                    articles = service.findApprovedArticlesByCategoryForPagination(ArticleCategory.WORLD, currentPage, RECORDS_ON_PAGE);
                    request.setAttribute("category", "world");
                    break;
                case "science":
                    articles = service.findApprovedArticlesByCategoryForPagination(ArticleCategory.SCIENCE, currentPage, RECORDS_ON_PAGE);
                    request.setAttribute("category", "science");
                    break;
                case "politics":
                    articles = service.findArticlesByCategory(ArticleCategory.POLITICS);
                    request.setAttribute("category", "politics");
                    break;
                case "accidents":
                    articles = service.findArticlesByCategory(ArticleCategory.ACCIDENTS);
                    request.setAttribute("category", "accidents");
                    break;
                case "technology":
                    articles = service.findArticlesByCategory(ArticleCategory.TECHNOLOGY);
                    request.setAttribute("category", "technology");
                    break;
                case "cars":
                    articles = service.findArticlesByCategory(ArticleCategory.CARS);
                    request.setAttribute("category", "cars");
                    break;
                default:
                    request.setAttribute("message", "This category not found");
                    request.getRequestDispatcher(ERROR_PAGE_FILE).forward(request, response);
            }

            request.setAttribute("newsByCategory", articles);
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", RECORDS_ON_PAGE);
            request.getRequestDispatcher(FORWARDED_PAGE).forward(request, response);
        } catch (NumberFormatException | ServiceException | ServletException | IOException e) {
            throw new ActionException(e);
        }
    }
}
