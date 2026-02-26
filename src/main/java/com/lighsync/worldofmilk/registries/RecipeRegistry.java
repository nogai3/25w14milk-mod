package com.lighsync.worldofmilk.registries;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.recipes.FridgeRecipe;
import com.lighsync.worldofmilk.recipes.FridgeRecipeSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Worldofmilk.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Worldofmilk.MODID);

    public static final RegistryObject<RecipeType<FridgeRecipe>> FRIDGE_TYPE =
            TYPES.register("fridge", () ->
                new RecipeType<>() {
                    @Override
                    public String toString() {
                        return ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "fridge").toString();
                    }
                }
    );

    public static final RegistryObject<RecipeSerializer<FridgeRecipe>> FRIDGE_SERIALIZER =
            SERIALIZERS.register("fridge", () -> new FridgeRecipeSerializer(200));
}
