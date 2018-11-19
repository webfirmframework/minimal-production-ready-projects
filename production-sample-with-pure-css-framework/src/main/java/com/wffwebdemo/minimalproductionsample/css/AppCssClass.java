package com.wffwebdemo.minimalproductionsample.css;

import static com.webfirmframework.wffweb.util.StringUtil.splitBySpace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.webfirmframework.wffweb.tag.html.attribute.global.ClassAttribute;

/**
 * The custom classes you created it.
 * 
 * You can also merge from custom classes, Eg:
 * 
 * <pre>
 * <code>
 * ClassAttribute classAttributeFromCssClasses = CustomCssClass.CUSTOM_CLASS.merge(AppCssClass.BUTTON_GREEN);
 * 
 * ClassAttribute classAttributeFromCssClasses2 = CustomCssClass.CUSTOM_CLASS.merge(AppCssClass.BUTTON_ERROR, AppCssClass.CUSTOM_CLASS2, CustomCssClass.CUSTOM_CLASS2);
 * </code>
 * </pre>
 *
 */
public enum AppCssClass implements CssClass {

    CUSTOM_CLASS("custom-class"),

    CUSTOM_CLASS2("custom-class2"),

    BUTTON_GREEN("button-success"),

    BUTTON_ERROR("button-error"),
    
    BUTTON_WARNING("button-warning"),

    ;

    private final String[] classNames;

    AppCssClass(String classNames) {
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
