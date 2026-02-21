package com.lighsync.worldofmilk.entities.projectile;

import com.lighsync.worldofmilk.registries.EntityRegistry;
import com.lighsync.worldofmilk.registries.ItemRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class TNTArrow extends AbstractArrow {
    private short fuse;

    public TNTArrow(EntityType<? extends TNTArrow> entityType, Level level) {
        super(entityType, level);
    }

    public TNTArrow(Level level, LivingEntity shooter, ItemStack item, ItemStack item2) {
        super(EntityRegistry.TNT_ARROW.get(), shooter, level);
    }

    public TNTArrow(Level level, double x, double y, double z) {
        super(EntityRegistry.TNT_ARROW.get(), x, y, z, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHit(hitResult);
        PrimedTnt tnt = new PrimedTnt(EntityType.TNT, this.level());
        tnt.setPos(this.getX(), this.getY(), this.getZ());
        tnt.setFuse(this.fuse);
        this.level().addFreshEntity(tnt);

        this.discard();
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putShort("Fuse", this.fuse);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.fuse = tag.contains("Fuse") ? tag.getShort("Fuse") : 30;
    }

    public short getFuse() { return this.fuse; }
    public void setFuse(short fuse) { this.fuse = fuse; }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemRegistry.TNT_ARROW.get());
    }
}
