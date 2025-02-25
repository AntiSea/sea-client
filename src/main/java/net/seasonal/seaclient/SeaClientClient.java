package net.seasonal.seaclient;

import net.fabricmc.api.ClientModInitializer;
import net.seasonal.seaclient.utils.KeybindHandler;

public class SeaClientClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Register the keybinds
        KeybindHandler.register();
    }
}
