package cf.witcheskitchen.api.banner;

import cf.witcheskitchen.WK;
import cf.witcheskitchen.api.banner.impl.LoomPatternsInternal;
import cf.witcheskitchen.api.banner.loom.LoomPattern;
import cf.witcheskitchen.api.banner.loom.LoomPatterns;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.fabricmc.fabric.api.event.registry.RegistryIdRemapCallback;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.List;

//Todo: Pull old banner registry. Use vanilla's provided system if we want to add any, since we don't need mixins for it anymore.
public class WKBannerRegistry {
    public static final Registry<LoomPattern> LOOM_PATTERN_REGISTRY = FabricRegistryBuilder
            .createSimple(LoomPattern.class, new Identifier(WK.MODID, "loom_patterns"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();


    private static final List<Identifier> BANNER_LOOT_TABLES = Arrays.asList(
            new Identifier("minecraft", "blocks/black_banner"),
            new Identifier("minecraft", "blocks/red_banner"),
            new Identifier("minecraft", "blocks/green_banner"),
            new Identifier("minecraft", "blocks/brown_banner"),
            new Identifier("minecraft", "blocks/blue_banner"),
            new Identifier("minecraft", "blocks/purple_banner"),
            new Identifier("minecraft", "blocks/cyan_banner"),
            new Identifier("minecraft", "blocks/light_gray_banner"),
            new Identifier("minecraft", "blocks/gray"),
            new Identifier("minecraft", "blocks/pink_banner"),
            new Identifier("minecraft", "blocks/lime_banner"),
            new Identifier("minecraft", "blocks/yellow_banner"),
            new Identifier("minecraft", "blocks/light_blue_banner"),
            new Identifier("minecraft", "blocks/magenta_banner"),
            new Identifier("minecraft", "blocks/orange_banner"),
            new Identifier("minecraft", "blocks/white_banner")
    );

    public static void registerBanner() {
        RegistryIdRemapCallback.event(LOOM_PATTERN_REGISTRY).register(state -> LoomPatternsInternal.remapLoomIndices());

        // registry sync is longer called on the server or in singleplayer, so we must set up the indices here using
        // a registry item added callback.
        for (LoomPattern p : LOOM_PATTERN_REGISTRY) {
            LoomPatternsInternal.addPattern(p);
        }

     //   RegistryEntryAddedCallback.event(LOOM_PATTERN_REGISTRY).register((raw, id, p) -> LoomPatternsInternal.addPattern(p));
//        LootTableEvents.MODIFY.register((resourceManager, lootManager, lootTableId, supplier, setter) -> {
//            if (BANNER_LOOT_TABLES.contains(lootTableId)) {
//                supplier.apply(CopyNbtLootFunction
//                        .builder(ContextLootNbtProvider.BLOCK_ENTITY)
//                        .withOperation(LoomPatternContainer.NBT_KEY, "BlockEntityTag." + LoomPatternContainer.NBT_KEY)
//                        .build()
//                );
//            }
//        });
    }

    public static void registerBannerClient() {
        ClientSpriteRegistryCallback.event(TexturedRenderLayers.BANNER_PATTERNS_ATLAS_TEXTURE)
                .register((texture, registry) -> {
                    for (LoomPattern pattern : LoomPatterns.REGISTRY) {
                        registry.register(pattern.getSpriteId("banner"));
                    }
                });
        ClientSpriteRegistryCallback.event(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE)
                .register((texture, registry) -> {
                    for (LoomPattern pattern : LoomPatterns.REGISTRY) {
                        registry.register(pattern.getSpriteId("shield"));
                    }
                });
    }

}
