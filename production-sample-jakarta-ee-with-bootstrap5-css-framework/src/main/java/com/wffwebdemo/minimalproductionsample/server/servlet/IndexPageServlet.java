package com.wffwebdemo.minimalproductionsample.server.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.webfirmframework.wffweb.server.page.BrowserPageContext;
import com.webfirmframework.wffweb.tag.html.attribute.core.AttributeRegistry;
import com.webfirmframework.wffweb.tag.html.core.TagRegistry;
import com.wffwebdemo.minimalproductionsample.page.IndexPage;
import com.wffwebdemo.minimalproductionsample.page.model.DocumentModel;
import com.wffwebdemo.minimalproductionsample.server.constants.ServerConstants;

/**
 * Servlet implementation class HomePageServlet
 */
@WebServlet({ "/index" })
public class IndexPageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static Logger LOGGER = Logger
            .getLogger(IndexPageServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexPageServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        // optional
        TagRegistry.loadAllTagClasses();
        AttributeRegistry.loadAllAttributeClasses();
        LOGGER.info("Loaded all wffweb classes");
        ServerConstants.CONTEXT_PATH = getServletContext().getContextPath();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        try (OutputStream os = response.getOutputStream();) {

            HttpSession session = request.getSession();
            
            session.setMaxInactiveInterval(ServerConstants.SESSION_TIMEOUT_SECONDS);

            DocumentModel documentModel = new DocumentModel();
            
            documentModel.setHttpSession(request.getSession());

            IndexPage indexPage = new IndexPage(documentModel);

            BrowserPageContext.INSTANCE.addBrowserPage(session.getId(),
                    indexPage);

            indexPage.toOutputStream(os, "UTF-8");
            os.flush();
        }

    }

}
