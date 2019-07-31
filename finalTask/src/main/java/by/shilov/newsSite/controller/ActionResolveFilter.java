package by.shilov.newsSite.controller;


import by.shilov.newsSite.action.*;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionResolveFilter implements Filter {
    private static Logger logger = Logger.getLogger(ActionResolveFilter.class);

    private static Map<String, ActionNames> actions = new ConcurrentHashMap<>();

    static {
        actions.put("/user_list", ActionNames.USER_LIST_ACTION);
        actions.put("/user_register", ActionNames.USER_REGISTER_ACTION);
        actions.put("/user_login", ActionNames.USER_LOGIN_ACTION);
        actions.put("/mainPage", ActionNames.TO_MAIN_PAGE_ACTION);
        actions.put("/user_logout", ActionNames.USER_LOGOUT_ACTION);
        actions.put("/change_local", ActionNames.CHANGE_LOCAL_ACTION);
        actions.put("/errorPage", ActionNames.TO_ERROR_PAGE_ACTION);
        actions.put("/news_category", ActionNames.NEWS_BY_CATEGORY_ACTION);
        actions.put("/read_article", ActionNames.READ_ARTICLE_ACTION);
        actions.put("/read_article_comments", ActionNames.READ_ARTICLE_COMMENTS_ACTION);
        actions.put("/write_comment", ActionNames.WRITE_COMMENT_ACTION);
        actions.put("/rate_comment", ActionNames.RATE_COMMENT_ACTION);
        actions.put("/personal_cabinet", ActionNames.TO_PERSONAL_CABINET_ACTION);
        actions.put("/write_article", ActionNames.WRITE_ARTICLE_ACTION);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();

            System.out.println(uri);
            System.out.println(contextPath);

            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf('.');
            String actionName;
            if (endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName = uri.substring(beginAction);
            }
            ActionNames name = actions.get(actionName);
            switch (name) {
                case USER_LIST_ACTION:
                    httpRequest.setAttribute("action", new UserListAction());
                    break;
                case USER_REGISTER_ACTION:
                    httpRequest.setAttribute("action", new UserRegisterAction());
                    break;
                case USER_LOGIN_ACTION:
                    httpRequest.setAttribute("action", new UserLoginAction());
                    break;
                case TO_MAIN_PAGE_ACTION:
                    httpRequest.setAttribute("action", new ToMainPageAction());
                    break;
                case USER_LOGOUT_ACTION:
                    httpRequest.setAttribute("action", new UserLogoutAction());
                    break;
                case CHANGE_LOCAL_ACTION:
                    httpRequest.setAttribute("action", new ChangeLocalAction());
                    break;
                case TO_ERROR_PAGE_ACTION:
                    httpRequest.setAttribute("action", new ToErrorPageAction());
                    break;
                case NEWS_BY_CATEGORY_ACTION:
                    httpRequest.setAttribute("action", new NewsByCategoryAction());
                    break;
                case READ_ARTICLE_ACTION:
                    httpRequest.setAttribute("action", new ReadArticleAction());
                    break;
                case READ_ARTICLE_COMMENTS_ACTION:
                    httpRequest.setAttribute("action", new ReadArticleCommentsAction());
                    break;
                case WRITE_COMMENT_ACTION:
                    httpRequest.setAttribute("action", new WriteCommentAction());
                    break;
                case RATE_COMMENT_ACTION:
                    httpRequest.setAttribute("action", new CommentRateAction());
                    break;
                case TO_PERSONAL_CABINET_ACTION:
                    httpRequest.setAttribute("action", new ToPersonalCabinetAction());
                    break;
                case WRITE_ARTICLE_ACTION:
                    httpRequest.setAttribute("action", new WriteArticleAction());
                    break;
            }
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
