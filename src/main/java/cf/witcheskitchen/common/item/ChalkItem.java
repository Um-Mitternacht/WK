package cf.witcheskitchen.common.item;

import cf.witcheskitchen.common.block.GlyphBlock;
import cf.witcheskitchen.common.registry.WKBlocks;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.minecraft.ClientOnly;

import java.util.List;

public class ChalkItem extends Item {
    private final GlyphBlock glyphType;

    public ChalkItem(Settings settings) {
        super(settings);
        this.glyphType = (GlyphBlock) WKBlocks.GLYPH;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        ItemPlacementContext placementContext = new ItemPlacementContext(context);
        BlockState state = glyphType.getPlacementState(placementContext);
        if (!world.getBlockState(pos).canReplace(placementContext)) {
            pos = pos.offset(context.getSide());
        }
        if (!world.getBlockState(pos).canReplace(placementContext)) {
            return ActionResult.PASS;
        }
        if (state != null && state.canPlaceAt(world, pos)) {
            if(!world.isClient()){
                PlayerEntity player = context.getPlayer();
                ItemStack stack = context.getStack();
                world.playSound(null, pos, state.getSoundGroup().getPlaceSound(), SoundCategory.BLOCKS, 1, MathHelper.nextFloat(world.random, 0.8f, 1.2f));
                world.setBlockState(pos, state);
                if (player instanceof ServerPlayerEntity serverPlayer) {
                    Criteria.PLACED_BLOCK.trigger(serverPlayer, pos, stack);
                    stack.damage(1, player, stackUser -> stackUser.sendToolBreakStatus(context.getHand()));
                }
            }
            return ActionResult.success(true);
        }
        return super.useOnBlock(context);
    }

    @ClientOnly
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(glyphType != null){
            String name = Registry.BLOCK.getId(glyphType).getPath();
            String formatName = name.substring(0, 1).toUpperCase() + name.substring(1);
            tooltip.add(Text.translatable(formatName).setStyle(Style.EMPTY.withColor(0xffffff)));
        }
    }
}
