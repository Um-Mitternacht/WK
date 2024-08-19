package cf.witcheskitchen.common.component.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

public record SeedTypeData(
    String name, String type, int color
) {
    public static final Codec<SeedTypeData> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            Codec.STRING
                .fieldOf("name")
                .forGetter(SeedTypeData::name),
            Codec.STRING
                .fieldOf("type")
                .forGetter(SeedTypeData::type),
            Codec.INT
                .optionalFieldOf("color", 0xFFFFFF)
                .forGetter(SeedTypeData::color)
        )
            .apply(instance, SeedTypeData::new)
    );

    public static final PacketCodec<PacketByteBuf, SeedTypeData> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.STRING, SeedTypeData::name,
        PacketCodecs.STRING, SeedTypeData::type,
        PacketCodecs.VAR_INT, SeedTypeData::color,
        SeedTypeData::new
    );

    public String getBlockId() {
        return this.name + "_" + this.type;
    }
}
