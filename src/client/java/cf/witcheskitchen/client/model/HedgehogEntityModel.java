package cf.witcheskitchen.client.model;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.common.entity.tameable.HedgehogEntity;
import mod.azure.azurelib.common.api.client.model.DefaultedEntityGeoModel;
import net.minecraft.util.Identifier;

public class HedgehogEntityModel extends DefaultedEntityGeoModel<HedgehogEntity> {
    public HedgehogEntityModel() {
        super(WitchesKitchen.id("hedgehog"), true);
    }

    @Override
    public Identifier getTextureResource(HedgehogEntity object) {
        return WitchesKitchen.id("textures/entity/hedgehog_" + object.getVariant() + ".png");
    }


}
