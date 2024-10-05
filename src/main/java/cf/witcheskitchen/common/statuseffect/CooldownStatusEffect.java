package cf.witcheskitchen.common.statuseffect;

import cf.witcheskitchen.common.registry.WKStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class CooldownStatusEffect extends StatusEffect {
    public CooldownStatusEffect(StatusEffectCategory type, int color) {
        super(type, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.hasStatusEffect(WKStatusEffects.DISROBING)) {
            entity.removeStatusEffect(WKStatusEffects.DISROBING);
        }
        if (entity.hasStatusEffect(WKStatusEffects.GROWTH)) {
            entity.removeStatusEffect(WKStatusEffects.GROWTH);
        }
        return true;
    }
}
