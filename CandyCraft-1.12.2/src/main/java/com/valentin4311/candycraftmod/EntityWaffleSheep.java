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

public class EntityWaffleSheep extends EntityAnimal {
   private static final DataParameter<Byte> WOOL = EntityDataManager.<Byte>createKey(EntityWaffleSheep.class, DataSerializers.BYTE);

   public EntityWaffleSheep(World world) {
      super(world);
      this.setSize(0.9F, 1.3F);
   }

   @Override
   protected void initEntityAI() {
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
      this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
      this.tasks.addTask(3, new EntityAITempt(this, 1.1D, CandyCraft.CandiedCherry, false));
      this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
      this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(7, new EntityAILookIdle(this));
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(WOOL, (byte)10);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setByte("wool", (byte)this.getWool());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setWool(compound.getByte("wool"));
   }

   public int getWool() {
      return this.dataManager.get(WOOL) & 10;
   }

   public void setWool(int wool) {
      this.dataManager.set(WOOL, (byte)(wool & 10));
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (!this.world.isRemote && source.getTrueSource() != null && this.rand.nextInt(4) == 0) {
         this.dropItem(CandyCraft.WaffleNugget, 1);
      }
      return super.attackEntityFrom(source, amount);
   }

   @Override
   public boolean isBreedingItem(ItemStack stack) {
      return !stack.isEmpty() && stack.getItem() == CandyCraft.CandiedCherry;
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data) {
      data = super.onInitialSpawn(difficulty, data);
      this.setWool(this.rand.nextInt(10));
      return data;
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
   }

   @Override
   protected ResourceLocation getLootTable() {
      return null;
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return SoundEvents.ENTITY_SHEEP_AMBIENT;
   }

   @Override
   protected float getSoundPitch() {
      return this.isChild() ? (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F : (this.rand.nextFloat() - this.rand.nextFloat()) * 0.8F + 1.0F;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource source) {
      return SoundEvents.ENTITY_SHEEP_HURT;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_SHEEP_DEATH;
   }

   @Override
   protected void playStepSound(BlockPos pos, Block block) {
      this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
   }

   @Override
   public EntityAgeable createChild(EntityAgeable ageable) {
      return new EntityWaffleSheep(this.world);
   }
}
