package com.lighsync.worldofmilk.client.gui.screen;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.client.gui.screen.recipebook.FridgeRecipeBookComponent;
import com.lighsync.worldofmilk.menu.FridgeMenu;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FridgeScreen extends AbstractFurnaceScreen<FridgeMenu> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "textures/gui/container/fridge.png");

    public FridgeScreen(FridgeMenu menu, Inventory inv, Component title) {
        super(menu, new FridgeRecipeBookComponent(), inv, title, TEXTURE);
    }
}