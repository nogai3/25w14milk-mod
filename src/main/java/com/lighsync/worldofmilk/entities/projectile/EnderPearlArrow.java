package com.lighsync.worldofmilk.entities.projectile;

import com.lighsync.worldofmilk.registries.EntityRegistry;
import com.lighsync.worldofmilk.registries.ItemRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EnderPearlArrow extends AbstractArrow {
    private int duration = 200;

    public EnderPearlArrow(EntityType<? extends EnderPearlArrow> entityType, Level level) {
        super(entityType, level);
    }

    public EnderPearlArrow(Level level, LivingEntity shooter, ItemStack item, ItemStack item2) {
        super(EntityRegistry.ENDER_PEARL_ARROW.get(), shooter, level);
    }

    public EnderPearlArrow(Level level, double x, double y, double z) {
        super(EntityRegistry.ENDER_PEARL_ARROW.get(), x, y, z, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity owner = this.getOwner();
        hitResult.getEntity().hurt(this.damageSources().thrown(this, owner), 0.0F);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        for (int i = 0; i < 32; i++) {
            this.level().addParticle(
                    ParticleTypes.PORTAL,
                    this.getX(),
                    this.getY() + this.random.nextDouble() * 2.0,
                    this.getZ(),
                    this.random.nextGaussian(),
                    0.0,
                    this.random.nextGaussian()
            );
        }

        if (!(this.level() instanceof ServerLevel serverLevel) || this.isRemoved()) {
            return;
        }

        Entity owner = this.getOwner();
        if (owner == null || !isAllowedToTeleportOwner(owner, serverLevel)) {
            this.discard();
            return;
        }

        Vec3 tpPos = this.position();

        if (owner instanceof ServerPlayer player) {
            if (!player.connection.isAcceptingMessages()) {
                this.discard();
                return;
            }

            if (this.random.nextFloat() < 0.05F && serverLevel.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
                Endermite endermite = EntityType.ENDERMITE.create(serverLevel);
                if (endermite != null) {
                    endermite.moveTo(owner.getX(), owner.getY(), owner.getZ(), owner.getYRot(), owner.getXRot());
                    serverLevel.addFreshEntity(endermite);
                }
            }

            if (this.isOnPortalCooldown()) {
                owner.setPortalCooldown();
            }

            player.teleportTo(serverLevel, tpPos.x, tpPos.y, tpPos.z, player.getYRot(), player.getXRot());
            player.fallDistance = 0.0F;

            player.hurt(player.damageSources().magic(), 5);

            playSound(serverLevel, tpPos);
            this.discard();
            return;
        }

        if (this.isOnPortalCooldown()) {
            owner.setPortalCooldown();
        }

        owner.teleportTo(tpPos.x, tpPos.y, tpPos.z);
        owner.fallDistance = 0.0F;
        playSound(serverLevel, tpPos);
        this.discard();
    }

    private static boolean isAllowedToTeleportOwner(Entity entity, Level level) {
        if (entity.level().dimension() == level.dimension()) {
            if (entity instanceof LivingEntity living) {
                return living.isAlive() && !living.isSleeping();
            }
            return entity.isAlive();
        } else {
            return entity.canChangeDimensions();
        }
    }

    @Override
    public void tick() {
        Entity owner = this.getOwner();
        if (owner instanceof ServerPlayer player && !owner.isAlive()) {
            this.discard();
            return;
        }
        super.tick();
    }

    private void playSound(ServerLevel level, Vec3 pos) {
        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    /*@Override
    protected void onInsideBlock(BlockState state) {
        super.onInsideBlock(state);
        if (state.is(Blocks.END_GATEWAY) && this.getOwner() instanceof ServerPlayer player) {
            player.onInsideBlock(state)
        }
    }*/

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, this.duration, 0));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Duration", this.duration);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.duration = tag.contains("Duration") ? tag.getInt("Duration") : 200;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemRegistry.ENDER_PEARL_ARROW.get());
    }
}