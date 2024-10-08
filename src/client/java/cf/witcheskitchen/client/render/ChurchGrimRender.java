package cf.witcheskitchen.client.render;

import cf.witcheskitchen.client.model.ChurchGrimModel;
import cf.witcheskitchen.common.entity.neutral.ChurchGrimEntity;
import mod.azure.azurelib.common.api.client.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class ChurchGrimRender extends GeoEntityRenderer<ChurchGrimEntity> {
    public ChurchGrimRender(EntityRendererFactory.Context ctx) {
        super(ctx, new ChurchGrimModel());
        this.shadowRadius = 0.33f;
    }
}
