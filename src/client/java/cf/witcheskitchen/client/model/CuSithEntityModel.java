package cf.witcheskitchen.client.model;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.common.entity.hostile.CuSithEntity;
import mod.azure.azurelib.common.api.client.model.DefaultedEntityGeoModel;
import net.minecraft.util.Identifier;

public class CuSithEntityModel extends DefaultedEntityGeoModel<CuSithEntity> {
    public CuSithEntityModel() {
        super(WitchesKitchen.id("cusith"), true);
    }

    @Override
    public Identifier getTextureResource(CuSithEntity object) {
        return WitchesKitchen.id("textures/entity/cusith_" + object.getVariant() + ".png");
    }


}
