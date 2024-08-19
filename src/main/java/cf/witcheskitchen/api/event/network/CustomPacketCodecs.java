package cf.witcheskitchen.api.event.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Vec3d;

public interface CustomPacketCodecs {
    PacketCodec<PacketByteBuf, Vec3d> VECTOR3D = PacketCodec.of(
        (value, buf) ->
            buf.writeDouble(value.x)
                .writeDouble(value.y)
                .writeDouble(value.z),
        buf -> new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble())
    );

    PacketCodec<PacketByteBuf, SoundCategory> SOUND_CATEGORY = PacketCodec.of(
        (value, buf) -> buf.writeString(value.name()),
        buf -> SoundCategory.valueOf(buf.readString())
    );
}
