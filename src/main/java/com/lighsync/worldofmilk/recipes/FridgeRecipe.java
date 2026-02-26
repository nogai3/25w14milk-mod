package com.lighsync.worldofmilk.recipes;

import com.lighsync.worldofmilk.registries.RecipeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class FridgeRecipe extends AbstractCookingRecipe {
    public FridgeRecipe(ResourceLocation id, String group, CookingBookCategory category, Ingredient ingredient, ItemStack result, float exp, int cookingTime) {
        super(RecipeRegistry.FRIDGE_TYPE.get(), id, group, category, ingredient, result, exp, cookingTime);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.FRIDGE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.FRIDGE_TYPE.get();
    }
}
