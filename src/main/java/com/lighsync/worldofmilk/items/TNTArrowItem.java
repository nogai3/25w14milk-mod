package com.lighsync.worldofmilk.items;

import com.lighsync.worldofmilk.entities.projectile.TNTArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TNTArrowItem extends ArrowItem {
    public TNTArrowItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack item, LivingEntity entity) {
        return new TNTArrow(level, entity, item.copyWithCount(1), item);
    }
}
