package com.wffwebdemo.minimalproductionsample.page.layout;

import java.util.logging.Logger;

import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.Body;
import com.webfirmframework.wffweb.tag.html.Br;
import com.webfirmframework.wffweb.tag.html.H1;
import com.webfirmframework.wffweb.tag.html.H5;
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
import com.wffwebdemo.minimalproductionsample.page.template.ResponsiveTable;
import com.wffwebdemo.minimalproductionsample.page.template.SampleTemplate1;

@SuppressWarnings("serial")
public class IndexPageLayout extends Html implements ServerAsyncMethod {

    private static final Logger LOGGER = Logger
            .getLogger(IndexPageLayout.class.getName());

    private DocumentModel documentModel;

    public IndexPageLayout(DocumentModel documentModel) {
        super(null);
        super.setPrependDocType(true);
        super.setSharedData(documentModel);
        this.documentModel = documentModel;
        develop();
    }

    // @formatter:off
    private void develop() {

        new Head(this) {{
            new TitleTag(this) {{
                new NoTag(this, "wffweb with foundation css example");
            }};
            new Meta(this,
                new Charset("utf-8"));
            new Meta(this,
                new Name("viewport"),
                new Content("width=device-width, initial-scale=1"));
            
            new Link(this,
                    new Rel(Rel.STYLESHEET),
                    new Href("assets/css/foundation.min.css"));
            new Link(this,
                    new Rel(Rel.STYLESHEET),
                    new Href("assets/css/app.css"));

            new Script(this,
                new Src("https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"));
            
            new Script(this,
                    new Defer(),
                    new Src("assets/js/vendor/what-input.js"));
            
            new Script(this,
                    new Defer(),
                    new Src("assets/js/vendor/foundation.min.js"));
            
            new Script(this,
                    new Defer(),
                    new Src("assets/js/app.js"));
            
        }};
        
        new Body(this) {{
            
            new Div(this, new Id("mainDivId")) {{
                
                new NoTag(this, "session id " + documentModel.getHttpSession().getId());
                
                new Br(this);
                
                new A(this, new Href("https://webfirmframework.github.io/developers-guide-wffweb-3/get-started.html"), 
                        new Target(Target.BLANK)) {{
                    new NoTag(this, "visit webfirmframework developers guide");
                }};
                
                new Br(this);
                
                new A(this, new Href("https://foundation.zurb.com/sites/docs/kitchen-sink.html"), 
                        new Target(Target.BLANK)) {{
                    new NoTag(this, "visit foundation tutorial");
                }};
                
                new H1(this) {{
                    new NoTag(this, "Sample with foundation css framework ");  
                }};
                
                new H5(this) {{
                    new NoTag(this, "I'm a responsive table in foundation css, "
                            + "resize the browser and check. "
                            + "Each row is added with a 1 second delay and "
                            + "Thread is used to add rows asynchronously. "
                            + "You can also click on Insert SampleTemplate1 button to check it.");  
                }};
                
                //responsive table
                new ResponsiveTable(this, documentModel);               
                
                
                new Button(this, new OnClick(IndexPageLayout.this), 
                        new ClassAttribute("button")) {{
                    new NoTag(this, "Insert SampleTemplate1");
                }};
                
                
                new Button(this,
                        new ClassAttribute("alert button"),
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
                
                
                new Br(this);
                new Br(this);
            }};
            
        }};
        
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
            titleTag.addInnerHtml(
                    new NoTag(null, "Foundation CSS Example | SampleTemplate1"));
        }

        return null;
    }

}
