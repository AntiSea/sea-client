package net.antisea.seaclient;

import net.antisea.seaclient.gui.Render;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeaClient implements ClientModInitializer {
    public static final String MOD_ID = "sea-client";
    public static final ModMetadata MOD_META;
    public static final String NAME;
    public static final String BUILD_NUMBER;
    public static final Logger LOG;

    static {
        MOD_META = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow().getMetadata();
        NAME = MOD_META.getName();
        LOG = LoggerFactory.getLogger(NAME);
        String versionString = MOD_META.getVersion().getFriendlyString();
        if (versionString.contains("-")) versionString = versionString.split("-")[0];
        String buildNumber = "unknown";
        if (MOD_META.containsCustomValue(SeaClient.MOD_ID + ":build_number")) {
            buildNumber = MOD_META.getCustomValue(SeaClient.MOD_ID + ":build_number").getAsString();
        }
        BUILD_NUMBER = buildNumber;
    }

    @Override
    public void onInitializeClient() {
        LOG.info("Initializing {}", NAME);
        LOG.info("Version: {} (build {})", MOD_META.getVersion().getFriendlyString(), BUILD_NUMBER);

        // Register a client tick event to initialize ImGui and handle key input
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            long windowHandle = GLFW.glfwGetCurrentContext();
            if (windowHandle != 0) {
                Render.init(windowHandle);
            } else {
                LOG.error("Failed to get GLFW window handle");
            }

            if (GLFW.glfwGetKey(windowHandle, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS) {
                Render.toggleGui();
            }
        });

        // Register a world render event to render ImGui
        WorldRenderEvents.END.register(context -> {
            Render.render();
        });
    }
}