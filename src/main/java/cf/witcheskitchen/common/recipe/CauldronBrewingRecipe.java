package cf.witcheskitchen.common.recipe;

import cf.witcheskitchen.api.event.network.CustomPacketCodecs;
import cf.witcheskitchen.api.util.RecipeUtils;
import cf.witcheskitchen.common.registry.WKRecipeTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import java.util.List;

public class CauldronBrewingRecipe implements Recipe<MultipleStackRecipeInput> {

    private final List<Ingredient> ingredients;
    private final ItemStack result;
    private final int color;

    public CauldronBrewingRecipe(List<Ingredient> ingredients, ItemStack result, int color) {
        this.ingredients = ingredients;
        this.result = result;
        this.color = color;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    @Override
    public boolean matches(MultipleStackRecipeInput inventory, World world) {
        return RecipeUtils.matches(inventory, this.ingredients, 0, inventory.getSize());
    }

    @Override
    public ItemStack craft(MultipleStackRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return this.result.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    public ItemStack getResult() {
        return this.result;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.result;
    }

    public List<Ingredient> getInputs() {
        return this.ingredients;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return WKRecipeTypes.CAULDRON_BREWING_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return WKRecipeTypes.CAULDRON_BREWING_RECIPE_TYPE;
    }

    public int getColor() {
        return color;
    }


    public static class Serializer implements RecipeSerializer<CauldronBrewingRecipe> {
        @Override
        public MapCodec<CauldronBrewingRecipe> codec() {
            return RecordCodecBuilder.mapCodec(instance ->
                    instance.group(
                                    Ingredient.DISALLOW_EMPTY_CODEC.listOf()
                                            .fieldOf("ingredients")
                                            .validate(ingredients -> {
                                                if (ingredients.size() < 2) {
                                                    return DataResult.error(() -> "Cauldron recipes must have at least 2 ingredients");
                                                } else if (ingredients.size() > 7) {
                                                    return DataResult.error(() -> "Too many ingredients for Cauldron recipe");
                                                }

                                                return DataResult.success(ingredients);
                                            })
                                            .forGetter(CauldronBrewingRecipe::getInputs),
                                    ItemStack.CODEC
                                            .fieldOf("result")
                                            .forGetter(CauldronBrewingRecipe::getResult),
                                    Codec.INT
                                            .fieldOf("color")
                                            .forGetter(CauldronBrewingRecipe::getColor)
                            )
                            .apply(instance, CauldronBrewingRecipe::new)
            );
        }

        @Override
        public PacketCodec<RegistryByteBuf, CauldronBrewingRecipe> packetCodec() {
            return PacketCodec.tuple(
                    CustomPacketCodecs.INGREDIENT_LIST, CauldronBrewingRecipe::getInputs,
                    ItemStack.PACKET_CODEC, CauldronBrewingRecipe::getResult,
                    PacketCodecs.VAR_INT, CauldronBrewingRecipe::getColor,
                    CauldronBrewingRecipe::new
            );
        }
    }
}