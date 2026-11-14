package com.valentin4311.candycraftmod;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityKingBeetle extends EntityGolem implements IEntityPowerMount {
   private static final DataParameter<Integer> POWER = EntityDataManager.<Integer>createKey(EntityKingBeetle.class, DataSerializers.VARINT);
   public int explosionCount = 0;

   public EntityKingBeetle(World world) {
      super(world);
      this.setSize(3.0F, 2.0F);
   }

   @Override
   protected void initEntityAI() {
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAITempt(this, 0.5D, Item.getItemFromBlock(CandyCraft.GoldenFlower), false));
      this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.5D));
      this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(4, new EntityAILookIdle(this));
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      if (super.processInteract(player, hand)) {
         return true;
      } else if (!this.world.isRemote && !this.isBeingRidden()) {
         player.startRiding(this);
         this.explosionCount = 0;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int powerUsed() {
      return 1200;
   }

   @Override
   public int maxPower() {
      return 1200;
   }

   @Override
   public void unleashPower() {
      this.setPower(0);
      this.explosionCount = 8 + this.rand.nextInt(8);
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      Entity rider = this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
      if (!this.world.isRemote && this.explosionCount > 0 && this.ticksExisted % 5 == 0 && rider != null) {
         double d1 = this.posX + (double)this.rand.nextInt(6) - 3.0D;
         double d2 = this.posY + (double)this.rand.nextInt(5) - 2.0D;
         double d3 = this.posZ + (double)this.rand.nextInt(6) - 3.0D;
         this.world.createExplosion(rider, d1, d2, d3, 2.0F, false);

         for (int x = -1; x < 2; ++x) {
            for (int z = -1; z < 2; ++z) {
               BlockPos p = new BlockPos((int)d1 + x, (int)d2, (int)d3 + z);
               IBlockState state = this.world.getBlockState(p);
               if (this.rand.nextBoolean() && ((BlockChewingGumPlate)CandyCraft.ChewingGumPlate).canBlockStay(this.world, p) && state.getMaterial() != Material.WATER && state.getBlock().isReplaceable(this.world, p)) {
                  this.world.setBlockState(p, CandyCraft.ChewingGumPlate.getDefaultState(), 3);
               }
            }
         }

         --this.explosionCount;
      }

      if (!this.world.isRemote && this.getPower() < this.maxPower()) {
         this.setPower(this.getPower() + 1);
      }

      if (!this.inWater && this.isJumping && rider != null) {
         this.isJumping = false;
         this.motionY *= 4.0D;
      }

      if (!this.onGround) {
         this.motionX /= 1.5D;
         this.motionZ /= 1.5D;
      }

      if (!this.world.isRemote && rider instanceof EntityLivingBase) {
         EntityLivingBase living = (EntityLivingBase)rider;
         this.rotationYaw = living.rotationYawHead;
         this.prevRotationYaw = living.rotationYawHead;
         living.moveStrafing = 0.0F;
         float f = rider.rotationYaw + -living.moveStrafing * 90.0F;
         this.motionX += -Math.sin((double)(f * (float)Math.PI / 180.0F)) * 4.0D * (double)living.moveForward * 0.05D;
         this.motionZ += Math.cos((double)(f * (float)Math.PI / 180.0F)) * 4.0D * (double)living.moveForward * 0.05D;
         if ((double)living.moveForward < 0.98D) {
            this.motionX = 0.0D;
            this.motionZ = 0.0D;
            this.moveForward = 0.0F;
            this.moveStrafing = 0.0F;
            this.getNavigator().clearPath();
         }
      }
   }

   @Override
   public void setPower(int power) {
      this.dataManager.set(POWER, power);
   }

   @Override
   public int getPower() {
      return this.dataManager.get(POWER);
   }

   @Override
   public boolean canRiderInteract() {
      return false;
   }

   @Override
   public double getMountedYOffset() {
      return (double)this.height + 0.25D;
   }

   @Override
   protected boolean canDespawn() {
      return false;
   }

   @Override
   public void fall(float distance, float damageMultiplier) {
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (!this.world.isRemote && source.isExplosion()) {
         return false;
      } else {
         Entity attacker = source.getTrueSource();
         return attacker != null && this.getPassengers().contains(attacker) ? false : super.attackEntityFrom(source, amount);
      }
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(POWER, 0);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("Power", this.getPower());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setPower(compound.getInteger("Power"));
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
}
