package com.lighsync.worldofmilk.recipes;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class FridgeRecipeSerializer implements RecipeSerializer<FridgeRecipe> {
    private final int defaultCookingTime;

    public FridgeRecipeSerializer(int defaultCookingTime) {
        this.defaultCookingTime = defaultCookingTime;
    }

    @Override
    public FridgeRecipe fromJson(ResourceLocation id, JsonObject json) {
        String group = GsonHelper.getAsString(json, "group", "");

        CookingBookCategory category = CookingBookCategory.CODEC
                .byName(GsonHelper.getAsString(json, "category", "misc"), CookingBookCategory.MISC);

        Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "ingredient"));
        ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

        float exp = GsonHelper.getAsFloat(json, "experience", 0.0F);
        int time = GsonHelper.getAsInt(json, "cookingtime", this.defaultCookingTime);

        return new FridgeRecipe(id, group, category, ingredient, result, exp, time);
    }

    @Override
    public FridgeRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        String group = buf.readUtf();
        CookingBookCategory category = buf.readEnum(CookingBookCategory.class);
        Ingredient ingredient = Ingredient.fromNetwork(buf);
        ItemStack result = buf.readItem();
        float exp = buf.readFloat();
        int time = buf.readVarInt();
        return new FridgeRecipe(id, group, category, ingredient, result, exp, time);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, FridgeRecipe recipe) {
        buf.writeUtf(recipe.getGroup());
        buf.writeEnum(recipe.category());
        recipe.getIngredients().get(0).toNetwork(buf);
        buf.writeItem(recipe.getResultItem(RegistryAccess.EMPTY));
        buf.writeFloat(recipe.getExperience());
        buf.writeVarInt(recipe.getCookingTime());
    }
}