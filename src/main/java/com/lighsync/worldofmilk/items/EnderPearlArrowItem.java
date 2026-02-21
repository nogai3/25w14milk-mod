package com.lighsync.worldofmilk.items;

import com.lighsync.worldofmilk.entities.projectile.EnderPearlArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EnderPearlArrowItem extends ArrowItem {
    public EnderPearlArrowItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level , ItemStack item, LivingEntity entity) {
        return new EnderPearlArrow(level, entity, item.copyWithCount(1), item);
    }
}