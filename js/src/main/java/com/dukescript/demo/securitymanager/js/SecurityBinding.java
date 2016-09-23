package com.dukescript.demo.securitymanager.js;

import net.java.html.js.JavaScriptBody;
import net.java.html.json.Model;
import net.java.html.json.Property;
import net.java.html.lib.Array;
import net.java.html.lib.Function;
import net.java.html.lib.Objs;
import net.java.html.lib.Union;
import net.java.html.lib.dom.CSSStyleDeclaration;
import static net.java.html.lib.dom.Exports.document;
import net.java.html.lib.dom.HTMLElement;
import static net.java.html.lib.knockout.Exports.ko;
import net.java.html.lib.knockout.KnockoutBindingHandler;
import net.java.html.lib.knockout.KnockoutBindingHandlers;
import net.java.html.lib.knockout.KnockoutSubscribable;

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

        @net.java.html.json.Function
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

    public static void init() {
        KnockoutBindingHandlers handlers = ko.bindingHandlers.get();
        class Init implements Function.A2<Object,Function.A0<? extends Object>, Union.A2<Void, Objs>> {
            @Override
            public Union.A2<Void, Objs> call(Object e, Function.A0<? extends Object> valueAccessor) {
                HTMLElement element = HTMLElement.$as(e);
                KnockoutSubscribable<Array<String>> roles = findRoles();
                roles.subscribe((changes) -> {
                    enableVisibility(element, valueAccessor, roles);
                    return null;
                });
                enableVisibility(element, valueAccessor, roles);
                return null;
            }

            private void enableVisibility(
                HTMLElement element, Function.A0<? extends Object> valueAccessor, KnockoutSubscribable<Array<String>> roles
            ) {
                Object value = ko.unwrap(valueAccessor.call());
                CSSStyleDeclaration style = element.style.get();
                Array roleValues = Array.$as(Function.$as(roles).apply(null));
                if (roleValues.indexOf(value) < 0) {
                    style.visibility.set("hidden");
                } else {
                    style.visibility.set("visible");
                }
            }
        }
        KnockoutBindingHandler authorize = KnockoutBindingHandler.$as(new Objs());
        authorize.init.set(new Init());
        handlers.$set("authorize", authorize);
    }

    static KnockoutSubscribable<Array<String>> findRoles() {
        Objs securityManager = Objs.$as(ko.contextFor(document.getElementById("security-manager")));
        Object user = Function.$as(Objs.$as(securityManager.$get("$root")).$get("user")).apply(null);
        KnockoutSubscribable<Array<String>> roles = KnockoutSubscribable.$as(Objs.$as(user).$get("roles"));
        return roles;
    }
}
