module com.webfirmframework.wffwebui {
    requires java.logging;
    requires jakarta.servlet;
    requires com.webfirmframework.wffweb;
    requires com.webfirmframework.wffwebcommon;

    exports com.webfirmframework.ui.page.layout;
}