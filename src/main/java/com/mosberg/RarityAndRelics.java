package com.mosberg;

import com.mosberg.component.ModDataComponents;
import com.mosberg.event.LootTableModifier;
import com.mosberg.rarity.RarityManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RarityAndRelics implements ModInitializer {
	public static final String MOD_ID = "rarity-and-relics";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Rarity and Relics mod...");

		// Register custom data components
		ModDataComponents.register();

		// Load rarity configuration from JSON
		RarityManager.loadRarityConfig();

		// Register loot table modifiers
		LootTableModifier.register();

		LOGGER.info("Rarity and Relics mod initialized successfully!");
	}
}
