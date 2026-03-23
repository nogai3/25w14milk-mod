package com.lighsync.worldofmilk.registries;

import com.lighsync.worldofmilk.Worldofmilk;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PotionRegistry {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Worldofmilk.MODID);

    public static final RegistryObject<Potion> S45_ZERO_BUTTERISH_POTION = POTIONS.register(
            "butterish_potion", () -> new Potion(
                    new MobEffectInstance(
                            EffectRegistry.BUTTERISH.get(),
                            900,
                            0
                    )
            )
    );

    public static final RegistryObject<Potion> M130_ZERO_BUTTERISH_POTION = POTIONS.register(
        "long_butterish_potion", () -> new Potion(
                new MobEffectInstance(
                        EffectRegistry.BUTTERISH.get(),
                        1800,
                        0
                )
            )
    );

    public static final RegistryObject<Potion> S21_ONE_BUTTERISH_POTION = POTIONS.register(
            "strong_butterish_potion", () -> new Potion(
                    new MobEffectInstance(
                            EffectRegistry.BUTTERISH.get(),
                            420,
                            1
                    )
            )
    );
}
