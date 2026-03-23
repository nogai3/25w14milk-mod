package com.lighsync.worldofmilk.items;

import com.lighsync.worldofmilk.Worldofmilk;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class PressedButterUpgradeItem extends SmithingTemplateItem {
    private static final ResourceLocation EMPTY_SLOT_HELMET = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots");

    private static final ResourceLocation EMPTY_SLOT_HOE = ResourceLocation.withDefaultNamespace("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = ResourceLocation.withDefaultNamespace("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.withDefaultNamespace("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = ResourceLocation.withDefaultNamespace("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe");

    private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.withDefaultNamespace("item/empty_slot_ingot");

    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

    // item.worldofmilk.smithing_template.pressed_butter_upgrade.applies_to
    // item.worldofmilk.smithing_template.pressed_butter_upgrade.ingredients
    // item.worldofmilk.smithing_template.pressed_butter_upgrade.base_slot_description
    // item.worldofmilk.smithing_template.pressed_butter_upgrade.additions_slot_description
    private static final Component PRESSED_BUTTER_UPGRADE_APPLIES_TO =
            Component.translatable(Util.makeDescriptionId("item",
                            ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "smithing_template.pressed_butter_upgrade.applies_to")))
                    .withStyle(DESCRIPTION_FORMAT);

    private static final Component PRESSED_BUTTER_UPGRADE_INGREDIENTS =
            Component.translatable(Util.makeDescriptionId("item",
                            ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "smithing_template.pressed_butter_upgrade.ingredients")))
                    .withStyle(DESCRIPTION_FORMAT);

    private static final Component PRESSED_BUTTER_UPGRADE =
            Component.translatable(Util.makeDescriptionId("upgrade",
                            ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "pressed_butter_upgrade")))
                    .withStyle(TITLE_FORMAT);

    private static final Component PRESSED_BUTTER_UPGRADE_BASE_SLOT_DESCRIPTION =
            Component.translatable(Util.makeDescriptionId("item",
                    ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "smithing_template.pressed_butter_upgrade.base_slot_description")));

    private static final Component PRESSED_BUTTER_UPGRADE_ADDITIONS_SLOT_DESCRIPTION =
            Component.translatable(Util.makeDescriptionId("item",
                    ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "smithing_template.pressed_butter_upgrade.additions_slot_description")));

    public PressedButterUpgradeItem() {
        super(
                PRESSED_BUTTER_UPGRADE_APPLIES_TO,
                PRESSED_BUTTER_UPGRADE_INGREDIENTS,
                PRESSED_BUTTER_UPGRADE,
                PRESSED_BUTTER_UPGRADE_BASE_SLOT_DESCRIPTION,
                PRESSED_BUTTER_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                createPressedBUTTERUpgradeIconList(),
                createPressedBUTTERUpgradeMaterialList()
        );
    }

    private static List<ResourceLocation> createPressedBUTTERUpgradeIconList() {
        return List.of(
                EMPTY_SLOT_HELMET,
                EMPTY_SLOT_SWORD,
                EMPTY_SLOT_CHESTPLATE,
                EMPTY_SLOT_PICKAXE,
                EMPTY_SLOT_LEGGINGS,
                EMPTY_SLOT_AXE,
                EMPTY_SLOT_BOOTS,
                EMPTY_SLOT_HOE,
                EMPTY_SLOT_SHOVEL
        );
    }

    private static List<ResourceLocation> createPressedBUTTERUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
}