package net.seasonal.seaclient;

import net.fabricmc.api.ClientModInitializer;
import net.seasonal.seaclient.modules.ModuleManager;
import net.seasonal.seaclient.utils.KeybindHandler;
import net.seasonal.seaclient.gui.HudRenderer;

public class SeaClientClient implements ClientModInitializer {

    // Module manager instance
    private static final ModuleManager moduleManager = new ModuleManager();

    @Override
    public void onInitializeClient() {
        // Initialize modules
        moduleManager.initModules();

        // Register keybinds
        KeybindHandler.register();

        // Register the HUD renderer
        HudRenderer.register();
    }

    // Method to check keybinds for all modules
    public static void checkKeybinds() {
        // Iterate over all modules and check if their respective keybinds are pressed
        for (var module : ModuleManager.getModules()) {
            KeybindHandler.checkKeybind(module);  // Call the checkKeybind method to toggle modules if key is pressed
        }
    }
}
