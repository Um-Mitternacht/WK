package cf.witcheskitchen.data.worldgen;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.common.registry.WKBlocks;
import cf.witcheskitchen.common.registry.WKTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.block.Block;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;

public interface WKPlacedFeatures {

    RegistryKey<PlacedFeature> BLACKTHORN = RegistryKey.of(RegistryKeys.PLACED_FEATURE, WitchesKitchen.id("blackthorn_tree"));
    RegistryKey<PlacedFeature> ELDER = RegistryKey.of(RegistryKeys.PLACED_FEATURE, WitchesKitchen.id("elder_tree"));
    RegistryKey<PlacedFeature> HAWTHORN = RegistryKey.of(RegistryKeys.PLACED_FEATURE, WitchesKitchen.id("hawthorn_tree"));
    RegistryKey<PlacedFeature> JUNIPER = RegistryKey.of(RegistryKeys.PLACED_FEATURE, WitchesKitchen.id("juniper_tree"));
    RegistryKey<PlacedFeature> ROWAN = RegistryKey.of(RegistryKeys.PLACED_FEATURE, WitchesKitchen.id("rowan_tree"));
    RegistryKey<PlacedFeature> SUMAC = RegistryKey.of(RegistryKeys.PLACED_FEATURE, WitchesKitchen.id("sumac_tree"));

    static void init(Registerable<PlacedFeature> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> configured = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, "blackthorn_tree", configured.getOptional(WKConfiguredFeatures.CONFIGURED_FEATURE_KEYS.get(WKConfiguredFeatures.BLACKTHORN_TREE)).orElseThrow(), 10, WKBlocks.BLACKTHORN_SAPLING);
        register(context, "elder_tree", configured.getOptional(WKConfiguredFeatures.CONFIGURED_FEATURE_KEYS.get(WKConfiguredFeatures.ELDER_TREE)).orElseThrow(), 10, WKBlocks.BLACKTHORN_SAPLING);
        register(context, "hawthorn_tree", configured.getOptional(WKConfiguredFeatures.CONFIGURED_FEATURE_KEYS.get(WKConfiguredFeatures.HAWTHORN_TREE)).orElseThrow(), 10, WKBlocks.BLACKTHORN_SAPLING);
        register(context, "juniper_tree", configured.getOptional(WKConfiguredFeatures.CONFIGURED_FEATURE_KEYS.get(WKConfiguredFeatures.JUNIPER_TREE)).orElseThrow(), 10, WKBlocks.BLACKTHORN_SAPLING);
        register(context, "rowan_tree", configured.getOptional(WKConfiguredFeatures.CONFIGURED_FEATURE_KEYS.get(WKConfiguredFeatures.ROWAN_TREE)).orElseThrow(), 10, WKBlocks.BLACKTHORN_SAPLING);
        register(context, "sumac_tree", configured.getOptional(WKConfiguredFeatures.CONFIGURED_FEATURE_KEYS.get(WKConfiguredFeatures.SUMAC_TREE)).orElseThrow(), 10, WKBlocks.BLACKTHORN_SAPLING);

        BiomeModification biomeMod = BiomeModifications.create(WitchesKitchen.id("worldgen"));

        addTree(biomeMod, WKTags.HAS_BLACKTHORN, BLACKTHORN);
        addTree(biomeMod, WKTags.HAS_ELDER, ELDER);
        addTree(biomeMod, WKTags.HAS_HAWTHORN, HAWTHORN);
        addTree(biomeMod, WKTags.HAS_JUNIPER, JUNIPER);
        addTree(biomeMod, WKTags.HAS_ROWAN, ROWAN);
        addTree(biomeMod, WKTags.HAS_SUMAC, SUMAC);
    }

    static void register(Registerable<PlacedFeature> registry, String id, RegistryEntry<ConfiguredFeature<?, ?>> feature, int rarity, Block sapling) {
        registry.register(RegistryKey.of(RegistryKeys.PLACED_FEATURE, WitchesKitchen.id(id)), new PlacedFeature(feature, VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(rarity), sapling)));
    }

    static void addTree(BiomeModification biomeMod, TagKey<Biome> tag, RegistryKey<PlacedFeature> featureRegistryKey) {
        biomeMod.add(ModificationPhase.ADDITIONS, b -> b.hasTag(tag), context -> context.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, featureRegistryKey));
    }
}
