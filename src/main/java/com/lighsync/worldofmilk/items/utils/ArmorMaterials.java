package com.lighsync.worldofmilk.items.utils;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.registries.ItemRegistry;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum ArmorMaterials implements StringRepresentable, ArmorMaterial {
    PRESSED_BREAD("pressed_bread", 47, Util.make(new EnumMap<>(ArmorItem.Type.class), (element) -> {
        element.put(ArmorItem.Type.BOOTS, 4);
        element.put(ArmorItem.Type.LEGGINGS, 7);
        element.put(ArmorItem.Type.CHESTPLATE, 9);
        element.put(ArmorItem.Type.HELMET, 5);
    }), 20, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.2F, () -> {
        return Ingredient.of(ItemRegistry.PRESSED_BREAD.get());
    });

    public static final StringRepresentable.EnumCodec<ArmorMaterials> CODEC = StringRepresentable.fromEnum(ArmorMaterials::values);
    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (element -> {
        element.put(ArmorItem.Type.BOOTS, 13);
        element.put(ArmorItem.Type.LEGGINGS, 15);
        element.put(ArmorItem.Type.CHESTPLATE, 16);
        element.put(ArmorItem.Type.HELMET, 11);
    }));
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ArmorMaterials(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> protectionFunctionForType, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionFunctionForType = protectionFunctionForType;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return HEALTH_FUNCTION_FOR_TYPE.get(type) * this.durabilityMultiplier;
    }


    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionFunctionForType.get(type);
    }

    public int getEnchantmentValue() { return this.enchantmentValue; }
    public SoundEvent getEquipSound() { return this.sound; }
    public Ingredient getRepairIngredient() { return this.repairIngredient.get(); }
    public String getName() { return "worldofmilk:" + this.name; }
    public float getToughness() { return this.toughness; }
    public float getKnockbackResistance() { return this.knockbackResistance; }
    public String getSerializedName() { return "worldofmilk:" + this.name; }
}