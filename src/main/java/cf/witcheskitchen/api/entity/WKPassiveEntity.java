package cf.witcheskitchen.api.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

//Todo: Revamp texture variants and their code
public abstract class WKPassiveEntity extends PassiveEntity {
    /**
     * This allows the mod to assign a number of textural variants for a mob.
     * Please be sane with it.
     */
    public static final TrackedData<Integer> VARIANT = DataTracker.registerData(WKPassiveEntity.class,
            TrackedDataHandlerRegistry.INTEGER);

    public WKPassiveEntity(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean shouldRender(double distance) {
        return true;
    }

    /**
     * This allows the mod to assign a number of textural variants for a mob.
     * Please be sane with it.
     */
    public abstract int getVariants();
}
