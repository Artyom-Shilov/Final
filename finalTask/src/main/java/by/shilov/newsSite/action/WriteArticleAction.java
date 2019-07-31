package by.shilov.newsSite.action;

import by.shilov.newsSite.domain.*;
import by.shilov.newsSite.exception.ActionException;
import by.shilov.newsSite.service.ArticleService;
import by.shilov.newsSite.service.ServiceNames;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class WriteArticleAction extends Action {

    private final String FORWARDED_PAGE = "/WEB-INF/jsp/writer/writeArticle.jsp";
    private final String UPLOAD_DIRECTORY = "C:/uploads";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            User user = (User) request.getSession(false).getAttribute("user");
        /*    if (user == null || user.getRole() != Role.WRITER) {
                request.setAttribute("message", "You must log in as writer");
                request.getRequestDispatcher(ERROR_PAGE_FILE).forward(request, response);
            }*/
            if (request.getMethod().equals("GET")){
                request.getRequestDispatcher(FORWARDED_PAGE).forward(request, response);
            }
            if (request.getMethod().equals("POST")) {
                Article article = new Article();
                ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
                List<FileItem> fileItemsList = upload.parseRequest(request);
                Iterator it = fileItemsList.iterator();
                while (it.hasNext()) {
                    FileItem fileItemTemp = (FileItem) it.next();
                    if (fileItemTemp.isFormField()) {
                        if (fileItemTemp.getFieldName().equals("articleTitle")){
                            article.setTitle(fileItemTemp.getString());
                            System.out.println(fileItemTemp.getString());
                        }
                       if (fileItemTemp.getFieldName().equals("articleText")){
                           article.setText(fileItemTemp.getString());
                           System.out.println(fileItemTemp.getString());
                       }
                       if (fileItemTemp.getFieldName().equals("markText")){
                           System.out.println(fileItemTemp.getString());
                       }
                    } else
                        if (fileItemTemp.getFieldName().equals("file")) {
                            if (fileItemTemp.getName()!= null) {
                                String name = new File(fileItemTemp.getName()).getName();
                                fileItemTemp.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                                article.setImageUrl(UPLOAD_DIRECTORY + File.separator + name);
                            }
                        }
                    }

                article.setWriterId(user.getId());
                article.setStatus(ArticleStatus.APPROVED);
                article.setCategory(ArticleCategory.WORLD);
                article.setCreationDate(new Date());
                ArticleService articleService = factory.getService(ServiceNames.ARTICLE_SERVICE);
                articleService.save(article);
            }
        } catch (Exception e) {
            throw new ActionException(e);
        }
    }
}