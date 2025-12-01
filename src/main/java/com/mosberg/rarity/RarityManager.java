package com.mosberg.rarity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mosberg.RarityAndRelics;
import com.mosberg.component.RarityData;
import com.mosberg.modifier.ModifierType;
import com.mosberg.modifier.StatModifier;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class RarityManager {
	private static final Map<String, Rarity> RARITIES = new HashMap<>();
	private static final Random RANDOM = new Random();

	public enum SourceType {
		CRAFTING,
		DROP,
		LOOT
	}

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
			JsonArray raritiesArray = root.getAsJsonArray("rarities");

			for (int i = 0; i < raritiesArray.size(); i++) {
				JsonObject rarityJson = raritiesArray.get(i).getAsJsonObject();
				Rarity rarity = Rarity.fromJson(rarityJson);
				RARITIES.put(rarity.getId(), rarity);
			}

			RarityAndRelics.LOGGER.info("Loaded {} rarities from configuration", RARITIES.size());
		} catch (IOException e) {
			RarityAndRelics.LOGGER.error("Failed to load rarity configuration", e);
		}
	}

	private static void copyDefaultConfig(Path targetPath) throws IOException {
		String defaultConfig = """
				{
				  "$id": "https://mosberg.github.io/src/schemas/rarities.schema.json",
				  "rarities": [
				    {
				      "id": "trash",
				      "name": "Trash",
				      "color": "#4B371C",
				      "description": "Worthless items, often discarded.",
				      "assets": {
				        "icon": "rarity:icons/trash",
				        "frame": "rarity:frames/trash"
				      },
				      "dropRates": { "crafting": 0.1, "drop": 0.1, "loot": 0.1 }
				    },
				    {
				      "id": "common",
				      "name": "Common",
				      "color": "#969696",
				      "description": "Everyday items, easily found.",
				      "assets": {
				        "icon": "rarity:icons/common",
				        "frame": "rarity:frames/common"
				      },
				      "dropRates": { "crafting": 60.0, "drop": 60.0, "loot": 60.0 }
				    },
				    {
				      "id": "uncommon",
				      "name": "Uncommon",
				      "color": "#3DD20B",
				      "description": "Slightly harder to find, with minor improvements.",
				      "assets": {
				        "icon": "rarity:icons/uncommon",
				        "frame": "rarity:frames/uncommon"
				      },
				      "dropRates": { "crafting": 20.0, "drop": 20.0, "loot": 20.0 }
				    },
				    {
				      "id": "rare",
				      "name": "Rare",
				      "color": "#2F78FF",
				      "description": "Hard to find, with significant improvements.",
				      "assets": { "icon": "rarity:icons/rare", "frame": "rarity:frames/rare" },
				      "dropRates": { "crafting": 10.0, "drop": 10.0, "loot": 10.0 }
				    },
				    {
				      "id": "epic",
				      "name": "Epic",
				      "color": "#AB46FF",
				      "description": "Very rare, with powerful enhancements.",
				      "assets": { "icon": "rarity:icons/epic", "frame": "rarity:frames/epic" },
				      "dropRates": { "crafting": 5.0, "drop": 5.0, "loot": 5.0 }
				    },
				    {
				      "id": "legendary",
				      "name": "Legendary",
				      "color": "#FF9600",
				      "description": "Extremely rare, with legendary enhancements.",
				      "assets": {
				        "icon": "rarity:icons/legendary",
				        "frame": "rarity:frames/legendary"
				      },
				      "dropRates": { "crafting": 2.0, "drop": 2.0, "loot": 2.0 }
				    },
				    {
				      "id": "mythic",
				      "name": "Mythic",
				      "color": "#FF0B00",
				      "description": "Mythical items, with godlike enhancements.",
				      "assets": {
				        "icon": "rarity:icons/mythic",
				        "frame": "rarity:frames/mythic"
				      },
				      "dropRates": { "crafting": 1.0, "drop": 1.0, "loot": 1.0 }
				    },
				    {
				      "id": "ancient",
				      "name": "Ancient",
				      "color": "#00FFFF",
				      "description": "Ancient items, with immense power.",
				      "assets": {
				        "icon": "rarity:icons/ancient",
				        "frame": "rarity:frames/ancient"
				      },
				      "dropRates": { "crafting": 0.7, "drop": 0.9, "loot": 0.9 }
				    },
				    {
				      "id": "celestial",
				      "name": "Celestial",
				      "color": "#FF69B4",
				      "description": "Celestial items, with otherworldly power.",
				      "assets": {
				        "icon": "rarity:icons/celestial",
				        "frame": "rarity:frames/celestial"
				      },
				      "dropRates": { "crafting": 0.5, "drop": 0.7, "loot": 0.7 }
				    },
				    {
				      "id": "divine",
				      "name": "Divine",
				      "color": "#FFD700",
				      "description": "Divine items, with godlike power.",
				      "assets": {
				        "icon": "rarity:icons/divine",
				        "frame": "rarity:frames/divine"
				      },
				      "dropRates": { "crafting": 0.3, "drop": 0.5, "loot": 0.5 }
				    },
				    {
				      "id": "transcendent",
				      "name": "Transcendent",
				      "color": "#222222",
				      "description": "Transcendent items, with ultimate power.",
				      "assets": {
				        "icon": "rarity:icons/transcendent",
				        "frame": "rarity:frames/transcendent"
				      },
				      "dropRates": { "crafting": 0.1, "drop": 0.3, "loot": 0.3 }
				    },
				    {
				      "id": "owner",
				      "name": "Owner",
				      "color": "#00000022",
				      "description": "Unique to the owner, cannot be traded or sold.",
				      "assets": {
				        "icon": "rarity:icons/owner",
				        "frame": "rarity:frames/owner"
				      },
				      "dropRates": { "crafting": 0.0, "drop": 0.0, "loot": 0.0 }
				    }
				  ]
				}
				""";
		Files.writeString(targetPath, defaultConfig);
	}

	public static Rarity getRandomRarity(SourceType sourceType) {
		if (RARITIES.isEmpty()) {
			return null;
		}

		// Calculate total weight for the specific source type
		double totalWeight = 0.0;
		for (Rarity rarity : RARITIES.values()) {
			double weight = switch (sourceType) {
				case CRAFTING -> rarity.getDropRates().getCrafting();
				case DROP -> rarity.getDropRates().getDrop();
				case LOOT -> rarity.getDropRates().getLoot();
			};
			totalWeight += weight;
		}

		if (totalWeight == 0) {
			return null;
		}

		// Perform weighted random selection
		double randomValue = RANDOM.nextDouble() * totalWeight;
		double currentWeight = 0.0;

		for (Rarity rarity : RARITIES.values()) {
			double weight = switch (sourceType) {
				case CRAFTING -> rarity.getDropRates().getCrafting();
				case DROP -> rarity.getDropRates().getDrop();
				case LOOT -> rarity.getDropRates().getLoot();
			};
			currentWeight += weight;
			if (randomValue <= currentWeight) {
				return rarity;
			}
		}

		return null;
	}

	public static Rarity getRarityById(String id) {
		return RARITIES.get(id);
	}

	public static RarityData generateRarityData(SourceType sourceType) {
		Rarity rarity = getRandomRarity(sourceType);
		if (rarity == null) {
			return null;
		}

		// Generate modifiers based on rarity tier
		int modifierCount = getModifierCountForRarity(rarity.getId());

		List<StatModifier> modifiers = new ArrayList<>();
		for (int i = 0; i < modifierCount; i++) {
			modifiers.add(generateRandomModifier(rarity.getId()));
		}

		return new RarityData(rarity.getId(), modifiers);
	}

	private static int getModifierCountForRarity(String rarityId) {
		return switch (rarityId) {
			case "trash" -> RANDOM.nextInt(2); // 0-1
			case "common" -> RANDOM.nextInt(2) + 1; // 1-2
			case "uncommon" -> RANDOM.nextInt(2) + 2; // 2-3
			case "rare" -> RANDOM.nextInt(2) + 3; // 3-4
			case "epic" -> RANDOM.nextInt(2) + 4; // 4-5
			case "legendary" -> RANDOM.nextInt(2) + 5; // 5-6
			case "mythic" -> RANDOM.nextInt(2) + 6; // 6-7
			case "ancient" -> RANDOM.nextInt(2) + 7; // 7-8
			case "celestial" -> RANDOM.nextInt(2) + 8; // 8-9
			case "divine" -> RANDOM.nextInt(2) + 9; // 9-10
			case "transcendent" -> RANDOM.nextInt(2) + 10; // 10-11
			case "owner" -> RANDOM.nextInt(3) + 10; // 10-12
			default -> RANDOM.nextInt(2) + 1; // Default 1-2
		};
	}

	private static StatModifier generateRandomModifier(String rarityId) {
		ModifierType[] types = ModifierType.values();
		ModifierType type = types[RANDOM.nextInt(types.length)];

		// Scale modifier strength based on rarity
		double baseMin = -0.2; // -20%
		double baseMax = 0.3;  // +30%

		double multiplier = switch (rarityId) {
			case "trash" -> 0.3;
			case "common" -> 0.5;
			case "uncommon" -> 0.7;
			case "rare" -> 1.0;
			case "epic" -> 1.3;
			case "legendary" -> 1.6;
			case "mythic" -> 2.0;
			case "ancient" -> 2.5;
			case "celestial" -> 3.0;
			case "divine" -> 3.5;
			case "transcendent" -> 4.0;
			case "owner" -> 5.0;
			default -> 1.0;
		};

		double value = (baseMin + RANDOM.nextDouble() * (baseMax - baseMin)) * multiplier;

		return new StatModifier(type, value);
	}

	public static Collection<Rarity> getAllRarities() {
		return RARITIES.values();
	}
}
