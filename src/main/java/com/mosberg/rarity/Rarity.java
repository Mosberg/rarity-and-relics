package com.mosberg.rarity;

import com.google.gson.JsonObject;

public class Rarity {
	private final String id;
	private final String displayName;
	private final int color; // RGB color as integer
	private final double weight; // Probability weight for random selection
	private final int minModifiers;
	private final int maxModifiers;

	public Rarity(String id, String displayName, int color, double weight, int minModifiers, int maxModifiers) {
		this.id = id;
		this.displayName = displayName;
		this.color = color;
		this.weight = weight;
		this.minModifiers = minModifiers;
		this.maxModifiers = maxModifiers;
	}

	public static Rarity fromJson(String id, JsonObject json) {
		String displayName = json.get("display_name").getAsString();
		String colorHex = json.get("color").getAsString().replace("#", "");
		int color = Integer.parseInt(colorHex, 16);
		double weight = json.get("weight").getAsDouble();
		int minModifiers = json.get("min_modifiers").getAsInt();
		int maxModifiers = json.get("max_modifiers").getAsInt();

		return new Rarity(id, displayName, color, weight, minModifiers, maxModifiers);
	}

	public String getId() {
		return id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getColor() {
		return color;
	}

	public double getWeight() {
		return weight;
	}

	public int getMinModifiers() {
		return minModifiers;
	}

	public int getMaxModifiers() {
		return maxModifiers;
	}
}
