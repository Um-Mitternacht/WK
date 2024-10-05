package cf.witcheskitchen.data.worldgen;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.common.world.generator.SumacFoliagePlacer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public interface WKFoliagePlacers {
    FoliagePlacerType<SumacFoliagePlacer> SUMAC_FOLIAGE_PLACER = Registry.register(Registries.FOLIAGE_PLACER_TYPE, WitchesKitchen.id("sumac_foliage_placer"), new FoliagePlacerType<>(SumacFoliagePlacer.CODEC));

    static void init() {

    }
}
