package com.dukescript.demo.securitymanager.js;

import net.java.html.js.JavaScriptBody;
import net.java.html.lib.Function;
import net.java.html.lib.Objs;
import net.java.html.lib.Union;
import static net.java.html.lib.knockout.Exports.ko;
import net.java.html.lib.knockout.KnockoutBindingHandler;
import net.java.html.lib.knockout.KnockoutBindingHandlers;

/** Use {@link JavaScriptBody} annotation on methods to
 * directly interact with JavaScript. See
 * http://bits.netbeans.org/html+java/1.2/net/java/html/js/package-summary.html
 * to understand how.
 */
public final class StopBinding {
    private StopBinding() {
    }
    
    public static void init() {
        KnockoutBindingHandlers handlers = ko.bindingHandlers.get();
        KnockoutBindingHandler stopBinding = KnockoutBindingHandler.$as(new Objs());
        stopBinding.init.set((Function.A0) () -> {
            return Union.$as(new Objs().$set("controlsDescendantBindings", true));
        });
        handlers.$set("stopBinding", stopBinding);
    }
    
}
