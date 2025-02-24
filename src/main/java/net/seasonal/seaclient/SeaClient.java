package net.seasonal.seaclient;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeaClient implements ModInitializer {
    public static final String MOD_ID = "sea-client";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("SeaClient Initialized!");
    }
}
