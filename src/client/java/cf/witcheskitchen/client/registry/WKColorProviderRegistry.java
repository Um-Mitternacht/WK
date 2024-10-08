package cf.witcheskitchen.client.registry;

import cf.witcheskitchen.common.registry.WKBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.biome.FoliageColors;

@Environment(EnvType.CLIENT)
public interface WKColorProviderRegistry {
    static void init() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> pos != null && world != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(),
                WKBlocks.SUMAC_LEAVES,
                WKBlocks.JUNIPER_LEAVES
        );

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ColorProviderRegistry.BLOCK.get(((BlockItem) stack.getItem()).getBlock()).getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, tintIndex),
                WKBlocks.SUMAC_LEAVES,
                WKBlocks.JUNIPER_LEAVES
        );

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xD8EAB4, WKBlocks.ENCHANTED_GLYPH);
    }
}
