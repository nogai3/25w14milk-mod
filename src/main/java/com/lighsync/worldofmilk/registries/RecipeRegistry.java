package com.lighsync.worldofmilk.registries;

import com.lighsync.worldofmilk.Worldofmilk;
// import com.lighsync.worldofmilk.blocks.entity.recipe.FridgeRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeRegistry {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Worldofmilk.MODID);

    // public static final RegistryObject<RecipeType<FridgeRecipe>> FRIDGE = RECIPE_TYPES.register(
    //        "fridge", () -> new RecipeType<>() {
    //            @Override
    //            public String toString() {
    //                return ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "fridge").toString();
    //            }
    //        }
    // );
}
