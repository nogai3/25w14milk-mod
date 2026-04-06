package com.lighsync.worldofmilk.capability;

import com.lighsync.worldofmilk.networking.Networking;
import com.lighsync.worldofmilk.networking.utils.SyncCapabilityPacket;
import com.lighsync.worldofmilk.registries.CapabilityRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SyncCapabilityManager {
    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        sync(event.getEntity());
    }
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        sync(event.getEntity());
    }
    @SubscribeEvent
    public static void onDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        sync(event.getEntity());
    }

    public static boolean sync(Player player) {
        if (player.level().isClientSide) return false;

        if (player instanceof ServerPlayer sp) {
            Networking.sendToClient(sp, new SyncCapabilityPacket(
                    CapabilityRegistry.getCap(player).serializeNBT()
            ));
            return true;
        }
        return false;
    }

    /*public static boolean sync(Player player, CompoundTag compoundTag) {
        if (player.level().isClientSide) return false;

        if (player instanceof ServerPlayer player1) {
            // Networking.sendToClient();
            return true;
        }
        return false;
    }*/
}
