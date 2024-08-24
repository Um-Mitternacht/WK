package cf.witcheskitchen.common.network.packet;

import cf.witcheskitchen.WitchesKitchen;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public record ParticlePacket(
        BlockPos pos,
        Identifier particle,
        Identifier sound,
        byte range
) implements CustomPayload {
    public static final Id<ParticlePacket> ID = new Id<>(WitchesKitchen.id("particle"));
    public static final Type<RegistryByteBuf, ParticlePacket> TYPE = new Type<>(ID, PacketCodec.tuple(
            BlockPos.PACKET_CODEC, ParticlePacket::pos,
            Identifier.PACKET_CODEC, ParticlePacket::particle,
            Identifier.PACKET_CODEC, ParticlePacket::sound,
            PacketCodecs.BYTE, ParticlePacket::range,
            ParticlePacket::new
    ));

    public static void send(ServerPlayerEntity player, final BlockPos pos, final Identifier particle, final byte range) {
        send(player, pos, particle, null, range);
    }

    public static void send(ServerPlayerEntity player, final BlockPos pos, final Identifier particle, final @Nullable Identifier sound, final byte range) {
        ServerPlayNetworking.send(player, new ParticlePacket(pos, particle, sound == null ? Identifier.of("") : sound, range));
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
