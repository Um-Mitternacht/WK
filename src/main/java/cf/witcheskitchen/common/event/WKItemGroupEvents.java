package cf.witcheskitchen.common.event;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.common.registry.WKBlocks;
import cf.witcheskitchen.common.registry.WKItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

import static cf.witcheskitchen.common.registry.WKBlocks.*;
import static cf.witcheskitchen.common.registry.WKItems.*;

public class WKItemGroupEvents {
    public static final RegistryKey<ItemGroup> GENERAL_TAB = RegistryKey.of(RegistryKeys.ITEM_GROUP, WitchesKitchen.id("general"));
    public static final RegistryKey<ItemGroup> FOOD_TAB = RegistryKey.of(RegistryKeys.ITEM_GROUP, WitchesKitchen.id("food"));

    public static void init() {
        Registry.register(Registries.ITEM_GROUP, GENERAL_TAB, FabricItemGroup.builder()
                .displayName(Text.translatable(GENERAL_TAB.getValue().toTranslationKey("itemGroup")))
                .icon(() -> new ItemStack(WKBlocks.IRON_WITCHES_OVEN.asItem()))
                .build()
        );
        Registry.register(Registries.ITEM_GROUP, FOOD_TAB, FabricItemGroup.builder()
                .displayName(Text.translatable(FOOD_TAB.getValue().toTranslationKey("itemGroup")))
                .icon(() -> new ItemStack(WKItems.ELDER_TEA)).build()
        );

        ItemGroupEvents.modifyEntriesEvent(GENERAL_TAB).register(WKItemGroupEvents::generalGroup);
        ItemGroupEvents.modifyEntriesEvent(FOOD_TAB).register(WKItemGroupEvents::foodGroup);
    }

