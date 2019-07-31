package by.shilov.newsSite.action;

import by.shilov.newsSite.domain.Role;
import by.shilov.newsSite.domain.User;
import by.shilov.newsSite.exception.ActionException;
import by.shilov.newsSite.exception.DaoException;
import by.shilov.newsSite.service.ServiceFactory;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class Action {

    protected ServiceFactory factory;

    protected final String ERROR_PAGE = "/errorPage.html";

    protected final String ERROR_PAGE_FILE = "/WEB-INF/jsp/error.jsp";

    public void setFactory(ServiceFactory factory) {
        this.factory = factory;
    }

    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ActionException;
}
