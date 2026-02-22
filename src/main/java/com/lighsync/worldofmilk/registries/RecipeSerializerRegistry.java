package com.lighsync.worldofmilk.registries;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.blocks.entity.recipe.FridgeRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeSerializerRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Worldofmilk.MODID);

    public static final RegistryObject<RecipeSerializer<FridgeRecipe>> FRIDGE = SERIALIZERS.register("fridge", FridgeRecipe.Serializer::new);
}