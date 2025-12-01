package com.mosberg.component;

import com.mosberg.RarityAndRelics;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponents {
	// Component type for storing item rarity data
	public static final ComponentType<RarityData> RARITY_DATA = register(
			"rarity_data",
			builder -> builder.codec(RarityData.CODEC)
	);


	private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
		return Registry.register(
				Registries.DATA_COMPONENT_TYPE,
				Identifier.of(RarityAndRelics.MOD_ID, name),
				builderOperator.apply(ComponentType.builder()).build()
		);
	}

	public static void register() {
		RarityAndRelics.LOGGER.info("Registering custom data components for " + RarityAndRelics.MOD_ID);
	}
}
