package by.shilov.newsSite.controller;

import by.shilov.newsSite.action.Action;
import by.shilov.newsSite.dao.mysql.TransactionFactoryImpl;
import by.shilov.newsSite.dao.pool.ConnectionPool;
import by.shilov.newsSite.exception.ActionException;
import by.shilov.newsSite.exception.DaoException;
import by.shilov.newsSite.exception.ServiceException;
import by.shilov.newsSite.service.*;
import org.apache.log4j.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(DispatcherServlet.class);
    public static final String LOG_FILE_NAME = "log.txt";
    public static final Level LOG_LEVEL = Level.ALL;
    public static final String LOG_MESSAGE_FORMAT = "%n%d%n%p\t%C.%M:%L%n%m%n";



    public static final String host = "jdbc:mysql://localhost:3306/news_db";
    public static final String login = "root";
    public static final String password = "";
    public static final String driver = "com.mysql.jdbc.Driver";

    public static final int startSize = 5;
    public static final int maxSize = 30;

    public void init() {
        try {
            Logger root = Logger.getRootLogger();
            Layout layout = new PatternLayout(LOG_MESSAGE_FORMAT);
            root.addAppender(new FileAppender(layout, LOG_FILE_NAME, true));
            root.addAppender(new ConsoleAppender(layout));
            root.setLevel(LOG_LEVEL);
            ConnectionPool.ConnectionPoolHolder.instance.init(driver, host, login, password, startSize, maxSize);
        } catch(IOException | DaoException e) {
            logger.error("It is impossible to initialize application", e);
            destroy();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      process(request, response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Action action = (Action) request.getAttribute("action");
        try {
            System.out.println(action.getClass());
            action.setFactory(new ServiceFactoryImpl(new TransactionFactoryImpl()));
            action.execute(request, response);
        } catch (ActionException e){
            e.printStackTrace();
            logger.error(e);
        }
    }
}
