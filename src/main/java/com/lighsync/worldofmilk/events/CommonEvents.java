package com.lighsync.worldofmilk.events;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.blocks.utils.JebBlockMessageManager;
import com.lighsync.worldofmilk.client.renderer.EnderPearlArrowRenderer;
import com.lighsync.worldofmilk.client.renderer.MilkZombieRenderer;
import com.lighsync.worldofmilk.client.renderer.TNTArrowRenderer;
import com.lighsync.worldofmilk.entities.monster.MilkZombie;
import com.lighsync.worldofmilk.items.BreadSwordItem;
import com.lighsync.worldofmilk.items.Foods;
import com.lighsync.worldofmilk.registries.BlockEntityRegistry;
import com.lighsync.worldofmilk.registries.BlockRegistry;
import com.lighsync.worldofmilk.registries.EntityRegistry;
import com.lighsync.worldofmilk.registries.ItemRegistry;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Worldofmilk.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            Sheets.addWoodType(BlockRegistry.MILK_WOOD_TYPE);
        });
    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.ENDER_PEARL_ARROW.get(), EnderPearlArrowRenderer::new);
        event.registerEntityRenderer(EntityRegistry.TNT_ARROW.get(), TNTArrowRenderer::new);
        event.registerEntityRenderer(EntityRegistry.MILK_ZOMBIE.get(), MilkZombieRenderer::new);

        event.registerBlockEntityRenderer(BlockEntityRegistry.DENSE_MILK_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.DENSE_MILK_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.MILK_ZOMBIE.get(), MilkZombie.createAttributes().build());
    }

    @Mod.EventBusSubscriber(modid = Worldofmilk.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ModEvents {
        @SubscribeEvent
        public static void onAddReloadListener(AddReloadListenerEvent event) {
            event.addListener(JebBlockMessageManager.INSTANCE);
        }

        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {
            if (!(event.getSource().getEntity() instanceof Player player)) return;

            ItemStack mainHand = player.getMainHandItem();
            if (!(mainHand.getItem() instanceof BreadSwordItem)) return;

            float bonus = BreadSwordItem.calcBonusFromFood(player);
            event.setAmount(event.getAmount() + bonus);
        }

        @SubscribeEvent
        public static void onEntityUseItem(LivingEntityUseItemEvent.Finish event) {
            if (!((event.getEntity()) instanceof Player player)) return;

            ItemStack item = event.getItem();
            if (item.is(Foods.MILK_FOOD)) {
                player.removeAllEffects();
            }
        }

        private static boolean isWearing(Player player, EquipmentSlot slot, Item item) {
            ItemStack stack = player.getItemBySlot(slot);
            return !stack.isEmpty() && stack.is(item);
        }

        private static boolean hasFullSet(Player player) {
            return isWearing(player, EquipmentSlot.HEAD, ItemRegistry.BREAD_HELMET.get()) ||
                    isWearing(player, EquipmentSlot.CHEST, ItemRegistry.BREAD_CHESTPLATE.get()) ||
                    isWearing(player, EquipmentSlot.LEGS, ItemRegistry.BREAD_LEGGINGS.get()) ||
                    isWearing(player, EquipmentSlot.FEET, ItemRegistry.BREAD_BOOTS.get());
        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if (event.phase != TickEvent.Phase.END) return;

            Player player = event.player;
            if (player.level().isClientSide) return;

            if (hasFullSet(player) && player.getFoodData().getFoodLevel() < 6) {
                player.addEffect(new MobEffectInstance(
                        MobEffects.SATURATION,
                        7,
                        0,
                        true, false, true
                ));
            }
        }

        @SubscribeEvent
        public static void onStruck(EntityStruckByLightningEvent event) {
            if (!(event.getEntity() instanceof Cow cow)) return;
            if (cow.level().isClientSide) return;

            ServerLevel level = (ServerLevel) cow.level();

            Mob milkZombie = EntityRegistry.MILK_ZOMBIE.get().create(level);
            if (milkZombie == null) return;

            milkZombie.moveTo(cow.getX(), cow.getY(), cow.getZ(), cow.getYRot(), cow.getXRot());
            if (cow.hasCustomName()) {
                milkZombie.setCustomName(cow.getCustomName());
                milkZombie.setCustomNameVisible(cow.isCustomNameVisible());
            }
            milkZombie.setPersistenceRequired();
            cow.discard();
            level.addFreshEntity(milkZombie);
        }

        @SubscribeEvent
        public static void onLivingDrops(LivingDropsEvent event) {
            if (!(event.getEntity() instanceof Player player)) return;

            if (player.level().isClientSide) return;

            String playerName = player.getGameProfile().getName();
            switch (playerName) {
                case "glackus", "wandarmo", "PLYTONI", "KAPITANKASTET" -> event.getDrops().add(player.spawnAtLocation(new ItemStack(Items.MILK_BUCKET)));
                case "jeb_", "Jeb_" -> event.getDrops().add(player.spawnAtLocation(new ItemStack(ItemRegistry.JEB_BLOCK.get())));
                // case "Dev" -> event.getDrops().add(player.spawnAtLocation(new ItemStack(ItemRegistry.MILK_LAYER_BLOCK.get())));
            }
        }
    }
}
