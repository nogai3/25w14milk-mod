package com.lighsync.worldofmilk.blocks;

import com.lighsync.worldofmilk.blocks.entity.DenseMilkHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class DenseMilkHangingSign extends CeilingHangingSignBlock {
    public DenseMilkHangingSign(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DenseMilkHangingSignBlockEntity(pos, state);
    }
}
