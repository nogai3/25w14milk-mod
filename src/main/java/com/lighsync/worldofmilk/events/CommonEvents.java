package com.lighsync.worldofmilk.events;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.blocks.utils.JebBlockMessageManager;
import com.lighsync.worldofmilk.client.renderer.EnderPearlArrowRenderer;
import com.lighsync.worldofmilk.client.renderer.MilkZombieRenderer;
import com.lighsync.worldofmilk.client.renderer.TNTArrowRenderer;
import com.lighsync.worldofmilk.entities.monster.MilkZombie;
import com.lighsync.worldofmilk.items.BreadSwordItem;
import com.lighsync.worldofmilk.registries.BlockEntityRegistry;
import com.lighsync.worldofmilk.registries.BlockRegistry;
import com.lighsync.worldofmilk.registries.EntityRegistry;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
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
    }
}
