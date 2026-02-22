package com.lighsync.worldofmilk.blocks;

import com.lighsync.worldofmilk.blocks.entity.DenseMilkHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class DenseMilkWallHangingSign extends WallHangingSignBlock {
    public DenseMilkWallHangingSign(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DenseMilkHangingSignBlockEntity(pos, state);
    }
}
