package cf.witcheskitchen.common.registry;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.api.registry.ObjectDefinition;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface WKParticleTypes {

    List<ObjectDefinition<ParticleType<?>>> PARTICLE_TYPES = new ArrayList<>();

    ParticleType<SimpleParticleType> BUBBLE = create("bubble", FabricParticleTypes.simple());
    ParticleType<SimpleParticleType> SPLASH = create("splash", FabricParticleTypes.simple());
    ParticleType<SimpleParticleType> MAGIC_SPARKLE = create("magic_sparkle", FabricParticleTypes.simple());

    static <T extends ParticleEffect> ParticleType<T> create(final String name, final ParticleType<T> type) {
        final Identifier id = WitchesKitchen.id(name);
        final ObjectDefinition<ParticleType<?>> definition = new ObjectDefinition<>(id, type);
        PARTICLE_TYPES.add(definition);
        return type;
    }

    static List<ObjectDefinition<ParticleType<?>>> getParticleTypes() {
        return Collections.unmodifiableList(PARTICLE_TYPES);
    }

    static void init() {
        PARTICLE_TYPES.forEach(entry -> Registry.register(Registries.PARTICLE_TYPE, entry.id(), entry.object()));
    }
}
