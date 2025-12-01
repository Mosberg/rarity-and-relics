package com.mosberg.mixin.client;

import com.mosberg.component.ModDataComponents;
import com.mosberg.component.RarityData;
import com.mosberg.rarity.Rarity;
import com.mosberg.rarity.RarityManager;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
	@Inject(method = "renderGuiItemOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
			at = @At("TAIL"))
	private void renderRarityBorder(TextRenderer renderer, ItemStack stack, int x, int y, String countLabel, CallbackInfo ci) {
		// This could be used to render a colored border around items in the future
		// For now, we'll rely on the name color change
	}
}
