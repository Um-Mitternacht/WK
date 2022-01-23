package cf.witcheskitchen.common.registry;

import cf.witcheskitchen.WK;
import cf.witcheskitchen.WKConfig;
import cf.witcheskitchen.common.items.WKSeedItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WKItems {

    // Flowers
    public static final Item BELLADONNA_BLOSSOM = new Item(new FabricItemSettings().group(WK.WK_SEED_GROUP));

    public static final Item AMARANTH_SEEDS = new WKSeedItem(WKBlocks.BELLADONNA, new FabricItemSettings().group(WK.WK_SEED_GROUP));
    public static final Item AMARANTH_SWEETBERRY_SEEDS = new WKSeedItem(WKBlocks.BELLADONNA_GLOW, new FabricItemSettings().group(WK.WK_SEED_GROUP));
    public static final Item AMARANTH_TORCH_SEEDS = new WKSeedItem(WKBlocks.BELLADONNA_NOCTURNAL, new FabricItemSettings().group(WK.WK_SEED_GROUP));
    public static final Item AMARANTH_SUNDEW_SEEDS = new WKSeedItem(WKBlocks.BELLADONNA_GLOW, new FabricItemSettings().group(WK.WK_SEED_GROUP));
    public static final Item AMARANTH_CREEPER_SEEDS = new WKSeedItem(WKBlocks.BELLADONNA_NOCTURNAL, new FabricItemSettings().group(WK.WK_SEED_GROUP));
    public static final Item AMARANTH_VIRIDIAN_SEEDS = new WKSeedItem(WKBlocks.BELLADONNA_GLOW, new FabricItemSettings().group(WK.WK_SEED_GROUP));
    public static final Item AMARANTH_GRISELIN_SEEDS = new WKSeedItem(WKBlocks.BELLADONNA_NOCTURNAL, new FabricItemSettings().group(WK.WK_SEED_GROUP));
    public static final Item AMARANTH_CERISE_SEEDS = new WKSeedItem(WKBlocks.BELLADONNA_GLOW, new FabricItemSettings().group(WK.WK_SEED_GROUP));
    public static final Item AMARANTH_DARK_PASSION_SEEDS = new WKSeedItem(WKBlocks.BELLADONNA_NOCTURNAL, new FabricItemSettings().group(WK.WK_SEED_GROUP));
    public static final Item AMARANTH_FIREBIRD_SEEDS = new WKSeedItem(WKBlocks.BELLADONNA_NOCTURNAL, new FabricItemSettings().group(WK.WK_SEED_GROUP));
    public static final Item BELLADONNA_SEEDS = new WKSeedItem(WKBlocks.BELLADONNA, new FabricItemSettings().group(WK.WK_SEED_GROUP));
    public static final Item BELLADONNA_GLOW_SEEDS = new WKSeedItem(WKBlocks.BELLADONNA_GLOW, new FabricItemSettings().group(WK.WK_SEED_GROUP));
    public static final Item BELLADONNA_NOCTURNAL_SEEDS = new WKSeedItem(WKBlocks.BELLADONNA_NOCTURNAL, new FabricItemSettings().group(WK.WK_SEED_GROUP));
    public static Item ST_JOHNS_WORT_SEEDS = new Item(new FabricItemSettings().group(WK.WK_GROUP));
    public static Item BRIAR_SEEDS = new Item(new FabricItemSettings().group(WK.WK_GROUP));
    public static Item CAMELLIA_SEEDS = new Item(new FabricItemSettings().group(WK.WK_GROUP));
    public static Item CHAMOMILE_SEEDS = new Item(new FabricItemSettings().group(WK.WK_GROUP));
    public static Item CONEFLOWER_SEEDS = new Item(new FabricItemSettings().group(WK.WK_GROUP));
    public static Item FOXGLOVE_SEEDS = new Item(new FabricItemSettings().group(WK.WK_GROUP));
    public static Item HELLEBORE_SEEDS = new Item(new FabricItemSettings().group(WK.WK_GROUP));
    public static Item IRIS_SEEDS = new Item(new FabricItemSettings().group(WK.WK_GROUP));
    public static Item SANGUINARY_SEEDS = new Item(new FabricItemSettings().group(WK.WK_GROUP));
    public static Item WORMWOOD_SEEDS = new Item(new FabricItemSettings().group(WK.WK_GROUP));

    public static Item DOLLOP_OF_FROSTING = new Item(new FabricItemSettings().food(WKFoodComponents.FROSTING).group(WK.WK_GROUP));
    public static Item CHOCOLATE_RUM_BALLS = new Item(new FabricItemSettings().food(WKFoodComponents.RUM_BALLS).group(WK.WK_GROUP));
    public static Item SUPER_BOOZE = new Item(new FabricItemSettings().food(WKFoodComponents.SUPER_BOOZE).group(WK.WK_GROUP));
    public static Item CU_SITH_SPAWN_EGG = new SpawnEggItem(WKEntities.CUSITH, 3421236, 3497531, new Item.Settings().group(WK.WK_GROUP));
    public static Item FERRET_SPAWN_EGG = new SpawnEggItem(WKEntities.FERRET, 9985082, 2631205, new Item.Settings().group(WK.WK_GROUP));

    public static void register() {
        registerItem("amaranth_seeds", AMARANTH_SEEDS);
        registerItem("amaranth_sweetberry_seeds", AMARANTH_SWEETBERRY_SEEDS);
        registerItem("amaranth_torch_seeds", AMARANTH_TORCH_SEEDS);
        registerItem("amaranth_sundew_seeds", AMARANTH_SUNDEW_SEEDS);
        registerItem("amaranth_creeper_seeds", AMARANTH_CREEPER_SEEDS);
        registerItem("amaranth_viridian_seeds", AMARANTH_VIRIDIAN_SEEDS);
        registerItem("amaranth_griselin_seeds", AMARANTH_GRISELIN_SEEDS);
        registerItem("amaranth_cerise_seeds", AMARANTH_CERISE_SEEDS);
        registerItem("amaranth_dark_passion_seeds", AMARANTH_DARK_PASSION_SEEDS);
        registerItem("amaranth_firebird_seeds", AMARANTH_FIREBIRD_SEEDS);
        registerItem("belladonna_blossom", BELLADONNA_BLOSSOM);
        registerItem("belladonna_seeds", BELLADONNA_SEEDS);
        registerItem("belladonna_glow_seeds", BELLADONNA_GLOW_SEEDS);
        registerItem("belladonna_nocturnal_seeds", BELLADONNA_NOCTURNAL_SEEDS);
        registerItem("dollop_of_frosting", DOLLOP_OF_FROSTING);
        registerItem("chocolate_rum_balls", CHOCOLATE_RUM_BALLS);
        registerItem("super_booze", SUPER_BOOZE);
        registerItem("cu_sith_spawn_egg", CU_SITH_SPAWN_EGG);
        registerItem("ferret_spawn_egg", FERRET_SPAWN_EGG);

        if (WKConfig.get().debugMode) {
            WK.logger.info("Witches Kitchen Base Items: Successfully Loaded");
        }
    }

    public static void registerItem(String id, Item item) {
        Registry.register(Registry.ITEM, new Identifier(WK.MODID, id), item);
    }
}

