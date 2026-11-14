package com.valentin4311.candycraftmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityBee extends EntityMob {
   private static final DataParameter<Byte> HANGED = EntityDataManager.<Byte>createKey(EntityBee.class, DataSerializers.BYTE);
   public float wings = 0.0F;
   private BlockPos currentFlightTarget;
   boolean isAngry = true;
   public int attackTick = 0;

   public EntityBee(World world) {
      super(world);
      this.isAngry = true;
      this.setSize(0.8F, 1.0F);
   }

   public EntityBee(World world, boolean hanged) {
      this(world);
      this.setHanged(hanged);
   }

   public EntityBee(World world, boolean hanged, boolean isAngry) {
      this(world);
      this.setHanged(hanged);
      this.isAngry = isAngry;
   }

   @Override
   protected void initEntityAI() {
      this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
      this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
   }

   @Override
   public double getMountedYOffset() {
      return (double)this.height * 0.75D - 0.5D;
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(HANGED, (byte)0);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("Hanged", this.getHanged());
      compound.setBoolean("Angry", this.isAngry);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setHanged(compound.getBoolean("Hanged"));
      this.isAngry = compound.getBoolean("Angry");
   }

   @Override
   protected boolean isValidLightLevel() {
      return true;
   }

   @Override
   public float getBlockPathWeight(BlockPos pos) {
      return 0.0F;
   }

   @Override
   public boolean getCanSpawnHere() {
      return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.getCanSpawnHere();
   }

   public boolean getHanged() {
      return (this.dataManager.get(HANGED) & 1) != 0;
   }

   @Override
   protected void collideWithEntity(Entity entity) {
      this.setHanged(false);
   }

   public void setHanged(boolean hanged) {
      this.dataManager.set(HANGED, (byte)(hanged ? 1 : 0));
   }

   @Override
   public void fall(float distance, float damageMultiplier) {
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.getHanged()) {
         this.motionX = this.motionY = this.motionZ = 0.0D;
         this.posY = (double)MathHelper.floor(this.posY) + 1.0D - (double)this.height;
      } else {
         this.motionY *= 0.6D;
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else {
         this.setHanged(false);
         this.isAngry = true;
         return super.attackEntityFrom(source, amount);
      }
   }

   @Override
   protected void updateAITasks() {
      super.updateAITasks();
      EntityPlayer player = this.world.getNearestAttackablePlayer(this, 8.0D, 8.0D);
      this.attackTick = Math.max(this.attackTick - 1, 0);
      if (player != null) {
         this.setHanged(false);
         double reach = (double)(this.width * 2.0F * this.width * 2.0F + player.width);
         if (this.getDistanceSq(player.posX, player.getEntityBoundingBox().minY, player.posZ) <= reach && this.attackTick <= 0) {
            this.attackTick = 20;
            if (!this.getHeldItemMainhand().isEmpty()) {
               this.swingArm(EnumHand.MAIN_HAND);
            }

            this.attackEntityAsMob(player);
            if (this.rand.nextInt(15) == 0) {
               player.addPotionEffect(new PotionEffect(MobEffects.POISON, 400, 0));
            }
         }
      }

      if (!this.getHanged()) {
         if (this.currentFlightTarget != null && (!this.world.isAirBlock(this.currentFlightTarget) || this.currentFlightTarget.getY() < 1)) {
            this.currentFlightTarget = null;
         }

         if (this.currentFlightTarget == null || this.rand.nextInt(100) == 0 || this.currentFlightTarget.distanceSq((double)((int)this.posX), (double)((int)this.posY), (double)((int)this.posZ)) < 4.0D) {
            this.currentFlightTarget = new BlockPos((int)this.posX + this.rand.nextInt(14) - this.rand.nextInt(14), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(14) - this.rand.nextInt(14));
         }

         double d0 = (double)this.currentFlightTarget.getX() + 0.5D - this.posX;
         double d1 = (double)this.currentFlightTarget.getY() + 0.1D - this.posY;
         double d2 = (double)this.currentFlightTarget.getZ() + 0.5D - this.posZ;
         if (!this.isAngry) {
            this.setAttackTarget(null);
         }

         if (this.isAngry && player != null) {
            if (this.getAttackTarget() == null) {
               this.setAttackTarget(player);
            }

            d0 = player.posX - this.posX;
            d1 = player.posY + 1.1D - this.posY;
            d2 = player.posZ - this.posZ;
            this.currentFlightTarget = new BlockPos((int)player.posX, (int)player.posY, (int)player.posZ);
         }

         this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.1D;
         this.motionY += (Math.signum(d1) * 0.7D - this.motionY) * 0.1D;
         this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.1D;
         float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
         float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
         this.moveForward = 0.5F;
         this.rotationYaw += f1;
      }
   }

   @Override
   protected boolean canTriggerWalking() {
      return false;
   }

   @Override
   public boolean isOnLadder() {
      return false;
   }

   public int getAttackStrength(Entity entity) {
      return 3;
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
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(2.0D);
   }

   @Override
   public void onLivingUpdate() {
      if (!this.getHanged()) {
         ++this.wings;
      } else {
         this.wings = 1.8F;
      }

      super.onLivingUpdate();
   }

   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      CandyEntityRegistry.dropDefault(this, CandyCraft.HoneyShard, lootingModifier);
   }
}
