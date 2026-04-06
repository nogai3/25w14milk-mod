package com.lighsync.worldofmilk.utils;

import net.minecraft.nbt.Tag;

public interface NBTSerializing<T extends Tag> {
    T serializeNBT();
    void deserializeNBT(T value);
}
