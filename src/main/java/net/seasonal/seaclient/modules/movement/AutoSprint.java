package net.seasonal.seaclient.modules.movement;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.seasonal.seaclient.modules.Module;
import net.seasonal.seaclient.modules.Category;

public class AutoSprint extends Module {

    public AutoSprint() {
        super("AutoSprint", "Automatically starts sprinting when moving.", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {
        // No additional setup needed for enabling auto sprint
    }

    @Override
    public void onDisable() {
        // When disabled, stop sprinting if it's active
        MinecraftClient.getInstance().player.setSprinting(false);
    }

    @Override
    public void tick() {
        // Automatically sprint when the player is moving and not already sprinting
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && player.isOnGround() && !player.isSprinting()) {
            player.setSprinting(true);
        }
    }
}
