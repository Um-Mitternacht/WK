package cf.witcheskitchen.common.block.crop;

import cf.witcheskitchen.api.block.crop.WKTallCropBlock;
import cf.witcheskitchen.api.interfaces.CropVariants;
import cf.witcheskitchen.api.util.SeedTypeHelper;
import cf.witcheskitchen.common.block.crop.types.AmaranthTypes;
import cf.witcheskitchen.common.registry.WKItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.quiltmc.loader.api.minecraft.ClientOnly;

import java.util.Optional;

public class AmaranthCropBlock extends WKTallCropBlock implements CropVariants {
    public static final VoxelShape[] LOWER_AGE_TO_SHAPE;
    public static final VoxelShape[] UPPER_AGE_TO_SHAPE;
    public static final int MAX_AGE = 6;
    private final AmaranthTypes type;

    public AmaranthCropBlock(Settings settings) {
        this(settings, AmaranthTypes.COMMON);
    }

    public AmaranthCropBlock(Settings settings, AmaranthTypes type) {
        super(settings);
        this.type = type;
        this.setDefaultState(this.getDefaultState().with(getAgeProperty(), 0).with(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    public IntProperty getAgeProperty() {
        return IntProperty.of("age", 0, MAX_AGE);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        Optional<AmaranthTypes> nextType = type.next(type);
        if (nextType.isPresent()) {
            NbtCompound nbtCompound = new NbtCompound();
            SeedTypeHelper.toNbt(nbtCompound, nextType.get().getName(), nextType.get().getType(), nextType.get().getColor());
            getNextSeed(world, pos, nbtCompound);
        }
        super.onBreak(world, pos, state, player);
    }

    @ClientOnly
    @Override
    protected ItemStack getSeedsItemStack() {
        NbtCompound nbt = new NbtCompound();
        SeedTypeHelper.toNbt(nbt, type.getName(), type.getType(), type.getColor());
        ItemStack seed = new ItemStack(WKItems.AMARANTH_SEEDS);
        seed.getOrCreateNbt().copyFrom(nbt);
        return seed;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public int doubleBlockAge() {
        return 5;
    }

    @Override
    public VoxelShape[] getLowerShape() {
        return LOWER_AGE_TO_SHAPE;
    }

    @Override
    public VoxelShape[] getUpperShape() {
        return UPPER_AGE_TO_SHAPE;
    }

    static {
        LOWER_AGE_TO_SHAPE = new VoxelShape[]{
                Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        };
        UPPER_AGE_TO_SHAPE = new VoxelShape[]{
                Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0)
        };
    }
}
