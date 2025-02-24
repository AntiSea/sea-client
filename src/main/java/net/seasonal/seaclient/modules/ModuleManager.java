package net.seasonal.seaclient.modules;

import net.seasonal.seaclient.modules.movement.AutoSprint;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private static List<Module> modules = new ArrayList<>();

    public static void addModule(Module module) {
        modules.add(module);
    }

    public static void removeModule(Module module) {
        modules.remove(module);
    }

    public static List<Module> getModules() {
        return modules;
    }

    public void initModules() {
        addModule(new AutoSprint()); // Add AutoSprint here as well
    }
}
