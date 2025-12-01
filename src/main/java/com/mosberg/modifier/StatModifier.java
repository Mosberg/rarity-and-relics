package com.mosberg.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

public record StatModifier(ModifierType type, double value) {
	public static final Codec<StatModifier> CODEC = RecordCodecBuilder.create(instance ->
			instance.group(
					Codec.STRING.xmap(ModifierType::valueOf, ModifierType::name)
							.fieldOf("type").forGetter(StatModifier::type),
					Codec.DOUBLE.fieldOf("value").forGetter(StatModifier::value)
			).apply(instance, StatModifier::new)
	);

	public static final PacketCodec<RegistryByteBuf, StatModifier> PACKET_CODEC = PacketCodec.tuple(
			PacketCodecs.STRING.xmap(ModifierType::valueOf, ModifierType::name), StatModifier::type,
			PacketCodecs.DOUBLE, StatModifier::value,
			StatModifier::new
	);

	public String getFormattedValue() {
		double percentage = value * 100;
		String sign = percentage > 0 ? "+" : "";
		return String.format("%s%.1f%%", sign, percentage);
	}
}
