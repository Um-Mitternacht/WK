package cf.witcheskitchen.client.render;

import cf.witcheskitchen.client.model.RoggenwolfModel;
import cf.witcheskitchen.common.entity.hostile.RoggenwolfEntity;
import mod.azure.azurelib.common.api.client.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class RoggenwolfRender extends GeoEntityRenderer<RoggenwolfEntity> {
    public RoggenwolfRender(EntityRendererFactory.Context ctx) {
        super(ctx, new RoggenwolfModel());
        this.shadowRadius = 0.33f;
    }
}
