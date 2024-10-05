package cf.witcheskitchen.common.registry;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.api.registry.ObjectDefinition;
import cf.witcheskitchen.common.entity.hostile.CuSithEntity;
import cf.witcheskitchen.common.entity.hostile.RoggenwolfEntity;
import cf.witcheskitchen.common.entity.neutral.ChurchGrimEntity;
import cf.witcheskitchen.common.entity.tameable.FerretEntity;
import cf.witcheskitchen.common.entity.tameable.HedgehogEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface WKEntityTypes {
    List<ObjectDefinition<EntityType<?>>> ENTITY_TYPES = new ArrayList<>();

    //monsters
    EntityType<CuSithEntity> CUSITH = create("cusith", CuSithEntity.createAttributes(), EntityType.Builder.create(CuSithEntity::new, SpawnGroup.MONSTER)
            .dimensions(1.0f, 1.0f)
            .build());

    //animals
    EntityType<FerretEntity> FERRET = create("ferret", FerretEntity.createAttributes(), EntityType.Builder.create(FerretEntity::new, SpawnGroup.CREATURE)
            .dimensions(1.0f, 1.0f)
            .build());
    EntityType<HedgehogEntity> HEDGEHOG = create("hedgehog", HedgehogEntity.createAttributes(), EntityType.Builder.create(HedgehogEntity::new, SpawnGroup.CREATURE)
            .dimensions(0.25f, 0.25f)
            .build());

    EntityType<ChurchGrimEntity> CHURCH_GRIM = create("church_grim", ChurchGrimEntity.createAttributes(), EntityType.Builder.create(ChurchGrimEntity::new, SpawnGroup.CREATURE)
            .dimensions(1.0f, 1.0f)
            .build());

    EntityType<RoggenwolfEntity> ROGGENWOLF = create("roggenwolf", RoggenwolfEntity.createAttributes(), EntityType.Builder.create(RoggenwolfEntity::new, SpawnGroup.CREATURE)
            .dimensions(1.0f, 1.0f)
            .build());


    static List<ObjectDefinition<EntityType<?>>> getEntityTypes() {
        return Collections.unmodifiableList(ENTITY_TYPES);
    }

    static <T extends LivingEntity> EntityType<T> create(String name, DefaultAttributeContainer.Builder attributes, EntityType<T> type) {
        FabricDefaultAttributeRegistry.register(type, attributes);
        ENTITY_TYPES.add(new ObjectDefinition<>(WitchesKitchen.id(name), type));
        return type;
    }

    static void init() {
        ENTITY_TYPES.forEach(entity -> Registry.register(Registries.ENTITY_TYPE, entity.id(), entity.object()));
    }
}
