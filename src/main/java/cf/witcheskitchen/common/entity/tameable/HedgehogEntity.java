package cf.witcheskitchen.common.entity.tameable;

import cf.witcheskitchen.api.entity.WKTameableEntity;
import cf.witcheskitchen.common.entity.ai.HedgehogBrain;
import cf.witcheskitchen.common.registry.WKEntityTypes;
import mod.azure.azurelib.common.api.common.animatable.GeoEntity;
import mod.azure.azurelib.common.internal.common.constant.DefaultAnimations;
import mod.azure.azurelib.common.internal.common.util.AzureLibUtil;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.AnimationState;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.core.object.PlayState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.SplittableRandom;
import java.util.UUID;

public class HedgehogEntity extends WKTameableEntity implements GeoEntity, SmartBrainOwner<HedgehogEntity> {
    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

    public HedgehogEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.setTamed(false, true);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    @Override
    protected Brain.Profile<?> createBrainProfile() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    protected void mobTick() {
        tickBrain(this);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        int var = new SplittableRandom().nextInt(1, 7);
        this.setVariant(var);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public int getVariants() {
        return 6;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        HedgehogEntity hedgehogEntity = WKEntityTypes.HEDGEHOG.create(world);
        UUID uUID = this.getOwnerUuid();
        if (uUID != null && hedgehogEntity != null) {
            hedgehogEntity.setOwnerUuid(uUID);
            hedgehogEntity.setTamed(true, true);
        }
        return hedgehogEntity;
    }


    @Override
    public List<ExtendedSensor<HedgehogEntity>> getSensors() {
        return HedgehogBrain.getSensors();
    }

    @Override
    public BrainActivityGroup<HedgehogEntity> getCoreTasks() {
        return HedgehogBrain.getCoreTasks();
    }

    @Override
    public BrainActivityGroup<HedgehogEntity> getIdleTasks() {
        return HedgehogBrain.getIdleTasks();
    }

    @Override
    public BrainActivityGroup<HedgehogEntity> getFightTasks() {
        return HedgehogBrain.getFightTasks(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controller) {
        controller.add(DefaultAnimations.genericIdleController(this)).add(new AnimationController<HedgehogEntity>(this, "move", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<HedgehogEntity> state) {
        if (this.isSitting()) {
            state.setAnimation(RawAnimation.begin().thenLoop("loaf"));
            return PlayState.CONTINUE;
        } else if (state.isMoving()) {
            state.setAnimation(RawAnimation.begin().thenLoop("walk"));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
