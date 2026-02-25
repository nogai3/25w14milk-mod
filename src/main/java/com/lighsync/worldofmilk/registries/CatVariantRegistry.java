package com.lighsync.worldofmilk.registries;

import com.lighsync.worldofmilk.Worldofmilk;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CatVariantRegistry {
    public static final DeferredRegister<CatVariant> CAT_VARIANTS = DeferredRegister.create(Registries.CAT_VARIANT, Worldofmilk.MODID);

    public static final RegistryObject<CatVariant> FILYA = CAT_VARIANTS.register("filya", () -> new CatVariant(ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "textures/entity/cat/filya.png")));
    public static final RegistryObject<CatVariant> GRAY = CAT_VARIANTS.register("gray", () -> new CatVariant(ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "textures/entity/cat/gray.png")));
    public static final RegistryObject<CatVariant> BALD = CAT_VARIANTS.register("bald", () -> new CatVariant(ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "textures/entity/cat/bald.png")));
}