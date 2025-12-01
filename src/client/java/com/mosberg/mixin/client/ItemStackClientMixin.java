package com.mosberg.mixin.client;

import com.mosberg.component.ModDataComponents;
import com.mosberg.component.RarityData;
import com.mosberg.rarity.Rarity;
import com.mosberg.rarity.RarityManager;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackClientMixin {
	@Inject(method = "getName", at = @At("RETURN"), cancellable = true)
	private void modifyItemName(CallbackInfoReturnable<Text> cir) {
		ItemStack stack = (ItemStack) (Object) this;
		RarityData rarityData = stack.get(ModDataComponents.RARITY_DATA);

		if (rarityData != null) {
			Rarity rarity = RarityManager.getRarityById(rarityData.rarityId());
			if (rarity != null) {
				Text originalName = cir.getReturnValue();
				Text coloredName = originalName.copy().styled(style -> style.withColor(rarity.getColor()));
				cir.setReturnValue(coloredName);
			}
		}
	}
}