    private static void generalGroup(FabricItemGroupEntries e) {
        e.add(CHALK);
        e.add(ENCHANTED_CHALK);
        e.add(BONE_NEEDLE);
        e.add(TAGLOCK);
        e.add(WAYSTONE);
        e.add(SALT_BLOCK);
        e.add(HEART_OF_INNOCENCE);

        e.add(BELLADONNA_BLOSSOM);
        e.add(WORMWOOD_SPRIG);
        e.add(ELDER_BLOSSOM);
        e.add(CONEFLOWER_BLOSSOM);
        e.add(SANGUINARY_BLOSSOM);
        e.add(ST_JOHNS_WORT_BLOSSOM);
        e.add(IRIS_BLOSSOM);
        e.add(CHAMOMILE_BLOSSOM);
        e.add(GINGER_ROOTS);
        e.add(HELLEBORE_BLOSSOM);
        e.add(FOXGLOVE_BLOSSOM);

        e.add(CALEFACTION_BUNDLE);
        e.add(CURSE_OF_MIDAS_BUNDLE);
        e.add(FEAR_BUNDLE);
        e.add(FIELD_GEISTER_HEX_BUNDLE);
        e.add(HUNGRY_POCKETS_BUNDLE);
        e.add(INEPTITUDE_BUNDLE);
        e.add(MISPLACEMENT_BUNDLE);
        e.add(NULLARDOR_BUNDLE);
        e.add(PARANOIA_BUNDLE);
        e.add(PERUNS_JEST_BUNDLE);

        e.add(AMARANTH_SEEDS);
        e.add(BELLADONNA_SEEDS);
        e.add(BRIAR_SEEDS);
        e.add(CAMELLIA_SEEDS);
        e.add(CHAMOMILE_SEEDS);
        e.add(CONEFLOWER_SEEDS);
        e.add(FOXGLOVE_SEEDS);
        e.add(HELLEBORE_SEEDS);
        e.add(IRIS_SEEDS);
        e.add(SANGUINARY_SEEDS);
        e.add(ST_JOHNS_WORT_SEEDS);
        e.add(WORMWOOD_SEEDS);

        e.add(BLACKTHORN_LOG);
        e.add(BLACKTHORN_WOOD);
        e.add(STRIPPED_BLACKTHORN_LOG);
        e.add(STRIPPED_BLACKTHORN_WOOD);
        e.add(BLACKTHORN_PLANKS);
        e.add(BLACKTHORN_STAIRS);
        e.add(BLACKTHORN_SLAB);
        e.add(BLACKTHORN_FENCE);
        e.add(BLACKTHORN_FENCE_GATE);
        e.add(BLACKTHORN_DOOR);
        e.add(BLACKTHORN_PRESSURE_PLATE);
        e.add(BLACKTHORN_BUTTON);
        e.add(BLACKTHORN_LEAVES);
        e.add(BLACKTHORN_SAPLING);

        e.add(ELDER_LOG);
        e.add(ELDER_WOOD);
        e.add(STRIPPED_ELDER_LOG);
        e.add(STRIPPED_ELDER_WOOD);
        e.add(ELDER_PLANKS);
        e.add(ELDER_STAIRS);
        e.add(ELDER_SLAB);
        e.add(ELDER_FENCE);
        e.add(ELDER_FENCE_GATE);
        e.add(ELDER_DOOR);
        e.add(ELDER_PRESSURE_PLATE);
        e.add(ELDER_BUTTON);
        e.add(ELDER_LEAVES);
        e.add(ELDER_SAPLING);

        e.add(HAWTHORN_LOG);
        e.add(HAWTHORN_WOOD);
        e.add(STRIPPED_HAWTHORN_LOG);
        e.add(STRIPPED_HAWTHORN_WOOD);
        e.add(HAWTHORN_PLANKS);
        e.add(HAWTHORN_STAIRS);
        e.add(HAWTHORN_SLAB);
        e.add(HAWTHORN_FENCE);
        e.add(HAWTHORN_FENCE_GATE);
        e.add(HAWTHORN_DOOR);
        e.add(HAWTHORN_PRESSURE_PLATE);
        e.add(HAWTHORN_BUTTON);
        e.add(HAWTHORN_LEAVES);
        e.add(HAWTHORN_SAPLING);

        e.add(JUNIPER_LOG);
        e.add(JUNIPER_WOOD);
        e.add(STRIPPED_JUNIPER_LOG);
        e.add(STRIPPED_JUNIPER_WOOD);
        e.add(JUNIPER_PLANKS);
        e.add(JUNIPER_STAIRS);
        e.add(JUNIPER_SLAB);
        e.add(JUNIPER_FENCE);
        e.add(JUNIPER_FENCE_GATE);
        e.add(JUNIPER_DOOR);
        e.add(JUNIPER_PRESSURE_PLATE);
        e.add(JUNIPER_BUTTON);
        e.add(JUNIPER_LEAVES);
        e.add(JUNIPER_SAPLING);

        e.add(ROWAN_LOG);
        e.add(ROWAN_WOOD);
        e.add(STRIPPED_ROWAN_LOG);
        e.add(STRIPPED_ROWAN_WOOD);
        e.add(ROWAN_PLANKS);
        e.add(ROWAN_STAIRS);
        e.add(ROWAN_SLAB);
        e.add(ROWAN_FENCE);
        e.add(ROWAN_FENCE_GATE);
        e.add(ROWAN_DOOR);
        e.add(ROWAN_PRESSURE_PLATE);
        e.add(ROWAN_BUTTON);
        e.add(ROWAN_LEAVES);
        e.add(ROWAN_SAPLING);

        e.add(SUMAC_LOG);
        e.add(SUMAC_WOOD);
        e.add(STRIPPED_SUMAC_LOG);
        e.add(STRIPPED_SUMAC_WOOD);
        e.add(SUMAC_PLANKS);
        e.add(SUMAC_STAIRS);
        e.add(SUMAC_SLAB);
        e.add(SUMAC_FENCE);
        e.add(SUMAC_FENCE_GATE);
        e.add(SUMAC_DOOR);
        e.add(SUMAC_PRESSURE_PLATE);
        e.add(SUMAC_BUTTON);
        e.add(SUMAC_LEAVES);
        e.add(SUMAC_SAPLING);

        e.add(TEAPOT);
        e.add(CAST_IRON_TEAPOT);
        e.add(COPPER_TEAPOT);
        e.add(WAXED_COPPER_TEAPOT);
        e.add(EXPOSED_COPPER_TEAPOT);
        e.add(WAXED_EXPOSED_COPPER_TEAPOT);
        e.add(WEATHERED_COPPER_TEAPOT);
        e.add(WAXED_WEATHERED_COPPER_TEAPOT);
        e.add(OXIDIZED_COPPER_TEAPOT);
        e.add(WAXED_OXIDIZED_COPPER_TEAPOT);

        e.add(IRON_WITCHES_OVEN);
        e.add(COPPER_WITCHES_OVEN);
        e.add(WAXED_COPPER_WITCHES_OVEN);
        e.add(EXPOSED_COPPER_WITCHES_OVEN);
        e.add(WAXED_EXPOSED_COPPER_WITCHES_OVEN);
        e.add(WEATHERED_COPPER_WITCHES_OVEN);
        e.add(WAXED_WEATHERED_COPPER_WITCHES_OVEN);
        e.add(OXIDIZED_COPPER_WITCHES_OVEN);
        e.add(WAXED_OXIDIZED_COPPER_WITCHES_OVEN);
        e.add(IRON_WITCHES_CAULDRON);
        e.add(OAK_BREWING_BARREL);
        e.add(SPRUCE_BREWING_BARREL);
        e.add(BIRCH_BREWING_BARREL);
        e.add(JUNGLE_BREWING_BARREL);
        e.add(ACACIA_BREWING_BARREL);
        e.add(DARK_OAK_BREWING_BARREL);
        e.add(CRIMSON_BREWING_BARREL);
        e.add(WARPED_BREWING_BARREL);

        e.add(CU_SITH_SPAWN_EGG);
        e.add(CHURCH_GRIM_SPAWN_EGG);
        e.add(FERRET_SPAWN_EGG);
        e.add(HEDGEHOG_SPAWN_EGG);
        e.add(ROGGENWOLF_SPAWN_EGG);
    }

