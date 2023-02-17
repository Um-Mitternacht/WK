package cf.witcheskitchen.common.util;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.quiltmc.qsl.networking.api.PlayerLookup;

import java.util.function.Consumer;

public final class PacketHelper {

    private PacketHelper() {
        // Don't let anyone instantiate this
    }

    /**
     * Sends the packet to all players tracking the given entity in a server world.
     *
     * <p><b>Warning</b>: If the provided entity is a player, it is not
     * guaranteed by the contract that said player is included in the
     * resulting stream.
     *
     * @param trackedEntity the entity being tracked
     * @throws IllegalArgumentException if the entity is not in a server world
     */

    public static void sendToAllTracking(final Entity trackedEntity, Consumer<? super ServerPlayerEntity> packet) {
        PlayerLookup.tracking(trackedEntity).forEach(packet);
    }

}
