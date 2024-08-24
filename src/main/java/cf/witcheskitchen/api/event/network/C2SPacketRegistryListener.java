package cf.witcheskitchen.api.event.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.packet.CustomPayload;

public interface C2SPacketRegistryListener<T extends CustomPayload> extends PacketRegistryListener<T> {
    void handle(T payload, ServerPlayNetworking.Context context);

    default void register() {
        ServerPlayNetworking.registerGlobalReceiver(this.type().id(), this::handle);
    }
}