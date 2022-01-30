package com.webfirmframework.ui.page.component;

import com.webfirmframework.ui.page.common.NavigationURI;
import com.webfirmframework.ui.page.css.Bootstrap5CssClass;
import com.webfirmframework.ui.page.model.DocumentModel;
import com.webfirmframework.wffweb.tag.html.attribute.AttributeNameConstants;
import com.webfirmframework.wffweb.tag.html.attribute.Name;
import com.webfirmframework.wffweb.tag.html.attribute.Type;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnSubmit;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Button;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Form;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Input;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Label;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Placeholder;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.htmlwff.TagContent;

public class LoginComponent extends Div {

    private final DocumentModel documentModel;

    public LoginComponent(DocumentModel documentModel) {
        super(null);
        this.documentModel = documentModel;
        develop();
    }

    private void develop() {
        Div msgDiv = new Div(null);
        new Form(this, new OnSubmit(true, event -> {

            msgDiv.removeAllChildren();
            msgDiv.removeAttributes(AttributeNameConstants.CLASS);

            String username = (String) event.data().getValue("username");
            String password = (String) event.data().getValue("password");

            if ("test".equals(username) && "test".equals(password)) {
                documentModel.httpSession().setAttribute("loginStatus", "true");
                documentModel.browserPage().setURI(NavigationURI.USER.getUri(documentModel));
                return null;
            }

            msgDiv.addAttributes(Bootstrap5CssClass.ALERT_DANGER.getAttribute());
            msgDiv.give(TagContent::text, "Incorrect username or password!");

            return null;
        }, "loadingIcon.hidden = false; return {username: username.value, password: password.value};", "loadingIcon.hidden = true;")).give(form -> {

            new Label(form).give(TagContent::text, "Username: ");
            new Input(form, new Type(Type.TEXT), new Name("username"), new Placeholder("test"));

            new Label(form).give(TagContent::text, "Password: ");
            new Input(form, new Type(Type.PASSWORD), new Name("password"), new Placeholder("test"));

            new Button(form, new Type(Type.SUBMIT), Bootstrap5CssClass.BTN_SECONDARY.getAttribute()).give(TagContent::text, "Login");


        });

        this.appendChild(msgDiv);

    }


}
