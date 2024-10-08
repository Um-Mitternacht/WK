package cf.witcheskitchen.client.render;

import cf.witcheskitchen.client.model.FerretEntityModel;
import cf.witcheskitchen.common.entity.tameable.FerretEntity;
import mod.azure.azurelib.common.api.client.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class FerretRender extends GeoEntityRenderer<FerretEntity> {
    public FerretRender(EntityRendererFactory.Context ctx) {
        super(ctx, new FerretEntityModel());
        this.shadowRadius = 0.33f;
    }
}