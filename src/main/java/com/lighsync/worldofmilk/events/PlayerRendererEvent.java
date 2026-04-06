package com.lighsync.worldofmilk.events;

import com.lighsync.worldofmilk.registries.CapabilityRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class PlayerRendererEvent {
    private static float rotation = 0;
    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
        Player player = event.getEntity();
        PoseStack pose = event.getPoseStack();

        if (CapabilityRegistry.getCap(player).isPlayerRotated()) {
            pose.translate(0, player.getBbHeight() + 0.1F, 0);
            pose.mulPose(Axis.ZP.rotationDegrees(180));
        }
        if (CapabilityRegistry.getCap(player).isPlayerSuperRotated()) {
            rotation += 5F;
            if (rotation >= 360) rotation = 0;

            // pose.translate(0, player.getBbHeight() + 0.1F, 0);
            // pose.mulPose(Axis.YP.rotationDegrees(rotation));
            pose.mulPose(Axis.XP.rotationDegrees(rotation));
            pose.mulPose(Axis.ZP.rotationDegrees(rotation));
        }
    }
}