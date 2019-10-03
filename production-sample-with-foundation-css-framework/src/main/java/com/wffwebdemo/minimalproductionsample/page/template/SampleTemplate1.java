package com.wffwebdemo.minimalproductionsample.page.template;

import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.Br;
import com.webfirmframework.wffweb.tag.html.H3;
import com.webfirmframework.wffweb.tag.html.attribute.Type;
import com.webfirmframework.wffweb.tag.html.attribute.event.ServerAsyncMethod;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnSubmit;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnClick;
import com.webfirmframework.wffweb.tag.html.attribute.global.ClassAttribute;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Button;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Form;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Input;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Label;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Placeholder;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.htmlwff.NoTag;
import com.webfirmframework.wffweb.tag.repository.TagRepository;
import com.webfirmframework.wffweb.wffbm.data.WffBMObject;
import com.wffwebdemo.minimalproductionsample.page.model.DocumentModel;

@SuppressWarnings("serial")
public class SampleTemplate1 extends Div implements ServerAsyncMethod {

    private DocumentModel documentModel;

    public SampleTemplate1(DocumentModel documentModel) {
        super(null);
        this.documentModel = documentModel;
        develop();
    }

    private void develop() {
        changeTitle();

        new Div(this, new ClassAttribute("grid-x grid-padding-x")).give(div -> {
            new Div(div, new ClassAttribute("large-12 cell")).give(div1 -> {
                new H3(div1).give(h -> {
                    new NoTag(h, "SampleTemplate1.java");
                });
            });
        });

        new Form(this, new OnSubmit("event.preventDefault(); return true;",
                this, null, null)).give(form -> {
                    
                    new Div(form, new ClassAttribute("grid-x grid-padding-x"))
                            .give(div -> {
                                
                                new Div(div,
                                        new ClassAttribute("large-12 cell"))
                                                .give(div1 -> {
                                                    
                                                    new Label(div1)
                                                            .give(label -> {
                                                                
                                                                new NoTag(label,
                                                                        "Input Label");
                                                            });
                                                    
                                                    new Input(div1,
                                                            new Type(Type.TEXT),
                                                            new Placeholder(
                                                                    "large-12.cell"));
                                                });
                            });
                });

        new Br(this);

        new Button(this, new OnClick(SampleTemplate1.this),
                new ClassAttribute("secondary button")).give(btn -> {
                    new NoTag(btn, "Click Me to change to SampleTemplate2");
                });
        new Br(this);
        new Br(this);
    }

    private void changeTitle() {
        // getTagRepository() will give object only if the browserPage.render is
        // returned
        TagRepository tagRepository = documentModel.getBrowserPage()
                .getTagRepository();
        if (tagRepository != null) {
            AbstractHtml title = tagRepository.findTagById("windowTitleId");
            if (title != null) {
                title.addInnerHtml(new NoTag(null, "SampleTemplate1"));
            }
        }
    }

    @Override
    public WffBMObject asyncMethod(WffBMObject wffBMObject, Event event) {

        this.insertBefore(new SampleTemplate2(documentModel));
        this.getParent().removeChild(this);

        return null;
    }
}
