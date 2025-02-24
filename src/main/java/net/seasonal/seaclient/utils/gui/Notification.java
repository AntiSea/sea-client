package net.seasonal.seaclient.utils.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Notification {

    private static final MinecraftClient client = MinecraftClient.getInstance();

    public static void showNotification(String message, int color) {
        Text textMessage = Text.literal(message).styled(style -> style.withColor(color));
        client.player.sendMessage(textMessage, true);
    }
}
