package com.lighsync.worldofmilk.entities.monster;

import com.lighsync.worldofmilk.registries.BlockRegistry;
import com.lighsync.worldofmilk.registries.EntityRegistry;
import com.lighsync.worldofmilk.registries.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RemoveBlockGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;

public class MilkZombie extends Zombie {
    public MilkZombie(EntityType<? extends MilkZombie> type, Level level) {
        super(type, level);
    }

    public MilkZombie(Level level) {
        this(EntityRegistry.MILK_ZOMBIE.get(), level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(4, new MilkZombieTurtleEggGoal(this, 1.0D, 3));

        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers(ZombifiedPiglin.class));
    }

    @Override
    public void aiStep() {
        if (this.isAlive()) {
            BlockState state = BlockRegistry.MILK_LAYER_BLOCK.get().defaultBlockState();

            for (int i = 0; i < 4; i++) {
                int x = Mth.floor(this.getX() + (double)((float)(i % 2 * 2 -1) * 0.25F));
                int y = Mth.floor(this.getY());
                int z = Mth.floor(this.getZ(0 + (double)((float)(i / 2 * 2 - 1) * 0.25F)));
                BlockPos pos = new BlockPos(x, y, z);

                if (this.level().getBlockState(pos).isAir() && state.canSurvive(this.level(), pos)) {
                    this.level().setBlockAndUpdate(pos, state);
                    this.level().gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(this, state));
                }
            }
        }
        super.aiStep();
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean ok = super.doHurtTarget(target);
        if (ok && this.getMainHandItem().isEmpty() && target instanceof LivingEntity living) {
            float difficulty = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            living.removeAllEffects();
        }
        return ok;
    }

    @Override protected SoundEvent getAmbientSound() { return SoundEvents.COW_AMBIENT; }
    @Override protected SoundEvent getHurtSound(DamageSource src) { return SoundEvents.COW_HURT; }
    @Override protected SoundEvent getDeathSound() { return SoundEvents.COW_DEATH; }
    @Override protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(random, difficulty);

        if (random.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
            int i = random.nextInt(3);
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(i == 0 ? ItemRegistry.BREAD_SWORD.get() : ItemRegistry.BREAD_SHOVEL.get()));
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack item = player.getItemInHand(hand);
        if (item.is(Items.BUCKET) && !this.isBaby()) {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack item1 = ItemUtils.createFilledResult(item, player, Items.MILK_BUCKET.getDefaultInstance());
            player.setItemInHand(hand, item1);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    class MilkZombieTurtleEggGoal extends RemoveBlockGoal {
        MilkZombieTurtleEggGoal(final PathfinderMob mob, final double speed, final int maxYDiff) {
            super(Blocks.TURTLE_EGG, mob, speed, maxYDiff);
        }

        @Override
        public void playDestroyProgressSound(LevelAccessor level, BlockPos pos) {
            level.playSound(null, pos, SoundEvents.ZOMBIE_DESTROY_EGG, SoundSource.HOSTILE, 0.5F, 0.9F + MilkZombie.this.random.nextFloat() * 0.2F);
        }

        @Override
        public void playBreakSound(Level level, BlockPos pos) {
            level.playSound(null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
        }

        @Override
        public double acceptedDistance() {
            return 1.14D;
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance diff, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        return super.finalizeSpawn(level, diff, spawnType, data, tag);
    }
}
