package com.lighsync.worldofmilk.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.lighsync.worldofmilk.registries.EffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class ButterSwordItem extends SwordItem {
    private static final int DEFAULT_ATTACK_DAMAGE = 3;
    private static final float DEFAULT_ATTACK_SPEED = -2.4F;
    public static final float SMASH_ATTACK_THRESHOLD = 6.0F;

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ButterSwordItem(Tier tier, Properties properties) {
        super(tier, DEFAULT_ATTACK_DAMAGE, DEFAULT_ATTACK_SPEED, properties);

        ImmutableMultimap.Builder<Attribute, AttributeModifier> b = ImmutableMultimap.builder();
        b.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", DEFAULT_ATTACK_DAMAGE + tier.getAttackDamageBonus(), AttributeModifier.Operation.ADDITION));
        b.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", DEFAULT_ATTACK_SPEED, AttributeModifier.Operation.ADDITION));

        this.defaultModifiers = b.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player) {
            if (!canSmashAttack(player)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0));
                player.addEffect(new MobEffectInstance(EffectRegistry.BUTTERISH.get(), 200, 0));
            }

            int food = player.getFoodData().getFoodLevel();
            int loss = Math.max(1, food / 10);
            player.getFoodData().setFoodLevel(Math.max(0, food - loss));
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0));
            target.addEffect(new MobEffectInstance(EffectRegistry.BUTTERISH.get(), 200, 0));
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    private boolean canSmashAttack(Player player) {
        return player.getFoodData().getFoodLevel() > (int) SMASH_ATTACK_THRESHOLD;
    }
}