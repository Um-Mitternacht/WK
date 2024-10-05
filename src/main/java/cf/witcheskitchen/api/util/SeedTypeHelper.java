package cf.witcheskitchen.api.util;

import cf.witcheskitchen.common.component.WKComponents;
import cf.witcheskitchen.common.component.item.SeedTypeData;
import cf.witcheskitchen.common.registry.WKBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.Map;
import java.util.Optional;

public class SeedTypeHelper {

    /**
     * Gets the block corresponding to the present data
     *
     * @param data the data to test for a block
     * @return optional block depending on if the nbt was valid for a block to be returned
     */
    public static Optional<Block> getBlockFromComponent(SeedTypeData data) {
        if (data != null) {
            String fullName = data.getBlockId();
            for (Map.Entry<String, Block> entry : WKBlocks.getTypeBlocks().entrySet()) {
                String nameMap = entry.getKey();
                Block blockMap = entry.getValue();
                if (nameMap.contains(fullName)) {
                    return Optional.of(blockMap);
                }
            }
        }
        return Optional.empty();
    }

    public static SeedTypeData toComponent(String plantName, String typeName, int variantColor) {
        return new SeedTypeData(plantName, typeName, variantColor);
    }

    public static MutableText getSeedTypeText(ItemStack stack) {
        if (stack.contains(WKComponents.SEED_TYPE)) {
            var seedType = stack.get(WKComponents.SEED_TYPE);
            return Text.translatable(TextUtils.capitalizeString(seedType.type())).setStyle(Style.EMPTY.withColor(seedType.color()));
        }
        return null;
    }
}
