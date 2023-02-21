package cf.witcheskitchen.common.registry;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.api.block.crop.WKCropBlock;
import cf.witcheskitchen.api.block.crop.WKTallCropBlock;
import cf.witcheskitchen.api.block.plant.WildPlantCropBlock;
import cf.witcheskitchen.api.block.plant.WildTallPlantCropBlock;
import cf.witcheskitchen.api.registry.ObjectDefinition;
import cf.witcheskitchen.common.block.*;
import cf.witcheskitchen.common.block.sapling.WKSaplingBlock;
import cf.witcheskitchen.common.block.crop.*;
import cf.witcheskitchen.common.world.generator.WKSaplingGenerator;
import cf.witcheskitchen.common.block.crop.types.*;
import cf.witcheskitchen.datagen.worldgen.WKConfiguredFeatures;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import java.util.*;

public interface WKBlocks {

    List<ObjectDefinition<Block>> BLOCKS = new ArrayList<>();
    List<ObjectDefinition<Item>> ITEMS = new ArrayList<>();
    Map<String, Block> TYPE_BLOCKS = new LinkedHashMap<>();

    static QuiltBlockSettings getCropSettings(){
        return QuiltBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.CROP);
    }

    Block SALT_BLOCK = register("salt", new SaltBlock(QuiltBlockSettings.of(Material.DECORATION).noCollision().breakInstantly()), true);

    Block RAW_GINGERBREAD_BLOCK = registerGingerBread("raw_gingerbread_block");
    Block RAW_CHISELED_GINGERBREAD_BLOCK = registerGingerBread("raw_chiseled_gingerbread_block");
    Block GINGERBREAD_BEVELED_BLOCK = registerGingerBread("gingerbread_beveled_block");
    Block GINGERBREAD_BLOCK = registerGingerBread("gingerbread_block");
    Block RAW_GINGERBREAD_BLOCK_STAIRS = registerGingerBread("raw_gingerbread_block_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block RAW_CHISELED_GINGERBREAD_BLOCK_STAIRS = registerGingerBread("raw_chiseled_gingerbread_block_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block GINGERBREAD_BEVELED_BLOCK_STAIRS = registerGingerBread("gingerbread_beveled_block_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block GINGERBREAD_BLOCK_STAIRS = registerGingerBread("gingerbread_block_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block RAW_GINGERBREAD_BEVELED_BLOCK_STAIRS = registerGingerBread("raw_gingerbread_beveled_block_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block FROSTED_GINGERBREAD_BLOCK_STAIRS = registerGingerBread("frosted_gingerbread_block_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_STAIRS = registerGingerBread("frosted_beveled_gingerbread_block_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block CHISELED_GINGERBREAD_BLOCK_STAIRS = registerGingerBread("chiseled_gingerbread_block_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block RAW_GINGERBREAD_TILED_BLOCK_STAIRS = registerGingerBread("raw_gingerbread_tiled_block_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block GINGERBREAD_TILED_BLOCK_STAIRS = registerGingerBread("gingerbread_tiled_block_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block FROSTING_BLOCK_STAIRS = registerGingerBread("frosting_block_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block FROSTED_GINGERBREAD_TILED_BLOCK_STAIRS = registerGingerBread("frosted_gingerbread_tiled_block_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_YELLOW_STAIRS = registerGingerBread("frosted_beveled_gingerbread_block_yellow_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_RED_STAIRS = registerGingerBread("frosted_beveled_gingerbread_block_red_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_PURPLE_STAIRS = registerGingerBread("frosted_beveled_gingerbread_block_purple_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_GREEN_STAIRS = registerGingerBread("frosted_beveled_gingerbread_block_green_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block FROSTED_TILED_GINGERBREAD_BLOCK_YELLOW_STAIRS = registerGingerBread("frosted_tiled_gingerbread_block_yellow_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block FROSTED_TILED_GINGERBREAD_BLOCK_RED_STAIRS = registerGingerBread("frosted_tiled_gingerbread_block_red_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block FROSTED_TILED_GINGERBREAD_BLOCK_PURPLE_STAIRS = registerGingerBread("frosted_tiled_gingerbread_block_purple_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block FROSTED_TILED_GINGERBREAD_BLOCK_GREEN_STAIRS = registerGingerBread("frosted_tiled_gingerbread_block_green_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block FROSTED_TILED_GINGERBREAD_BLOCK_VARIANT_STAIRS = registerGingerBread("frosted_tiled_gingerbread_block_variant_stairs", new StairsBlock(GINGERBREAD_BLOCK.getDefaultState(), gingerSettings()));
    Block RAW_GINGERBREAD_BEVELED_BLOCK = registerGingerBread("raw_gingerbread_beveled_block");
    Block FROSTED_GINGERBREAD_BLOCK = registerGingerBread("frosted_gingerbread_block");
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK = registerGingerBread("frosted_beveled_gingerbread_block");
    Block CHISELED_GINGERBREAD_BLOCK = registerGingerBread("chiseled_gingerbread_block");
    Block RAW_GINGERBREAD_TILED_BLOCK = registerGingerBread("raw_gingerbread_tiled_block");
    Block GINGERBREAD_TILED_BLOCK = registerGingerBread("gingerbread_tiled_block");
    Block FROSTING_BLOCK = registerGingerBread("frosting_block");
    Block FROSTED_GINGERBREAD_TILED_BLOCK = registerGingerBread("frosted_gingerbread_tiled_block");
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_YELLOW = registerGingerBread("frosted_beveled_gingerbread_block_yellow");
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_RED = registerGingerBread("frosted_beveled_gingerbread_block_red");
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_PURPLE = registerGingerBread("frosted_beveled_gingerbread_block_purple");
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_GREEN = registerGingerBread("frosted_beveled_gingerbread_block_green");
    Block FROSTED_TILED_GINGERBREAD_BLOCK_YELLOW = registerGingerBread("frosted_tiled_gingerbread_block_yellow");
    Block FROSTED_TILED_GINGERBREAD_BLOCK_RED = registerGingerBread("frosted_tiled_gingerbread_block_red");
    Block FROSTED_TILED_GINGERBREAD_BLOCK_PURPLE = registerGingerBread("frosted_tiled_gingerbread_block_purple");
    Block FROSTED_TILED_GINGERBREAD_BLOCK_GREEN = registerGingerBread("frosted_tiled_gingerbread_block_green");
    Block FROSTED_TILED_GINGERBREAD_BLOCK_VARIANT = registerGingerBread("frosted_tiled_gingerbread_block_variant");
    Block RAW_GINGERBREAD_BLOCK_SLAB = register("raw_gingerbread_block_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block RAW_CHISELED_GINGERBREAD_BLOCK_SLAB = register("raw_chiseled_gingerbread_block_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block GINGERBREAD_BEVELED_BLOCK_SLAB = register("gingerbread_beveled_block_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block GINGERBREAD_BLOCK_SLAB = register("gingerbread_block_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block RAW_GINGERBREAD_BEVELED_BLOCK_SLAB = register("raw_gingerbread_beveled_block_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block FROSTED_GINGERBREAD_BLOCK_SLAB = register("frosted_gingerbread_block_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_SLAB = register("frosted_beveled_gingerbread_block_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block CHISELED_GINGERBREAD_BLOCK_SLAB = register("chiseled_gingerbread_block_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block RAW_GINGERBREAD_TILED_BLOCK_SLAB = register("raw_gingerbread_tiled_block_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block GINGERBREAD_TILED_BLOCK_SLAB = register("gingerbread_tiled_block_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block FROSTING_BLOCK_SLAB = register("frosting_block_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block FROSTED_GINGERBREAD_TILED_BLOCK_SLAB = register("frosted_gingerbread_tiled_block_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_YELLOW_SLAB = register("frosted_beveled_gingerbread_block_yellow_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_RED_SLAB = register("frosted_beveled_gingerbread_block_red_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_PURPLE_SLAB = register("frosted_beveled_gingerbread_block_purple_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block FROSTED_BEVELED_GINGERBREAD_BLOCK_GREEN_SLAB = register("frosted_beveled_gingerbread_block_green_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block FROSTED_TILED_GINGERBREAD_BLOCK_YELLOW_SLAB = register("frosted_tiled_gingerbread_block_yellow_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block FROSTED_TILED_GINGERBREAD_BLOCK_RED_SLAB = register("frosted_tiled_gingerbread_block_red_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block FROSTED_TILED_GINGERBREAD_BLOCK_PURPLE_SLAB = register("frosted_tiled_gingerbread_block_purple_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block FROSTED_TILED_GINGERBREAD_BLOCK_GREEN_SLAB = register("frosted_tiled_gingerbread_block_green_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);
    Block FROSTED_TILED_GINGERBREAD_BLOCK_VARIANT_SLAB = register("frosted_tiled_gingerbread_block_variant_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CAKE)), WitchesKitchen.GINGER_TAB, true);

    Block ELDER_PLANKS = register("elder_planks", new Block(plankSettings()), true);
    Block ELDER_STAIRS = registerWoodenStair("elder_stairs", ELDER_PLANKS);
    Block SUMAC_PLANKS = register("sumac_planks", new Block(plankSettings()), true);
    Block SUMAC_STAIRS = registerWoodenStair("sumac_stairs", SUMAC_PLANKS);
    Block HAWTHORN_PLANKS = register("hawthorn_planks", new Block(plankSettings()), true);
    Block HAWTHORN_STAIRS = registerWoodenStair("hawthorn_stairs", HAWTHORN_PLANKS);
    Block BLACKTHORN_PLANKS = register("blackthorn_planks", new Block(plankSettings()), true);
    Block BLACKTHORN_STAIRS = registerWoodenStair("blackthorn_stairs", BLACKTHORN_PLANKS);
    Block JUNIPER_PLANKS = register("juniper_planks", new Block(plankSettings()), true);
    Block JUNIPER_STAIRS = registerWoodenStair("juniper_stairs", JUNIPER_PLANKS);
    Block ROWAN_PLANKS = register("rowan_planks", new Block(plankSettings()), true);
    Block ROWAN_STAIRS = registerWoodenStair("rowan_stairs", ROWAN_PLANKS);
    Block ELDER_LOG = registerLog("elder_log");
    Block SUMAC_LOG = registerLog("sumac_log");
    Block HAWTHORN_LOG = registerLog("hawthorn_log");
    Block BLACKTHORN_LOG = register("blackthorn_log", new BlackthornPillarBlock(logSettings()),true);
    Block JUNIPER_LOG = registerLog("juniper_log");
    Block ROWAN_LOG = registerLog("rowan_log");
    Block STRIPPED_BLACKTHORN_LOG = registerLog("stripped_blackthorn_log");
    Block STRIPPED_ELDER_LOG = registerLog("stripped_elder_log");
    Block STRIPPED_HAWTHORN_LOG = registerLog("stripped_hawthorn_log");
    Block STRIPPED_JUNIPER_LOG = registerLog("stripped_juniper_log");
    Block STRIPPED_ROWAN_LOG = registerLog("stripped_rowan_log");
    Block STRIPPED_SUMAC_LOG = registerLog("stripped_sumac_log");
    Block BLACKTHORN_WOOD = registerWood("blackthorn_wood", MapColor.TERRACOTTA_BLACK);
    Block STRIPPED_BLACKTHORN_WOOD = registerWood("stripped_blackthorn_wood", MapColor.TERRACOTTA_BLACK);
    Block ELDER_WOOD = registerWood("elder_wood", MapColor.OAK_TAN);
    Block STRIPPED_ELDER_WOOD = registerWood("stripped_elder_wood", MapColor.OAK_TAN);
    Block HAWTHORN_WOOD = registerWood("hawthorn_wood", MapColor.DIRT_BROWN);
    Block STRIPPED_HAWTHORN_WOOD = registerWood("stripped_hawthorn_wood", MapColor.DIRT_BROWN);
    Block JUNIPER_WOOD = registerWood("juniper_wood", MapColor.DEEPSLATE_GRAY);
    Block STRIPPED_JUNIPER_WOOD = registerWood("stripped_juniper_wood", MapColor.DEEPSLATE_GRAY);
    Block ROWAN_WOOD = registerWood("rowan_wood", MapColor.TERRACOTTA_BLACK);
    Block STRIPPED_ROWAN_WOOD = registerWood("stripped_rowan_wood", MapColor.TERRACOTTA_BLACK);
    Block SUMAC_WOOD = registerWood("sumac_wood", MapColor.OAK_TAN);
    Block STRIPPED_SUMAC_WOOD = registerWood("stripped_sumac_wood", MapColor.OAK_TAN);
    Block ELDER_SLAB = registerSlab("elder_slab");
    Block SUMAC_SLAB = registerSlab("sumac_slab");
    Block HAWTHORN_SLAB = registerSlab("hawthorn_slab");
    Block BLACKTHORN_SLAB = registerSlab("blackthorn_slab");
    Block JUNIPER_SLAB = registerSlab("juniper_slab");
    Block ROWAN_SLAB = registerSlab("rowan_slab");
    Block ELDER_LEAVES = registerLeaf("elder_leaves");
    Block SUMAC_LEAVES = registerLeaf("sumac_leaves");
    Block HAWTHORN_LEAVES = registerLeaf("hawthorn_leaves");
    Block BLACKTHORN_LEAVES = register("blackthorn_leaves", new BlackthornLeavesBlock(leavesSettings()),true);
    Block JUNIPER_LEAVES = registerLeaf("juniper_leaves");
    Block ROWAN_LEAVES = registerLeaf("rowan_leaves");
    //Tile Entities
    Block TEAPOT = register("teapot", new TeapotBlock(QuiltBlockSettings.copyOf(Blocks.TERRACOTTA).nonOpaque()),true);
    Block CAST_IRON_TEAPOT = register("cast_iron_teapot", new TeapotBlock(QuiltBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()),true);
    Block COPPER_TEAPOT = register("copper_teapot", new CopperTeapotBlock(QuiltBlockSettings.copyOf(Blocks.COPPER_BLOCK).nonOpaque(), Oxidizable.OxidizationLevel.UNAFFECTED),true);
    Block WAXED_COPPER_TEAPOT = register("waxed_copper_teapot", new TeapotBlock(QuiltBlockSettings.copyOf(Blocks.COPPER_BLOCK).nonOpaque()),true);
    Block EXPOSED_COPPER_TEAPOT = register("exposed_copper_teapot", new CopperTeapotBlock(QuiltBlockSettings.copyOf(Blocks.EXPOSED_COPPER).nonOpaque(), Oxidizable.OxidizationLevel.EXPOSED),true);
    Block WAXED_EXPOSED_COPPER_TEAPOT = register("waxed_exposed_copper_teapot", new TeapotBlock(QuiltBlockSettings.copyOf(Blocks.COPPER_BLOCK).nonOpaque()),true);
    Block WEATHERED_COPPER_TEAPOT = register("weathered_copper_teapot", new CopperTeapotBlock(QuiltBlockSettings.copyOf(Blocks.WEATHERED_COPPER).nonOpaque(), Oxidizable.OxidizationLevel.WEATHERED),true);
    Block WAXED_WEATHERED_COPPER_TEAPOT = register("waxed_weathered_copper_teapot", new TeapotBlock(QuiltBlockSettings.copyOf(Blocks.COPPER_BLOCK).nonOpaque()),true);
    Block OXIDIZED_COPPER_TEAPOT = register("oxidized_copper_teapot", new CopperTeapotBlock(QuiltBlockSettings.copyOf(Blocks.OXIDIZED_COPPER).nonOpaque(), Oxidizable.OxidizationLevel.OXIDIZED),true);
    Block WAXED_OXIDIZED_COPPER_TEAPOT = register("waxed_oxidized_copper_teapot", new TeapotBlock(QuiltBlockSettings.copyOf(Blocks.COPPER_BLOCK).nonOpaque()),true);


    Block IRON_WITCHES_OVEN = register("iron_witches_oven", new WitchesOvenBlock(QuiltBlockSettings.of(Material.METAL).strength(4.0F, 5.0F).requiresTool().nonOpaque().luminance(state -> state.get(WitchesOvenBlock.LIT) ? 13 : 0)),true);
    Block COPPER_WITCHES_OVEN = register("copper_witches_oven", new CopperWitchesOvenBlock(QuiltBlockSettings.copyOf(Blocks.COPPER_BLOCK).luminance(state -> state.get(WitchesOvenBlock.LIT) ? 13 : 0), Oxidizable.OxidizationLevel.UNAFFECTED),true);
    Block WAXED_COPPER_WITCHES_OVEN = register("waxed_copper_witches_oven", new WitchesOvenBlock(QuiltBlockSettings.copy(COPPER_WITCHES_OVEN)),true);
    Block EXPOSED_COPPER_WITCHES_OVEN = register("exposed_copper_witches_oven", new CopperWitchesOvenBlock(QuiltBlockSettings.copy(Blocks.EXPOSED_COPPER).luminance(state -> state.get(WitchesOvenBlock.LIT) ? 13 : 0), Oxidizable.OxidizationLevel.EXPOSED),true);
    Block WAXED_EXPOSED_COPPER_WITCHES_OVEN = register("waxed_exposed_copper_witches_oven", new WitchesOvenBlock(QuiltBlockSettings.copy(EXPOSED_COPPER_WITCHES_OVEN)),true);
    Block WEATHERED_COPPER_WITCHES_OVEN = register("weathered_copper_witches_oven", new CopperWitchesOvenBlock(QuiltBlockSettings.copy(Blocks.WEATHERED_COPPER).luminance(state -> state.get(WitchesOvenBlock.LIT) ? 13 : 0), Oxidizable.OxidizationLevel.WEATHERED),true);
    Block WAXED_WEATHERED_COPPER_WITCHES_OVEN = register("waxed_weathered_copper_witches_oven", new WitchesOvenBlock(QuiltBlockSettings.copy(WEATHERED_COPPER_WITCHES_OVEN)),true);
    Block OXIDIZED_COPPER_WITCHES_OVEN = register("oxidized_copper_witches_oven", new CopperWitchesOvenBlock(QuiltBlockSettings.copy(Blocks.OXIDIZED_COPPER).luminance(state -> state.get(WitchesOvenBlock.LIT) ? 13 : 0), Oxidizable.OxidizationLevel.OXIDIZED),true);
    Block WAXED_OXIDIZED_COPPER_WITCHES_OVEN = register("waxed_oxidized_copper_witches_oven", new WitchesOvenBlock(QuiltBlockSettings.copy(OXIDIZED_COPPER_WITCHES_OVEN)),true);
    Block GLYPH = register("glyph", new GlyphBlock(QuiltBlockSettings.of(Material.STONE).breakInstantly()), false);
    Block IRON_WITCHES_CAULDRON = register("iron_witches_cauldron", new WitchesCauldronBlock(QuiltBlockSettings.copy(Blocks.CAULDRON).luminance(state -> state.get(WitchesCauldronBlock.LIT) ? 13 : 0)),true);
    Block OAK_BREWING_BARREL = registerBarrel("oak_brewing_barrel");
    Block SPRUCE_BREWING_BARREL = registerBarrel("spruce_brewing_barrel");
    Block BIRCH_BREWING_BARREL = registerBarrel("birch_brewing_barrel");
    Block JUNGLE_BREWING_BARREL = registerBarrel("jungle_brewing_barrel");
    Block ACACIA_BREWING_BARREL = registerBarrel("acacia_brewing_barrel");
    Block DARK_OAK_BREWING_BARREL = registerBarrel("dark_oak_brewing_barrel");
    Block CRIMSON_BREWING_BARREL = registerBarrel("crimson_brewing_barrel");
    Block WARPED_BREWING_BARREL = registerBarrel("warped_brewing_barrel");

    //Sapling
    Block BLACKTHORN_SAPLING = registerSapling("blackthorn_sapling", WKConfiguredFeatures.BLACKTHORN_TREE.value());
    Block ELDER_SAPLING = registerSapling("elder_sapling", WKConfiguredFeatures.ELDER_TREE.value());
    Block HAWTHORN_SAPLING = registerSapling("hawthorn_sapling", WKConfiguredFeatures.HAWTHORN_TREE.value());
    Block JUNIPER_SAPLING = registerSapling("juniper_sapling", WKConfiguredFeatures.JUNIPER_TREE.value());
    Block ROWAN_SAPLING = registerSapling("rowan_sapling", WKConfiguredFeatures.ROWAN_TREE.value());
    Block SUMAC_SAPLING = registerSapling("sumac_sapling", WKConfiguredFeatures.SUMAC_TREE.value());
    Block POTTED_BLACKTHORN_SAPLING = registerPottedSapling("potted_blackthorn_sapling", BLACKTHORN_SAPLING);
    Block POTTED_ELDER_SAPLING = registerPottedSapling("potted_elder_sapling", ELDER_SAPLING);
    Block POTTED_HAWTHORN_SAPLING = registerPottedSapling("potted_hawthorn_sapling", HAWTHORN_SAPLING);
    Block POTTED_JUNIPER_SAPLING = registerPottedSapling("potted_juniper_sapling", JUNIPER_SAPLING);
    Block POTTED_ROWAN_SAPLING = registerPottedSapling("potted_rowan_sapling", ROWAN_SAPLING);
    Block POTTED_SUMAC_SAPLING = registerPottedSapling("potted_sumac_sapling", SUMAC_SAPLING);

    //Crops
    WKTallCropBlock AMARANTH = registerWithType("amaranth", new AmaranthCropBlock(QuiltBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));
    WKTallCropBlock AMARANTH_SWEETBERRY = registerWithType("amaranth_sweetberry", new AmaranthCropBlock(QuiltBlockSettings.copyOf(AMARANTH), AmaranthTypes.SWEETBERRY));
    WKTallCropBlock AMARANTH_TORCH = registerWithType("amaranth_torch", new AmaranthCropBlock(QuiltBlockSettings.copyOf(AMARANTH), AmaranthTypes.TORCH));
    WKTallCropBlock AMARANTH_SUNDEW = registerWithType("amaranth_sundew", new AmaranthCropBlock(QuiltBlockSettings.copyOf(AMARANTH), AmaranthTypes.SUNDEW));
    WKTallCropBlock AMARANTH_CREEPER = registerWithType("amaranth_creeper", new AmaranthCropBlock(QuiltBlockSettings.copyOf(AMARANTH), AmaranthTypes.CREEPER));
    WKTallCropBlock AMARANTH_VIRIDIAN = registerWithType("amaranth_viridian", new AmaranthCropBlock(QuiltBlockSettings.copyOf(AMARANTH), AmaranthTypes.VIRIDIAN));
    WKTallCropBlock AMARANTH_GRISELIN = registerWithType("amaranth_griselin", new AmaranthCropBlock(QuiltBlockSettings.copyOf(AMARANTH), AmaranthTypes.GRISELIN));
    WKTallCropBlock AMARANTH_CERISE = registerWithType("amaranth_cerise", new AmaranthCropBlock(QuiltBlockSettings.copyOf(AMARANTH), AmaranthTypes.CERISE));
    WKTallCropBlock AMARANTH_DARK_PASSION = registerWithType("amaranth_dark_passion", new AmaranthCropBlock(QuiltBlockSettings.copyOf(AMARANTH), AmaranthTypes.DARK_PASSION));
    WKTallCropBlock AMARANTH_FIREBIRD = registerWithType("amaranth_firebird", new AmaranthCropBlock(QuiltBlockSettings.copyOf(AMARANTH), AmaranthTypes.FIREBIRD));

    WKTallCropBlock BELLADONNA = registerWithType("belladonna", new BelladonnaCropBlock(QuiltBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));
    WKTallCropBlock BELLADONNA_GLOW = registerWithType("belladonna_glow", new BelladonnaCropBlock(QuiltBlockSettings.copyOf(BELLADONNA), BelladonnaTypes.GLOW));
    WKTallCropBlock BELLADONNA_NOCTURNAL = registerWithType("belladonna_nocturnal", new BelladonnaCropBlock(QuiltBlockSettings.copyOf(BELLADONNA), BelladonnaTypes.NOCTURNAL));

    WKTallCropBlock CAMELLIA = registerWithType("camellia", new CamelliaCropBlock(QuiltBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));
    WKTallCropBlock CAMELLIA_BUTTERCREAM = registerWithType("camellia_buttercream", new CamelliaCropBlock(QuiltBlockSettings.copyOf(CAMELLIA), CamelliaTypes.BUTTERCREAM));
    WKTallCropBlock CAMELLIA_BISQUE = registerWithType("camellia_bisque", new CamelliaCropBlock(QuiltBlockSettings.copyOf(CAMELLIA), CamelliaTypes.BISQUE));
    WKTallCropBlock CAMELLIA_FLINT = registerWithType("camellia_flint", new CamelliaCropBlock(QuiltBlockSettings.copyOf(CAMELLIA), CamelliaTypes.FLINT));
    WKTallCropBlock CAMELLIA_DEEP_LOVE = registerWithType("camellia_deep_love", new CamelliaCropBlock(QuiltBlockSettings.copyOf(CAMELLIA), CamelliaTypes.DEEP_LOVE));

    WKCropBlock CHAMOMILE = registerWithType("chamomile", new ChamomileCropBlock(QuiltBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));
    WKCropBlock CHAMOMILE_VIRESCENT = registerWithType("chamomile_virescent", new ChamomileCropBlock(QuiltBlockSettings.copyOf(CHAMOMILE), ChamomileTypes.VIRESCENT));
    WKCropBlock CHAMOMILE_STARLETT = registerWithType("chamomile_starlett", new ChamomileCropBlock(QuiltBlockSettings.copyOf(CHAMOMILE), ChamomileTypes.STARLETT));
    WKCropBlock CHAMOMILE_DYEWORKS = registerWithType("chamomile_dyeworks", new ChamomileCropBlock(QuiltBlockSettings.copyOf(CHAMOMILE), ChamomileTypes.DYEWORKS));

    WKTallCropBlock CONEFLOWER = registerWithType("coneflower", new ConeflowerCropBlock(QuiltBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));
    WKTallCropBlock CONEFLOWER_DANCING_LADIES = registerWithType("coneflower_dancing_ladies", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.DANCING_LADIES));
    WKTallCropBlock CONEFLOWER_VIOLET = registerWithType("coneflower_violet", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.VIOLET));
    WKTallCropBlock CONEFLOWER_QUEENS_DESIRE = registerWithType("coneflower_queens_desire", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.QUEENS_DESIRE));
    WKTallCropBlock CONEFLOWER_ROSE_DRESS = registerWithType("coneflower_rose_dress", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.ROSE_DRESS));
    WKTallCropBlock CONEFLOWER_SUITOR = registerWithType("coneflower_suitor", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.SUITOR));
    WKTallCropBlock CONEFLOWER_NETHER = registerWithType("coneflower_nether", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.NETHER));
    WKTallCropBlock CONEFLOWER_LADYS_WISH = registerWithType("coneflower_ladys_wish", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.LADYS_WISH));
    WKTallCropBlock CONEFLOWER_SUNGLOW = registerWithType("coneflower_sunglow", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.SUNGLOW));
    WKTallCropBlock CONEFLOWER_FLAME = registerWithType("coneflower_flame", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.FLAME));
    WKTallCropBlock CONEFLOWER_GILDED = registerWithType("coneflower_gilded", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.GILDED));
    WKTallCropBlock CONEFLOWER_MORNING_MIST = registerWithType("coneflower_morning_mist", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.MORNING_MIST));
    WKTallCropBlock CONEFLOWER_FLEECE = registerWithType("coneflower_fleece", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.FLEECE));
    WKTallCropBlock CONEFLOWER_COMPANY = registerWithType("coneflower_company", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.COMPANY));
    WKTallCropBlock CONEFLOWER_MASQUERADE = registerWithType("coneflower_masquerade", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.MASQUERADE));
    WKTallCropBlock CONEFLOWER_PARTY_BLEND = registerWithType("coneflower_party_blend", new ConeflowerCropBlock(QuiltBlockSettings.copyOf(CONEFLOWER), ConeflowerTypes.PARTY_BLEND));

    WKTallCropBlock FOXGLOVE = registerWithType("foxglove", new FoxgloveCropBlock(QuiltBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));
    WKTallCropBlock FOXGLOVE_SMALT = registerWithType("foxglove_smalt", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.SMALT));
    WKTallCropBlock FOXGLOVE_TRANQUIL_EVENING = registerWithType("foxglove_tranquil_evening", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.TRANQUIL_EVENING));
    WKTallCropBlock FOXGLOVE_PURPUREA = registerWithType("foxglove_purpurea", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.PURPUREA));
    WKTallCropBlock FOXGLOVE_LOVELY_MORNING = registerWithType("foxglove_lovely_morning", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.LOVELY_MORNING));
    WKTallCropBlock FOXGLOVE_IANTHINE = registerWithType("foxglove_ianthine", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.IANTHINE));
    WKTallCropBlock FOXGLOVE_QUEENS_HAT = registerWithType("foxglove_queens_hat", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.QUEENS_HAT));
    WKTallCropBlock FOXGLOVE_BLUSH = registerWithType("foxglove_blush", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.BLUSH));
    WKTallCropBlock FOXGLOVE_ROYAL_BLANKET = registerWithType("foxglove_royal_blanket", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.ROYAL_BLANKET));
    WKTallCropBlock FOXGLOVE_LOVE = registerWithType("foxglove_love", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.LOVE));
    WKTallCropBlock FOXGLOVE_BABYS_DRESS = registerWithType("foxglove_babys_dress", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.BABYS_DRESS));
    WKTallCropBlock FOXGLOVE_STROLL = registerWithType("foxglove_stroll", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.STROLL));
    WKTallCropBlock FOXGLOVE_MAIDENS_PINK = registerWithType("foxglove_maidens_pink", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.MAIDENS));
    WKTallCropBlock FOXGLOVE_MORNING_FIELD = registerWithType("foxglove_morning_field", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.MORNING_FIELD));
    WKTallCropBlock FOXGLOVE_SIGHE_GOWN = registerWithType("foxglove_sighe_gown", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.SIGHE_GOWN));
    WKTallCropBlock FOXGLOVE_CALAMINE = registerWithType("foxglove_calamine", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.CALAMINE));
    WKTallCropBlock FOXGLOVE_NETHERINE = registerWithType("foxglove_netherine", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.NETHERINE));
    WKTallCropBlock FOXGLOVE_SUNGLOW = registerWithType("foxglove_sunglow", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.SUNGLOW));
    WKTallCropBlock FOXGLOVE_SANDSTONE_TEMPLE = registerWithType("foxglove_sandstone_temple", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.SANDSTONE_TEMPLE));
    WKTallCropBlock FOXGLOVE_FIERY_FIELD = registerWithType("foxglove_fiery_field", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.FIERY_FIELD));
    WKTallCropBlock FOXGLOVE_PASSION = registerWithType("foxglove_passion", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.PASSION));
    WKTallCropBlock FOXGLOVE_BASTARD_AMBER = registerWithType("foxglove_bastard_amber", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.BASTARD_AMBER));
    WKTallCropBlock FOXGLOVE_SUNDROP = registerWithType("foxglove_sundrop", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.SUNDROP));
    WKTallCropBlock FOXGLOVE_AURULENT = registerWithType("foxglove_aurulent", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.AURULENT));
    WKTallCropBlock FOXGLOVE_IVORY = registerWithType("foxglove_ivory", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.IVORY));
    WKTallCropBlock FOXGLOVE_NIVEOUS = registerWithType("foxglove_niveous", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.NIVEOUS));
    WKTallCropBlock FOXGLOVE_COWS_CREAM = registerWithType("foxglove_cows_cream", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.COWS_CREAM));
    WKTallCropBlock FOXGLOVE_SIGHE_MIST = registerWithType("foxglove_sighe_mist", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.SIGHE_MIST));
    WKTallCropBlock FOXGLOVE_PURITY = registerWithType("foxglove_purity", new FoxgloveCropBlock(QuiltBlockSettings.copyOf(FOXGLOVE), FoxgloveTypes.PURITY));

    WKCropBlock HELLEBORE = registerWithType("hellebore", new HelleboreCropBlock(QuiltBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));
    WKCropBlock HELLEBORE_MORNING_TEA = registerWithType("hellebore_morning_tea", new HelleboreCropBlock(QuiltBlockSettings.copyOf(HELLEBORE), HelleboreTypes.MORNING_TEA));
    WKCropBlock HELLEBORE_MORNING_CASANOVA = registerWithType("hellebore_casanova", new HelleboreCropBlock(QuiltBlockSettings.copyOf(HELLEBORE), HelleboreTypes.CASANOVA));
    WKCropBlock HELLEBORE_MORNING_BLUSHING = registerWithType("hellebore_blushing", new HelleboreCropBlock(QuiltBlockSettings.copyOf(HELLEBORE), HelleboreTypes.BLUSHING));
    WKCropBlock HELLEBORE_MORNING_CELADON = registerWithType("hellebore_celadon", new HelleboreCropBlock(QuiltBlockSettings.copyOf(HELLEBORE), HelleboreTypes.CELADON));
    WKCropBlock HELLEBORE_MORNING_FURY = registerWithType("hellebore_fury", new HelleboreCropBlock(QuiltBlockSettings.copyOf(HELLEBORE), HelleboreTypes.FURY));
    WKCropBlock HELLEBORE_MORNING_ANGEL = registerWithType("hellebore_angel", new HelleboreCropBlock(QuiltBlockSettings.copyOf(HELLEBORE), HelleboreTypes.ANGEL));
    WKCropBlock HELLEBORE_MORNING_TWILIGHT = registerWithType("hellebore_twilight", new HelleboreCropBlock(QuiltBlockSettings.copyOf(HELLEBORE), HelleboreTypes.TWILIGHT));
    WKCropBlock HELLEBORE_MORNING_GRIMM = registerWithType("hellebore_grimm", new HelleboreCropBlock(QuiltBlockSettings.copyOf(HELLEBORE), HelleboreTypes.GRIMM));
    WKCropBlock HELLEBORE_MORNING_NOCTURNE = registerWithType("hellebore_nocturne", new HelleboreCropBlock(QuiltBlockSettings.copyOf(HELLEBORE), HelleboreTypes.NOCTURNE));

    WKTallCropBlock IRIS = registerWithType("iris", new IrisCropBlock(QuiltBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));
    WKTallCropBlock IRIS_OCEAN = registerWithType("iris_ocean", new IrisCropBlock(QuiltBlockSettings.copyOf(IRIS), IrisTypes.OCEAN));
    WKTallCropBlock IRIS_DEEP_SEA = registerWithType("iris_deep_sea", new IrisCropBlock(QuiltBlockSettings.copyOf(IRIS), IrisTypes.DEEP_SEA));
    WKTallCropBlock IRIS_BLEEDING_HEART = registerWithType("iris_bleeding_heart", new IrisCropBlock(QuiltBlockSettings.copyOf(IRIS), IrisTypes.BLEEDING_HEART));

    WKCropBlock SANGUINARY = registerWithType("sanguinary", new SanguinaryCropBlock(QuiltBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));
    WKCropBlock SANGUINARY_MEADOW = registerWithType("sanguinary_meadow", new SanguinaryCropBlock(QuiltBlockSettings.copyOf(SANGUINARY), SanguinaryTypes.MEADOW));
    WKCropBlock SANGUINARY_BLUSHING = registerWithType("sanguinary_blushing", new SanguinaryCropBlock(QuiltBlockSettings.copyOf(SANGUINARY), SanguinaryTypes.BLUSHING));
    WKCropBlock SANGUINARY_SUNSET = registerWithType("sanguinary_sunset", new SanguinaryCropBlock(QuiltBlockSettings.copyOf(SANGUINARY), SanguinaryTypes.SUNSET));
    WKCropBlock SANGUINARY_MADDER = registerWithType("sanguinary_madder", new SanguinaryCropBlock(QuiltBlockSettings.copyOf(SANGUINARY), SanguinaryTypes.MADDER));
    WKCropBlock SANGUINARY_AUREOLIN = registerWithType("sanguinary_aureolin", new SanguinaryCropBlock(QuiltBlockSettings.copyOf(SANGUINARY), SanguinaryTypes.AUREOLIN));


    Block MINT = register("mint", new MintCropBlock(QuiltBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)),false);
    Block WORMWOOD = register("wormwood", new WormwoodCropBlock(QuiltBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)), false);

    //Plants
    Block AMARANTH_PLANT = register("amaranth_plant", new WildTallPlantCropBlock(getCropSettings(), AMARANTH, 0), false);
    Block BELLADONNA_PLANT = register("belladonna_plant", new WildTallPlantCropBlock(getCropSettings(), BELLADONNA, 0), false);
    Block CHAMOMILE_PLANT = register("chamomile_plant", new WildPlantCropBlock(getCropSettings(), CHAMOMILE), false);
    Block CONEFLOWER_PLANT = register("coneflower_plant", new WildTallPlantCropBlock(getCropSettings(), CONEFLOWER, 3), false);
    Block FOXGLOVE_PLANT = register("foxglove_plant", new WildTallPlantCropBlock(getCropSettings(), FOXGLOVE, 2), false);
    Block HELLEBORE_PLANT = register("hellebore_plant", new WildPlantCropBlock(getCropSettings(), HELLEBORE), false);
    Block IRIS_PLANT = register("iris_plant", new WildTallPlantCropBlock(getCropSettings(), IRIS, 2), false);
    Block SANGUINARY_PLANT = register("sanguinary_plant", new WildPlantCropBlock(getCropSettings(), SANGUINARY), false);

    /**
     * Returns an <a href="Collection.html#unmodview">read-only view</a> of the WitchesKitchen's Blocks
     */
    static List<ObjectDefinition<Block>> getBlocks() {
        return Collections.unmodifiableList(BLOCKS);
    }

    /**
     * Returns an <a href="Collection.html#unmodview">read-only view</a> of the WitchesKitchen's BlockItems
     */
    static List<ObjectDefinition<Item>> getItems() {
        return Collections.unmodifiableList(ITEMS);
    }

    static Map<String, Block> getTypeBlocks() {
        return TYPE_BLOCKS;
    }

    static QuiltBlockSettings leavesSettings() {
        return QuiltBlockSettings.of(Material.LEAVES)
                .strength(0.2F)
                .ticksRandomly()
                .sounds(BlockSoundGroup.GRASS)
                .nonOpaque()
                .allowsSpawning(Blocks::canSpawnOnLeaves)
                .suffocates(Blocks::never)
                .blockVision(Blocks::never);
    }

    static QuiltBlockSettings plankSettings() {
        return QuiltBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(2.0f);
    }

    static QuiltBlockSettings logSettings() {
        return QuiltBlockSettings.copyOf(Blocks.OAK_LOG).strength(2.0f);
    }

    static QuiltBlockSettings gingerSettings() {
        return QuiltBlockSettings.of(Material.CAKE).strength(2.0f);
    }

    static <T extends Block> T registerGingerBread(String id, T Block){
        return register(id, Block, WitchesKitchen.GINGER_TAB, true);
    }

    static Block registerGingerBread(String id){
        return register(id, new Block(gingerSettings()), WitchesKitchen.GINGER_TAB, true);
    }


    static Block registerBarrel(String path) {
        return register(path, new BrewingBarrelBlock(QuiltBlockSettings.of(Material.WOOD).nonOpaque().strength(2.5F)), true);
    }

    static Block registerPottedSapling(String path, Block block) {
        final Block pottedSapling = new FlowerPotBlock(block, QuiltBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque());
        BLOCKS.add(new ObjectDefinition<>(WitchesKitchen.id(path), pottedSapling));
        return pottedSapling;
    }

    static Block registerSapling(String path, ConfiguredFeature<TreeFeatureConfig, ?> feature) {
        final Block sapling = new WKSaplingBlock(new WKSaplingGenerator(() -> Holder.createDirect(feature)),
                QuiltBlockSettings.of(Material.PLANT)
                        .noCollision()
                        .ticksRandomly()
                        .breakInstantly()
                        .sounds(BlockSoundGroup.GRASS));
        return register(path, sapling, true);
    }

    static Block registerLeaf(String path) {
        return register(path, new LeavesBlock(leavesSettings()), true);
    }

    static Block registerWood(String path, MapColor color) {
        final PillarBlock wood = new PillarBlock(QuiltBlockSettings.of(Material.WOOD, color).strength(2.0f).sounds(BlockSoundGroup.WOOD));
        return register(path, wood, true);
    }

    static PillarBlock registerLog(String path) {
        return register(path, new PillarBlock(logSettings()), true);
    }

    static Block registerSlab(String path) {
        return register(path, new SlabBlock(QuiltBlockSettings.of(Material.WOOD)), true);
    }

    static Block registerWoodenStair(String path, Block block) {
        return register(path, new StairsBlock(block.getDefaultState(), QuiltBlockSettings.of(Material.WOOD)), true);
    }

    static <T extends Block> T registerWithType(String name, T block) {
        TYPE_BLOCKS.put(name, block);
        BLOCKS.add(new ObjectDefinition<>(WitchesKitchen.id(name), block));
        return block;
    }

    static <T extends Block> T register(String path, T block, ItemGroup group, boolean createItem) {
        Identifier id = WitchesKitchen.id(path);
        BLOCKS.add(new ObjectDefinition<>(id, block));
        if(createItem){
            ITEMS.add(new ObjectDefinition<>(id, new BlockItem(block, new Item.Settings().group(group))));
        }
        return block;
    }

    static <T extends Block> T register(String path, T block, boolean createItem) {
        return register(path, block, WitchesKitchen.GENERAL_TAB, createItem);
    }

    static void init() {
        BLOCKS.forEach(entry -> Registry.register(Registry.BLOCK, entry.id(), entry.object()));
        ITEMS.forEach(entry -> Registry.register(Registry.ITEM, entry.id(), entry.object()));
    }
}
