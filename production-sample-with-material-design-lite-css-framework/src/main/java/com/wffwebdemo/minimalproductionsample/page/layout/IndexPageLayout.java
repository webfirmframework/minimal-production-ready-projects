package com.wffwebdemo.minimalproductionsample.page.layout;

import java.util.logging.Logger;

import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.Body;
import com.webfirmframework.wffweb.tag.html.Br;
import com.webfirmframework.wffweb.tag.html.H1;
import com.webfirmframework.wffweb.tag.html.Html;
import com.webfirmframework.wffweb.tag.html.TitleTag;
import com.webfirmframework.wffweb.tag.html.attribute.Charset;
import com.webfirmframework.wffweb.tag.html.attribute.Defer;
import com.webfirmframework.wffweb.tag.html.attribute.Href;
import com.webfirmframework.wffweb.tag.html.attribute.Name;
import com.webfirmframework.wffweb.tag.html.attribute.Rel;
import com.webfirmframework.wffweb.tag.html.attribute.Src;
import com.webfirmframework.wffweb.tag.html.attribute.Target;
import com.webfirmframework.wffweb.tag.html.attribute.event.ServerAsyncMethod;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnClick;
import com.webfirmframework.wffweb.tag.html.attribute.global.ClassAttribute;
import com.webfirmframework.wffweb.tag.html.attribute.global.Id;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Button;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Content;
import com.webfirmframework.wffweb.tag.html.links.A;
import com.webfirmframework.wffweb.tag.html.links.Link;
import com.webfirmframework.wffweb.tag.html.metainfo.Head;
import com.webfirmframework.wffweb.tag.html.metainfo.Meta;
import com.webfirmframework.wffweb.tag.html.programming.Script;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.htmlwff.NoTag;
import com.webfirmframework.wffweb.tag.repository.TagRepository;
import com.webfirmframework.wffweb.wffbm.data.BMValueType;
import com.webfirmframework.wffweb.wffbm.data.WffBMObject;
import com.wffwebdemo.minimalproductionsample.page.model.DocumentModel;
import com.wffwebdemo.minimalproductionsample.page.template.SampleTemplate1;

@SuppressWarnings("serial")
public class IndexPageLayout extends Html implements ServerAsyncMethod {
    
    private static final Logger LOGGER = Logger.getLogger(IndexPageLayout.class.getName());

    private DocumentModel documentModel;
    
    private Div mainDiv;

    public IndexPageLayout(DocumentModel documentModel) {
        super(null);
        super.setPrependDocType(true);
        super.setSharedData(documentModel);
        this.documentModel = documentModel;
        develop();
    }

    private void develop() {

        new Head(this) {{
            
            new TitleTag(this) {{
                new NoTag(this, "wffweb with material design lite css example");
            }};
            
            new Meta(this,
                new Charset("utf-8"));
            
            new Meta(this,
                new Name("viewport"),
                new Content("width=device-width, initial-scale=1"));
            
            new Link(this,
                    new Href("https://fonts.googleapis.com/icon?family=Material+Icons"),
                    new Rel(Rel.STYLESHEET));
            
            new Link(this,
                    new Rel(Rel.STYLESHEET),
                    new Href("https://code.getmdl.io/1.3.0/material.indigo-pink.min.css"));
            
            new Link(this,
                    new Rel(Rel.STYLESHEET),
                    new Href("assets/css/app.css"));            
            
            new Script(this,
                    new Defer(),
                    new Src("https://code.getmdl.io/1.3.0/material.min.js"));
                
            new Script(this,
                    new Defer(),
                    new Src("assets/js/app.js"));
            
        }};
        
        new Body(this).give(body -> {
            
            mainDiv = new Div(body, new Id("mainDivId")) .give(div -> {
                new NoTag(div, "Loading...");
            });
            
        });
        
    }
    
    public void buildMainDivTags() {
        Div div = mainDiv;
        div.removeAllChildren();
        
        new NoTag(div, "session id " + documentModel.getHttpSession().getId());
        
        new Br(div);
        
        new A(div, new Href("https://webfirmframework.github.io/developers-guide-wffweb-3/get-started.html"), 
                new Target(Target.BLANK)) {{
            new NoTag(this, "visit webfirmframework developers guide");
        }};
        
        new Br(div);
        
        new A(div, new Href("https://getmdl.io"), 
                new Target(Target.BLANK)) {{
            new NoTag(this, "visit material lite tutorial");
        }};
        
        new H1(div) {{
            new NoTag(this, "Sample with material lite css framework ");  
        }};
        
        new Button(div, new OnClick(IndexPageLayout.this), 
                new ClassAttribute("mdl-button mdl-js-button mdl-button--raised mdl-button--colored")) {{
            new NoTag(this, "Insert SampleTemplate1");
        }};
        
        new Button(div,
                new ClassAttribute("mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"),
                new OnClick("return confirm('Do you want to send some data to server to print in server console?');", 
                (data, event) -> {
                    String value = (String) data.getValue("val");
                    
                    System.out.println("value: " + value);
                    
                    WffBMObject resultData = new WffBMObject();
                    
                    resultData.put("msg", 
                            BMValueType.STRING, 
                            "This is a message received from server!");
                    
                    documentModel.getBrowserPage().getTagRepository().findTitleTag().addInnerHtml(new NoTag(null, "Some other title"));
                    
                    return resultData;
                    },
                "return {val: 'this is from client'};", 
                "if (jsObject && jsObject.msg) {alert(jsObject.msg);}")) {{
            new NoTag(this, "Send data to server");
        }};
        
        
        new Br(div);
        new Br(div);
    }

    @Override
    public WffBMObject asyncMethod(WffBMObject wffBMObject, Event event) {

        TagRepository tagRepository = documentModel.getBrowserPage().getTagRepository();
        
        AbstractHtml mainDiv = tagRepository.findTagById("mainDivId");
        
        final AbstractHtml firstChild = mainDiv.getFirstChild();
        
        System.out.println("mainDiv.getFirstChild() " + firstChild.toHtmlString());
        
        if (mainDiv != null) {
            LOGGER.info("SampleTemplate1 appended");
            mainDiv.appendChild(new SampleTemplate1(documentModel));
            TitleTag titleTag = tagRepository.findOneTagAssignableToTag(TitleTag.class);
            titleTag.addInnerHtml(new NoTag(null, "MDL Example | SampleTemplate1"));
        }
        
        return null;
    }

}
