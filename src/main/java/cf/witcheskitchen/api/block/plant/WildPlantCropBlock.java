package cf.witcheskitchen.api.block.plant;

import cf.witcheskitchen.api.block.crop.WKCropBlock;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.PlantBlock;

public class WildPlantCropBlock extends PlantBlock {
    public WKCropBlock wkCropBlock;

    public WildPlantCropBlock(Settings settings, WKCropBlock wkCropBlock) {
        super(settings);
        this.wkCropBlock = wkCropBlock;
    }

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return null;
    }
}
