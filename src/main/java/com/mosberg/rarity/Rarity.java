package com.mosberg.rarity;

import com.google.gson.JsonObject;

public class Rarity {
	private final String id;
	private final String name;
	private final int color;
	private final String description;
	private final RarityAssets assets;
	private final DropRates dropRates;

	public Rarity(String id, String name, int color, String description, RarityAssets assets, DropRates dropRates) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.description = description;
		this.assets = assets;
		this.dropRates = dropRates;
	}

	public static Rarity fromJson(JsonObject json) {
		String id = json.get("id").getAsString();
		String name = json.get("name").getAsString();
		String colorHex = json.get("color").getAsString().replace("#", "");
		int color = Integer.parseInt(colorHex, 16);
		String description = json.get("description").getAsString();

		JsonObject assetsJson = json.getAsJsonObject("assets");
		RarityAssets assets = new RarityAssets(
				assetsJson.get("icon").getAsString(),
				assetsJson.get("frame").getAsString()
		);

		JsonObject dropRatesJson = json.getAsJsonObject("dropRates");
		DropRates dropRates = new DropRates(
				dropRatesJson.get("crafting").getAsDouble(),
				dropRatesJson.get("drop").getAsDouble(),
				dropRatesJson.get("loot").getAsDouble()
		);

		return new Rarity(id, name, color, description, assets, dropRates);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getColor() {
		return color;
	}

	public String getDescription() {
		return description;
	}

	public RarityAssets getAssets() {
		return assets;
	}

	public DropRates getDropRates() {
		return dropRates;
	}

	public static class RarityAssets {
		private final String icon;
		private final String frame;

		public RarityAssets(String icon, String frame) {
			this.icon = icon;
			this.frame = frame;
		}

		public String getIcon() {
			return icon;
		}

		public String getFrame() {
			return frame;
		}
	}

	public static class DropRates {
		private final double crafting;
		private final double drop;
		private final double loot;

		public DropRates(double crafting, double drop, double loot) {
			this.crafting = crafting;
			this.drop = drop;
			this.loot = loot;
		}

		public double getCrafting() {
			return crafting;
		}

		public double getDrop() {
			return drop;
		}

		public double getLoot() {
			return loot;
		}
	}
}
