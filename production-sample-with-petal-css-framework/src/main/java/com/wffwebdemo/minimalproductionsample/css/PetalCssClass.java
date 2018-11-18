package com.wffwebdemo.minimalproductionsample.css;

import static com.webfirmframework.wffweb.util.StringUtil.splitBySpace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.webfirmframework.wffweb.tag.html.attribute.global.ClassAttribute;

/**
 * 
 * Keeping an enum for css classes will prevent developer mistakes declaring
 * class names. It will also help us to know what are the classes available in
 * the whole project. <br>
 * <br>
 * NB: It doesn't contain all classes from the css framework, update it with the
 * doc. You can also merge from custom classes, Eg:
 * 
 * <pre>
 * <code>
 * ClassAttribute classAttributeFromCssClasses = PetalCssClass.BTN.merge(PetalCssClass.RED, CustomCssClass.CUSTOM_CLASS);
 * </code>
 * </pre>
 *
 */
public enum PetalCssClass implements CssClass {

    BTN("btn"),

    RED("red"),

    GREEN("green"),

    BLUE("blue"),

    FORM_GROUP("form-group"),

    FORM_LABEL("form-label"),

    INPUT("input"),

    FORM_CAPTION("form-caption"),

    HOLLOW("hollow"),

    WHITETEXT("whitetext"),

    CONTAINER("container"),

    ;

    private final String[] classNames;

    PetalCssClass(String classNames) {
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
