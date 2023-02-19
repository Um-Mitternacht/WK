package cf.witcheskitchen.common.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.mob.MobEntity;

public class BrainUtils {
    public static LivingEntity getAttackTarget(MobEntity entity) {
        return entity.getBrain().getOptionalMemory(MemoryModuleType.ATTACK_TARGET).get();
    }

    public static boolean isTarget(MobEntity mobEntity, LivingEntity entity) {
        return mobEntity.getBrain().getOptionalMemory(MemoryModuleType.ATTACK_TARGET).filter(targetedEntity -> targetedEntity == entity).isPresent();
    }

    public static void setTargetInvalid(MobEntity mobEntity, LivingEntity target) {
        mobEntity.getBrain().forget(MemoryModuleType.ATTACK_TARGET);
        mobEntity.getBrain().forget(MemoryModuleType.ANGRY_AT);
    }
}
