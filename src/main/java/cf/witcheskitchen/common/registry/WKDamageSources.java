package cf.witcheskitchen.common.registry;

import cf.witcheskitchen.WitchesKitchen;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface WKDamageSources {

    RegistryKey<DamageType> ON_OVEN = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, WitchesKitchen.id("on_oven"));
    RegistryKey<DamageType> HOLY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, WitchesKitchen.id("holy"));
    RegistryKey<DamageType> HUGGING_BLACKTHORN = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, WitchesKitchen.id("hugging_blackthorn"));
    RegistryKey<DamageType> PUNCHING_BLACKTHORN = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, WitchesKitchen.id("punching_blackthorn"));

    // Used to control in which order static constructors are called
    static void init() {

    }
}
