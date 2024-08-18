package cf.witcheskitchen.common.registry;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.api.registry.ObjectDefinition;
import cf.witcheskitchen.common.statuseffect.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface WKStatusEffects {
    List<ObjectDefinition<StatusEffect>> STATUS_EFFECTS = new ArrayList<>();

    RegistryEntry<StatusEffect> HORROR = create("horror", new HorrorStatusEffect(StatusEffectCategory.HARMFUL, 0x555D50));
    RegistryEntry<StatusEffect> HELLFIRE = create("hellfire", new HellfireStatusEffect(StatusEffectCategory.HARMFUL, 0xA91101));
    RegistryEntry<StatusEffect> FROST_SHIELD = create("frost_shield", new FrostShieldStatusEffect(StatusEffectCategory.BENEFICIAL, 0xAFDBF5));
    RegistryEntry<StatusEffect> FROSTBITE = create("frostbite", new FrostbiteStatusEffect(StatusEffectCategory.HARMFUL, 0xAFDBF5));
    RegistryEntry<StatusEffect> DRUNK = create("drunk", new DrunkStatusEffect(StatusEffectCategory.NEUTRAL, 0x7B3F00));
    RegistryEntry<StatusEffect> FIRE_SHIELD = create("fire_shield", new FireShieldStatusEffect(StatusEffectCategory.BENEFICIAL, 0xAF28500));
    RegistryEntry<StatusEffect> DISROBING = create("disrobing", new DisrobingStatusEffect(StatusEffectCategory.HARMFUL, 0xFDF5E6));
    RegistryEntry<StatusEffect> COOLDOWN = create("cooldown", new CooldownStatusEffect(StatusEffectCategory.NEUTRAL, 0x1F75FE));
    RegistryEntry<StatusEffect> GROWTH = create("growth", new GrowthStatusEffect(StatusEffectCategory.BENEFICIAL, 0x4F7942));
    RegistryEntry<StatusEffect> LOVE = create("love", new LoveStatusEffect(StatusEffectCategory.BENEFICIAL, 0xFFB7C5));
    RegistryEntry<StatusEffect> PARALYSIS = create("paralysis", new ParalysisStatusEffect(StatusEffectCategory.HARMFUL, 0xFADA5E)).addAttributeModifier(ReachEntityAttributes.REACH, "76d02fc2-c274-11ed-afa1-0242ac120002", -9.0D, EntityAttributeModifier.Operation.ADDITION).addAttributeModifier(ReachEntityAttributes.ATTACK_RANGE, "7c45b0e4-c274-11ed-afa1-0242ac120002", -9.D, EntityAttributeModifier.Operation.ADDITION);
    RegistryEntry<StatusEffect> PHASING = create("phasing", new PhasingStatusEffect(StatusEffectCategory.NEUTRAL, 0x7851A9));
    RegistryEntry<StatusEffect> SHADOWS = create("shadows", new PhasingStatusEffect(StatusEffectCategory.BENEFICIAL, 0x86608E));
    RegistryEntry<StatusEffect> CORROSION = create("corrosion", new CorrosionStatusEffect(StatusEffectCategory.HARMFUL, 0x3FFF00)).addAttributeModifier(EntityAttributes.GENERIC_ARMOR, "e8506ffe-e2b4-4f19-8669-becb8e3eb666", -4D, EntityAttributeModifier.Operation.ADDITION).addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, "1845f14c-5411-4380-8be7-85e81317523a", -2D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    RegistryEntry<StatusEffect> REINFORCEMENT = create("reinforcement", new ReinforcementStatusEffect(StatusEffectCategory.BENEFICIAL, 0x4000FF)).addAttributeModifier(EntityAttributes.GENERIC_ARMOR, "52823351-ea91-4db3-958d-1b1ce3804dd6", 4D, EntityAttributeModifier.Operation.ADDITION).addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, "ec255b60-8b01-450b-8538-17a8a28e4aaf", 2D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    RegistryEntry<StatusEffect> FELIFORM = create("feliform", new FeliformStatusEffect(StatusEffectCategory.BENEFICIAL, 0x228B22));
    RegistryEntry<StatusEffect> BUG_SPRAY = create("bug_spray", new BugSprayStatusEffect(StatusEffectCategory.BENEFICIAL, 0x32CD32));
    RegistryEntry<StatusEffect> LONG_REACH = create("long_reach", new LongReachStatusEffect(StatusEffectCategory.BENEFICIAL, 0x964a6e).addAttributeModifier(ReachEntityAttributes.REACH, "03f26044-be99-11ed-afa1-0242ac120002", 5.5D, EntityAttributeModifier.Operation.ADDITION).addAttributeModifier(ReachEntityAttributes.ATTACK_RANGE, "8827ff04-be99-11ed-afa1-0242ac120002", 4.5D, EntityAttributeModifier.Operation.ADDITION));
    RegistryEntry<StatusEffect> LONG_STRIDE = create("long_stride", new LongStrideStatusEffect(StatusEffectCategory.BENEFICIAL, 0xB7410E)).addAttributeModifier(StepHeightEntityAttributeMain.STEP_HEIGHT, "aac36d3e-bf87-4675-8763-53933a74ebfb", 1.25D, EntityAttributeModifier.Operation.ADDITION);

    static List<ObjectDefinition<StatusEffect>> getStatusEffects() {
        return Collections.unmodifiableList(STATUS_EFFECTS);
    }

    static <T extends StatusEffect> RegistryEntry<StatusEffect> create(String name, T effect) {
        Validate.isTrue(effect != null);
        final Identifier id = WitchesKitchen.id(name);
        final ObjectDefinition<StatusEffect> def = new ObjectDefinition<>(id, effect);
        STATUS_EFFECTS.add(def);
        Registry.register(Registries.STATUS_EFFECT, id, effect);
        return Registries.STATUS_EFFECT.getEntry(effect);
    }

    static void init() {
        STATUS_EFFECTS.forEach(entry -> Registry.register(Registries.STATUS_EFFECT, entry.id(), entry.object()));
    }
}
