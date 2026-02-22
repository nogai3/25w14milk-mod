package com.lighsync.worldofmilk.blocks.entity;

import com.lighsync.worldofmilk.registries.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DenseMilkSignBlockEntity extends SignBlockEntity {
    public DenseMilkSignBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.DENSE_MILK_SIGN.get(), pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return BlockEntityRegistry.DENSE_MILK_SIGN.get();
    }
}