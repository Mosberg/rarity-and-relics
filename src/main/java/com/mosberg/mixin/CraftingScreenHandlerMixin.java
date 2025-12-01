package com.mosberg.mixin;

import com.mosberg.component.ModDataComponents;
import com.mosberg.rarity.RarityManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingScreenHandler.class)
public abstract class CraftingScreenHandlerMixin extends ScreenHandler {
	protected CraftingScreenHandlerMixin(ScreenHandlerType<?> type, int syncId) {
		super(type, syncId);
	}

	@Inject(method = "onContentChanged", at = @At("TAIL"))
	private void onCraftingResult(net.minecraft.inventory.Inventory inventory, CallbackInfo ci) {
		if (inventory instanceof CraftingResultInventory) {
			ItemStack result = inventory.getStack(0);
			if (!result.isEmpty() && !result.contains(ModDataComponents.RARITY_DATA)) {
				var rarityData = RarityManager.generateRarityData();
				if (rarityData != null) {
					result.set(ModDataComponents.RARITY_DATA, rarityData);
				}
			}
		}
	}
}
