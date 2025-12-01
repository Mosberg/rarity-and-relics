package com.mosberg.mixin;

import com.mosberg.component.ModDataComponents;
import com.mosberg.rarity.RarityManager;
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@Inject(method = "<init>(Lnet/minecraft/item/Item;ILnet/minecraft/component/ComponentChanges;)V", at = @At("RETURN"))
	private void onItemStackCreation(Item item, int count, ComponentChanges components, CallbackInfo ci) {
		ItemStack stack = (ItemStack) (Object) this;

		// Only apply rarity to tools, weapons, and armor
		if (shouldApplyRarity(item) && !stack.contains(ModDataComponents.RARITY_DATA)) {
			// Default to LOOT source type for general item creation
			var rarityData = RarityManager.generateRarityData(RarityManager.SourceType.LOOT);
			if (rarityData != null) {
				stack.set(ModDataComponents.RARITY_DATA, rarityData);
			}
		}
	}

	private boolean shouldApplyRarity(Item item) {
		// Check for tools, weapons, and armor using Item properties
		// This approach works across all Minecraft versions
		return item instanceof MiningToolItem ||
				item instanceof SwordItem ||
				item instanceof ArmorItem ||
				item instanceof BowItem ||
				item instanceof CrossbowItem ||
				item instanceof TridentItem ||
				item instanceof ShieldItem ||
				item instanceof ElytraItem ||
				item instanceof FishingRodItem ||
				item instanceof ShearsItem ||
				item instanceof FlintAndSteelItem;
	}
}
