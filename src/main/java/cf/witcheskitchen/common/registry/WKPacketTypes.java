package cf.witcheskitchen.common.registry;

import cf.witcheskitchen.api.event.network.C2SPacketRegistryListener;

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
    }
}
