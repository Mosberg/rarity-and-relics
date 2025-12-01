package com.mosberg.modifier;

public enum ModifierType {
	ATTACK_DAMAGE("Attack Damage", "⚔"),
	ATTACK_SPEED("Attack Speed", "⚡"),
	DURABILITY("Durability", "◆"),
	EFFICIENCY("Efficiency", "⛏"),
	ARMOR("Armor", "🛡"),
	ARMOR_TOUGHNESS("Armor Toughness", "💪"),
	KNOCKBACK_RESISTANCE("Knockback Resistance", "⚓"),
	MOVEMENT_SPEED("Movement Speed", "👟"),
	LUCK("Luck", "🍀");

	private final String displayName;
	private final String icon;

	ModifierType(String displayName, String icon) {
		this.displayName = displayName;
		this.icon = icon;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getIcon() {
		return icon;
	}
}
