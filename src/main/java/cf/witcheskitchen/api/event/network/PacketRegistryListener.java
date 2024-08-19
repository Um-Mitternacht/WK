package cf.witcheskitchen.api.event.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.packet.CustomPayload;

public interface PacketRegistryListener<T extends CustomPayload> {
    CustomPayload.Type<RegistryByteBuf, T> type();
}
