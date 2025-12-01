package com.mosberg.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mosberg.modifier.StatModifier;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.util.List;

public record RarityData(String rarityId, List<StatModifier> modifiers) {
	public static final Codec<RarityData> CODEC = RecordCodecBuilder.create(instance ->
			instance.group(
					Codec.STRING.fieldOf("rarity_id").forGetter(RarityData::rarityId),
					StatModifier.CODEC.listOf().fieldOf("modifiers").forGetter(RarityData::modifiers)
			).apply(instance, RarityData::new)
	);

	public static final PacketCodec<RegistryByteBuf, RarityData> PACKET_CODEC = PacketCodec.tuple(
			PacketCodecs.STRING, RarityData::rarityId,
			StatModifier.PACKET_CODEC.collect(PacketCodecs.toList()), RarityData::modifiers,
			RarityData::new
	);
}
