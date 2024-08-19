package cf.witcheskitchen.common.network.packet;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.api.event.network.CustomPacketCodecs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public record SplashParticlePacket(
    BlockPos pos,
    Vec3d rgb,
    Vec3d offset,
    byte amount
) implements CustomPayload {
    public static final Id<SplashParticlePacket> ID = new Id<>(WitchesKitchen.id("splash_particle"));
    public static final Type<RegistryByteBuf, SplashParticlePacket> TYPE = new Type<>(ID, PacketCodec.tuple(
        BlockPos.PACKET_CODEC, SplashParticlePacket::pos,
        CustomPacketCodecs.VECTOR3D, SplashParticlePacket::rgb,
        CustomPacketCodecs.VECTOR3D, SplashParticlePacket::offset,
        PacketCodecs.BYTE, SplashParticlePacket::amount,
        SplashParticlePacket::new
    ));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static void send(ServerPlayerEntity player, BlockPos pos, double r, double g, double b) {
        send(player, pos, r, g, b, 0, 0, 0, (byte) 1);
    }

    public static void send(ServerPlayerEntity player, BlockPos pos, double r, double g, double b, double offsetX, double offsetY, double offsetZ, byte amount) {
        ServerPlayNetworking.send(player, new SplashParticlePacket(pos, new Vec3d(r, g, b), new Vec3d(offsetX, offsetY, offsetZ), amount));
    }
}
