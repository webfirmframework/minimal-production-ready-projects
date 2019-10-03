package com.wffwebdemo.minimalproductionsample.page.template;

import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.H3;
import com.webfirmframework.wffweb.tag.html.attribute.Name;
import com.webfirmframework.wffweb.tag.html.attribute.Type;
import com.webfirmframework.wffweb.tag.html.attribute.event.ServerAsyncMethod;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnSubmit;
import com.webfirmframework.wffweb.tag.html.attribute.global.ClassAttribute;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Button;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Form;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Input;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Label;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Placeholder;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Required;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.htmlwff.NoTag;
import com.webfirmframework.wffweb.tag.repository.TagRepository;
import com.webfirmframework.wffweb.wffbm.data.BMValueType;
import com.webfirmframework.wffweb.wffbm.data.WffBMObject;
import com.wffwebdemo.minimalproductionsample.page.model.DocumentModel;

@SuppressWarnings("serial")
public class SampleTemplate2 extends Div implements ServerAsyncMethod {

    private DocumentModel documentModel;

    public SampleTemplate2(DocumentModel documentModel) {
        super(null);
        this.documentModel = documentModel;
        develop();
    }

    private void develop() {
        changeTitle();

        new H3(this).give(h -> {
            new NoTag(this, "SampleTemplate2.java");
        });
        new Form(this, new OnSubmit("event.preventDefault(); return true;",
                this, "return {name:fullname.value};",
                "console.log(jsObject.msg); alert('The full name is printed in server console');"))
                        .give(form -> {

                            new Div(form,
                                    new ClassAttribute("large-4 medium-4 cell"))
                                            .give(div -> {
                                                new Label(div).give(label -> {
                                                    new NoTag(label,
                                                            "Full Name");
                                                });
                                                new Input(div,
                                                        new Name("fullname"),
                                                        new Type(Type.TEXT),
                                                        new Required(),
                                                        new Placeholder(
                                                                "Your Full Name"));
                                            });
                            new Button(form, new Type(Type.SUBMIT),
                                    new ClassAttribute("success button"))
                                            .give(btn -> {
                                                new NoTag(btn, "Submit");
                                            });
                        });
    }
    
    private void changeTitle() {
        
        // getTagRepository() will give object only if the browserPage.render is returned
        TagRepository tagRepository = documentModel.getBrowserPage().getTagRepository();
        if (tagRepository != null) {
            AbstractHtml title = tagRepository.findTagById("windowTitleId");
            if (title != null) {
                title.addInnerHtml(new NoTag(null, "SampleTemplate2"));
            } 
        }
        
        
        
    }
    
    @Override
    public WffBMObject asyncMethod(WffBMObject data, Event event) {
        
        System.out.println("full name: " + data.getValue("name"));
        
        this.insertBefore(new SampleTemplate1(documentModel));
        this.getParent().removeChild(this);
        
        
        WffBMObject result = new WffBMObject();
        result.put("msg", BMValueType.STRING, "This msg will be printed in the browser console.");
        
        return result;
    }
}
