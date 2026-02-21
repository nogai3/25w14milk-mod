package com.lighsync.worldofmilk.registries;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.blocks.JebBlock;
import com.lighsync.worldofmilk.blocks.MilkLayerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Worldofmilk.MODID);
    public static final BlockSetType MILK_SET_TYPE = BlockSetType.register(new BlockSetType("milk", true, SoundType.COPPER, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));
    public static final WoodType MILK_WOOD_TYPE = WoodType.register(new WoodType("milk", MILK_SET_TYPE));

    public static final RegistryObject<Block> MILK_BLOCK = BLOCKS.register("milk_block",
            () -> new FallingBlock(
                    BlockBehaviour.Properties.of()
                            .instrument(NoteBlockInstrument.COW_BELL)
                            .strength(2.0f, 3.0f)
                            .sound(SoundType.GRASS)
                            .ignitedByLava()
    ));

    public static final RegistryObject<Block> MILK_LAYER_BLOCK = BLOCKS.register("milk_layer_block",
            () -> new MilkLayerBlock(
                    BlockBehaviour.Properties.of()
                            .replaceable()
                            .forceSolidOff()
                            .randomTicks()
                            .strength(0.1f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.SNOW)
                            .isViewBlocking((state, level, pos) -> state.getValue(MilkLayerBlock.LAYERS) >= 8)
                            .pushReaction(PushReaction.DESTROY)
    ));

    public static final RegistryObject<Block> DENSE_MILK_BLOCK = BLOCKS.register("dense_milk_block",
                () -> new Block(
                        BlockBehaviour.Properties.of()
                                .instrument(NoteBlockInstrument.BELL)
                                .strength(2.5f, 3.0f)
                                .sound(SoundType.COPPER)
    ));

    public static final RegistryObject<Block> DENSE_MILK_DOOR = BLOCKS.register("dense_milk_door",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .instrument(NoteBlockInstrument.BELL)
                            .strength(2.5f)
                            .noOcclusion()
                            .pushReaction(PushReaction.DESTROY),
                    MILK_SET_TYPE
    ));

    public static final RegistryObject<Block> DENSE_MILK_TRAPDOOR = BLOCKS.register("dense_milk_trapdoor",
            () -> new TrapDoorBlock(
                    BlockBehaviour.Properties.of()
                            .instrument(NoteBlockInstrument.BELL)
                            .strength(2.5f)
                            .noOcclusion(),
                    MILK_SET_TYPE
    ));

     /*public static final RegistryObject<Block> DENSE_MILK_SIGN = BLOCKS.register("dense_milk_sign",
            () -> new SignBlock(BlockBehaviour.Properties.of()
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BELL)
                    .noCollission()
                    .strength(1.5f), MILK_WOOD_TYPE));*/

    public static final RegistryObject<Block> DENSE_MILK_BUTTON = BLOCKS.register("dense_milk_button",
             () -> new ButtonBlock(buttonProperties(), MILK_SET_TYPE, 30, true)
    );

    public static final RegistryObject<Block> DENSE_MILK_SLAB = BLOCKS.register("dense_milk_slab",
            () -> new SlabBlock(
                    BlockBehaviour.Properties.of()
                            .instrument(NoteBlockInstrument.BELL)
                            .strength(2.5F, 3.0F)
                            .sound(SoundType.COPPER)
    ));

    public static final RegistryObject<Block> DENSE_MILK_STAIRS = BLOCKS.register("dense_milk_stairs",
            () -> new StairBlock(
                    () -> BlockRegistry.DENSE_MILK_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(BlockRegistry.DENSE_MILK_BLOCK.get())
    ));

    public static final RegistryObject<Block> DENSE_MILK_FENCE = BLOCKS.register("dense_milk_fence",
            () -> new FenceBlock(
                    BlockBehaviour.Properties.of()
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.BELL)
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.COPPER)
    ));

    public static final RegistryObject<Block> DENSE_MILK_FENCE_GATE = BLOCKS.register("dense_milk_fence_gate",
            () -> new FenceGateBlock(
                BlockBehaviour.Properties.of()
                        .forceSolidOn()
                        .instrument(NoteBlockInstrument.BELL)
                        .strength(2.0F, 3.0F),
                MILK_WOOD_TYPE
    ));

    public static final RegistryObject<Block> DENSE_MILK_PRESSURE_PLATE = BLOCKS.register("dense_milk_pressure_plate",
            () -> new PressurePlateBlock(
                    PressurePlateBlock.Sensitivity.MOBS,
                    BlockBehaviour.Properties.of()
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.BELL)
                            .noCollission()
                            .strength(1.0F)
                            .pushReaction(PushReaction.DESTROY),
                    MILK_SET_TYPE
    ));

    public static final RegistryObject<Block> CARVED_DENSE_MILK_BLOCK = BLOCKS.register("carved_dense_milk_block",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .instrument(NoteBlockInstrument.XYLOPHONE)
                            .strength(2.5f, 5.0f)
                            .sound(SoundType.COPPER)
    ));

    public static final RegistryObject<Block> CARVED_DENSE_MILK_SLAB = BLOCKS.register("carved_dense_milk_slab",
            () -> new SlabBlock(
                    BlockBehaviour.Properties.of()
                            .instrument(NoteBlockInstrument.XYLOPHONE)
                            .strength(2.5f, 5.0f)
                            .sound(SoundType.COPPER)
    ));

    public static final RegistryObject<Block> CARVED_DENSE_MILK_WALL = BLOCKS.register("carved_dense_milk_wall",
            () -> new WallBlock(
                BlockBehaviour.Properties.copy(CARVED_DENSE_MILK_BLOCK.get())
    ));

    public static final RegistryObject<Block> CARVED_DENSE_MILK_STAIRS = BLOCKS.register("carved_dense_milk_stairs",
            () -> new StairBlock(
                    () -> BlockRegistry.CARVED_DENSE_MILK_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(BlockRegistry.CARVED_DENSE_MILK_BLOCK.get())
    ));

    public static final RegistryObject<Block> JEB_BLOCK = BLOCKS.register("jeb_block",
            () -> new JebBlock(
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.STONE)
                            .instrument(NoteBlockInstrument.BASS)
                            .strength(2.5f)
                            .noOcclusion()
                            .ignitedByLava()
                            .pushReaction(PushReaction.DESTROY)
                            .isRedstoneConductor(BlockRegistry::never)
                            .pushReaction(PushReaction.BLOCK)
    ));

    private static BlockBehaviour.Properties buttonProperties() {
        return BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
    }

    private static boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }
}