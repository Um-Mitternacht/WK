package cf.witcheskitchen.api;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.quiltmc.loader.api.minecraft.ClientOnly;

//Todo: Revamp texture variants and their code
public abstract class WKTameableEntity extends TameableEntity {
    /**
     * This allows the mod to assign a number of textural variants for a mob.
     * Please be sane with it.
     */
    public static final TrackedData<Integer> VARIANT = DataTracker.registerData(WKTameableEntity.class,
            TrackedDataHandlerRegistry.INTEGER);

    public WKTameableEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.getDataTracker().startTracking(VARIANT, 0);
    }

    @Override
    @ClientOnly
    public boolean shouldRender(double distance) {
        return true;
    }

    /**
     * This allows the mod to assign a number of textural variants for a mob.
     * Please be sane with it.
     */
    public abstract int getVariants();
}
