package com.lighsync.worldofmilk.registries;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.items.*;
import com.lighsync.worldofmilk.items.utils.ArmorMaterials;
import com.lighsync.worldofmilk.items.utils.Tiers;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Worldofmilk.MODID);
 
    public static final RegistryObject<Item> MILK_BLOCK = ITEMS.register("milk_block", () -> new BlockItem(BlockRegistry.MILK_BLOCK.get(), new Item.Properties().food(Foods.MILK_BLOCK)));
    public static final RegistryObject<Item> MILK_LAYER_BLOCK = ITEMS.register("milk_layer_block", () -> new BlockItem(BlockRegistry.MILK_LAYER_BLOCK.get(), new Item.Properties().food(Foods.MILK_BLOCK)));
    public static final RegistryObject<Item> DENSE_MILK_BLOCK = ITEMS.register("dense_milk_block", () -> new BlockItem(BlockRegistry.DENSE_MILK_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> DENSE_MILK_DOOR = ITEMS.register("dense_milk_door", () -> new BlockItem(BlockRegistry.DENSE_MILK_DOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> DENSE_MILK_TRAPDOOR = ITEMS.register("dense_milk_trapdoor", () -> new BlockItem(BlockRegistry.DENSE_MILK_TRAPDOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> DENSE_MILK_BUTTON = ITEMS.register("dense_milk_button", () -> new BlockItem(BlockRegistry.DENSE_MILK_BUTTON.get(), new Item.Properties()));
    public static final RegistryObject<Item> DENSE_MILK_SLAB = ITEMS.register("dense_milk_slab", () -> new BlockItem(BlockRegistry.DENSE_MILK_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> DENSE_MILK_STAIRS = ITEMS.register("dense_milk_stairs", () -> new BlockItem(BlockRegistry.DENSE_MILK_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> DENSE_MILK_FENCE = ITEMS.register("dense_milk_fence", () -> new BlockItem(BlockRegistry.DENSE_MILK_FENCE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DENSE_MILK_FENCE_GATE = ITEMS.register("dense_milk_fence_gate", () -> new BlockItem(BlockRegistry.DENSE_MILK_FENCE_GATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DENSE_MILK_SIGN = ITEMS.register("dense_milk_sign", () -> new SignItem(new Item.Properties().stacksTo(16), BlockRegistry.DENSE_MILK_SIGN.get(), BlockRegistry.DENSE_MILK_WALL_SIGN.get()));
    public static final RegistryObject<Item> DENSE_MILK_HANGING_SIGN = ITEMS.register("dense_milk_hanging_sign", () -> new HangingSignItem(BlockRegistry.DENSE_MILK_HANGING_SIGN.get(), BlockRegistry.DENSE_MILK_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> DENSE_MILK_PRESSURE_PLATE = ITEMS.register("dense_milk_pressure_plate", () -> new BlockItem(BlockRegistry.DENSE_MILK_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CARVED_DENSE_MILK_BLOCK = ITEMS.register("carved_dense_milk_block", () -> new BlockItem(BlockRegistry.CARVED_DENSE_MILK_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> CARVED_DENSE_MILK_SLAB = ITEMS.register("carved_dense_milk_slab", () -> new BlockItem(BlockRegistry.CARVED_DENSE_MILK_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> CARVED_DENSE_MILK_WALL = ITEMS.register("carved_dense_milk_wall", () -> new BlockItem(BlockRegistry.CARVED_DENSE_MILK_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> CARVED_DENSE_MILK_STAIRS = ITEMS.register("carved_dense_milk_stairs", () -> new BlockItem(BlockRegistry.CARVED_DENSE_MILK_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> JEB_BLOCK = ITEMS.register("jeb_block", () -> new BlockItem(BlockRegistry.JEB_BLOCK.get(), new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> DINNERBONE_BLOCK = ITEMS.register("dinnerbone_block", () -> new BlockItem(BlockRegistry.DINNERBONE_BLOCK.get(), new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> FRIDGE_BLOCK = ITEMS.register("fridge", () -> new BlockItem(BlockRegistry.FRIDGE_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> CONCENTRED_MILK = ITEMS.register("concentred_milk", () -> new Item(new Item.Properties().food(Foods.CONCENTRED_MILK)));
    public static final RegistryObject<Item> BREAD_WITH_CONCENTRED_MILK = ITEMS.register("bread_with_concentred_milk", () -> new Item(new Item.Properties().food(Foods.BREAD_WITH_CONCENTRED_MILK)));
    public static final RegistryObject<Item> BUTTER_BUCKET = ITEMS.register("butter_bucket", () -> new ButterBucketItem(new Item.Properties().food(Foods.CONCENTRED_MILK).stacksTo(16).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<Item> BREAD_WITH_BUTTER = ITEMS.register("bread_with_butter", () -> new Item(new Item.Properties().food(Foods.MILK_BLOCK)));
    public static final RegistryObject<Item> DOUGH = ITEMS.register("dough", () -> new Item(new Item.Properties().food(Foods.EASTER_CAKE_BLANK)));
    public static final RegistryObject<Item> EASTER_CAKE = ITEMS.register("easter_cake", () -> new Item(new Item.Properties().food(Foods.EASTER_CAKE)));
    public static final RegistryObject<Item> EASTER_CAKE_BLANK = ITEMS.register("easter_cake_blank", () -> new Item(new Item.Properties().food(Foods.EASTER_CAKE_BLANK)));
    public static final RegistryObject<Item> SPRINKLES = ITEMS.register("sprinkles", () -> new Item(new Item.Properties().food(Foods.SPRIKNLES)));
    public static final RegistryObject<Item> WAFFLE = ITEMS.register("waffle", () -> new Item(new Item.Properties().food(Foods.WAFFLE)));
    public static final RegistryObject<Item> ICE_CREAM_BALL = ITEMS.register("ice_cream_ball", () -> new Item(new Item.Properties().food(Foods.ICE_CREAM_BALL)));
    public static final RegistryObject<Item> ICE_CREAM = ITEMS.register("ice_cream", () -> new Item(new Item.Properties().food(Foods.ICE_CREAM)));
    public static final RegistryObject<Item> ICE_CREAM_WITH_WAFFLE_PLOMBIRE = ITEMS.register("ice_cream_with_waffle_plombire", () -> new Item(new Item.Properties().food(Foods.ICE_CREAM_WITH_WAFFLE_PLOMBIRE)));

    public static final RegistryObject<Item> PRESSED_BREAD = ITEMS.register("pressed_bread", () -> new Item(new Item.Properties().food(Foods.PRESSED_BREAD)));
    public static final RegistryObject<Item> BAKED_PRESSED_BREAD = ITEMS.register("baked_pressed_bread", () -> new Item(new Item.Properties().food(Foods.PRESSED_BREAD)));
    public static final RegistryObject<Item> PRESSED_BREAD_UPGRADE = ITEMS.register("pressed_bread_upgrade", () -> new PressedBreadUpgradeItem());

    public static final RegistryObject<Item> BREAD_SWORD = ITEMS.register("bread_sword", () -> new BreadSwordItem(Tiers.PRESSED_BREAD, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> BREAD_SWORD_NEW_TEXTURE = ITEMS.register("bread_sword_with_new_texture", () -> new BreadSwordItem(Tiers.PRESSED_BREAD, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> BREAD_PICKAXE = ITEMS.register("bread_pickaxe", () -> new PickaxeItem(Tiers.PRESSED_BREAD, 1, -2.8f, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> BREAD_AXE = ITEMS.register("bread_axe", () -> new AxeItem(Tiers.PRESSED_BREAD, 5, -3.0f, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> BREAD_SHOVEL = ITEMS.register("bread_shovel", () -> new ShovelItem(Tiers.PRESSED_BREAD, 1, -3.0f, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> BREAD_HOE = ITEMS.register("bread_hoe", () -> new HoeItem(Tiers.PRESSED_BREAD, -4, 0.0f, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> BREAD_HELMET = ITEMS.register("bread_helmet", () -> new ArmorItem(ArmorMaterials.PRESSED_BREAD, ArmorItem.Type.HELMET, (new Item.Properties().fireResistant().rarity(Rarity.EPIC))));
    public static final RegistryObject<Item> BREAD_CHESTPLATE = ITEMS.register("bread_chestplate", () -> new ArmorItem(ArmorMaterials.PRESSED_BREAD, ArmorItem.Type.CHESTPLATE, (new Item.Properties().fireResistant().rarity(Rarity.EPIC))));
    public static final RegistryObject<Item> BREAD_LEGGINGS = ITEMS.register("bread_leggings", () -> new ArmorItem(ArmorMaterials.PRESSED_BREAD, ArmorItem.Type.LEGGINGS, (new Item.Properties().fireResistant().rarity(Rarity.EPIC))));
    public static final RegistryObject<Item> BREAD_BOOTS = ITEMS.register("bread_boots", () -> new ArmorItem(ArmorMaterials.PRESSED_BREAD, ArmorItem.Type.BOOTS, (new Item.Properties().fireResistant().rarity(Rarity.EPIC))));

    public static final RegistryObject<Item> BAKED_BREAD_HELMET = ITEMS.register("baked_bread_helmet", () -> new ArmorItem(ArmorMaterials.BAKED_PRESSED_BREAD, ArmorItem.Type.HELMET, (new Item.Properties().fireResistant().rarity(Rarity.EPIC))));
    public static final RegistryObject<Item> BAKED_BREAD_CHESTPLATE = ITEMS.register("baked_bread_chestplate", () -> new ArmorItem(ArmorMaterials.BAKED_PRESSED_BREAD, ArmorItem.Type.CHESTPLATE, (new Item.Properties().fireResistant().rarity(Rarity.EPIC))));
    public static final RegistryObject<Item> BAKED_BREAD_LEGGINGS = ITEMS.register("baked_bread_leggings", () -> new ArmorItem(ArmorMaterials.BAKED_PRESSED_BREAD, ArmorItem.Type.LEGGINGS, (new Item.Properties().fireResistant().rarity(Rarity.EPIC))));
    public static final RegistryObject<Item> BAKED_BREAD_BOOTS = ITEMS.register("baked_bread_boots", () -> new ArmorItem(ArmorMaterials.BAKED_PRESSED_BREAD, ArmorItem.Type.BOOTS, (new Item.Properties().fireResistant().rarity(Rarity.EPIC))));

    public static final RegistryObject<Item> PRESSED_BUTTER = ITEMS.register("pressed_butter", () -> new Item(new Item.Properties().food(Foods.MILK_BLOCK)));
    public static final RegistryObject<Item> PRESSED_BUTTER_UPGRADE = ITEMS.register("pressed_butter_upgrade", () -> new PressedButterUpgradeItem());

    public static final RegistryObject<Item> BUTTER_HELMET = ITEMS.register("butter_helmet", () -> new ArmorItem(ArmorMaterials.BUTTER, ArmorItem.Type.HELMET, (new Item.Properties().rarity(Rarity.UNCOMMON))));
    public static final RegistryObject<Item> BUTTER_CHESTPLATE = ITEMS.register("butter_chestplate", () -> new ArmorItem(ArmorMaterials.BUTTER, ArmorItem.Type.CHESTPLATE, (new Item.Properties().rarity(Rarity.UNCOMMON))));
    public static final RegistryObject<Item> BUTTER_LEGGINGS = ITEMS.register("butter_leggings", () -> new ArmorItem(ArmorMaterials.BUTTER, ArmorItem.Type.LEGGINGS, (new Item.Properties().rarity(Rarity.UNCOMMON))));
    public static final RegistryObject<Item> BUTTER_BOOTS = ITEMS.register("butter_boots", () -> new ArmorItem(ArmorMaterials.BUTTER, ArmorItem.Type.BOOTS, (new Item.Properties().rarity(Rarity.UNCOMMON))));

    public static final RegistryObject<Item> BUTTER_SWORD = ITEMS.register("butter_sword", () -> new ButterSwordItem(Tiers.BUTTER, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BUTTER_PICKAXE = ITEMS.register("butter_pickaxe", () -> new PickaxeItem(Tiers.BUTTER, 1, -2.8F, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BUTTER_AXE = ITEMS.register("butter_axe", () -> new AxeItem(Tiers.BUTTER, 5.0F, -3.1F, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BUTTER_SHOVEL = ITEMS.register("butter_shovel", () -> new ShovelItem(Tiers.BUTTER, 1.5F, -3.0F, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BUTTER_HOE = ITEMS.register("butter_hoe", () -> new HoeItem(Tiers.BUTTER, -2, -1.0F, new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> ENDER_PEARL_ARROW = ITEMS.register("ender_pearl_arrow", () -> new EnderPearlArrowItem(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> TNT_ARROW = ITEMS.register("tnt_arrow", () -> new TNTArrowItem(new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MILK_ZOMBIE_SPAWN_EGG = ITEMS.register("milk_zombie_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.MILK_ZOMBIE, 44975,14144467, new Item.Properties()));
    public static final RegistryObject<Item> ETHERIA_REFERENCE = ITEMS.register("etheria_reference", () -> new EtheriaReferenceItem(new Item.Properties().rarity(Rarity.EPIC)));
}