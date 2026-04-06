package com.lighsync.worldofmilk.blocks;

import com.lighsync.worldofmilk.networking.Networking;
import com.lighsync.worldofmilk.networking.utils.C2SPacket;
import com.lighsync.worldofmilk.registries.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class DinnerboneBlock extends Block {
    public DinnerboneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (hand == InteractionHand.MAIN_HAND) {
            if (level.isClientSide() && !player.isShiftKeyDown()) {
                Networking.sendToServer(new C2SPacket(new CompoundTag(), 1));
            } else if (level.isClientSide() && player.isShiftKeyDown()) {
                Networking.sendToServer(new C2SPacket(new CompoundTag(), 2));
            }
            level.playSound(player, pos, SoundRegistry.ROTATION.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return InteractionResult.PASS;
    }
}