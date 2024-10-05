package cf.witcheskitchen.common.recipe;

import cf.witcheskitchen.api.event.network.CustomPacketCodecs;
import cf.witcheskitchen.api.util.RecipeUtils;
import cf.witcheskitchen.common.registry.WKRecipeTypes;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import java.util.List;

public class BarrelFermentingRecipe implements Recipe<MultipleStackRecipeInput> {

    private final List<Ingredient> inputs;
    private final ItemStack output;

    public BarrelFermentingRecipe(List<Ingredient> inputs, ItemStack output) {
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public boolean matches(MultipleStackRecipeInput inventory, World world) {
        return RecipeUtils.matches(inventory, this.inputs, 0, 5);
    }

    @Override
    public ItemStack craft(MultipleStackRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return this.output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.output;
    }

    public List<Ingredient> getInputs() {
        return inputs;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return WKRecipeTypes.BARREL_FERMENTING_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return WKRecipeTypes.BARREL_FERMENTING_RECIPE_TYPE;
    }

    public static class Serializer implements RecipeSerializer<BarrelFermentingRecipe> {
        @Override
        public MapCodec<BarrelFermentingRecipe> codec() {
            return RecordCodecBuilder.mapCodec(instance ->
                    instance.group(
                                    Ingredient.DISALLOW_EMPTY_CODEC.listOf()
                                            .fieldOf("ingredients")
                                            .validate(inputs -> {
                                                if (inputs.isEmpty()) {
                                                    return DataResult.error(() -> "No ingredients for fermenting recipe");
                                                } else if (inputs.size() > 6) {
                                                    return DataResult.error(() -> "Too many ingredients for fermenting recipe");
                                                }

                                                return DataResult.success(inputs);
                                            })
                                            .forGetter(BarrelFermentingRecipe::getInputs),
                                    ItemStack.CODEC
                                            .fieldOf("result")
                                            .validate(output -> {
                                                if (output.isEmpty()) {
                                                    return DataResult.error(() -> "No output for fermenting recipe");
                                                }

                                                return DataResult.success(output);
                                            })
                                            .forGetter(BarrelFermentingRecipe::getOutput)
                            )
                            .apply(instance, BarrelFermentingRecipe::new)
            );
        }

        @Override
        public PacketCodec<RegistryByteBuf, BarrelFermentingRecipe> packetCodec() {
            return PacketCodec.tuple(
                    CustomPacketCodecs.INGREDIENT_LIST, BarrelFermentingRecipe::getInputs,
                    ItemStack.PACKET_CODEC, BarrelFermentingRecipe::getOutput,

                    BarrelFermentingRecipe::new
            );
        }
    }
}