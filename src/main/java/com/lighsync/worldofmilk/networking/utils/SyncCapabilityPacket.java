package com.lighsync.worldofmilk.networking.utils;

import com.lighsync.worldofmilk.registries.CapabilityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncCapabilityPacket {
    public final CompoundTag tag;

    public SyncCapabilityPacket(FriendlyByteBuf byteBuf) {
        this.tag = byteBuf.readNbt();
    }

    public SyncCapabilityPacket(CompoundTag tag) {
        this.tag = tag;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeNbt(this.tag);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ((NetworkEvent.Context) ctx.get()).enqueueWork(this::handleClient);
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    private void handleClient() {
        if (Minecraft.getInstance().player != null) {
            CapabilityRegistry.getCap(Minecraft.getInstance().player).deserializeNBT(this.tag);
        }
    }
}