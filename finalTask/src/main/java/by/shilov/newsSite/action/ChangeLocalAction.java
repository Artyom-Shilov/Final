package by.shilov.newsSite.action;

import by.shilov.newsSite.exception.ActionException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocalAction extends Action {

    private final String REDIRECTED_PAGE = "/mainPage.html";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            int langCookieNumber = 0;
            if (request.getParameter("locale")!=null) {
                if (request.getCookies() != null) {
                    for (Cookie cookie : request.getCookies()) {
                        if (cookie.getName().equals("language")) {
                            langCookieNumber++;
                            cookie.setValue(request.getParameter("locale"));
                            response.addCookie(cookie);
                        }
                    }
                } if (langCookieNumber == 0) {
                    response.addCookie(new Cookie("language", request.getParameter("locale")));
                }
                response.sendRedirect(request.getContextPath() + REDIRECTED_PAGE);
            }
        } catch (IOException e) {
            throw new ActionException(e);
        }
    }
}
