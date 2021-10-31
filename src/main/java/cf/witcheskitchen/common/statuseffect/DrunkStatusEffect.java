package cf.witcheskitchen.common.statuseffect;

import cf.witcheskitchen.common.registry.WKStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Random;

//Todo: Learn shaders
public class DrunkStatusEffect extends StatusEffect {
    public int drunkTimer = 1500;

    public DrunkStatusEffect(StatusEffectType type, int color) {
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

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        Random rand = entity.getRandom();
        int i = rand.nextInt(500);
        int j = 1;
        int sum = j + amplifier;

        entity.hasStatusEffect(WKStatusEffects.DRUNK);
        {
            if (i < 50 && drunkTimer < 1499) {
                drunkTimer = 1500;
                applyUpdateEffect(entity, sum);
            }
        }
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (drunkTimer > 0) drunkTimer--;
        if (amplifier >= 3) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 6000, 3));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 6000, 3));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 6000, 3));
        }
    }
}