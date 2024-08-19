package cf.witcheskitchen.common.block.crop;

import cf.witcheskitchen.api.block.crop.WKCropBlock;
import cf.witcheskitchen.api.interfaces.CropVariants;
import cf.witcheskitchen.api.util.SeedTypeHelper;
import cf.witcheskitchen.common.block.crop.types.SanguinaryTypes;
import cf.witcheskitchen.common.registry.WKItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.quiltmc.loader.api.minecraft.ClientOnly;

import java.util.Optional;

public class SanguinaryCropBlock extends WKCropBlock implements CropVariants {
    public static final int MAX_AGE = 4;
    private final SanguinaryTypes type;

    public SanguinaryCropBlock(Settings settings) {
        this(settings, SanguinaryTypes.COMMON);
    }

    public SanguinaryCropBlock(Settings settings, SanguinaryTypes rarity) {
        super(settings);
        this.type = rarity;
        this.setDefaultState(this.getDefaultState().with(getAgeProperty(), 0));
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        Optional<SanguinaryTypes> nextType = type.next(type);
        if (nextType.isPresent()) {
            NbtCompound nbtCompound = new NbtCompound();
            SeedTypeHelper.toComponent(nbtCompound, nextType.get().getName(), nextType.get().getType(), nextType.get().getColor());
            getNextSeed(world, pos, nbtCompound);
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public IntProperty getAgeProperty() {
        return IntProperty.of("age", 0, MAX_AGE);
    }

    @ClientOnly
    @Override
    protected ItemStack getSeedsItemStack() {
        NbtCompound nbt = new NbtCompound();
        SeedTypeHelper.toComponent(nbt, type.getName(), type.getType(), type.getColor());
        ItemStack seed = new ItemStack(WKItems.SANGUINARY_SEEDS);
        seed.getOrCreateNbt().copyFrom(nbt);
        return seed;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }
}
