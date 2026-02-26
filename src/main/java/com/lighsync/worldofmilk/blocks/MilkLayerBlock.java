package com.lighsync.worldofmilk.blocks;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MilkLayerBlock extends Block {
    public static final int MAX_HEIGHT = 8;
    public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
    protected static final VoxelShape[] SHAPE_BY_LAYER = new VoxelShape[]{
      Shapes.empty(),
      Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    };
    public static final int HEIGHT_IMPASSABLE = 5;

    public MilkLayerBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(LAYERS, Integer.valueOf(1)));
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos pos, PathComputationType compType) {
        switch (compType) {
            case LAND:
                return state.getValue(LAYERS) < 5;
            case WATER:
                return false;
            case AIR:
                return false;
            default:
                return false;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_LAYER[state.getValue(LAYERS)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_LAYER[state.getValue(LAYERS)-1];
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter getter, BlockPos pos) {
        return SHAPE_BY_LAYER[state.getValue(LAYERS)];
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_LAYER[state.getValue(LAYERS)];
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.getValue(LAYERS) == 0 ? 0.2F : 1.0F;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        BlockState blockstate = reader.getBlockState(pos.below());
        if (blockstate.is(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON)) {
            return false;
        } else {
            return blockstate.is(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON)
                    ? true
                    : Block.isFaceFull(blockstate.getCollisionShape(reader, pos.below()), Direction.UP);
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState state1, LevelAccessor accessor, BlockPos pos, BlockPos fromPos) {
        return !state.canSurvive(accessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, dir, state1, accessor, pos, fromPos);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getBrightness(LightLayer.BLOCK, pos) > 11) {
            dropResources(state, level, pos);
            level.removeBlock(pos, false);
        }
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        int i = state.getValue(LAYERS);
        if (context.getItemInHand().is(this.asItem()) && i < 8) {
            return context.replacingClickedOnBlock()
                    ? context.getClickedFace() == Direction.UP
                    : true;
        }
        return i == 1;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        if (state.is(this)) {
            int i = state.getValue(LAYERS);
            return state.setValue(LAYERS, Integer.valueOf(Math.min(8, i + 1)));
        } else {
            return super.getStateForPlacement(context);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        ItemStack item = player.getItemInHand(hand);
        if (item.is(Items.BUCKET)) {
            if (player.getAbilities().instabuild) {
                item.shrink(1);
            }
            ItemStack milkBucket = new ItemStack(Items.MILK_BUCKET);
            player.setItemInHand(hand, milkBucket);
            level.removeBlock(pos, true);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LAYERS);
    }

}
