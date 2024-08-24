package cf.witcheskitchen.common.network.packet;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.api.event.network.CustomPacketCodecs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record SoundPacket(
        BlockPos pos,
        Identifier sound,
        SoundCategory category
) implements CustomPayload {
    public static final Id<SoundPacket> ID = new Id<>(WitchesKitchen.id("sound"));
    public static final Type<RegistryByteBuf, SoundPacket> TYPE = new Type<>(ID, PacketCodec.tuple(
            BlockPos.PACKET_CODEC, SoundPacket::pos,
            Identifier.PACKET_CODEC, SoundPacket::sound,
            CustomPacketCodecs.SOUND_CATEGORY, SoundPacket::category,
            SoundPacket::new
    ));

    public static void send(ServerPlayerEntity player, BlockPos pos, Identifier sound, SoundCategory category) {
        ServerPlayNetworking.send(player, new SoundPacket(pos, sound == null ? Identifier.of("") : sound, category));
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
