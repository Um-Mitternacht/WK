package cf.witcheskitchen.common.world.generator;


//this will all need to be reworked to better suit our needs

import cf.witcheskitchen.data.worldgen.WKFoliagePlacers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class SumacFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<SumacFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> SumacFoliagePlacer.fillFoliagePlacerFields(instance).apply(instance, SumacFoliagePlacer::new));

    public SumacFoliagePlacer(IntProvider intProvider, IntProvider intProvider2) {
        super(intProvider, intProvider2);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return WKFoliagePlacers.SUMAC_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode node, int foliageHeight, int radius, int offset) {
        boolean bl = node.isGiantTrunk();
        BlockPos blockPos = node.getCenter().up(offset);
        this.generateSquare(world, placer, random, config, blockPos, radius + node.getFoliageRadius(), -1 - foliageHeight, bl);
        this.generateSquare(world, placer, random, config, blockPos, radius - 1, -foliageHeight, bl);
        this.generateSquare(world, placer, random, config, blockPos, radius + node.getFoliageRadius() - 1, 0, bl);
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return 0;
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        if (y == 0) {
            return (dx > 1 || dz > 1) && dx != 0 && dz != 0;
        }
        return dx == radius && dz == radius && radius > 0;
    }
}