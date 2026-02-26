package com.lighsync.worldofmilk.client.gui.screen.recipebook;

import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;

import java.util.Set;

public class FridgeRecipeBookComponent extends AbstractFurnaceRecipeBookComponent {
    private static final Component FILTER_NAME = Component.translatable("gui.recipebook.toggleRecipes.fridgeable");

    protected Component getRecipeFilterName() {
        return FILTER_NAME;
    }

    protected Set<Item> getFuelItems() {
        return AbstractFurnaceBlockEntity.getFuel().keySet();
    }
}