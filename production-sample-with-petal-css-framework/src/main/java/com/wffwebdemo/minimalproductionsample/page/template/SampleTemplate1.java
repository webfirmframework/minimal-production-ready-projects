package com.wffwebdemo.minimalproductionsample.page.template;

import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.Br;
import com.webfirmframework.wffweb.tag.html.H3;
import com.webfirmframework.wffweb.tag.html.P;
import com.webfirmframework.wffweb.tag.html.attribute.For;
import com.webfirmframework.wffweb.tag.html.attribute.Type;
import com.webfirmframework.wffweb.tag.html.attribute.event.ServerAsyncMethod;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnSubmit;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnClick;
import com.webfirmframework.wffweb.tag.html.attribute.global.Id;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Button;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Form;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Input;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Label;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Placeholder;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.htmlwff.NoTag;
import com.webfirmframework.wffweb.tag.repository.TagRepository;
import com.webfirmframework.wffweb.wffbm.data.WffBMObject;
import com.wffwebdemo.minimalproductionsample.css.PetalCssClass;
import com.wffwebdemo.minimalproductionsample.page.model.DocumentModel;

@SuppressWarnings("serial")
public class SampleTemplate1 extends Div implements ServerAsyncMethod {

    private DocumentModel documentModel;

    public SampleTemplate1(DocumentModel documentModel) {
        super(null, PetalCssClass.CONTAINER.getAttribute());
        this.documentModel = documentModel;
        develop();
    }

 // @formatter:off
    private void develop() {
        changeTitle();
        
        
        new H3(this) {{
            new NoTag(this, "SampleTemplate1.java");
        }};
        
        
        new Form(this, new OnSubmit("event.preventDefault(); return true;", this, null, null)) {{
            new Div(this,
                PetalCssClass.FORM_GROUP.getAttribute()) {{
                    new Label(this, new For("inputId"), PetalCssClass.FORM_LABEL.getAttribute()) {{
                        new NoTag(this, "Email Input");
                    }};
                    new Input(this,
                        new Id("inputId"),    
                        new Type(Type.EMAIL),
                        PetalCssClass.INPUT.getAttribute(),
                        new Placeholder("Eg: tech-support@webfirmframework.com"));
                    new P(this, PetalCssClass.FORM_CAPTION.getAttribute()) {{
                      new NoTag(this, "Enter your email address");  
                    }};
            }};
        }};
        
        
        
        new Br(this);
        
        new Button(this, new OnClick(SampleTemplate1.this), PetalCssClass.BTN.merge(PetalCssClass.BLUE)) {{
            new NoTag(this, "Click Me to change to SampleTemplate2");
        }};
        new Br(this);
        new Br(this);
    }
 // @formatter:on

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
