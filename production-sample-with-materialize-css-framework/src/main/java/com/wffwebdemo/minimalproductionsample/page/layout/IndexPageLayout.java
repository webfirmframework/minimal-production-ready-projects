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
                new NoTag(this, "wffweb with materialize css example");
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
                    new Href("https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"));
            
            new Link(this,
                    new Rel(Rel.STYLESHEET),
                    new Href("assets/css/app.css"));
            
            
            new Script(this,
                    new Defer(),
                    new Src("https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"));
                
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
                
                new A(this, new Href("https://materializecss.com/getting-started.html"), 
                        new Target(Target.BLANK)) {{
                    new NoTag(this, "visit materialize tutorial");
                }};
                
                new H1(this) {{
                    new NoTag(this, "Sample with materialize css framework ");  
                }};
                
                new Button(this, new OnClick(IndexPageLayout.this), 
                        new ClassAttribute("waves-effect waves-light btn")) {{
                    new NoTag(this, "Insert SampleTemplate1");
                }};
                
                new Button(this,
                        new ClassAttribute("waves-effect waves-light btn"),
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
            titleTag.addInnerHtml(new NoTag(null, "Materialize Example | SampleTemplate1"));
        }
        
        return null;
    }

}
