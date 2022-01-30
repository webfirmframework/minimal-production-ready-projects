package com.webfirmframework.ui.page.component;

import com.webfirmframework.ui.page.common.NavigationURI;
import com.webfirmframework.ui.page.css.Bootstrap5CssClass;
import com.webfirmframework.ui.page.model.DocumentModel;
import com.webfirmframework.ui.page.template.SampleTemplate1;
import com.webfirmframework.ui.page.template.SampleTemplate2;
import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.Br;
import com.webfirmframework.wffweb.tag.html.attribute.Href;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnClick;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Button;
import com.webfirmframework.wffweb.tag.html.links.A;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.htmlwff.NoTag;
import com.webfirmframework.wffweb.tag.htmlwff.TagContent;

public class UserAccountComponent extends Div {

    private final DocumentModel documentModel;

    public UserAccountComponent(DocumentModel documentModel) {
        super(null);
        this.documentModel = documentModel;
        develop();
    }

    private void develop() {
        new Button(this,
                Bootstrap5CssClass.BTN_PRIMARY.getAttribute(),
                new OnClick(event -> {
                    documentModel.httpSession().removeAttribute("loginStatus");
                    documentModel.browserPage().setURI(NavigationURI.LOGIN.getUri(documentModel));
                    return null;
                }))
                .give(TagContent::text, "Logout");


        new Br(this);
        new Br(this);

        //navigation using client side setURI method
        final String itemsNavigationURI = NavigationURI.VIEW_ITEMS.getUri(documentModel);
        new A(this,
                Bootstrap5CssClass.BTN_PRIMARY.getAttribute(),
                new Href(itemsNavigationURI),
                new OnClick("event.preventDefault(); wffAsync.setURI('" + itemsNavigationURI + "');"))
                .give(TagContent::text, "View Items");

        new Br(this);
        new Br(this);

        //navigation using server side setURI method
        new A(this,
                Bootstrap5CssClass.BTN_PRIMARY.getAttribute(),
                new Href(itemsNavigationURI),
                new OnClick("""
                        event.preventDefault();
                        loadingIcon.hidden = false;
                        return true;""", event -> {
                    documentModel.browserPage().setURI(NavigationURI.ADD_ITEM.getUri(documentModel));
                    return null;
                }, null, "loadingIcon.hidden = true;"))
                .give(TagContent::text, "Add Item");


        new Br(this);
        new Br(this);

        var widgetDiv = new Div(this);

        ViewItemsComponent viewItems = new ViewItemsComponent();
        AddItemComponent addItem = new AddItemComponent();

        widgetDiv.whenURI(NavigationURI.VIEW_ITEMS.getPredicate(documentModel), () -> new AbstractHtml[]{viewItems});
        widgetDiv.whenURI(NavigationURI.ADD_ITEM.getPredicate(documentModel), () -> new AbstractHtml[]{addItem});


        SampleTemplate1 sampleTemplate1 = new SampleTemplate1(documentModel);

        SampleTemplate2 sampleTemplate2 = new SampleTemplate2(documentModel);

        widgetDiv.whenURI(NavigationURI.SAMPLE_TEMPLATE1.getPredicate(documentModel), () -> new AbstractHtml[]{sampleTemplate1});

        widgetDiv.whenURI(NavigationURI.SAMPLE_TEMPLATE2.getPredicate(documentModel), () -> new AbstractHtml[]{sampleTemplate2});


        sampleTemplateButtons();
    }

    private void sampleTemplateButtons() {

        String sampleTemplate1URI = NavigationURI.SAMPLE_TEMPLATE1.getUri(documentModel);
        String sampleTemplate2URI = NavigationURI.SAMPLE_TEMPLATE2.getUri(documentModel);

        new Br(this);
        new Br(this);
        new A(this,
                new Href(sampleTemplate1URI),
                new OnClick("event.preventDefault(); wffAsync.setURI('" + sampleTemplate1URI + "', function(){loadingIcon.hidden = false;});"),
                Bootstrap5CssClass.BTN_INFO_SM.getAttribute()).give(TagContent::text, "SampleTemplate1");


        //just for space
        new NoTag(this, " ");


        new A(this,
                new Href(sampleTemplate2URI),
                new OnClick("event.preventDefault(); wffAsync.setURI('" + sampleTemplate2URI + "', function(){loadingIcon.hidden = false;});"),
                Bootstrap5CssClass.BTN_INFO_SM.getAttribute()).give(TagContent::text, "SampleTemplate2");

    }
}
