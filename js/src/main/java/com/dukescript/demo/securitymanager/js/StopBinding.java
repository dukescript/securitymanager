package com.dukescript.demo.securitymanager.js;

import net.java.html.js.JavaScriptBody;

/** Use {@link JavaScriptBody} annotation on methods to
 * directly interact with JavaScript. See
 * http://bits.netbeans.org/html+java/1.2/net/java/html/js/package-summary.html
 * to understand how.
 */
public final class StopBinding {
    private StopBinding() {
    }
    
    @JavaScriptBody(args = {  },body = "ko.bindingHandlers.stopBinding = {\n" +
"    init: function() {\n" +
"        return { controlsDescendantBindings: true };\n" +
"    }\n" +
"};")
    public static native void init();
    
}
