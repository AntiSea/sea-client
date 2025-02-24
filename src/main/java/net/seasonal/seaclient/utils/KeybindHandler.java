package net.seasonal.seaclient.utils;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.seasonal.seaclient.modules.Module;
import net.seasonal.seaclient.modules.ModuleManager;
import org.lwjgl.glfw.GLFW;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.seasonal.seaclient.gui.ClickGuiScreen;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;  // Add this import

public class KeybindHandler {

    // Register all keybinds
    public static void register() {
        for (Module module : ModuleManager.getModules()) {
            KeyBinding keyBind = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.seaclient." + module.getName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.seaclient")
            );
        }

        // Add keybind to open the GUI (e.g., "G" key)
        KeyBinding openGuiKey = new KeyBinding("key.seaclient.openGui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, "category.seaclient");
        KeyBindingHelper.registerKeyBinding(openGuiKey);

        // Register tick event to check keybinds
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openGuiKey.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new ClickGuiScreen());  // Open GUI on key press
            }
        });
    }

    // Method to check if a keybind is pressed for a specific module
    public static void checkKeybind(Module module) {
        KeyBinding keyBinding = getKeyBindingForModule(module);
        if (keyBinding != null && keyBinding.wasPressed()) {
            module.toggle();  // Toggle the module when the key is pressed
        }
    }

    // Helper method to get the keybinding for a specific module
    private static KeyBinding getKeyBindingForModule(Module module) {
        return new KeyBinding("key.seaclient." + module.getName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.seaclient");
    }
}
