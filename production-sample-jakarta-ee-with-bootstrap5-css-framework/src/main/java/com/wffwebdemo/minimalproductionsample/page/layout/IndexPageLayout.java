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
import com.webfirmframework.wffweb.tag.html.attributewff.CustomAttribute;
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
import com.wffwebdemo.minimalproductionsample.css.Bootstrap5CssClass;
import com.wffwebdemo.minimalproductionsample.page.model.DocumentModel;
import com.wffwebdemo.minimalproductionsample.page.template.SampleTemplate1;

@SuppressWarnings("serial")
public class IndexPageLayout extends Html implements ServerAsyncMethod {

    private static final Logger LOGGER = Logger
            .getLogger(IndexPageLayout.class.getName());

    private DocumentModel documentModel;
    
    private Div mainDiv;

    public IndexPageLayout(DocumentModel documentModel) {
        super(null);
        super.setPrependDocType(true);
        super.setSharedData(documentModel);
        this.documentModel = documentModel;
        develop();
    }

    // @formatter:off
    private void develop() {

        new Head(this).give(head -> {
            new TitleTag(head) {{
                new NoTag(this, "wffweb with bootstrap 5 css example");
            }};
            new Meta(head,
                new Charset("utf-8"));
            new Meta(head,
                new Name("viewport"),
                new Content("width=device-width, initial-scale=1"));
            
            new Link(head,
                    new Href("https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"),
                    new Rel("stylesheet"),
                    new CustomAttribute("integrity", "sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"),
                    new CustomAttribute("crossorigin", "anonymous"));
            new Link(head,
                    new Rel(Rel.STYLESHEET),
                    new Href("assets/css/app.css"));

            
            new Script(head,
                    new Defer(),
                    new Src("assets/js/app.js"));
            
        });
        
        new Body(this).give(body -> {
            
            mainDiv = new Div(body, new Id("mainDivId")) .give(div -> {
                new NoTag(div, "Loading...");
            });
            
            new Script(body,
                    new Src("https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"),
                    new CustomAttribute("integrity", "sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"),
                    new CustomAttribute("crossorigin", "anonymous"));
            
        });
        
    }
    
    public void buildMainDivTags() {
        Div div = mainDiv;
        div.removeAllChildren();
        
        new NoTag(div, "session id " + documentModel.getHttpSession().getId());
        
        new Br(div);
        
        new A(div, new Href("https://webfirmframework.github.io/developers-guide-wffweb-3/get-started.html"), 
                new Target(Target.BLANK)) .give(a -> {
            new NoTag(a, "visit webfirmframework developers guide");
        });
        
        new Br(div);
        
        new A(div, new Href("https://getbootstrap.com/docs/5.0/getting-started/introduction/"), 
                new Target(Target.BLANK)).give(a -> {
            new NoTag(a, "visit bootstrap 5 tutorial");
        });
        
        new H1(div).give(h -> {
            new NoTag(h, "Sample with bootstrap 5 css");  
        });
        
        
        
        new Button(div, new OnClick(IndexPageLayout.this), 
                Bootstrap5CssClass.BTN_PRIMARY.getAttribute()).give(btn -> {
            new NoTag(btn, "Insert SampleTemplate1");
        });
        
        
        new Button(div,
                Bootstrap5CssClass.BTN_INFO.getAttribute(),
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
                "if (jsObject && jsObject.msg) {alert(jsObject.msg);}")).give(btn -> {
            new NoTag(btn, "Send data to server");
        });
        
        
        new Br(div);
        new Br(div);
    }
    // @formatter:on

    @Override
    public WffBMObject asyncMethod(WffBMObject wffBMObject, Event event) {

        TagRepository tagRepository = documentModel.getBrowserPage()
                .getTagRepository();

        AbstractHtml mainDiv = tagRepository.findTagById("mainDivId");

        final AbstractHtml firstChild = mainDiv.getFirstChild();

        System.out.println(
                "mainDiv.getFirstChild() " + firstChild.toHtmlString());

        if (mainDiv != null) {
            LOGGER.info("SampleTemplate1 appended");
            mainDiv.appendChild(new SampleTemplate1(documentModel));
            TitleTag titleTag = tagRepository
                    .findOneTagAssignableToTag(TitleTag.class);
            titleTag.addInnerHtml(new NoTag(null,
                    "Bootstrap 5 CSS Example | SampleTemplate1"));
        }

        return null;
    }

}
