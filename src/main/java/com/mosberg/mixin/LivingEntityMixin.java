package com.mosberg.mixin;

import com.mosberg.component.ModDataComponents;
import com.mosberg.rarity.RarityManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@ModifyVariable(
			method = "dropLoot",
			at = @At("HEAD"),
			argsOnly = true
	)
	private DamageSource applyRarityToDrops(DamageSource source) {
		// This mixin is triggered when an entity dies and drops loot
		// The actual item modification happens in ItemStackMixin, but we can track this context
		return source;
	}
}
