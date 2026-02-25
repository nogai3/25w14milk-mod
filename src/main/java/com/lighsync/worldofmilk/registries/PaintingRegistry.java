package com.lighsync.worldofmilk.registries;

import com.lighsync.worldofmilk.Worldofmilk;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PaintingRegistry {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(Registries.PAINTING_VARIANT, Worldofmilk.MODID);

    public static final RegistryObject<PaintingVariant> GLACKUS = PAINTING_VARIANTS.register("glackus", () -> new PaintingVariant(64, 64));
    public static final RegistryObject<PaintingVariant> WANDARMO = PAINTING_VARIANTS.register("wandarmo", () -> new PaintingVariant(64, 64));
    public static final RegistryObject<PaintingVariant> KAPITANKASTET = PAINTING_VARIANTS.register("kapitankastet", () -> new PaintingVariant(64, 64));
    public static final RegistryObject<PaintingVariant> PLYTONI = PAINTING_VARIANTS.register("plytoni", () -> new PaintingVariant(64, 64));

    public static final RegistryObject<PaintingVariant> FILYA_CAT = PAINTING_VARIANTS.register("filya_cat", () -> new PaintingVariant(64, 64));
    public static final RegistryObject<PaintingVariant> GRAY_CAT = PAINTING_VARIANTS.register("gray_cat", () -> new PaintingVariant(64, 64));
    public static final RegistryObject<PaintingVariant> BALD_CAT = PAINTING_VARIANTS.register("bald_cat", () -> new PaintingVariant(64, 64));
    public static final RegistryObject<PaintingVariant> MARY_DOG = PAINTING_VARIANTS.register("mary_dog", () -> new PaintingVariant(64, 64));
}