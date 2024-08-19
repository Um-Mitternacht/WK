package cf.witcheskitchen.common.item;

import cf.witcheskitchen.api.util.TextUtils;
import cf.witcheskitchen.data.DimColorReloadListener;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class WaystoneItem extends Item {
    private static final int MAX_USE_TIME = 40;

    public WaystoneItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack stack = context.getStack();
        if ((context.getWorld() instanceof ServerWorld serverWorld)) {
            bindBlockPosition(serverWorld, context.getBlockPos().offset(context.getSide()), stack);
        }
        return super.useOnBlock(context);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getWorld().isClient()) {
            bindEntityPosition(entity, stack);
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    public void teleportToWaystoneLocation(World world, ItemStack waystone, Entity entity) {
        if (world instanceof ServerWorld serverWorld) {
            BlockPos blockPos = getPosFromWaystone(waystone);
            ServerWorld toWorld = getDimFromWaystone(serverWorld, waystone);
            if (blockPos != null) {
                Vec3d center = blockPos.toBottomCenterPos();
                // TODO: is this correct?
                entity.teleport(toWorld != null ? toWorld : serverWorld, center.x, center.y, center.z, Set.of(PositionFlag.X, PositionFlag.Y, PositionFlag.Z), entity.getYaw(), entity.getPitch());
            }
        }
    }

    public void bindBlockPosition(World world, BlockPos pos, ItemStack stack) {
        stack.getOrCreateNbt().putLong("BlockPos", pos.asLong());
        stack.getOrCreateNbt().putString("Dimension", world.getRegistryKey().getValue().toString());
    }

    @Deprecated(forRemoval = true)
    public void bindEntityPosition(LivingEntity entity, ItemStack stack) {
        stack.getOrCreateNbt().putUuid("Entity", entity.getUuid());
    }

    @Nullable
    public BlockPos getPosFromWaystone(ItemStack stack) {
        if (stack.hasNbt()) {
            if (stack.getOrCreateNbt().contains("BlockPos")) {
                return BlockPos.fromLong(stack.getNbt().getLong("BlockPos"));
            }
        }
        return null;
    }

    @Nullable
    public ServerWorld getDimFromWaystone(ServerWorld serverWorld, ItemStack stack) {
        MinecraftServer minecraftServer = serverWorld.getServer();
        if (stack.getOrCreateNbt().contains("Dimension")) {
            String dimension = stack.getOrCreateNbt().getString("Dimension");
            RegistryKey<World> registryKey = RegistryKey.of(RegistryKeys.WORLD, new Identifier(dimension));
            return minecraftServer.getWorld(registryKey);
        }
        return null;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.getNbt() != null && stack.getOrCreateNbt().contains("BlockPos") && stack.getOrCreateNbt().contains("Dimension")) {
            String dimension = stack.getOrCreateNbt().getString("Dimension");
            int color = 0xffffff;
            for (Pair<String, Integer> dimData : DimColorReloadListener.COLOR_DATA) {
                if (dimData.getFirst().equals(dimension)) {
                    color = dimData.getSecond();
                }
            }

            String formattedDim = TextUtils.capitalizeString(dimension.substring(dimension.indexOf(":") + 1));
            BlockPos pos = BlockPos.fromLong(stack.getNbt().getLong("BlockPos"));

            tooltip.add(TextUtils.formattedFromTwoStrings("Dimension", formattedDim, 0xFFAA00, color));
            tooltip.add(TextUtils.formattedFromTwoStrings("Position", pos.getX() + " " + pos.getY() + " " + pos.getZ(), 0xFFAA00, 0x55FFFF));
        }
    }
}
