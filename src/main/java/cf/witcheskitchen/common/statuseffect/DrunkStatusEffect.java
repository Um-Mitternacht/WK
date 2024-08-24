package cf.witcheskitchen.common.statuseffect;

import cf.witcheskitchen.common.registry.WKStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

//Todo: Learn shaders
public class DrunkStatusEffect extends StatusEffect {

    public DrunkStatusEffect(StatusEffectCategory type, int color) {
        super(type, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }

    //Todo: Increment to a max of level 4 if one drinks too much
    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (amplifier == 1) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 2000, 1));
        }
        if (amplifier == 2) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 4000, 2));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 4000, 2));
        }
        if (amplifier >= 3) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 6000, 3));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 6000, 3));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 6000, 3));
        }
    }

    //NOTICE: Values are not final! These will change once the alcohol system is fully functional
    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (amplifier >= 3) {
            entity.addStatusEffect(new StatusEffectInstance(WKStatusEffects.DRUNK, 3000, 2));
        }
        if (amplifier == 2) {
            entity.addStatusEffect(new StatusEffectInstance(WKStatusEffects.DRUNK, 2000, 1));
        }
        if (amplifier == 1) {
            entity.addStatusEffect(new StatusEffectInstance(WKStatusEffects.DRUNK, 1000, 0));
        }
    }
}
