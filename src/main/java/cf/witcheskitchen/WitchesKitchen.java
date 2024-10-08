package cf.witcheskitchen;

import cf.witcheskitchen.common.component.WKComponents;
import cf.witcheskitchen.common.event.WKItemGroupEvents;
import cf.witcheskitchen.common.registry.*;
import cf.witcheskitchen.data.DimColorReloadListener;
import cf.witcheskitchen.data.worldgen.WKConfiguredFeatures;
import cf.witcheskitchen.data.worldgen.WKFoliagePlacers;
import cf.witcheskitchen.data.worldgen.WKPlacedFeatures;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WitchesKitchen implements ModInitializer {

    public static final String MODID = "witcheskitchen";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static Identifier id(String name) {
        return Identifier.of(MODID, name);
    }

    @Override
    public void onInitialize() {
        WitchesKitchenConfig.init(MODID, WitchesKitchenConfig.class);
        WKPacketTypes.init();
        WKBlocks.init();
        WKComponents.init();
        WKItems.init();
        WKPotions.init();
        WKFoodComponents.init();
        WKTags.init();
        WKBlockEntityTypes.init();
        WKRitualRegistry.init();
        WKRecipeTypes.init();
        WKScreenHandlerTypes.init();
        WKParticleTypes.init();
        WKFoliagePlacers.init();
        WKPlacedFeatures.init();
        WKEventsRegistry.init(EnvType.SERVER);
        WKItemGroupEvents.init();
        WKStatusEffects.init();
        WKSoundEvents.init();
        WKEntityTypes.init();
        WKMemoryModuleTypes.init();
        WKSensorTypes.init();
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new DimColorResourceReloader());

        DynamicRegistrySetupCallback.EVENT.register(view -> {
            DynamicRegistryManager registries = view.asDynamicRegistryManager();
            Registry<ConfiguredFeature<?, ?>> configured = registries.get(RegistryKeys.CONFIGURED_FEATURE);
            WKConfiguredFeatures.init(configured);
            WKPlacedFeatures.init(configured, registries);
        });

    }

    public static class DimColorResourceReloader extends DimColorReloadListener implements IdentifiableResourceReloadListener {
        @Override
        public Identifier getFabricId() {
            return Identifier.of(MODID, "dimension_color");
        }
    }
}
