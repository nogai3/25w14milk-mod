package com.lighsync.worldofmilk;

import com.lighsync.worldofmilk.registries.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Worldofmilk.MODID)
public class Worldofmilk {
    public static final String MODID = "worldofmilk";
    private static final Logger LOGGER = LogUtils.getLogger();


    public Worldofmilk() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        BlockRegistry.BLOCKS.register(modEventBus);
        BlockEntityRegistry.BLOCKS_ENTITIES.register(modEventBus);
        EntityRegistry.ENTITIES.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        EffectRegistry.EFFECTS.register(modEventBus);
        PotionRegistry.POTIONS.register(modEventBus);
        SoundRegistry.SOUND_EVENTS.register(modEventBus);
        PaintingRegistry.PAINTING_VARIANTS.register(modEventBus);
        CatVariantRegistry.CAT_VARIANTS.register(modEventBus);
        RecipeRegistry.SERIALIZERS.register(modEventBus);
        RecipeRegistry.TYPES.register(modEventBus);
        MenuRegistry.MENUS.register(modEventBus);
        TabRegistry.CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}
    public static Logger getLogger() {
        return LOGGER;
    }
}