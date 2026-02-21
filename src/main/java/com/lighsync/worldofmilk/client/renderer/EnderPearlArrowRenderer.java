package com.lighsync.worldofmilk.client.renderer;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.entities.projectile.EnderPearlArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class EnderPearlArrowRenderer extends ArrowRenderer<EnderPearlArrow> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "textures/entity/projectile/ender_pearl_arrow.png");

    public EnderPearlArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(EnderPearlArrow entity) {
        return TEXTURE;
    }
}
