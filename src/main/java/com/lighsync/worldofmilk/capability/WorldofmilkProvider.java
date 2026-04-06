package com.lighsync.worldofmilk.capability;

import com.lighsync.worldofmilk.registries.CapabilityRegistry;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class WorldofmilkProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    private final WorldofmilkCapability cap = new WorldofmilkCapability();
    private final LazyOptional<WorldofmilkCapability> optional = LazyOptional.of(() -> cap);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == CapabilityRegistry.WORLDOFMILK_CAPABILITY ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return cap.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        cap.deserializeNBT(nbt);
    }
}