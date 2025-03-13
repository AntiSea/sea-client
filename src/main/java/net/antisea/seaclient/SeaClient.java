package net.antisea.seaclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
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
    }
}