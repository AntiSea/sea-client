package net.seasonal.seaclient.utils;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.seasonal.seaclient.gui.GuiScreen;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class KeybindHandler {

    private static KeyBinding openGuiKey;

    public static void register() {
        // Register keybind to open the GUI (e.g., "G" key)
        openGuiKey = new KeyBinding("key.seaclient.openGui", GLFW.GLFW_KEY_G, "category.seaclient");
        KeyBindingHelper.registerKeyBinding(openGuiKey);
        System.out.println("Keybind registered for G key!");

        // Register tick event to check for key press
        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openGuiKey.wasPressed()) {
                System.out.println("G key pressed! Opening GUI...");
                MinecraftClient.getInstance().setScreen(new GuiScreen()); // Open GUI on key press
            }
        });
    }
}
