package com.valentin4311.candycraftmod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityCandyArrow extends EntityArrow {
   private static final DataParameter<Integer> BOLT = EntityDataManager.<Integer>createKey(EntityCandyArrow.class, DataSerializers.VARINT);
   public int slow = 0;
   public int maxTick = 1200;

   public EntityCandyArrow(World world) {
      super(world);
      this.setSize(0.5F, 0.5F);
   }

   public EntityCandyArrow(World world, double x, double y, double z) {
      super(world, x, y, z);
   }

   public EntityCandyArrow(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float inaccuracy) {
      super(world, shooter);
      double d0 = target.posX - shooter.posX;
      double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - this.posY;
      double d2 = target.posZ - shooter.posZ;
      double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
      if (d3 >= 1.0E-7D) {
         this.shoot(d0, d1 + d3 * 0.2D, d2, velocity, inaccuracy);
      }
   }

   public EntityCandyArrow(World world, EntityLivingBase shooter, float power) {
      super(world, shooter);
      this.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0F, power * 1.5F, 1.0F);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(BOLT, 0);
   }

   public void setBolt(boolean bolt) {
      this.dataManager.set(BOLT, bolt ? 1 : 0);
   }

   public boolean isBolt() {
      return this.dataManager.get(BOLT) == 1;
   }

   public void setKnockbackStrengthPublic(int strength) {
      this.setKnockbackStrength(strength);
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.isBolt()) {
         this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
         if (this.inGround && !this.world.isRemote) {
            this.setDead();
         }
      }
      if (!this.world.isRemote && this.ticksExisted > this.maxTick) {
         this.setDead();
      }
   }

   @Override
   protected void arrowHit(EntityLivingBase living) {
      super.arrowHit(living);
      if (this.slow > 0) {
         living.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 300, this.slow));
      }
   }

   @Override
   protected ItemStack getArrowStack() {
      return new ItemStack(CandyCraft.HoneyArrow);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("Slow", this.slow);
      compound.setInteger("MaxTick", this.maxTick);
      compound.setBoolean("Bolt", this.isBolt());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.slow = compound.getInteger("Slow");
      if (compound.hasKey("MaxTick")) {
         this.maxTick = compound.getInteger("MaxTick");
      }
      this.setBolt(compound.getBoolean("Bolt"));
   }
}
