package com.lighsync.worldofmilk.items;

import com.lighsync.worldofmilk.client.gui.screen.EtheriaReferenceScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EtheriaReferenceItem extends Item {
    public EtheriaReferenceItem(Item.Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) return InteractionResultHolder.success(new ItemStack(this));

        Minecraft.getInstance().setScreen(new EtheriaReferenceScreen());
        return InteractionResultHolder.success(new ItemStack(this));
    }
}