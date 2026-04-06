package com.lighsync.worldofmilk.registries;

import com.lighsync.worldofmilk.capability.WorldofmilkCapability;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class CapabilityRegistry {
    public static final Capability<WorldofmilkCapability> WORLDOFMILK_CAPABILITY = CapabilityManager.get(new CapabilityToken<WorldofmilkCapability>() {
    });

    public static LazyOptional<WorldofmilkCapability> getWorldofmilkCapability(Player player) {
        return player.getCapability(WORLDOFMILK_CAPABILITY);
    }

    /**public static WorldofmilkCapability getCap(Player player) {
        return getWorldofmilkCapability(player).orElse(new WorldofmilkCapability());
    }**/
    public static WorldofmilkCapability getCap(Player player) {
        return getWorldofmilkCapability(player).orElseThrow(() ->
                new IllegalStateException("Capability not present!")
        );
    }

    @SubscribeEvent
    public static void onCapabilityRegistry(RegisterCapabilitiesEvent event) {
        event.register(WorldofmilkCapability.class);
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        Player oldPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();
        oldPlayer.reviveCaps();
        oldPlayer.getCapability(WORLDOFMILK_CAPABILITY).ifPresent(oldData -> {
            newPlayer.getCapability(WORLDOFMILK_CAPABILITY).ifPresent(newData -> {
                newData.deserializeNBT(oldData.serializeNBT());
            });
        });
        oldPlayer.invalidateCaps();
    }
}