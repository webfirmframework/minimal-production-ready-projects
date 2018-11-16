package com.wffwebdemo.minimalproductionsample.page.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnClick;
import com.webfirmframework.wffweb.tag.html.attribute.global.ClassAttribute;
import com.webfirmframework.wffweb.tag.html.html5.attribute.global.DataAttribute;
import com.webfirmframework.wffweb.tag.html.links.A;
import com.webfirmframework.wffweb.tag.html.tables.TBody;
import com.webfirmframework.wffweb.tag.html.tables.THead;
import com.webfirmframework.wffweb.tag.html.tables.Table;
import com.webfirmframework.wffweb.tag.html.tables.Td;
import com.webfirmframework.wffweb.tag.html.tables.Th;
import com.webfirmframework.wffweb.tag.html.tables.Tr;
import com.webfirmframework.wffweb.tag.htmlwff.NoTag;
import com.wffwebdemo.minimalproductionsample.page.model.DocumentModel;

/**
 * The html code is referred from
 * https://foundation.zurb.com/building-blocks/blocks/responsive-card-table.html.
 * The assets/css/app.css contains css for this table.
 */
@SuppressWarnings("serial")
public class ResponsiveTable extends Table {

    private DocumentModel documentModel;

    public ResponsiveTable(AbstractHtml base, DocumentModel documentModel) {
        super(base, new ClassAttribute("responsive-card-table unstriped"));
        this.documentModel = documentModel;

        develop();
    }

    // @formatter:off
    private void develop() {
        
        List<String> headNames = Arrays.asList("First Name", "Last Name", "Hero Title", "Action");
        
       
        Collection<Collection<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList("Bruce", "Wayne", "Batman"));
        rows.add(Arrays.asList("Peter", "Parker", "Spiderman"));
        rows.add(Arrays.asList("Bruce", "Banner", "The Hulk"));
        rows.add(Arrays.asList("Clark", "Kent", "Superman"));
        
        for (int i = 0; i < 5; i++) {
            rows.add(Arrays.asList("Dynamic fname" + i, "Dynamic lname" + i, "Dynamic Hero" +i));
        }
        
        //thead of table
        THead tHead = new THead(this) {{
            new Tr(this) {{
                for (String headName : headNames) {
                    new Th(this) {{
                        new NoTag(this, headName);
                    }};    
                }               
            }};
        }};
        
        //tbody of table
        new TBody(this) {{
            
            //used thread to very slowly add rows to the table.
            //To prove other ui portions can be
            //updated while inserting row in to the table
            Thread thread = new Thread(() -> {                
                for (Collection<String> row : rows) {
                    
                    // if the tHead tag is not available in the ui the stop inserting row
                    if (documentModel.getBrowserPage().getTagRepository() != null && 
                            !documentModel.getBrowserPage().getTagRepository().exists(tHead)) {
                        System.out.println("TBody instance is not available in the ui or removed it so breaking");
                        break;
                    }
                    
                    developTr(this, headNames, row);
                    
                    try {
                        //to make a delay of 1 second
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }};    
        
    }
    
    private void developTr(TBody tBody, List<String> headNames, Collection<String> row) {
        Tr tr = new Tr(this) {{
            Tr thisTr = this;
            
            int i = 0;
            for (String cellValue : row) {
                new Td(this,
                        new DataAttribute("label", headNames.get(i))) {{
                    new NoTag(this, cellValue);
                }};
                i++;
            }
            new Td(this,
                    new DataAttribute("label", "Action")) {{
                new A(this, new ClassAttribute("clear button alert"), 
                        new OnClick((bm, event) -> {
                                thisTr.getParent().removeChild(thisTr);
                                return null;
                            })) {{
                    new NoTag(this, "delete");
                }};
            }};
        }};
        tBody.appendChildren(tr);
    }
    // @formatter:on
}
