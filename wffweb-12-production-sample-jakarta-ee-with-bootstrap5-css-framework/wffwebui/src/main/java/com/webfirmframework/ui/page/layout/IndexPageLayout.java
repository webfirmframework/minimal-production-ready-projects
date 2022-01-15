package com.webfirmframework.ui.page.layout;

import com.webfirmframework.ui.page.css.Bootstrap5CssClass;
import com.webfirmframework.wffweb.server.page.BrowserPage;
import com.webfirmframework.wffweb.tag.html.*;
import com.webfirmframework.wffweb.tag.html.attribute.*;
import com.webfirmframework.wffweb.tag.html.attribute.event.ServerMethod;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnClick;
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
import com.webfirmframework.ui.page.model.DocumentModel;
import com.webfirmframework.ui.page.template.SampleTemplate1;
import jakarta.servlet.http.HttpSession;

import java.util.logging.Logger;

public class IndexPageLayout extends Html implements ServerMethod {

    private static final Logger LOGGER = Logger
            .getLogger(IndexPageLayout.class.getName());

    private final DocumentModel documentModel;
    
    private Div mainDiv;

    public IndexPageLayout(BrowserPage browserPage, HttpSession httpSession) {
        super(null);
        super.setPrependDocType(true);
        this.documentModel = new DocumentModel(httpSession, browserPage);
        super.setSharedData(documentModel);
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
                    new Href("https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"),
                    new Rel("stylesheet"),
                    new CustomAttribute("integrity", "sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"),
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
                    new Src("https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"),
                    new CustomAttribute("integrity", "sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"),
                    new CustomAttribute("crossorigin", "anonymous"));
            
        });
        
    }
    
    public void buildMainDivTags() {
        Div div = mainDiv;
        div.removeAllChildren();
        
        new NoTag(div, "session id " + documentModel.httpSession().getId());
        
        new Br(div);
        
        new A(div, new Href("https://webfirmframework.github.io/developers-guide-wffweb-3/get-started.html"), 
                new Target(Target.BLANK)) .give(a -> {
            new NoTag(a, "visit webfirmframework developers guide");
        });
        
        new Br(div);
        
        new A(div, new Href("https://getbootstrap.com/docs/5.1/getting-started/introduction/"),
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
                (event) -> {

                    String value = (String) event.data().getValue("val");
                    
                    System.out.println("value: " + value);
                    
                    WffBMObject resultData = new WffBMObject();
                    
                    resultData.put("msg", 
                            BMValueType.STRING, 
                            "This is a message received from server!");
                    
                    documentModel.browserPage().getTagRepository().findTitleTag().addInnerHtml(new NoTag(null, "Some other title"));
                    
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
    public WffBMObject invoke(Event event) {

        TagRepository tagRepository = documentModel.browserPage()
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
