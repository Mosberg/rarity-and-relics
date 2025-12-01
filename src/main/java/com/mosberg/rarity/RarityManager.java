package com.mosberg.rarity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mosberg.RarityAndRelics;
import com.mosberg.component.RarityData;
import com.mosberg.modifier.ModifierType;
import com.mosberg.modifier.StatModifier;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class RarityManager {
	private static final Map<String, Rarity> RARITIES = new HashMap<>();
	private static final List<Rarity> WEIGHTED_RARITIES = new ArrayList<>();
	private static double totalWeight = 0.0;
	private static final Random RANDOM = new Random();

	public static void loadRarityConfig() {
		Path configDir = FabricLoader.getInstance().getConfigDir().resolve(RarityAndRelics.MOD_ID);
		Path rarityFile = configDir.resolve("rarities.json");

		try {
			// Create config directory if it doesn't exist
			Files.createDirectories(configDir);

			// Copy default config if it doesn't exist
			if (!Files.exists(rarityFile)) {
				RarityAndRelics.LOGGER.info("Creating default rarities.json configuration...");
				copyDefaultConfig(rarityFile);
			}

			// Load rarities from config
			Gson gson = new Gson();
			JsonObject root = gson.fromJson(new FileReader(rarityFile.toFile()), JsonObject.class);
			JsonObject raritiesJson = root.getAsJsonObject("rarities");

			for (String key : raritiesJson.keySet()) {
				JsonObject rarityJson = raritiesJson.getAsJsonObject(key);
				Rarity rarity = Rarity.fromJson(key, rarityJson);
				RARITIES.put(key, rarity);
				WEIGHTED_RARITIES.add(rarity);
				totalWeight += rarity.getWeight();
			}

			RarityAndRelics.LOGGER.info("Loaded {} rarities from configuration", RARITIES.size());
		} catch (IOException e) {
			RarityAndRelics.LOGGER.error("Failed to load rarity configuration", e);
		}
	}

	private static void copyDefaultConfig(Path targetPath) throws IOException {
		String defaultConfig = """
				{
				  "rarities": {
				    "common": {
				      "display_name": "Common",
				      "color": "#AAAAAA",
				      "weight": 50.0,
				      "min_modifiers": 0,
				      "max_modifiers": 1
				    },
				    "uncommon": {
				      "display_name": "Uncommon",
				      "color": "#55FF55",
				      "weight": 30.0,
				      "min_modifiers": 1,
				      "max_modifiers": 2
				    },
				    "rare": {
				      "display_name": "Rare",
				      "color": "#5555FF",
				      "weight": 15.0,
				      "min_modifiers": 2,
				      "max_modifiers": 3
				    },
				    "epic": {
				      "display_name": "Epic",
				      "color": "#AA00AA",
				      "weight": 4.0,
				      "min_modifiers": 3,
				      "max_modifiers": 4
				    },
				    "legendary": {
				      "display_name": "Legendary",
				      "color": "#FFAA00",
				      "weight": 1.0,
				      "min_modifiers": 4,
				      "max_modifiers": 5
				    }
				  }
				}
				""";
		Files.writeString(targetPath, defaultConfig);
	}

	public static Rarity getRandomRarity() {
		if (WEIGHTED_RARITIES.isEmpty()) {
			return null;
		}

		double randomValue = RANDOM.nextDouble() * totalWeight;
		double currentWeight = 0.0;

		for (Rarity rarity : WEIGHTED_RARITIES) {
			currentWeight += rarity.getWeight();
			if (randomValue <= currentWeight) {
				return rarity;
			}
		}

		return WEIGHTED_RARITIES.get(WEIGHTED_RARITIES.size() - 1);
	}

	public static Rarity getRarityById(String id) {
		return RARITIES.get(id);
	}

	public static RarityData generateRarityData() {
		Rarity rarity = getRandomRarity();
		if (rarity == null) {
			return null;
		}

		// Generate random number of modifiers within rarity's range
		int modifierCount = rarity.getMinModifiers() +
				RANDOM.nextInt(rarity.getMaxModifiers() - rarity.getMinModifiers() + 1);

		List<StatModifier> modifiers = new ArrayList<>();
		for (int i = 0; i < modifierCount; i++) {
			modifiers.add(generateRandomModifier());
		}

		return new RarityData(rarity.getId(), modifiers);
	}

	private static StatModifier generateRandomModifier() {
		ModifierType[] types = ModifierType.values();
		ModifierType type = types[RANDOM.nextInt(types.length)];

		// Generate value between -20% to +30%
		double value = -0.2 + RANDOM.nextDouble() * 0.5;

		return new StatModifier(type, value);
	}
}
