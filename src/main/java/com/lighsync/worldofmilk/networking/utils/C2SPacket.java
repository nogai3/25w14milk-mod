package com.lighsync.worldofmilk.networking.utils;

import com.lighsync.worldofmilk.capability.SyncCapabilityManager;
import com.lighsync.worldofmilk.registries.CapabilityRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SPacket {
    private final CompoundTag tag;
    private final int action;

    public C2SPacket(FriendlyByteBuf buf) {
        this.tag = buf.readNbt();
        this.action = buf.readInt();
    }

    public C2SPacket(CompoundTag tag, int action) {
        this.tag = tag;
        this.action = action;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeNbt(this.tag);
        buf.writeInt(this.action);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer player = ((NetworkEvent.Context) ctx.get()).getSender();
        if (player == null) {
            return false;
        } else {
            switch (this.action) {
                case 1 -> {
                    var cap = CapabilityRegistry.getCap(player);
                    cap.setPlayerRotated(!cap.isPlayerRotated());
                    SyncCapabilityManager.sync(player);
                }
                case 2 -> {
                    var cap = CapabilityRegistry.getCap(player);
                    cap.setPlayerSuperRotate(!cap.isPlayerSuperRotated());
                    SyncCapabilityManager.sync(player);
                }
            }
        }
        return true;
    }
}