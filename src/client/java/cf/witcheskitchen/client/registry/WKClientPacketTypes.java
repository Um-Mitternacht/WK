package cf.witcheskitchen.client.registry;

import cf.witcheskitchen.api.event.network.S2CPacketRegistryListener;
import cf.witcheskitchen.client.network.packet.ParticlePacketHandler;
import cf.witcheskitchen.client.network.packet.SoundPacketHandler;
import cf.witcheskitchen.client.network.packet.SplashParticlePacketHandler;
import net.minecraft.network.packet.CustomPayload;

import java.util.LinkedList;
import java.util.List;

public interface WKClientPacketTypes {
    // Packets that are sent by the server and received by the client
    List<S2CPacketRegistryListener<? extends CustomPayload>> CLIENT_PACKETS = new LinkedList<>();

    ParticlePacketHandler PARTICLE_PACKET_HANDLER = registerClientPacket(new ParticlePacketHandler());
    SplashParticlePacketHandler SPLASH_PARTICLE_HANDLER = registerClientPacket(new SplashParticlePacketHandler());
    SoundPacketHandler SOUND_EVENT = registerClientPacket(new SoundPacketHandler());

    static <U extends CustomPayload, T extends S2CPacketRegistryListener<U>> T registerClientPacket(T handler) {
        CLIENT_PACKETS.add(handler);
        return handler;
    }

    static void init() {
        CLIENT_PACKETS.forEach(S2CPacketRegistryListener::register);
    }
}
