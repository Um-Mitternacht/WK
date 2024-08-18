package cf.witcheskitchen.common.item;

import cf.witcheskitchen.WitchesKitchen;
import net.minecraft.component.Component;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.BlockPos;

public class WKItemComponents {
    public static final ComponentType<BlockPos> BLOCK_POS = register("block_pos", ComponentType.<BlockPos>builder()
        .codec(BlockPos.CODEC)
        .packetCodec(BlockPos.PACKET_CODEC)
        .build()
    );

    private static <T> ComponentType<T> register(String name, ComponentType<T> component) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, WitchesKitchen.id(name), component);
    }

    public static void init() {}
}
