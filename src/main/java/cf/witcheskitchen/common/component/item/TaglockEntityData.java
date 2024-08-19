package cf.witcheskitchen.common.component.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record TaglockEntityData(UUID uuid, String name) {
    public static final Codec<TaglockEntityData> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            Uuids.CODEC
                .fieldOf("uuid")
                .forGetter(TaglockEntityData::uuid),
            Codec.STRING
                .fieldOf("name")
                .forGetter(TaglockEntityData::name)
        )
            .apply(instance, TaglockEntityData::new)
    );

    public static final PacketCodec<PacketByteBuf, TaglockEntityData> PACKET_CODEC = PacketCodec.tuple(
        Uuids.PACKET_CODEC, TaglockEntityData::uuid,
        PacketCodecs.STRING, TaglockEntityData::name,
        TaglockEntityData::new
    );
}
