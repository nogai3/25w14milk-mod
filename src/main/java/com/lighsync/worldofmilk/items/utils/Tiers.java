package com.lighsync.worldofmilk.items.utils;

import com.lighsync.worldofmilk.registries.ItemRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum Tiers implements Tier {
    PRESSED_BREAD(4, 2031, 9.0F, 4.0F, 15, () -> {
        return Ingredient.of(ItemRegistry.PRESSED_BREAD.get());
    });

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private Tiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getUses() { return this.uses; }
    public float getSpeed() { return this.speed; }
    public float getAttackDamageBonus() { return this.damage; }
    public int getLevel() { return this.level; }
    public int getEnchantmentValue() { return this.enchantmentValue; }
    public Ingredient getRepairIngredient() { return this.repairIngredient.get(); }
}
