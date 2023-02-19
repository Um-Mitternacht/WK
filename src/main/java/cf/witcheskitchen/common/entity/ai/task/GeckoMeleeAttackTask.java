package cf.witcheskitchen.common.entity.ai.task;

import cf.witcheskitchen.common.entity.tameable.FerretEntity;
import cf.witcheskitchen.common.util.BrainUtils;
import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.LookTargetUtil;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;

import java.util.function.Predicate;

public class GeckoMeleeAttackTask extends Task<MobEntity> {
    private final Predicate<LivingEntity> predicate;
    private final int interval;
    private final double animationTimeOfAttack;
    private double animationTicker = 0;
    private final double animationTime;

    /**
     * @param interval the attacks cooldown
     * @param animationTime total time of the attack animation
     * @param animationTimeOfAttack when during the attack animation the entity should execute {@link MobEntity#tryAttack(Entity)}}
     */
    public GeckoMeleeAttackTask(Predicate<LivingEntity> predicate, int interval, double animationTime, double animationTimeOfAttack) {
        super(ImmutableMap.of(
                MemoryModuleType.LOOK_TARGET, MemoryModuleState.REGISTERED,
                MemoryModuleType.ATTACK_TARGET, MemoryModuleState.VALUE_PRESENT,
                MemoryModuleType.ATTACK_COOLING_DOWN, MemoryModuleState.VALUE_ABSENT
        ));
        this.interval = interval;
        this.animationTime = animationTime;
        this.animationTimeOfAttack = animationTimeOfAttack;
        this.predicate = predicate;
    }

    protected boolean shouldRun(ServerWorld serverWorld, MobEntity mobEntity) {
        LivingEntity livingEntity = BrainUtils.getAttackTarget(mobEntity);
        return predicate.test(mobEntity) && (LookTargetUtil.isVisibleInMemory(mobEntity, livingEntity) && mobEntity.m_kxhmsdlh(livingEntity));
    }

    protected void run(ServerWorld serverWorld, MobEntity mobEntity, long l) {
        mobEntity.setAttacking(true);
        LivingEntity livingEntity = BrainUtils.getAttackTarget(mobEntity);
        mobEntity.setTarget(livingEntity);

        if(mobEntity instanceof FerretEntity ferretEntity){
            ferretEntity.getDataTracker().set(FerretEntity.IS_ATTACKING, true);
        }
        LookTargetUtil.lookAt(mobEntity, livingEntity);
        mobEntity.getBrain().remember(MemoryModuleType.ATTACK_COOLING_DOWN, true, this.interval);

    }

    @Override
    protected boolean shouldKeepRunning(ServerWorld world, MobEntity entity, long time) {
        return this.animationTicker < animationTime;
    }

    @Override
    protected void keepRunning(ServerWorld world, MobEntity entity, long time) {
        if(entity.getBrain().hasMemoryModule(MemoryModuleType.ATTACK_TARGET)){
            LivingEntity livingEntity = BrainUtils.getAttackTarget(entity);
            this.animationTicker--;
            if(animationTicker == animationTimeOfAttack){
                entity.swingHand(Hand.MAIN_HAND);
                entity.tryAttack(livingEntity);
            }
        }
        super.keepRunning(world, entity, time);
    }

    @Override
    protected void finishRunning(ServerWorld world, MobEntity entity, long time) {
        entity.setAttacking(false);
        if(entity instanceof FerretEntity ferretEntity){
            ferretEntity.getDataTracker().set(FerretEntity.IS_ATTACKING, false);
        }

        this.animationTicker = animationTimeOfAttack;
        super.finishRunning(world, entity, time);
    }
}