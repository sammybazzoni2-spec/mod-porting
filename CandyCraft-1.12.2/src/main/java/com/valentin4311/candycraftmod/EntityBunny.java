package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityBunny extends EntityAnimal {
   private static final DataParameter<Integer> RED = EntityDataManager.<Integer>createKey(EntityBunny.class, DataSerializers.VARINT);
   private static final DataParameter<Integer> GREEN = EntityDataManager.<Integer>createKey(EntityBunny.class, DataSerializers.VARINT);
   private static final DataParameter<Integer> BLUE = EntityDataManager.<Integer>createKey(EntityBunny.class, DataSerializers.VARINT);
   boolean isJp = false;
   public float lastRotationYaw;
   public int jumpDelay = 0;

   public EntityBunny(World world) {
      super(world);
      this.setSize(0.5F, 0.4F);
      this.setColor(this.rand.nextInt(230) + 20, this.rand.nextInt(230) + 20, this.rand.nextInt(230) + 20);
   }

   @Override
   protected void initEntityAI() {
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
      this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
      this.tasks.addTask(3, new EntityAITempt(this, 1.25D, CandyCraft.Licorice, false));
      this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
      this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(7, new EntityAILookIdle(this));
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(RED, 0);
      this.dataManager.register(GREEN, 0);
      this.dataManager.register(BLUE, 0);
   }

   public int getRed() {
      return this.dataManager.get(RED);
   }

   public int getGreen() {
      return this.dataManager.get(GREEN);
   }

   public int getBlue() {
      return this.dataManager.get(BLUE);
   }

   public void setColor(int r, int g, int b) {
      this.dataManager.set(RED, r);
      this.dataManager.set(GREEN, g);
      this.dataManager.set(BLUE, b);
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
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
   protected ResourceLocation getLootTable() {
      return null;
   }

   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      this.dropItem(CandyCraft.Gummy, 1);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("red", this.getRed());
      compound.setInteger("green", this.getGreen());
      compound.setInteger("blue", this.getBlue());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setColor(compound.getInteger("red"), compound.getInteger("green"), compound.getInteger("blue"));
   }

   @Override
   protected void playStepSound(BlockPos pos, Block block) {
      this.playSound(SoundEvents.ENTITY_SLIME_SQUISH, 0.15F, 1.0F);
   }

   @Override
   public boolean isBreedingItem(ItemStack stack) {
      return !stack.isEmpty() && stack.getItem() == CandyCraft.Licorice;
   }

   @Override
   protected float getSoundVolume() {
      return 0.4F;
   }

   @Override
   public void fall(float distance, float damageMultiplier) {
   }

   public EntityBunny spawnBabyAnimal(EntityAgeable ageable) {
      EntityBunny bunny = new EntityBunny(this.world);
      bunny.setColor(this.rand.nextInt(230) + 20, this.rand.nextInt(230) + 20, this.rand.nextInt(230) + 20);
      return bunny;
   }

   @Override
   public EntityAgeable createChild(EntityAgeable ageable) {
      return this.spawnBabyAnimal(ageable);
   }

   @Override
   public void onLivingUpdate() {
      if (!this.world.isRemote && !this.isInWater()) {
         if (this.jumpDelay > 0 && this.onGround) {
            --this.jumpDelay;
            this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
            this.motionX = 0.0D;
            this.motionZ = 0.0D;
         }
         if (this.jumpDelay <= 0) {
            this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
         }
         if (this.onGround) {
            this.isJp = false;
         }
         if ((this.motionX != 0.0D || this.motionZ != 0.0D) && !this.isJp && this.jumpDelay <= 0) {
            this.isJumping = true;
            this.jumpDelay = 10;
         }
         if (this.isJumping) {
            this.motionY = !this.isInLove() ? 0.55D : 0.45D;
            this.isJumping = false;
            this.isJp = true;
         }
         if (this.isJp && !this.onGround) {
            this.rotationYaw = this.lastRotationYaw;
            if (!this.isInLove()) {
               this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
            }
         }
         this.lastRotationYaw = this.rotationYaw;
      }
      super.onLivingUpdate();
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data) {
      data = super.onInitialSpawn(difficulty, data);
      this.setColor(this.rand.nextInt(230) + 20, this.rand.nextInt(230) + 20, this.rand.nextInt(230) + 20);
      return data;
   }
}
