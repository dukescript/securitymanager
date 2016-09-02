package com.dukescript.demo.securitymanager.js;

import net.java.html.js.JavaScriptBody;
import net.java.html.json.Function;
import net.java.html.json.Model;
import net.java.html.json.Property;

/**
 * Use {@link JavaScriptBody} annotation on methods to directly interact with
 * JavaScript. See
 * http://bits.netbeans.org/html+java/1.2/net/java/html/js/package-summary.html
 * to understand how.
 */
@Model(className = "SecurityManager", targetId = "", properties = {
    @Property(name = "user", type = User.class),})
public final class SecurityBinding {

    @Model(className = "User",
            properties = {
                @Property(name = "name", type = String.class),
                @Property(name = "roles", type = String.class, array = true),
                @Property(name = "availableRoles", type = String.class, array = true)},
            targetId = "")
    public static class Uservmd {

        @Function
        public static void toggleRole(User user, String data) {
            if (user.getRoles().contains(data)) {
                user.getRoles().remove(data);
            } else {
                user.getRoles().add(data);
            }
        }

    }

    private SecurityBinding() {
    }

    @JavaScriptBody(args = {}, body = "ko.bindingHandlers.authorize = {\n"
            + "    init: function(element, valueAccessor, allBindings, viewModel, bindingContext) {\n"
            + "         var value = ko.unwrap(valueAccessor());\n"
            + "         var user = ko.contextFor(document.getElementById('security-manager')).$root.user();\n"
            + "         var roles = user.roles;\n"
            + "         if (roles().indexOf(value)==-1){\n"
            + "                 element.style.visibility='hidden';\n"
            + "             } else { element.style.visibility='visible'; }"
            + "         roles.subscribe(function(changes) {\n"
            + "             console.log('change in roles checking for role '+value);     "
            + "             if (roles().indexOf(value)==-1){\n"
            + "                 element.style.visibility='hidden';\n"
            + "             } else { element.style.visibility='visible'; }"
            + "         });\n"
            + "    },\n"
            + "};")
    public static native void init();

}
