package com.mosberg.event;

import com.mosberg.RarityAndRelics;
import com.mosberg.component.ModDataComponents;
import com.mosberg.rarity.RarityManager;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.*;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetComponentsLootFunction;
import net.minecraft.component.ComponentChanges;

public class LootTableModifier {
	public static void register() {
		LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
			// Apply to all loot tables (chests, mobs, etc.)
			RarityAndRelics.LOGGER.debug("Modifying loot table: {}", key.getValue());
		});
	}
}
