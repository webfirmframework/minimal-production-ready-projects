package com.webfirmframework.ui.page.component;

import com.webfirmframework.ui.page.common.NavigationURI;
import com.webfirmframework.ui.page.css.Bootstrap5CssClass;
import com.webfirmframework.ui.page.model.DocumentModel;
import com.webfirmframework.ui.page.template.SampleTemplate1;
import com.webfirmframework.ui.page.template.SampleTemplate2;
import com.webfirmframework.wffweb.server.page.BrowserPage;
import com.webfirmframework.wffweb.server.page.BrowserPageContext;
import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.Br;
import com.webfirmframework.wffweb.tag.html.URIStateSwitch;
import com.webfirmframework.wffweb.tag.html.attribute.Href;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnClick;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Button;
import com.webfirmframework.wffweb.tag.html.links.A;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.htmlwff.NoTag;
import com.webfirmframework.wffweb.tag.htmlwff.TagContent;

import java.util.Collection;

public class UserAccountComponent extends Div {

    private final DocumentModel documentModel;

    // no need use volatile modifier, the framework internally handles it unless
    // the value is assigned via a custom thread.
    private AbstractHtml widgetDivCurrentChild;

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
                    //gets all browser pages associated with this session and navigate to login page
                    Collection<BrowserPage> browserPages = BrowserPageContext.INSTANCE.getBrowserPages(documentModel.httpSession().getId()).values();
                    for (BrowserPage browserPage : browserPages) {
                        if (BrowserPageContext.INSTANCE.existsAndValid(browserPage)) {
                            browserPage.setURI(NavigationURI.LOGIN.getUri(documentModel));
                        }
                    }
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

        URIStateSwitch widgetDiv = new Div(this);

        widgetDiv.whenURI(NavigationURI.VIEW_ITEMS.getPredicate(documentModel),
                () -> {
                    documentModel.browserPage().getTagRepository().findTitleTag().give(
                            TagContent::text, "View Items | User Account | wffweb demo");
                    if (!(widgetDivCurrentChild instanceof ViewItemsComponent)) {
                        widgetDivCurrentChild = new ViewItemsComponent();
                    }
                    return new AbstractHtml[]{widgetDivCurrentChild};
                });

        widgetDiv.whenURI(NavigationURI.ADD_ITEM.getPredicate(documentModel),
                () -> {
                    documentModel.browserPage().getTagRepository().findTitleTag().give(
                            TagContent::text, "Add Item | User Account | wffweb demo");
                    if (!(widgetDivCurrentChild instanceof AddItemComponent)) {
                        widgetDivCurrentChild = new AddItemComponent();
                    }
                    return new AbstractHtml[]{widgetDivCurrentChild};
                });

        widgetDiv.whenURI(NavigationURI.SAMPLE_TEMPLATE1.getPredicate(documentModel),
                () -> {
                    documentModel.browserPage().getTagRepository().findTitleTag().give(
                            TagContent::text, "SampleTemplate1 | User Account | wffweb demo");
                    if (!(widgetDivCurrentChild instanceof SampleTemplate1)) {
                        widgetDivCurrentChild = new SampleTemplate1(documentModel);
                    }
                    return new AbstractHtml[]{widgetDivCurrentChild};
                });

        widgetDiv.whenURI(NavigationURI.SAMPLE_TEMPLATE2.getPredicate(documentModel),
                () -> {
                    documentModel.browserPage().getTagRepository().findTitleTag().give(
                            TagContent::text, "SampleTemplate2 | User Account | wffweb demo");
                    if (!(widgetDivCurrentChild instanceof SampleTemplate2)) {
                        widgetDivCurrentChild = new SampleTemplate2(documentModel);
                    }
                    return new AbstractHtml[]{widgetDivCurrentChild};
                });

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