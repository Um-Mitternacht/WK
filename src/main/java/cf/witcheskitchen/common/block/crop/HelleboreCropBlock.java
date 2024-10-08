package cf.witcheskitchen.common.block.crop;

import cf.witcheskitchen.api.block.crop.WKCropBlock;
import cf.witcheskitchen.api.interfaces.CropVariants;
import cf.witcheskitchen.api.util.SeedTypeHelper;
import cf.witcheskitchen.common.block.crop.types.HelleboreTypes;
import cf.witcheskitchen.common.component.WKComponents;
import cf.witcheskitchen.common.registry.WKItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public class HelleboreCropBlock extends WKCropBlock implements CropVariants {
    public static final int MAX_AGE = 6;
    private final HelleboreTypes type;
    private static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);

    public HelleboreCropBlock(Settings settings) {
        this(settings, HelleboreTypes.COMMON);
    }

    public HelleboreCropBlock(Settings settings, HelleboreTypes rarity) {
        super(settings);
        this.type = rarity;
        this.setDefaultState(this.getDefaultState().with(getAgeProperty(), 0));
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        Optional<HelleboreTypes> nextType = type.next(type);
        if (nextType.isPresent()) {
            var component = SeedTypeHelper.toComponent(nextType.get().getName(), nextType.get().getType(), nextType.get().getColor());
            getNextSeed(world, pos, component);
        }
        return super.onBreak(world, pos, state, player);
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Environment(EnvType.CLIENT)
    @Override
    protected ItemStack getSeedsItemStack() {
        var component = SeedTypeHelper.toComponent(type.getName(), type.getType(), type.getColor());
        ItemStack seed = new ItemStack(WKItems.HELLEBORE_SEEDS);
        seed.set(WKComponents.SEED_TYPE, component);
        return seed;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }
}
