// till wffweb-12.0.0-beta.8 keep the following code and
// call this method everywhere instead of wffAsync.setURI to avoid keeping history for the same uri
window.setURI = function(uri, preFunction, postFunction, replace) {
    if (uri === window.location.pathname || uri === window.location.href) {
        return;
    }
    wffAsync.setURI(uri, preFunction, postFunction, replace);
};

const wffGlobalListeners = new function() {

    this.onSetURI = function(event) {
//        console.log('wffGlobalListeners > onSetURI', event);
        loadingIcon.hidden = false;
    };
    this.afterSetURI = function(event) {
//        console.log('wffGlobalListeners > afterSetURI', event);
        loadingIcon.hidden = true;
    };

};

