package com.lighsync.worldofmilk.blocks.entity;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.menu.FridgeMenu;
import com.lighsync.worldofmilk.registries.BlockEntityRegistry;
import com.lighsync.worldofmilk.registries.RecipeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
// net.minecraft.world.inventory.FridgeMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class FridgeBlockEntity extends AbstractFurnaceBlockEntity {
    private static final Map<Item, Integer> COLD_FUEL_VALUES = Map.of(
            Items.ICE, 200,
            Items.PACKED_ICE, 400,
            Items.BLUE_ICE, 800,
            Items.SNOW_BLOCK, 300,
            Items.SNOWBALL, 50,
            Items.POWDER_SNOW_BUCKET, 600
    );

    public FridgeBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FRIDGE_BLOCK.get(), pos, state, RecipeRegistry.FRIDGE_TYPE.get());
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container." + Worldofmilk.MODID + ".fridge");
    }

    @Override
    protected int getBurnDuration(ItemStack fuelStack) {
        return COLD_FUEL_VALUES.getOrDefault(fuelStack.getItem(), 0);
    }

    @Override
    public boolean canPlaceItem(int slotIndex, ItemStack stack) {
        if (slotIndex == 1) {
            return isValidFridgeFuel(stack);
        }
        return super.canPlaceItem(slotIndex, stack);
    }

    public static boolean isValidFridgeFuel(ItemStack stack) {
        return COLD_FUEL_VALUES.containsKey(stack.getItem());
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new FridgeMenu(containerId, playerInventory, this, this.dataAccess);
    }
}