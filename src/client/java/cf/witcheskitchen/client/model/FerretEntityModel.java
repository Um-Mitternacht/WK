package cf.witcheskitchen.client.model;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.common.entity.tameable.FerretEntity;
import mod.azure.azurelib.common.api.client.model.DefaultedEntityGeoModel;
import net.minecraft.util.Identifier;

public class FerretEntityModel extends DefaultedEntityGeoModel<FerretEntity> {
    public FerretEntityModel() {
        super(WitchesKitchen.id("ferret"), true);
    }

    @Override
    public Identifier getTextureResource(FerretEntity object) {
        return WitchesKitchen.id("textures/entity/ferret_" + object.getVariant() + ".png");
    }
}
