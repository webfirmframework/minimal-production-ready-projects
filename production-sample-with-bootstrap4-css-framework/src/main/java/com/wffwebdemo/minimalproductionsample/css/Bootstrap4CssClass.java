package com.wffwebdemo.minimalproductionsample.css;

import com.webfirmframework.wffweb.tag.html.attribute.global.ClassAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.webfirmframework.wffweb.util.StringUtil.splitBySpace;

/**
 * 
 * Keeping an enum for css classes will prevent developer mistakes declaring
 * class names. It will also help us to know what are the classes available in
 * the whole project.
 * <br><br>
 * It contains bootstrap4 classes but this is not complete. It may also contain
 * bootstrap3 classes so use it carefully. NB: update it with bootstrap4 doc.
 * You can also merge from custom classes, Eg:
 * 
 * <pre>
 * <code>
 * ClassAttribute classAttributeFromCssClasses = Bootstrap4CssClass.BTN_DANGER.merge(CustomCssClass.CUSTOM_CLASS);
 * </code>
 * </pre>
 *
 */
public enum Bootstrap4CssClass implements CssClass {

    FORM_CONTROL("form-control"),

    TEXT_LEFT("text-left"),

    TEXT_CENTER("text-center"),

    TEXT_RIGHT("text-right"),

    BTN_INFO_SM("btn btn-info btn-sm"),

    /**
     * extra small info button
     */
    BTN_INFO_XS("btn btn-info btn-xs"),

    BTN_PRIMARY("btn btn-primary"),

    BTN_PRIMARY_SM("btn btn-primary btn-sm"),

    BTN_PRIMARY_MD("btn btn-primary btn-md"),

    BTN_SUCCESS("btn btn-success"),

    BTN_SUCCESS_SM("btn btn-success btn-sm"),

    BTN_SUCCESS_XS("btn btn-success btn-xs"),

    BTN_DANGER("btn btn-danger"),

    BTN_DANGER_SM("btn btn-danger btn-sm"),

    BTN_DANGER_XS("btn btn-danger btn-xs"),

    BTN_GROUP("btn-group"),

    BTN_GROUP_SM("btn-group btn-group-sm"),

    BTN_GROUP_XS("btn-group btn-group-xs"),

    TABLE_RESPONSIVE("table-responsive"),

    FORM_GROUP("form-group"),

    FORM_INLINE("form-inline"),

    LABEL_INFO("label label-info"),

    LABEL_WARNING("label label-warning"),

    LABEL_DANGER("label label-danger"),

    TEXT_MUTED("text-muted"),

    TEXT_PRIMARY("text-primary"),

    TEXT_SUCCESS("text-success"),

    TEXT_INFO("text-info"),

    TEXT_WARNING("text-warning"),

    TEXT_DANGER("text-danger"),

    ALERT_SUCCESS("alert alert-success"),

    ALERT_INFO("alert alert-info"),

    ALERT_WARNING("alert alert-warning"),

    ALERT_DANGER("alert alert-danger"),

    BTN_LINK("btn btn-link"),

    BTN_SECONDARY("btn btn-secondary"),

    CONTAINER("container"),

    ;

    private final String[] classNames;

    Bootstrap4CssClass(String classNames) {
        this.classNames = splitBySpace(classNames);
    }

    public String[] getClassNames() {
        return classNames;
    }

    public ClassAttribute getClassAttribute() {
        return new ClassAttribute(classNames);
    }

    public ClassAttribute getAttribute() {
        return getClassAttribute();
    }

    public ClassAttribute merge(String... cssClasses) {

        List<String> list = new ArrayList<>();
        Collections.addAll(list, classNames);
        list.addAll(Arrays.asList(cssClasses));

        return new ClassAttribute(list.toArray(new String[list.size()]));
    }

    public ClassAttribute merge(CssClass... cssClasses) {

        List<String> list = new ArrayList<>();
        Collections.addAll(list, classNames);
        for (CssClass cssClass : cssClasses) {
            Collections.addAll(list, cssClass.getClassNames());
        }

        return new ClassAttribute(list.toArray(new String[list.size()]));
    }

    public ClassAttribute merge(CssClass[]... twoDArray) {

        List<String> list = new ArrayList<>();
        Collections.addAll(list, classNames);
        for (CssClass[] cssClasses : twoDArray) {
            for (CssClass cssClass : cssClasses) {
                Collections.addAll(list, cssClass.getClassNames());
            }
        }

        return new ClassAttribute(list.toArray(new String[list.size()]));
    }

}
