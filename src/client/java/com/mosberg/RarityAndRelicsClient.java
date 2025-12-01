package com.mosberg;

import com.mosberg.client.tooltip.RarityTooltipHandler;
import net.fabricmc.api.ClientModInitializer;

public class RarityAndRelicsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		RarityAndRelics.LOGGER.info("Initializing Rarity and Relics client...");

		// Register tooltip handler
		RarityTooltipHandler.register();

		RarityAndRelics.LOGGER.info("Rarity and Relics client initialized successfully!");
	}
}
