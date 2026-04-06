package com.lighsync.worldofmilk.events;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.capability.WorldofmilkProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CapabilityAttacher {
    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(
                    ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "player_cap"),
                    new WorldofmilkProvider()
            );
        }
    }
}