module com.webfirmframework.wffwebcommon {
    requires java.logging;
    requires com.webfirmframework.wffweb;
    requires com.auth0.jwt;
    requires org.json;
    requires jakarta.servlet;

    exports com.webfirmframework.wffwebcommon;
}