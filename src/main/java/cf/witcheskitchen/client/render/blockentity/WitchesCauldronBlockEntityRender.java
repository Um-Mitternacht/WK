package cf.witcheskitchen.client.render.blockentity;

import cf.witcheskitchen.api.fluid.FluidStack;
import cf.witcheskitchen.client.RenderHelper;
import cf.witcheskitchen.common.blocks.entity.WitchesCauldronBlockEntity;
import cf.witcheskitchen.common.registry.WKParticleTypes;
import cf.witcheskitchen.common.util.TimeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class WitchesCauldronBlockEntityRender implements BlockEntityRenderer<WitchesCauldronBlockEntity> {

    @Override
    public void render(WitchesCauldronBlockEntity cauldron, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        final FluidStack stack = cauldron.getStackForTank(0);
        if (cauldron.getWorld() != null && !stack.isEmpty()) {
            final World world = cauldron.getWorld();
            matrices.push();
            final float depth = (float) (((cauldron.getPercentFilled() - 1) * (0.4D)) + (0.6D));
            matrices.translate(0, depth, 0);
            final VertexConsumer buffer = vertexConsumers.getBuffer(RenderLayer.getTranslucent());
            final int color = cauldron.getColor();
            final float i = 0.12F;
            boolean water = stack.getFluid() != Fluids.LAVA;
            if (water) {
                RenderHelper.renderWaterSprite(matrices, buffer, color, i, light, overlay);
                float range = 0.3F;
                double offsetX = 0.5D + MathHelper.nextDouble(world.getRandom(), -range, range);
                double offsetZ = 0.5D + MathHelper.nextDouble(world.getRandom(), -range, range);
                final int heatTicks = TimeHelper.toSeconds(cauldron.getTicksHeated());
                if (heatTicks > 0) {
                    final double r = ((color >> 16) & 0xff) / 255F;
                    final double g = ((color >> 8) & 0xff) / 255F;
                    final double b = (color & 0xff) / 255F;
                    switch (heatTicks) {
                        case 1, 2, 3, 4 -> {
                            if (world.getTime() % 5 == 0) { // Wait 5 ticks
                                world.addParticle((ParticleEffect) WKParticleTypes.BUBBLE, cauldron.getPos().getX() + offsetX, cauldron.getPos().getY() + depth, cauldron.getPos().getZ() + offsetZ, r, g, b);
                            }
                        }
                        case 5 -> world.addParticle((ParticleEffect) WKParticleTypes.BUBBLE, cauldron.getPos().getX() + offsetX, cauldron.getPos().getY() + depth, cauldron.getPos().getZ() + offsetZ, r, g, b);
                    }
                }
            } else {
                RenderHelper.renderLavaSprite(matrices, buffer, i, light, overlay);
            }
            matrices.pop();
        }
    }
}
