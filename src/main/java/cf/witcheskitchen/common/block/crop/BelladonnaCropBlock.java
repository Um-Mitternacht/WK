package cf.witcheskitchen.common.block.crop;

import cf.witcheskitchen.api.block.crop.WKTallCropBlock;
import cf.witcheskitchen.api.interfaces.CropVariants;
import cf.witcheskitchen.api.util.SeedTypeHelper;
import cf.witcheskitchen.common.block.crop.types.BelladonnaTypes;
import cf.witcheskitchen.common.component.WKComponents;
import cf.witcheskitchen.common.registry.WKItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

import java.util.Optional;

public class BelladonnaCropBlock extends WKTallCropBlock implements CropVariants {
    public static final int MAX_AGE = 6;
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);
    private final BelladonnaTypes type;

    public BelladonnaCropBlock(Settings settings) {
        this(settings, BelladonnaTypes.COMMON);
    }

    public BelladonnaCropBlock(Settings settings, BelladonnaTypes type) {
        super(settings);
        this.type = type;
        this.setDefaultState(this.getDefaultState().with(AGE, 0).with(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    public VoxelShape[] getLowerShape() {
        return AmaranthCropBlock.LOWER_AGE_TO_SHAPE;
    }

    @Override
    public VoxelShape[] getUpperShape() {
        return AmaranthCropBlock.UPPER_AGE_TO_SHAPE;
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        Optional<BelladonnaTypes> nextType = type.next(type);
        if (nextType.isPresent()) {
            var component = SeedTypeHelper.toComponent(nextType.get().getName(), nextType.get().getType(), nextType.get().getColor());
            getNextSeed(world, pos, component);
        }
        super.onBreak(world, pos, state, player);
        return state;
    }

    @Environment(EnvType.CLIENT)
    @Override
    protected ItemStack getSeedsItemStack() {
        var component = SeedTypeHelper.toComponent(type.getName(), type.getType(), type.getColor());
        ItemStack seed = new ItemStack(WKItems.BELLADONNA_SEEDS);
        seed.set(WKComponents.SEED_TYPE, component);
        return seed;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int doubleBlockAge() {
        return 4;
    }
}
