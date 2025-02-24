package net.seasonal.seaclient;

import net.seasonal.seaclient.utils.gui.Notification;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeaClient implements ModInitializer {
    public static final String MOD_ID = "sea-client";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("SeaClient initialized!");
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                
                LOGGER.info("Notification!");
                Notification.showNotification("§aTesting", 0x00FF00);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
