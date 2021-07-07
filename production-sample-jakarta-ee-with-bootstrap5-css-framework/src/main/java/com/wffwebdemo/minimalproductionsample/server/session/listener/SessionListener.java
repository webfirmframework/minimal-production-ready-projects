package com.wffwebdemo.minimalproductionsample.server.session.listener;

import java.util.logging.Logger;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import com.webfirmframework.wffweb.server.page.BrowserPageContext;
import com.wffwebdemo.minimalproductionsample.page.IndexPage;

@WebListener
public class SessionListener implements HttpSessionListener {

    private static final Logger LOGGER = Logger
            .getLogger(SessionListener.class.getName());

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        LOGGER.info("SessionListener.sessionCreated()");
        // NOP
    }

    @SuppressWarnings("unused")
    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        LOGGER.info("SessionListener.sessionDestroyed()");

        HttpSession session = sessionEvent.getSession();
        Object attrValue = session.getAttribute("indexPageInstanceId");

        if (attrValue != null) {
            String indexPageInstanceId = attrValue.toString();
            IndexPage indexPage = (IndexPage) BrowserPageContext.INSTANCE
                    .getBrowserPage(indexPageInstanceId);

        }

        BrowserPageContext.INSTANCE.httpSessionClosed(session.getId());
    }

}
