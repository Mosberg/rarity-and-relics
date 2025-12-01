package com.mosberg.mixin;

import com.mosberg.component.ModDataComponents;
import com.mosberg.rarity.RarityManager;
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to automatically apply rarity data to items when they are created.
 * This applies to all item creation scenarios: crafting, loot, drops, etc.
 */
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	
	/**
	 * Injects into the ItemStack constructor to apply rarity when items are created.
	 * Uses the LOOT source type as the default for general item creation.
	 */
	@Inject(
		method = "<init>(Lnet/minecraft/item/Item;ILnet/minecraft/component/ComponentChanges;)V",
		at = @At("RETURN")
	)
	private void onItemStackCreation(Item item, int count, ComponentChanges components, CallbackInfo ci) {
		ItemStack stack = (ItemStack) (Object) this;

		// Only apply rarity to eligible items that don't already have rarity data
		if (shouldApplyRarity(item) && !stack.contains(ModDataComponents.RARITY_DATA)) {
			// Generate rarity data using LOOT source type as default
			var rarityData = RarityManager.generateRarityData(RarityManager.SourceType.LOOT);
			if (rarityData != null) {
				stack.set(ModDataComponents.RARITY_DATA, rarityData);
			}
		}
	}

	/**
	 * Determines if an item should receive rarity data.
	 * Includes tools, weapons, armor, and other equipment.
	 * 
	 * @param item The item to check
	 * @return true if the item should have rarity applied
	 */
	private boolean shouldApplyRarity(Item item) {
		return item instanceof MiningToolItem ||      // Pickaxes, Axes, Shovels, Hoes
			   item instanceof SwordItem ||           // Swords
			   item instanceof ArmorItem ||           // Helmets, Chestplates, Leggings, Boots
			   item instanceof BowItem ||             // Bows
			   item instanceof CrossbowItem ||        // Crossbows
			   item instanceof TridentItem ||         // Tridents
			   item instanceof ShieldItem ||          // Shields
			   item instanceof ElytraItem ||          // Elytra
			   item instanceof FishingRodItem ||      // Fishing Rods
			   item instanceof ShearsItem ||          // Shears
			   item instanceof FlintAndSteelItem;     // Flint and Steel
	}
}
