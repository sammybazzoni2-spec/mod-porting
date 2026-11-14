package com.valentin4311.candycraftmod;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityNougatGolem extends EntityGolem {
   private static final DataParameter<Float> LENGHT = EntityDataManager.<Float>createKey(EntityNougatGolem.class, DataSerializers.FLOAT);

   public EntityNougatGolem(World world) {
      super(world);
      this.setSize(0.65F, this.getLenght());
      this.setHealth(20.0F);
   }

   @Override
   protected void initEntityAI() {
      this.tasks.addTask(1, new EntityAIExplode(this, 1.0D, false));
      this.tasks.addTask(6, new EntityAIWander(this, 0.6D));
      this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, 0, false, true, IMob.MOB_SELECTOR));
   }

   @Override
   public boolean canAttackClass(Class<? extends EntityLivingBase> cls) {
      return !EntityPlayer.class.isAssignableFrom(cls);
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
   public boolean canBePushed() {
      return false;
   }

   private EntityNougatGolem getRider() {
      for (Entity e : this.getPassengers()) {
         if (e instanceof EntityNougatGolem) {
            return (EntityNougatGolem)e;
         }
      }
      return null;
   }

   @Override
   public void onLivingUpdate() {
      if (this.isTop() && this.getLenght() != 0.8F) {
         this.setLenght(0.8F);
         this.setSize(0.65F, this.getLenght());
      }
      if (this.isBase()) {
         EntityNougatGolem last = this;
         float height = 0.8F;
         while (!last.isTop()) {
            height += last.getLenght();
            EntityNougatGolem next = last.getRider();
            if (next == null) {
               break;
            }
            last = next;
         }
         this.setSize(0.64F, height);
      }
      super.onLivingUpdate();
      if (this.getRidingEntity() != null) {
         this.rotationYaw = this.getRidingEntity().prevRotationYaw;
      }
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(LENGHT, this.rand.nextFloat() / 10.0F + 0.65F);
   }

   @Override
   public double getMountedYOffset() {
      return (double)this.getLenght();
   }

   @Override
   public boolean shouldDismountInWater(Entity rider) {
      return false;
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (!this.world.isRemote && source.isExplosion()) {
         return false;
      }
      if (!this.world.isRemote && this.getHealth() - amount <= 0.0F) {
         for (Entity rider : new ArrayList<Entity>(this.getPassengers())) {
            rider.dismountRidingEntity();
            rider.setPosition(this.posX, this.posY + 2.0D, this.posZ);
         }
      }
      return super.attackEntityFrom(source, amount);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setFloat("lenght", this.getLenght());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      if (compound.hasKey("lenght")) {
         this.dataManager.set(LENGHT, compound.getFloat("lenght"));
      }
   }

   public float getLenght() {
      return this.dataManager.get(LENGHT);
   }

   public void setLenght(float lenght) {
      this.setSize(this.getLenght(), this.getLenght());
      this.dataManager.set(LENGHT, lenght);
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data) {
      data = super.onInitialSpawn(difficulty, data);
      this.setLenght(this.rand.nextFloat() / 10.0F + 0.65F);
      return data;
   }

   public boolean isTop() {
      return this.getPassengers().isEmpty();
   }

   public boolean isBase() {
      return this.getRidingEntity() == null;
   }
}
