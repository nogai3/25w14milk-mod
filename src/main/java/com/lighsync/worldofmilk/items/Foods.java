package com.lighsync.worldofmilk.items;

import com.lighsync.worldofmilk.Worldofmilk;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class Foods {
    public static final TagKey<Item> MILK_FOOD = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "milk_food"));

    public static final FoodProperties MILK_BLOCK = new FoodProperties.Builder().nutrition(5).saturationMod(0.4f).build();
    public static final FoodProperties CONCENTRED_MILK = new FoodProperties.Builder().nutrition(3).saturationMod(0.1f).build();
    public static final FoodProperties BREAD_WITH_CONCENTRED_MILK = new FoodProperties.Builder().nutrition(8).saturationMod(0.7f).build();
    public static final FoodProperties EASTER_CAKE = new FoodProperties.Builder().nutrition(8).saturationMod(1.0f).build();
    public static final FoodProperties EASTER_CAKE_BLANK = new FoodProperties.Builder().nutrition(3).saturationMod(-0.1f).effect(new MobEffectInstance(MobEffects.HUNGER, 300, 0), 0.8F).build();
    public static final FoodProperties SPRIKNLES = new FoodProperties.Builder().nutrition(3).saturationMod(0.1f).build();
    public static final FoodProperties PRESSED_BREAD = new FoodProperties.Builder().nutrition(5).saturationMod(0.6f).build();
    public static final FoodProperties WAFFLE = new FoodProperties.Builder().nutrition(3).saturationMod(0.1f).build();
    public static final FoodProperties ICE_CREAM_BALL = new FoodProperties.Builder().nutrition(3).saturationMod(0.2f).build();
    public static final FoodProperties ICE_CREAM = new FoodProperties.Builder().nutrition(6).saturationMod(0.5f).build();
    public static final FoodProperties ICE_CREAM_WITH_WAFFLE_PLOMBIRE = new FoodProperties.Builder().nutrition(7).saturationMod(0.5f).build();

    private static FoodProperties.Builder stew(int nutrinition) {
        return new FoodProperties.Builder().nutrition(nutrinition).saturationMod(0.6f);
    }
}