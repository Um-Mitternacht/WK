package cf.witcheskitchen.common.world.generator;

import cf.witcheskitchen.data.worldgen.WKConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;


public class WKSaplingGenerator extends SaplingGenerator {

    private final ConfiguredFeature<TreeFeatureConfig, ?> tree;

    public WKSaplingGenerator(ConfiguredFeature<TreeFeatureConfig, ?> tree) {
        this.tree = tree;
    }

    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return WKConfiguredFeatures.CONFIGURED_FEATURE_KEYS.get(tree);
    }
}
