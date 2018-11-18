package com.wffwebdemo.minimalproductionsample.page.template;

import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.Br;
import com.webfirmframework.wffweb.tag.html.attribute.Type;
import com.webfirmframework.wffweb.tag.html.attribute.event.ServerAsyncMethod;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnSubmit;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnClick;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Button;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Form;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Input;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Label;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Legend;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Placeholder;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.htmlwff.NoTag;
import com.webfirmframework.wffweb.tag.repository.TagRepository;
import com.webfirmframework.wffweb.wffbm.data.WffBMObject;
import com.wffwebdemo.minimalproductionsample.css.MUICssClass;
import com.wffwebdemo.minimalproductionsample.page.model.DocumentModel;

@SuppressWarnings("serial")
public class SampleTemplate1 extends Div implements ServerAsyncMethod {

    private DocumentModel documentModel;

    public SampleTemplate1(DocumentModel documentModel) {
        super(null, MUICssClass.CONTAINER_FLUID.getAttribute());
        this.documentModel = documentModel;
        develop();
    }

 // @formatter:off
    private void develop() {
        changeTitle();
        
        
        new Form(this, MUICssClass.FORM.getAttribute(), new OnSubmit("event.preventDefault(); return true;", this, null, null)) {{
            new Legend(this) {{
               new NoTag(this, "SampleTemplate1.java"); 
            }};
            new Div(this, MUICssClass.TEXTFIELD.getAttribute()) {{                    
                new Input(this,
                    new Type(Type.EMAIL),
                    new Placeholder("Eg: tech-support@webfirmframework.com"));
                new Label(this) {{
                  new NoTag(this, "Email");  
                }};
            }};
        }};
        
        
        
        new Br(this);
        
        new Button(this, new OnClick(SampleTemplate1.this), MUICssClass.BTN_RAISED.getAttribute()) {{
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
