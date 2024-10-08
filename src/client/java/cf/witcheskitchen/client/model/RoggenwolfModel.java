package cf.witcheskitchen.client.model;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.common.entity.hostile.RoggenwolfEntity;
import mod.azure.azurelib.common.api.client.model.DefaultedEntityGeoModel;
import net.minecraft.util.Identifier;

public class RoggenwolfModel extends DefaultedEntityGeoModel<RoggenwolfEntity> {
    public RoggenwolfModel() {
        super(WitchesKitchen.id("roggenwolf"), true);
    }

    @Override
    public Identifier getTextureResource(RoggenwolfEntity object) {
        return WitchesKitchen.id("textures/entity/roggenwolf_" + object.getVariant() + ".png");
    }
}
