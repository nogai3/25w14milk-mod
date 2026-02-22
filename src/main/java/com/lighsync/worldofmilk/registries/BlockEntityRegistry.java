package com.lighsync.worldofmilk.registries;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.blocks.entity.FridgeBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCKS_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Worldofmilk.MODID);

    public static final RegistryObject<BlockEntityType<?>, BlockEntityType<FridgeBlockEntity>> FRIDGE_BLOCK = BLOCKS_ENTITIES.register("fridge_block", () -> BlockEntityType.Builder.of(FridgeBlockEntity::new, BlockRegistry.FRIDGE_BLOCK.get()).build(null));
}
