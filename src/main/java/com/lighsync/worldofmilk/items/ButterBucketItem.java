package com.lighsync.worldofmilk.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class ButterBucketItem extends Item {
    private static final int DRINK_DURATION = 40;

    public ButterBucketItem(Item.Properties properties) {
        super(properties);
    }

    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        super.finishUsingItem(stack, level, user);
        if (user instanceof ServerPlayer player) {
            CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
        }

        if (!level.isClientSide()) {
            user.removeAllEffects();
        }

        if (stack.isEmpty()) {
            return new ItemStack(Items.BUCKET);
        } else {
            if (user instanceof Player && !((Player) user).getAbilities().instabuild) {
                ItemStack stack1 =  new ItemStack(Items.BUCKET);
                Player player = (Player) user;
                if (!player.getInventory().add(stack1)) {

                    player.drop(stack1, false);
                }
            }
        }
        return stack;
    }

    public int getUseDuration(ItemStack stack) { return DRINK_DURATION; }
    public UseAnim getUseAnimation(ItemStack stack) { return UseAnim.DRINK; }
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }
    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }
}