package com.lighsync.worldofmilk.client.gui.screen;

import com.lighsync.worldofmilk.Worldofmilk;
import com.lighsync.worldofmilk.registries.SoundRegistry;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class EtheriaReferenceScreen extends Screen {
    private int ticksCount = 0;

    public EtheriaReferenceScreen() {
        super(Component.literal("Nothing..."));
    }

    @Override
    protected void init() {
        super.init();

        if (this.minecraft != null && this.minecraft.player != null) {
            this.minecraft.player.playSound(
                    SoundRegistry.ETHERIA_SIREN.get(),
                    1.0f, 1.0f
            );
        }
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTicks) {
        gui.blit(
                ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "textures/gui/container/reference.png"),
                0, 0,
                0.0f, 0.0f,
                this.width, this.height,
                this.width, this.height
        );
    }

    @Override
    public void tick() {
        super.tick();
        ticksCount++;

        if (ticksCount >= 200) {
            if (this.minecraft != null) {
                this.minecraft.setScreen(null);
                this.minecraft.getSoundManager().stop();
            }
        }
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}