package cf.witcheskitchen.client.render;

import cf.witcheskitchen.client.model.HedgehogEntityModel;
import cf.witcheskitchen.common.entity.tameable.HedgehogEntity;
import mod.azure.azurelib.common.api.client.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class HedgehogRender extends GeoEntityRenderer<HedgehogEntity> {
    public HedgehogRender(EntityRendererFactory.Context ctx) {
        super(ctx, new HedgehogEntityModel());
        this.shadowRadius = 0.1f;
    }
}