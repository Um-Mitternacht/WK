package cf.witcheskitchen.api;


import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

/**
 * This object gives you all properties of a tall plant
 * while keeping the stages of a crop block
 */
public abstract class WKTallCropBlock extends WKCropBlock {

    /**
     * A property that specifies whether a double height block is the upper or lower half.
     */
    public static final EnumProperty<DoubleBlockHalf> TALL_PLANT = TallPlantBlock.HALF;

    public WKTallCropBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(this.getAgeRange(), 0).with(TALL_PLANT, DoubleBlockHalf.LOWER));
    }

    /**
     * Age where the plant is going to start
     * using a second texture (upper plant part)
     *
     * @return Integer (age)
     */
    public abstract int topLayerAge();

    /**
     * Called when the {@link net.minecraft.item.BoneMealItem} attempts to make grow this plant.
     * This makes it to put the second layer to the plant if the next stage requires it.
     *
     * @param world World
     * @param pos   BlockPos
     * @param state BlockState
     */
    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        //TODO: handle a bug that destroys the plant if the player attempts to use a bone meal in the upper part and the stage requires an upper part
        // For now i simply drop a seed and return the method as it is without growing the plant
        // Because even if i return this method, the bone meal is going to be consumed
        if (this.isUpperState(world, pos)) {
            dropStacks(state, world, pos, null);
            return;
        }
        int j;
        int i = this.getAge(state) + this.getGrowthAmount(world);
        if (i > (j = this.getMaxAge())) {
            i = j;
        }
        final BlockState nextState = this.withAge(i);
        world.setBlockState(pos, nextState, Block.NOTIFY_LISTENERS);
        if (i >= topLayerAge()) {
            this.putSecondLayer(world, pos, nextState);
        }
    }

    protected void putSecondLayer(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos.up(), state.with(TALL_PLANT, DoubleBlockHalf.UPPER), Block.NOTIFY_ALL);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Minecraft check for random plant tick
        int i = this.getAge(state) + 1;
        final BlockState nextState = this.withAge(i);
        if (world.getBaseLightLevel(pos, 0) >= 9 && this.getAge(state) < this.getMaxAge() && random.nextInt((int) (25.0f / CropBlock.getAvailableMoisture(this, world, pos)) + 1) == 0) {
            world.setBlockState(pos, this.withAge(i), Block.NOTIFY_LISTENERS);
            if (i >= this.topLayerAge()) {
                this.putSecondLayer(world, pos, nextState);
            }
        }
    }

    /**
     * Checks that the block below is the same as this
     * and therefore this is the upper part of the plant
     *
     * @param world World
     * @param pos   BlockPos
     * @return whether this is the upper state
     */
    protected boolean isUpperState(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock() == this;
    }

    // We not want to drop loot twice (upper plant and lower plant)
    // Instead if the upper part exists, we skip the drop.
    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        final BlockState drop = this.getAge(state) >= this.topLayerAge() ? Blocks.AIR.getDefaultState() : state;
        super.afterBreak(world, player, pos, drop, blockEntity, stack);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder); // Appends age property
        builder.add(TALL_PLANT);
    }

    /**
     * Break the upper block, if the state below is empty.
     * From {@link TallPlantBlock}.
     */
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf doubleBlockHalf = state.get(TALL_PLANT);
        if (!(direction.getAxis() != Direction.Axis.Y || doubleBlockHalf == DoubleBlockHalf.LOWER != (direction == Direction.UP) || neighborState.isOf(this) && neighborState.get(TALL_PLANT) != doubleBlockHalf)) {
            return Blocks.AIR.getDefaultState();
        }
        if (doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    /**
     * Checks that the plants has the required states to place it in the world.
     * From {@link TallPlantBlock}
     */
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(TALL_PLANT) == DoubleBlockHalf.UPPER) {
            BlockState downState = world.getBlockState(pos.down());
            return downState.isOf(this) && downState.get(TALL_PLANT) == DoubleBlockHalf.LOWER;
        }
        return super.canPlaceAt(state, world, pos);
    }

}
