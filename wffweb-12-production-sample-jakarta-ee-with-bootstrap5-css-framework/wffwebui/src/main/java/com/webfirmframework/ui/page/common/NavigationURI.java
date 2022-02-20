package com.webfirmframework.ui.page.common;

import com.webfirmframework.ui.page.model.DocumentModel;
import com.webfirmframework.wffweb.server.page.BrowserPageSession;

import java.util.function.Predicate;

public enum NavigationURI {

    LOGIN("/ui/login", false, false),

    USER("/ui/user/", true, true),

    VIEW_ITEMS("/ui/user/items/view", false, true),

    ADD_ITEM("/ui/user/items/add", false, true),

    SAMPLE_TEMPLATE1("/ui/user/sampletemplate1", false, true),

    SAMPLE_TEMPLATE2("/ui/user/sampletemplate2", false, true);

    private final String uri;

    private final boolean parentPath;

    private final boolean loginRequired;

    NavigationURI(String uri, boolean parentPath, boolean loginRequired) {
        this.uri = uri;
        this.parentPath = parentPath;
        this.loginRequired = loginRequired;
    }

    public Predicate<String> getPredicate(DocumentModel documentModel) {
        BrowserPageSession session = documentModel.session();
        String contextPath = documentModel.contextPath();
        if (NavigationURI.LOGIN.equals(this)) {
            return uri -> !"true".equals(session.userProperties().get("loginStatus")) && contextPath.concat(this.uri).equals(uri);
        }
        if (!loginRequired) {
            return uri -> contextPath.concat(this.uri).equals(uri);
        }
        if (parentPath) {
            return uri -> "true".equals(session.userProperties().get("loginStatus")) && uri.startsWith(contextPath.concat(this.uri));
        }
        return uri -> "true".equals(session.userProperties().get("loginStatus")) && uri.equals(contextPath.concat(this.uri));
    }

    public String getUri(DocumentModel documentModel) {
        return documentModel.contextPath() + uri;
    }
}
