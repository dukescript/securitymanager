package com.dukescript.demo.securitymanager;

import com.dukescript.demo.securitymanager.js.StopBinding;
import com.dukescript.demo.securitymanager.js.SecurityBinding;
import com.dukescript.demo.securitymanager.js.User;
import com.dukescript.demo.securitymanager.js.SecurityManager;
import net.java.html.json.Model;
import net.java.html.json.Models;
import net.java.html.json.Property;


final class DataModel {

    private static SecurityManager ui;

    /**
     * Called when the page is ready.
     */
    static void onPageLoad() throws Exception {
        ui = new SecurityManager();
        Models.toRaw(ui);
        StopBinding.init();
        SecurityBinding.init();
        ui.setUser(new User("toni"));
        ui.getUser().getAvailableRoles().add("admin");
        ui.getUser().getAvailableRoles().add("editor");
        ui.getUser().getAvailableRoles().add("reader");
        ui.applyBindings();
        vm1 vm1 = new vm1("vm1value");
        vm2 vm2 = new vm2("vm2value", "prop2value");
        Models.applyBindings(vm2, "vm2");
        Models.applyBindings(vm1, "vm1");
    }



    @Model(className = "vm1",
            properties = {
                @Property(name = "prop", type = String.class)}, targetId = "")
    public static class vmd1 {
    }

    @Model(className = "vm2",
            properties = {
                @Property(name = "prop", type = String.class),
                @Property(name = "prop2", type = String.class)           
            }, targetId = "")
    public static class vmd2 {
    }

}
