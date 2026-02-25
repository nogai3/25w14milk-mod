package com.lighsync.worldofmilk.blocks;

import com.lighsync.worldofmilk.blocks.utils.JebBlockMessageManager;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import javax.annotation.Nullable;

public class JebBlock extends Block {
    private static final String CRYPTED_API_KEY = "";
    private boolean canUseBlock = true;
    private static final JebBlockMessageManager MESSAGE_MANAGER = JebBlockMessageManager.INSTANCE;
    private static final SoundEvent[] SOUND_EVENTS = {};

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public JebBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(POWERED, false));
    }

    private void useBlock(Level level, @Nullable Player player, BlockPos pos) {
        String message = MESSAGE_MANAGER.getRandomMessage();
        Component text = Component.literal(message);

        if (!level.isClientSide) {
            if (player != null) {
                player.sendSystemMessage(text);
            } else if (level.getServer() != null) {
                level.getServer().getPlayerList().getPlayers()
                        .forEach(p -> p.sendSystemMessage(text));
            }
        }

        if (SOUND_EVENTS.length > 0) {
            int index = level.getRandom().nextInt(SOUND_EVENTS.length);
            playCustomSound(level, pos, SOUND_EVENTS[index]);
        }
    }

    private void playCustomSound(Level level, BlockPos pos, SoundEvent event) {
        level.playSound(null, pos, event, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return false;
    }

    @Override
    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
        return 0;
    }

    @Override
    public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
        return 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWERED);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (hand == InteractionHand.MAIN_HAND) {
            if (!level.isClientSide() && canUseBlock) {
                useBlock(level, player, pos);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide()) {
            boolean powered = level.hasNeighborSignal(pos);

            if (powered != state.getValue(POWERED)) {
                level.setBlock(pos, state.setValue(POWERED, powered), 3);

                if (powered) {
                    useBlock(level, null, pos);
                    level.scheduleTick(pos, this, 1);
                }
            }
        }
    }
}