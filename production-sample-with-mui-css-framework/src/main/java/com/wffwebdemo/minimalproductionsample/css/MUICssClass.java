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
 * the whole project. <br>
 * <br>
 * It contains MUI classes but this is not complete. NB: update it with MUI doc.
 * You can also merge from custom classes, Eg:
 * 
 * <pre>
 * <code>
 * ClassAttribute classAttributeFromCssClasses = MUICssClass.BTN_DANGER.merge(CustomCssClass.CUSTOM_CLASS);
 * </code>
 * </pre>
 *
 */
public enum MUICssClass implements CssClass {

    BTN_PRIMARY("mui-btn mui-btn--primary"),

    BTN_SMALL("mui-btn--small"),

    BTN_RAISED("mui-btn mui-btn--raised"),

    BTN_ACCENT("mui-btn mui-btn--accent"),

    BTN_DANGER("mui-btn mui-btn--danger"),

    FORM("mui-form"),

    TEXTFIELD("mui-textfield"),

    CONTAINER_FLUID("mui-container-fluid"),

    ;

    private final String[] classNames;

    MUICssClass(String classNames) {
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
