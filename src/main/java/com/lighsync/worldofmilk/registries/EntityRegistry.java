package com.lighsync.worldofmilk.registries;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.entities.monster.MilkZombie;
import com.lighsync.worldofmilk.entities.projectile.EnderPearlArrow;
import com.lighsync.worldofmilk.entities.projectile.TNTArrow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Worldofmilk.MODID);

    public static final RegistryObject<EntityType<EnderPearlArrow>> ENDER_PEARL_ARROW = ENTITIES.register("ender_pearl_arrow", () ->
            EntityType.Builder.<EnderPearlArrow>of(EnderPearlArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "ender_pearl_arrow").toString())
    );

    public static final RegistryObject<EntityType<TNTArrow>> TNT_ARROW = ENTITIES.register("tnt_arrow", () ->
            EntityType.Builder.<TNTArrow>of(TNTArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "tnt_arrow").toString())
    );

    public static final RegistryObject<EntityType<MilkZombie>> MILK_ZOMBIE = ENTITIES.register("milk_zombie", () ->
            EntityType.Builder.<MilkZombie>of(MilkZombie::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .clientTrackingRange(25)
                    .build(ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "milk_zombie").toString())
    );
}
