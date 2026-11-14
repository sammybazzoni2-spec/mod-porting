package com.valentin4311.candycraftmod;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityTornadoSlime extends EntityLiving implements IMob {
   private static final DataParameter<Byte> SIZE = EntityDataManager.<Byte>createKey(EntityTornadoSlime.class, DataSerializers.BYTE);
   private int slimeJumpDelay = 0;
   public float squishAmount;
   public float squishFactor;
   public float prevSquishFactor;
   public float count = 0.1F;

   public EntityTornadoSlime(World world) {
      super(world);
      this.isImmuneToFire = true;
      this.slimeJumpDelay = 1;
      this.setSlimeSize(1);
   }

   @Override
   protected void initEntityAI() {
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(SIZE, (byte)1);
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
   }

   protected void setSlimeSize(int size) {
      this.dataManager.set(SIZE, (byte)size);
      this.setSize(0.6F * (float)size, 0.6F * (float)size);
      this.setPosition(this.posX, this.posY, this.posZ);
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
      this.setHealth(this.getMaxHealth());
      this.experienceValue = size;
   }

   public int getSlimeSize() {
      return this.dataManager.get(SIZE);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("Size", this.getSlimeSize() - 1);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setSlimeSize(compound.getInteger("Size") + 1);
   }

   protected SoundEvent getJumpSound() {
      return SoundEvents.ENTITY_SLIME_JUMP;
   }

   @Override
   protected ResourceLocation getLootTable() {
      return null;
   }

   @Override
   public void onUpdate() {
      if (!this.onGround) {
         this.count += 50.0F;
      } else {
         this.count = 0.0F;
      }
      if (!this.world.isRemote && this.world.getDifficulty().getId() == 0 && this.getSlimeSize() > 0) {
         this.isDead = true;
      }
      this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
      this.prevSquishFactor = this.squishFactor;
      boolean wasOnGround = this.onGround;
      super.onUpdate();
      if (this.onGround && !wasOnGround) {
         if (this.makesSoundOnLand()) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
         }
         this.squishAmount = -0.5F;
      } else if (!this.onGround && wasOnGround) {
         this.squishAmount = 1.0F;
      }
      this.alterSquishAmount();
      if (this.world.isRemote) {
         int size = this.getSlimeSize();
         this.setSize(0.6F * (float)size, 0.6F * (float)size);
      }
   }

   @Override
   protected void updateEntityActionState() {
      if (this.isInWater()) {
         this.motionY += 0.12D;
      }
      EntityPlayer player = this.world.getNearestAttackablePlayer(this.posX, this.posY, this.posZ, 16.0D, 16.0D, null, null);
      if (player != null) {
         this.faceEntity(player, 10.0F, 20.0F);
      }
      if (this.onGround || this.isInWater() && this.slimeJumpDelay-- <= 0) {
         this.slimeJumpDelay = this.getJumpDelay();
         if (player != null) {
            this.slimeJumpDelay = 1;
         }
         this.isJumping = true;
         if (this.makesSoundOnJump()) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
         }
         this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
         this.moveForward = (float)this.getSlimeSize();
      } else {
         this.isJumping = false;
         if (this.onGround) {
            this.moveStrafing = 0.0F;
            this.moveForward = 0.0F;
         }
      }
   }

   protected void alterSquishAmount() {
      this.squishAmount *= 0.6F;
   }

   protected int getJumpDelay() {
      return this.rand.nextInt(20) + 10;
   }

   protected EntityTornadoSlime createInstance() {
      return new EntityTornadoSlime(this.world);
   }

   @Override
   public void setDead() {
      int size = this.getSlimeSize();
      if (!this.world.isRemote && size > 1 && this.getHealth() <= 0.0F) {
         int count = 2 + this.rand.nextInt(3);
         for (int k = 0; k < count; ++k) {
            float f = ((float)(k % 2) - 0.5F) * (float)size / 4.0F;
            float f1 = ((float)(k / 2) - 0.5F) * (float)size / 4.0F;
            EntityTornadoSlime slime = this.createInstance();
            slime.setSlimeSize(size / 2);
            slime.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
            this.world.spawnEntity(slime);
         }
      }
      super.setDead();
   }

   @Override
   public void onCollideWithPlayer(EntityPlayer player) {
      if (this.canDamagePlayer()) {
         int size = this.getSlimeSize();
         if (this.canEntityBeSeen(player) && this.getDistanceSq(player) < (double)size * (double)size && player.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength()) && !this.world.isRemote) {
            this.world.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, true, false);
            this.setDead();
         }
      }
   }

   protected boolean canDamagePlayer() {
      return true;
   }

   protected int getAttackStrength() {
      return 6;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource source) {
      return SoundEvents.ENTITY_SLIME_HURT;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_SLIME_DEATH;
   }

   @Override
   protected boolean canDespawn() {
      return false;
   }

   @Override
   public boolean getCanSpawnHere() {
      return this.world.getDifficulty().getId() > 0 && this.isValidLightLevel() && super.getCanSpawnHere();
   }

   protected boolean isValidLightLevel() {
      BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY), MathHelper.floor(this.posZ));
      if (this.world.getLightFor(EnumSkyBlock.SKY, pos) > this.rand.nextInt(32)) {
         return false;
      }
      return this.world.getLightFromNeighbors(pos) <= this.rand.nextInt(8);
   }

   @Override
   protected float getSoundVolume() {
      return 0.4F * (float)this.getSlimeSize();
   }

   @Override
   public int getVerticalFaceSpeed() {
      return 0;
   }

   protected boolean makesSoundOnJump() {
      return this.getSlimeSize() > 0;
   }

   protected boolean makesSoundOnLand() {
      return this.getSlimeSize() > 2;
   }
}
