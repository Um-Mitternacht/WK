package cf.witcheskitchen.common.statuseffect;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

import java.util.Random;

public class DisrobingStatusEffect extends StatusEffect {

    public DisrobingStatusEffect(StatusEffectType type, int color) {
        super(type, color);
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }

    public boolean hasBeenDisrobed() {
        return false;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        Random rand = entity.getRandom();
        int i = rand.nextInt(100);
        if (i < 75) {
            if (!hasBeenDisrobed())
            switch (rand.nextInt(4)) {
                case 0 -> {
                    entity.dropItem(entity.getEquippedStack(EquipmentSlot.HEAD).getItem(), 1);
                    entity.getEquippedStack(EquipmentSlot.HEAD).decrement(1);
                    hasBeenDisrobed();
                }
            }
        }

    }
}