module com.webfirmframework.wffwebui {
    requires java.logging;
    requires jakarta.servlet;
    requires com.webfirmframework.wffweb;
    requires com.webfirmframework.wffwebcommon;
    requires org.json;

    exports com.webfirmframework.ui.page.layout;
}