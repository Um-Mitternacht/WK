package cf.witcheskitchen.client.render;

import mod.azure.azurelib.common.api.client.renderer.layer.GeoRenderLayer;
import mod.azure.azurelib.common.internal.client.renderer.GeoRenderer;
import mod.azure.azurelib.common.internal.common.cache.object.BakedGeoModel;
import mod.azure.azurelib.common.internal.common.cache.texture.AutoGlowingTexture;
import mod.azure.azurelib.core.animatable.GeoAnimatable;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

public class CuSithEyeLayer<T extends GeoAnimatable> extends GeoRenderLayer<T> {
    private static Identifier[] TEXTURES;

    public CuSithEyeLayer(GeoRenderer<T> renderer) {
        super(renderer);
    }

    protected RenderLayer getRenderLayer(T animatable) {
        return AutoGlowingTexture.getRenderType(getTextureResource(animatable));
    }

    @Override
    public void render(MatrixStack poseStack, T animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        //TODO do color instead of multiple textures
        RenderLayer layer = getRenderLayer(animatable);
        getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, layer,
                bufferSource.getBuffer(layer), partialTick, 15728640, OverlayTexture.DEFAULT_UV,
            ColorHelper.Argb.getArgb(255, 255, 255, 255));
    }
}