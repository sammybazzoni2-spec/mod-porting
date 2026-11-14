package com.valentin4311.candycraftmod;

import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFish extends EntityWaterMob {
   float current = 0.0F;
   private BlockPos currentFlightTarget;

   public EntityFish(World world) {
      super(world);
      this.setSize(0.95F, 0.95F);
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(2.0D);
   }

   @Override
   public void travel(float strafe, float vertical, float forward) {
      if (this.isInWater()) {
         this.motionY -= 0.002D;
      }
      super.travel(strafe, vertical, forward);
   }

   @Override
   public boolean getCanSpawnHere() {
      return this.posY > 45.0D && this.posY < 63.0D && super.getCanSpawnHere();
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return null;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource source) {
      return null;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return null;
   }

   @Override
   protected float getSoundVolume() {
      return 0.4F;
   }

   @Override
   protected ResourceLocation getLootTable() {
      return null;
   }

   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      this.entityDropItem(new ItemStack(CandyCraft.CranberryFish, 1, 0), 0.0F);
      int scales = this.rand.nextInt(3);
      for (int i = 0; i < scales; ++i) {
         this.entityDropItem(new ItemStack(CandyCraft.CranberryScale, 1, 0), 0.0F);
      }
      if (this.rand.nextInt(50) == 0) {
         this.entityDropItem(new ItemStack(CandyCraft.CranberryEmblem, 1, 0), 0.0F);
      }
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      this.current += 0.2F;
   }

   @Override
   protected boolean canTriggerWalking() {
      return false;
   }

   @Override
   protected void updateAITasks() {
      super.updateAITasks();
      if (this.isInWater()) {
         if (this.currentFlightTarget != null && (this.world.getBlockState(this.currentFlightTarget).getMaterial() != Material.WATER || this.currentFlightTarget.getY() < 1)) {
            this.currentFlightTarget = null;
         }
         if (this.currentFlightTarget == null || this.rand.nextInt(100) == 0 || this.currentFlightTarget.distanceSq((double)(int)this.posX, (double)(int)this.posY, (double)(int)this.posZ) < 4.0D) {
            this.currentFlightTarget = new BlockPos((int)this.posX + this.rand.nextInt(14) - this.rand.nextInt(14), (int)this.posY + this.rand.nextInt(3) - 1, (int)this.posZ + this.rand.nextInt(14) - this.rand.nextInt(14));
         }
         double d0 = (double)this.currentFlightTarget.getX() + 0.5D - this.posX;
         double d1 = (double)this.currentFlightTarget.getY() + 0.1D - this.posY;
         double d2 = (double)this.currentFlightTarget.getZ() + 0.5D - this.posZ;
         this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.1D;
         this.motionY += (Math.signum(d1) * 0.7D - this.motionY) * 0.1D;
         this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.1D;
         float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
         float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
         this.moveForward = 0.5F;
         this.rotationYaw += f1;
      }
   }
}
