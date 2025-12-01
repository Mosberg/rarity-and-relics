package com.mosberg.client.tooltip;

import com.mosberg.component.ModDataComponents;
import com.mosberg.component.RarityData;
import com.mosberg.modifier.StatModifier;
import com.mosberg.rarity.Rarity;
import com.mosberg.rarity.RarityManager;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class RarityTooltipHandler {
	public static void register() {
		ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, lines) -> {
			RarityData rarityData = stack.get(ModDataComponents.RARITY_DATA);
			if (rarityData == null) {
				return;
			}

			Rarity rarity = RarityManager.getRarityById(rarityData.rarityId());
			if (rarity == null) {
				return;
			}

			// Add blank line before rarity info
			lines.add(Text.empty());

			// Add rarity tier
			lines.add(Text.literal(rarity.getDisplayName() + " Item")
					.styled(style -> style.withColor(rarity.getColor()).withBold(true)));

			// Add modifiers
			for (StatModifier modifier : rarityData.modifiers()) {
				Formatting color = modifier.value() > 0 ? Formatting.GREEN : Formatting.RED;
				String icon = modifier.type().getIcon();
				String modifierText = icon + " " + modifier.getFormattedValue() + " " + modifier.type().getDisplayName();

				lines.add(Text.literal(modifierText).formatted(color));
			}
		});
	}
}
