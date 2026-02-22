/*package com.lighsync.worldofmilk.menu;

import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
// import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class AbstractFridgeMenu extends RecipeBookMenu<Container> {

    public static final int INGREDIENT_SLOT = 0;
    public static final int FUEL_SLOT = 1;
    public static final int RESULT_SLOT = 2;

    private static final int SLOT_COUNT = 3;
    private static final int DATA_COUNT = 4;

    private static final int PLAYER_INV_START = 3;
    private static final int PLAYER_INV_END = 30;
    private static final int HOTBAR_START = 30;
    private static final int HOTBAR_END = 39;

    protected final Container container;
    protected final ContainerData data;
    protected final Level level;

    private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    private final RecipePropertySet acceptedInputs;
    private final RecipeBookType recipeBookType;

    protected AbstractFridgeMenu(
            MenuType<?> menuType,
            RecipeType<? extends AbstractCookingRecipe> recipeType,
            ResourceKey<RecipePropertySet> acceptedInputsKey,
            RecipeBookType recipeBookType,
            int containerId,
            Inventory playerInventory
    ) {
        this(menuType, recipeType, acceptedInputsKey, recipeBookType, containerId, playerInventory,
                new SimpleContainer(SLOT_COUNT), new SimpleContainerData(DATA_COUNT));
    }

    protected AbstractFridgeMenu(
            MenuType<?> menuType,
            RecipeType<? extends AbstractCookingRecipe> recipeType,
            ResourceKey<RecipePropertySet> acceptedInputsKey,
            RecipeBookType recipeBookType,
            int containerId,
            Inventory playerInventory,
            Container container,
            ContainerData data
    ) {
        super(menuType, containerId);

        this.recipeType = recipeType;
        this.recipeBookType = recipeBookType;

        checkContainerSize(container, SLOT_COUNT);
        checkContainerDataCount(data, DATA_COUNT);

        this.container = container;
        this.data = data;
        this.level = playerInventory.player.level();

        this.acceptedInputs = this.level.recipeAccess().propertySet(acceptedInputsKey);

        this.addSlot(new Slot(container, INGREDIENT_SLOT, 56, 17));
        this.addSlot(new FridgeFuelSlot(this, container, FUEL_SLOT, 56, 53));
        this.addSlot(new FurnaceResultSlot(playerInventory.player, container, RESULT_SLOT, 116, 35));

        this.addStandardInventorySlots(playerInventory, 8, 84);
        this.addDataSlots(data);
    }

    public Slot getResultSlot() {
        return this.slots.get(RESULT_SLOT);
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedItemContents contents) {
        if (this.container instanceof StackedContentsCompatible compatible) {
            compatible.fillStackedContents(contents);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        ItemStack ret = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            ret = stack.copy();

            if (slotIndex == RESULT_SLOT) {
                if (!this.moveItemStackTo(stack, PLAYER_INV_START, HOTBAR_END, true)) return ItemStack.EMPTY;
                slot.onQuickCraft(stack, ret);
            } else if (slotIndex != FUEL_SLOT && slotIndex != INGREDIENT_SLOT) {
                if (this.canSmelt(stack)) {
                    if (!this.moveItemStackTo(stack, INGREDIENT_SLOT, INGREDIENT_SLOT + 1, false)) return ItemStack.EMPTY;
                } else if (this.isFuel(stack)) {
                    if (!this.moveItemStackTo(stack, FUEL_SLOT, FUEL_SLOT + 1, false)) return ItemStack.EMPTY;
                } else if (slotIndex >= PLAYER_INV_START && slotIndex < PLAYER_INV_END) {
                    if (!this.moveItemStackTo(stack, HOTBAR_START, HOTBAR_END, false)) return ItemStack.EMPTY;
                } else if (slotIndex >= HOTBAR_START && slotIndex < HOTBAR_END) {
                    if (!this.moveItemStackTo(stack, PLAYER_INV_START, PLAYER_INV_END, false)) return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(stack, PLAYER_INV_START, HOTBAR_END, false)) return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) slot.setByPlayer(ItemStack.EMPTY);
            else slot.setChanged();

            if (stack.getCount() == ret.getCount()) return ItemStack.EMPTY;

            slot.onTake(player, stack);
        }

        return ret;
    }

    protected boolean canSmelt(ItemStack stack) {
        return this.acceptedInputs.test(stack);
    }

    protected boolean isFuel(ItemStack stack) {
        return stack.is(Items.ICE)
                || stack.is(Items.PACKED_ICE)
                || stack.is(Items.BLUE_ICE)
                || stack.is(Items.SNOW_BLOCK)
                || stack.is(Items.SNOWBALL)
                || stack.is(Items.POWDER_SNOW_BUCKET);
    }

    public float getBurnProgress() {
        int cookTime = this.data.get(2);
        int cookTimeTotal = this.data.get(3);
        return cookTimeTotal != 0 && cookTime != 0
                ? Mth.clamp((float) cookTime / (float) cookTimeTotal, 0.0F, 1.0F)
                : 0.0F;
    }

    public float getLitProgress() {
        int burnTotal = this.data.get(1);
        if (burnTotal == 0) burnTotal = 200;
        return Mth.clamp((float) this.data.get(0) / (float) burnTotal, 0.0F, 1.0F);
    }

    public boolean isLit() {
        return this.data.get(0) > 0;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return this.recipeBookType;
    }

    @Override
    public PostPlaceAction handlePlacement(boolean placeAll, boolean shiftDown, RecipeHolder<?> recipe, ServerLevel level, Inventory inventory) {
        final List<Slot> results = List.of(this.getSlot(INGREDIENT_SLOT), this.getSlot(RESULT_SLOT));

        return ServerPlaceRecipe.placeRecipe(
                new ServerPlaceRecipe.CraftingMenuAccess<AbstractCookingRecipe>() {
                    @Override public void fillCraftSlotsStackedContents(StackedItemContents contents) {
                        AbstractFridgeMenu.this.fillCraftSlotsStackedContents(contents);
                    }

                    @Override public void clearCraftingContent() {
                        results.forEach(s -> s.set(ItemStack.EMPTY));
                    }

                    @Override public boolean recipeMatches(RecipeHolder<AbstractCookingRecipe> holder) {
                        return holder.value().matches(new SingleRecipeInput(AbstractFridgeMenu.this.container.getItem(INGREDIENT_SLOT)), level);
                    }
                },
                1, 1,
                List.of(this.getSlot(INGREDIENT_SLOT)),
                results,
                inventory,
                (RecipeHolder<AbstractCookingRecipe>) recipe,
                placeAll, shiftDown
        );
    }
}*/