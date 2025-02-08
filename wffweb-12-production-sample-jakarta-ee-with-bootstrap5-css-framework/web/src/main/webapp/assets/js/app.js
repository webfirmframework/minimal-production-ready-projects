
window.setURI = function(uri, preFunction, postFunction, replace) {
    wffAsync.setURI(uri, preFunction, postFunction, replace);
};

const wffGlobalListeners = new function() {

    this.onSetURI = function(event) {
	    // Workaround for a whenURI bug. It is required till 12.0.2. The bug will be fixed in 12.0.3.
	    wffGlobal.getAndUpdateLocation();

//        console.log('wffGlobalListeners > onSetURI', event);
        loadingIcon.hidden = false;
    };
    this.afterSetURI = function(event) {
//        console.log('wffGlobalListeners > afterSetURI', event);
        loadingIcon.hidden = true;
    };

};

