package com.lighsync.worldofmilk.blocks.entity;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.menu.FridgeMenu;
import com.lighsync.worldofmilk.registries.BlockEntityRegistry;
import com.lighsync.worldofmilk.registries.ItemRegistry;
import com.lighsync.worldofmilk.registries.RecipeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

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
    private int lastProgress = 0;
    private ItemStack lastInputCopy = ItemStack.EMPTY;

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

    public static void serverTick(Level level, BlockPos pos, BlockState state, FridgeBlockEntity entity) {
        ItemStack inputBefore = entity.getItem(0).copy();
        int prevProgress = entity.lastProgress;

        AbstractFurnaceBlockEntity.serverTick(level, pos, state, entity);

        int currentProgress = entity.dataAccess.get(2);

        if (prevProgress > currentProgress) {
            if (!inputBefore.isEmpty()) {
                ItemStack container = inputBefore.getCraftingRemainingItem();

                if (!container.isEmpty()) {
                    if (inputBefore.getCount() == 1) {
                        entity.setItem(0, container.copy());
                    } else {
                        ItemStack output = entity.getItem(2);

                        if (output.isEmpty()) {
                            entity.setItem(2, container.copy());
                        } else if (ItemStack.isSameItemSameTags(output, container)
                                && output.getCount() < output.getMaxStackSize()) {

                            output.grow(1);
                            entity.setItem(2, output);
                        } else {
                            if (level instanceof net.minecraft.server.level.ServerLevel serverLevel) {
                                net.minecraft.world.Containers.dropItemStack(
                                        serverLevel,
                                        pos.getX(),
                                        pos.getY(),
                                        pos.getZ(),
                                        container.copy()
                                );
                            }
                        }
                    }
                }
            }
        }

        entity.lastProgress = currentProgress;
    }

    public static boolean isValidFridgeFuel(ItemStack stack) {
        return COLD_FUEL_VALUES.containsKey(stack.getItem());
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new FridgeMenu(containerId, playerInventory, this, this.dataAccess);
    }
}