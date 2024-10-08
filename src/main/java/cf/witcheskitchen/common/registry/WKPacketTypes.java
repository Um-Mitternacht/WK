package cf.witcheskitchen.common.registry;

import cf.witcheskitchen.api.event.network.C2SPacketRegistryListener;
import cf.witcheskitchen.common.network.packet.ParticlePacket;
import cf.witcheskitchen.common.network.packet.SoundPacket;
import cf.witcheskitchen.common.network.packet.SplashParticlePacket;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

import java.util.LinkedList;
import java.util.List;

public interface WKPacketTypes {


    // Packets that are sent by the client and received by the server
    List<C2SPacketRegistryListener> SERVER_PACKETS = new LinkedList<>();

    static <T extends C2SPacketRegistryListener> T registerServerPacket(T handler) {
        SERVER_PACKETS.add(handler);
        return handler;
    }

    static void init() {
        SERVER_PACKETS.forEach(handler -> handler.register());

        // TODO: maybe move this to a separate class?
        //       the packet handlers are on the client-side, and our sources are split,
        //       which makes this a little more difficult.
        PayloadTypeRegistry.playS2C().register(ParticlePacket.ID, ParticlePacket.TYPE.codec());
        PayloadTypeRegistry.playS2C().register(SoundPacket.ID, SoundPacket.TYPE.codec());
        PayloadTypeRegistry.playS2C().register(SplashParticlePacket.ID, SplashParticlePacket.TYPE.codec());
    }
}
