package com.dukescript.demo.securitymanager;

import com.dukescript.demo.securitymanager.js.StopBinding;
import net.java.html.json.Function;
import net.java.html.json.Model;
import net.java.html.json.Models;
import net.java.html.json.Property;

@Model(className = "SecurityManager", targetId = "", properties = {
    @Property(name = "user", type = User.class)
})
final class DataModel {

    private static SecurityManager ui;

    /**
     * Called when the page is ready.
     */
    static void onPageLoad() throws Exception {
        ui = new SecurityManager();
        Models.toRaw(ui);
        StopBinding.init();
        ui.setUser(new User("toni", true, false));
        ui.applyBindings();
        vm1 vm1 = new vm1("vm1");
        vm2 vm2 = new vm2("vm2");
        Models.applyBindings(vm2, "vm2");
        Models.applyBindings(vm1, "vm1");
    }

    @Model(className = "User",
            properties = {
                @Property(name = "name", type = String.class),
                @Property(name = "admin", type = boolean.class),
                @Property(name = "editor", type = boolean.class),
            }, targetId = "")
    public static class Uservmd {

        @Function
        public static void toggleAdmin(User user) {
            user.setAdmin(!user.isAdmin());
        }
        
        @Function
        public static void toggleEditor(User user) {
            user.setEditor(!user.isEditor());
        }

    }

    @Model(className = "vm1",
            properties = {
                @Property(name = "prop", type = String.class)}, targetId = "")
    public static class vmd1 {
    }

    @Model(className = "vm2",
            properties = {
                @Property(name = "prop", type = String.class)}, targetId = "")
    public static class vmd2 {
    }

}
