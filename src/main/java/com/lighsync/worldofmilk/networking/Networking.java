package com.lighsync.worldofmilk.networking;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.networking.utils.C2SPacket;
import com.lighsync.worldofmilk.networking.utils.SyncCapabilityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.util.thread.EffectiveSide;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Networking {
    public static SimpleChannel INSTANCE;
    public static int ID = 0;

    public static int nextId() {
        return ID++;
    }

    public static void init() {
        ID = 0;
        INSTANCE = NetworkRegistry.newSimpleChannel(ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "networking"), () -> "0.2.0", (s) -> true, (s) -> true);
        INSTANCE.messageBuilder(SyncCapabilityPacket.class, nextId(), NetworkDirection.PLAY_TO_CLIENT).encoder(SyncCapabilityPacket::encode).decoder(SyncCapabilityPacket::new).consumerMainThread(SyncCapabilityPacket::handle).add();
        INSTANCE.messageBuilder(C2SPacket.class, nextId(), NetworkDirection.PLAY_TO_SERVER).encoder(C2SPacket::encode).decoder(C2SPacket::new).consumerMainThread(C2SPacket::handle).add();
    }

    public static void sendToClients(PacketDistributor.PacketTarget target, Object packet) {
        INSTANCE.send(target, packet);
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }

    public static void sendToAll(Object packet) {
        if (EffectiveSide.get() == LogicalSide.SERVER) {
            INSTANCE.send(PacketDistributor.ALL.noArg(), packet);
        }
    }

    public static void sendToClient(ServerPlayer player, Object packet) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendTrackingEntity(Entity entity, Object packet) {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), packet);
    }

    public static void sendTrackingChunk(LevelChunk entity, Object packet) {
        INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> entity), packet);
    }
}