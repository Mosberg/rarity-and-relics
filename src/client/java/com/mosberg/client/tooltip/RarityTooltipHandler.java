package com.mosberg.client.tooltip;

import com.mosberg.component.ModDataComponents;
import com.mosberg.component.RarityData;
import com.mosberg.modifier.StatModifier;
import com.mosberg.rarity.Rarity;
import com.mosberg.rarity.RarityManager;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

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

			// Add rarity tier with name
			lines.add(Text.literal("✦ " + rarity.getName() + " ✦")
					.styled(style -> style.withColor(rarity.getColor()).withBold(true)));

			// Add rarity description
			lines.add(Text.literal(rarity.getDescription())
					.styled(style -> style.withColor(rarity.getColor()).withItalic(true)));

			// Add blank line before modifiers
			if (!rarityData.modifiers().isEmpty()) {
				lines.add(Text.empty());
				lines.add(Text.literal("Modifiers:").formatted(Formatting.GRAY, Formatting.BOLD));
			}

			// Add modifiers
			for (StatModifier modifier : rarityData.modifiers()) {
				Formatting color = modifier.value() > 0 ? Formatting.GREEN : Formatting.RED;
				String icon = modifier.type().getIcon();
				String modifierText = "  " + icon + " " + modifier.getFormattedValue() + " " + modifier.type().getDisplayName();

				lines.add(Text.literal(modifierText).formatted(color));
			}

			// Add asset info (for debugging/info)
			if (tooltipContext.isAdvanced()) {
				lines.add(Text.empty());
				lines.add(Text.literal("Rarity ID: " + rarity.getId()).formatted(Formatting.DARK_GRAY));
				lines.add(Text.literal("Icon: " + rarity.getAssets().getIcon()).formatted(Formatting.DARK_GRAY));
				lines.add(Text.literal("Frame: " + rarity.getAssets().getFrame()).formatted(Formatting.DARK_GRAY));
			}
		});
	}
}