    private static void foodGroup(FabricItemGroupEntries e) {
        e.add(AMARANTH_SPRIG);
        e.add(MINT_SPRIG);
        e.add(ROWAN_BERRIES);
        e.add(SLOE_BERRIES);
        e.add(JUNIPER_BERRIES);
        e.add(BLACKBERRY);
        e.add(HAWTHORN_BERRIES);
        e.add(SUMAC_BERRIES);
        e.add(BRIAR_HIPS);
        e.add(TEA_LEAF);

        e.add(BLACKBERRY_TEA);
        e.add(CHAMOMILE_TEA);
        e.add(DOGROSE_TEA);
        e.add(ECHINACEA_TEA);
        e.add(ELDER_TEA);
        e.add(GINGER_TEA);
        e.add(HAWTHORN_TEA);
        e.add(ST_JOHNS_WORT_TEA);
        e.add(MINT_TEA);
        e.add(SUMAC_TEA);
        e.add(YARROW_TEA);

        e.add(ABSINTHE);
        e.add(BLACKBERRY_LIQUEUR);
        e.add(BRINJEVEC);
        e.add(HOLUNDERSEKT);
        e.add(JUNIPER_MEAD);
        e.add(RUM);
        e.add(TRAVARICA);
        e.add(GROUND_BEEF);
        e.add(GROUND_MUTTON);
        e.add(GROUND_PORK);
        e.add(HEART_PIE);
        e.add(ROOTS_PLATTER);
        e.add(DEMONIC_STEW);
        e.add(MEATY_STEW);
        e.add(VEGETABLE_STEW);
    }


}
