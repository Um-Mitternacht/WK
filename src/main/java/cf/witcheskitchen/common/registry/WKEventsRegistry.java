package cf.witcheskitchen.common.registry;

import cf.witcheskitchen.common.event.WKEventsHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;

public interface WKEventsRegistry {

    static void init(EnvType type) {
        switch (type) {
            case SERVER -> {
                LootTableEvents.MODIFY.register(new WKEventsHandler.LootTablesListener());
            }
            case CLIENT -> {

            }
        }
    }
}
