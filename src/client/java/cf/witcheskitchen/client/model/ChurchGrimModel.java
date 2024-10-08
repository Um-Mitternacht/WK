package cf.witcheskitchen.client.model;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.common.entity.neutral.ChurchGrimEntity;
import mod.azure.azurelib.common.api.client.model.DefaultedEntityGeoModel;
import net.minecraft.util.Identifier;

public class ChurchGrimModel extends DefaultedEntityGeoModel<ChurchGrimEntity> {
    public ChurchGrimModel() {
        super(WitchesKitchen.id("churchgrim"), true);
    }

    @Override
    public Identifier getTextureResource(ChurchGrimEntity object) {
        return WitchesKitchen.id("textures/entity/grim_" + object.getVariant() + ".png");
    }
}
