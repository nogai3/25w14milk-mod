package com.lighsync.worldofmilk.registries;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.blocks.entity.FridgeBlockEntity;
import com.lighsync.worldofmilk.blocks.entity.DenseMilkHangingSignBlockEntity;
import com.lighsync.worldofmilk.blocks.entity.DenseMilkSignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCKS_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Worldofmilk.MODID);

    public static final RegistryObject<BlockEntityType<FridgeBlockEntity>> FRIDGE_BLOCK = BLOCKS_ENTITIES.register("fridge_block",
            () -> BlockEntityType.Builder.of(
                    FridgeBlockEntity::new,
                    BlockRegistry.FRIDGE_BLOCK.get())
                    .build(null)
    );

    public static final RegistryObject<BlockEntityType<DenseMilkSignBlockEntity>> DENSE_MILK_SIGN = BLOCKS_ENTITIES.register("dense_milk_sign",
            () -> BlockEntityType.Builder.of(
                    DenseMilkSignBlockEntity::new,
                    BlockRegistry.DENSE_MILK_SIGN.get(), BlockRegistry.DENSE_MILK_WALL_SIGN.get())
                .build(null)
    );

    public static final RegistryObject<BlockEntityType<DenseMilkHangingSignBlockEntity>> DENSE_MILK_HANGING_SIGN = BLOCKS_ENTITIES.register("dense_milk_hanging_sign",
            () -> BlockEntityType.Builder.of(
                    DenseMilkHangingSignBlockEntity::new,
                    BlockRegistry.DENSE_MILK_HANGING_SIGN.get(), BlockRegistry.DENSE_MILK_WALL_HANGING_SIGN.get())
                .build(null)
    );
}