package com.lighsync.worldofmilk.capability;

import com.lighsync.worldofmilk.utils.NBTHelper;
import com.lighsync.worldofmilk.utils.NBTSerializing;
import com.lighsync.worldofmilk.utils.annotations.AutomaticSerialization;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class WorldofmilkCapability implements NBTSerializing<CompoundTag> {
    @AutomaticSerialization
    boolean playerRotated = false;
    @AutomaticSerialization
    boolean playerSuperRotate = false;

    @Override
    public CompoundTag serializeNBT() {
        return serializeNBT(null);
    }

    public CompoundTag serializeNBT(@Nullable Level level) {
        CompoundTag compoundTag = NBTHelper.serialize(this);
        compoundTag.putBoolean("playerRotated", playerRotated);
        compoundTag.putBoolean("playerSuperRotate", playerSuperRotate);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        deserializeNBT(tag, null);
    }

    public void deserializeNBT(CompoundTag tag, @Nullable Level level) {
        NBTHelper.deserialize(this, tag);
        this.playerRotated = tag.getBoolean("playerRotated");
        this.playerSuperRotate = tag.getBoolean("playerSuperRotate");
    }

    public boolean isPlayerRotated() {
        return this.playerRotated;
    }
    public boolean isPlayerSuperRotated() { return this.playerSuperRotate; }

    public void setPlayerRotated(boolean flag) {
        this.playerRotated = flag;
    }
    public void setPlayerSuperRotate(boolean flag) { this.playerSuperRotate = flag; }
}