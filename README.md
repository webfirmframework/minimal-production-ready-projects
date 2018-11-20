# minimal-production-ready-projects
This repository contains minimal production ready webfirmframework  projects. The sample projects in this repository use one of the css frameworks like bootstrap css, foundation css, material design lite css, materialize css, MUI css, Petal css etc..

___

The projects are based on *wffweb-3.0.1-SNAPSHOT*. You can replace it with *wffweb-3.0.1* version once it is released. [Visit developers guide for webfirmframework doc](https://webfirmframework.github.io/developers-guide/get-started.html).

Currently, the value of `ServerConstants.WS_BINARY_BUFFER_SIZE` is `8192`, i.e the default websocket byte buffer size in [tomcat 9 (Refer doc)](https://tomcat.apache.org/tomcat-9.0-doc/web-socket-howto.html). This byte buffer size may vary based on application server vendor. If you are using an application server other than tomcat, please refer it in its doc and assign that value in `ServerConstants.WS_BINARY_BUFFER_SIZE`. Or, if you are changing this default value by context.xml/or similar methods please assign  the same value in `ServerConstants.WS_BINARY_BUFFER_SIZE`. NB: Please know that `ServerConstants.WS_BINARY_BUFFER_SIZE` should not have a value higher than the websocket byte buffer size configured for your server but it's OK to have a lower value.

___

All projects are in maven.

### To open it in IntelliJ Java IDE: 
#### Go to File > Open and select project directory 

### To open it in eclipse: 
#### Go to File > Import > Existing Maven Projects and select project directory 

___

### Sample HTML code for foundation css framework
#### [https://foundation.zurb.com/building-blocks/](https://foundation.zurb.com/building-blocks/)

xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

### Doc for MUI css framework
#### [https://www.muicss.com/docs/v1/css-js/buttons/](https://www.muicss.com/docs/v1/css-js/buttons). 
MUI follows Google's [Material Design](https://material.io/design/introduction/) guidelines. 

xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

### Doc for Bootstrap4 css framework
#### [https://getbootstrap.com/docs/4.1/getting-started/introduction](https://getbootstrap.com/docs/4.1/getting-started/introduction). 
 
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

### Doc for Petal css framework
#### [https://shakrmedia.github.io/petal/docs/basic.html](https://shakrmedia.github.io/petal/docs/basic.html) 

xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

### Doc for Materialize css framework
#### [https://materializecss.com/getting-started.html](https://materializecss.com/getting-started.html). 
 
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

### Doc for Material Design Lite css framework
#### [https://getmdl.io](https://getmdl.io). 
 
___

Got best result on Google Chrome when tested
___




