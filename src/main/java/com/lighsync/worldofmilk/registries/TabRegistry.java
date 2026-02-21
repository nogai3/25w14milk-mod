package com.lighsync.worldofmilk.registries;

import com.lighsync.worldofmilk.Worldofmilk;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Worldofmilk.MODID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("worldofmilk", () -> CreativeModeTab.builder()
            .title(Component.translatable("container.creative.worldofmilk"))
            .icon(() -> ItemRegistry.MILK_BLOCK.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                for (var item : ItemRegistry.ITEMS.getEntries()) {
                    output.accept(item.get());
                }
            }).build()
    );
}