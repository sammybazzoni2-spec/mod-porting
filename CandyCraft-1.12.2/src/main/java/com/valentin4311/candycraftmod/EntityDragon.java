package com.valentin4311.candycraftmod;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityDragon extends EntityGolem implements IEntityLockable, IEntityPowerMount {
   private static final DataParameter<Integer> POWER = EntityDataManager.<Integer>createKey(EntityDragon.class, DataSerializers.VARINT);
   private static final DataParameter<Integer> FALLING = EntityDataManager.<Integer>createKey(EntityDragon.class, DataSerializers.VARINT);
   private static final DataParameter<Integer> LOCKED = EntityDataManager.<Integer>createKey(EntityDragon.class, DataSerializers.VARINT);
   private static final DataParameter<Integer> SHOOT = EntityDataManager.<Integer>createKey(EntityDragon.class, DataSerializers.VARINT);

   public EntityDragon(World world) {
      super(world);
      this.setSize(3.0F, 2.2F);
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
   public void setPower(int i) {
      this.dataManager.set(POWER, i);
   }

   @Override
   public int getPower() {
      return this.dataManager.get(POWER);
   }

   public void setShoot(int i) {
      this.dataManager.set(SHOOT, i);
   }

   public int getShoot() {
      return this.dataManager.get(SHOOT);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(POWER, 0);
      this.dataManager.register(FALLING, 0);
      this.dataManager.register(LOCKED, 0);
      this.dataManager.register(SHOOT, 0);
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
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      if (super.processInteract(player, hand)) {
         return true;
      } else if (!this.world.isRemote && !this.isBeingRidden()) {
         player.startRiding(this);
         this.setShoot(0);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int maxPower() {
      return 1500;
   }

   @Override
   public int powerUsed() {
      return 300;
   }

   @Override
   public void unleashPower() {
      this.setPower(this.getPower() - 300);
      this.setShoot(this.getShoot() + 10 + this.rand.nextInt(8));
   }

   private Entity getRider() {
      return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
   }

   private float groundFriction() {
      BlockPos below = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ));
      IBlockState state = this.world.getBlockState(below);
      return state.getBlock().getSlipperiness(state, this.world, below, this) * 0.91F;
   }

   @Override
   public void travel(float strafe, float vertical, float forward) {
      if (this.getRider() != null && !this.isFalling()) {
         if (this.isInWater()) {
            this.moveRelative(strafe, vertical, forward, 0.02F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.8D;
            this.motionY *= 0.8D;
            this.motionZ *= 0.8D;
         } else if (this.isInLava()) {
            this.moveRelative(strafe, vertical, forward, 0.02F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
         } else {
            float f2 = 0.91F;
            if (this.onGround) {
               f2 = this.groundFriction();
            }
            float f3 = 0.16277136F / (f2 * f2 * f2);
            this.moveRelative(strafe, vertical, forward, this.onGround ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;
            if (this.onGround) {
               f2 = this.groundFriction();
            }
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)f2;
            this.motionY *= (double)f2;
            this.motionZ *= (double)f2;
         }
         this.prevLimbSwingAmount = this.limbSwingAmount;
         double d1 = this.posX - this.prevPosX;
         double d0 = this.posZ - this.prevPosZ;
         float f4 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
         if (f4 > 1.0F) {
            f4 = 1.0F;
         }
         this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
         this.limbSwing += this.limbSwingAmount;
      } else {
         super.travel(strafe, vertical, forward);
      }
   }

   @Override
   protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
      if (this.getRider() == null || this.isFalling()) {
         super.updateFallState(y, onGroundIn, state, pos);
      }
   }

   @Override
   public boolean isOnLadder() {
      return false;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      Entity rider = this.getRider();
      if (!this.world.isRemote && this.getPower() < this.maxPower() && !this.isFalling()) {
         this.setPower(this.getPower() + 1);
      }
      if (!this.onGround) {
         this.motionX /= 1.25D;
         this.motionZ /= 1.25D;
         BlockPos below = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY - 0.1D), MathHelper.floor(this.posZ));
         if (!this.world.isAirBlock(below)) {
            IBlockState state = this.world.getBlockState(below);
            if (state.getCollisionBoundingBox(this.world, below) != null) {
               this.onGround = true;
            }
         }
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
         float nextMotionY = 0.0F;
         if (living.rotationPitch < -10.0F || living.rotationPitch > 10.0F) {
            nextMotionY = -living.rotationPitch / 1000.0F;
         }
         if (!this.isFalling() && !this.isLocked()) {
            this.motionY += (double)nextMotionY;
         }
         if (!this.onGround) {
            this.setPower(this.getPower() - 2);
            if (this.getPower() < 0) {
               this.setPower(0);
               this.setFalling(true);
            }
         }
         if (this.isFalling() && this.onGround) {
            this.setFalling(false);
         }
      }
      if (this.getShoot() > 0 && this.ticksExisted % 2 == 0 && rider != null) {
         this.setPositionAndRotation(this.posX, this.posY, this.posZ, rider.rotationYaw, rider.rotationPitch);
         this.setPositionAndRotationDirect(this.posX, this.posY, this.posZ, rider.rotationYaw, rider.rotationPitch, 0, false);
         if (!this.world.isRemote && rider instanceof EntityLivingBase) {
            EntityGummyBall ball = new EntityGummyBall(this.world, (EntityLivingBase)rider, 2);
            double d1 = (double)MathHelper.sin(rider.rotationYaw / 180.0F * (float)Math.PI) * 3.5D;
            double d2 = (double)(-MathHelper.cos(rider.rotationYaw / 180.0F * (float)Math.PI)) * 3.5D;
            ball.posX -= d1;
            ball.posZ -= d2;
            this.motionX = d1 / 3.4D;
            this.motionZ = d2 / 3.4D;
            ball.setPosition(ball.posX, ball.posY, ball.posZ);
            this.world.playSound(null, rider.posX, rider.posY, rider.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));
            this.world.spawnEntity(ball);
            this.setShoot(this.getShoot() - 1);
         }
      }
   }

   @Override
   protected boolean isMovementBlocked() {
      return !this.inWater;
   }

   @Override
   public boolean canRiderInteract() {
      return false;
   }

   @Override
   public double getMountedYOffset() {
      return (double)this.height - 0.9D + Math.sin((double)((float)this.ticksExisted * 0.05F)) / 6.0D;
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
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float amount) {
      Entity attacker = source.getTrueSource();
      return attacker != null && this.getPassengers().contains(attacker) ? false : super.attackEntityFrom(source, amount);
   }

   public void setFalling(boolean i) {
      this.dataManager.set(FALLING, i ? 1 : 0);
   }

   public boolean isFalling() {
      return this.dataManager.get(FALLING) == 1;
   }

   @Override
   public boolean isLocked() {
      return this.dataManager.get(LOCKED) == 1;
   }

   @Override
   public void setLocked(boolean i) {
      this.dataManager.set(LOCKED, i ? 1 : 0);
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
