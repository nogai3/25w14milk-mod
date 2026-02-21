package com.lighsync.worldofmilk.client.renderer;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.entities.projectile.TNTArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class TNTArrowRenderer extends ArrowRenderer<TNTArrow> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "textures/entity/projectile/tnt_arrow.png");

    public TNTArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(TNTArrow entity) {
        return TEXTURE;
    }
}
