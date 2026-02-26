package com.lighsync.worldofmilk.menu;

import com.lighsync.worldofmilk.blocks.entity.FridgeBlockEntity;
import com.lighsync.worldofmilk.registries.MenuRegistry;
import com.lighsync.worldofmilk.registries.RecipeRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;

public class FridgeMenu extends AbstractFurnaceMenu {

    public FridgeMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv);
    }

    public FridgeMenu(int id, Inventory inv) {
        super(MenuRegistry.FRIDGE_MENU.get(),
                RecipeRegistry.FRIDGE_TYPE.get(),
                RecipeBookType.FURNACE,
                id, inv);
    }

    public FridgeMenu(int id, Inventory inv, Container container, ContainerData data) {
        super(MenuRegistry.FRIDGE_MENU.get(),
                RecipeRegistry.FRIDGE_TYPE.get(),
                RecipeBookType.FURNACE,
                id, inv, container, data);
    }

    @Override
    public boolean stillValid(Player player) {
        return super.stillValid(player);
    }

    @Override
    protected boolean isFuel(ItemStack item) {
        return FridgeBlockEntity.isValidFridgeFuel(item);
    }
}