package com.webfirmframework.ui.page.component;

import com.webfirmframework.wffweb.tag.html.H1;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.htmlwff.TagContent;

public class ViewItemsComponent extends Div {

    public ViewItemsComponent() {
        super(null);

        develop();
    }

    private void develop() {
        new H1(this).give(TagContent::text, "This is view items");
    }
}
