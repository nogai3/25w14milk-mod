package com.lighsync.worldofmilk.effects;

import com.lighsync.worldofmilk.effects.utils.ColorGen;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;

public class ButterishEffect extends MobEffect {
    public ButterishEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorGen.toIntFormat(245, 215, 66, 1));
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Vec3 motion = entity.getDeltaMovement();
        double factor = 0.9 - (amplifier * 0.05);
        entity.setDeltaMovement(
                motion.x * factor,
                motion.y,
                motion.z * factor
        );
        entity.setSpeed((float) factor);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}