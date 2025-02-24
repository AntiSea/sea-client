package net.seasonal.seaclient.gui;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.seasonal.seaclient.modules.ModuleManager;
import net.seasonal.seaclient.modules.Module;

public class HudRenderer {

    public static void register() {
        HudRenderCallback.EVENT.register((context, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null) {
                // Display the HUD text "SeaClient HUD"
                context.drawTextWithShadow(client.textRenderer, "SeaClient Hub", 10, 10, 0xFFFFFF);

                // Display the status of each module (active or inactive)
                int yOffset = 25; // Vertical offset for the modules' text
                for (Module module : ModuleManager.getModules()) {
                    String status = module.isEnabled() ? "Enabled" : "Disabled";
                    context.drawTextWithShadow(client.textRenderer, module.getName() + ": " + status, 10, yOffset, 0xFFFFFF);
                    yOffset += 15; // Increment the vertical position for each module
                }
            }
        });
    }
}
