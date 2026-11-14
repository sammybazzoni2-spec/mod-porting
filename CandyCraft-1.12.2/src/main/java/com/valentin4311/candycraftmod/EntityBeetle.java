package com.valentin4311.candycraftmod;

import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityBeetle extends EntityMob {
   private static final DataParameter<Integer> CHILD = EntityDataManager.<Integer>createKey(EntityBeetle.class, DataSerializers.VARINT);
   private static final DataParameter<Integer> ANGRY = EntityDataManager.<Integer>createKey(EntityBeetle.class, DataSerializers.VARINT);

   public EntityBeetle(World world) {
      super(world);
      this.setSize(1.0F, 0.8F);
   }

   @Override
   protected void initEntityAI() {
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.3D, false));
      this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.3D));
      this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(4, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
      this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.2D);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
   }

   @Override
   public boolean shouldDismountInWater(Entity rider) {
      return false;
   }

   @Override
   public void onDeath(DamageSource source) {
      if (this.isChild() && source.getTrueSource() instanceof EntityPlayer) {
         List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(32.0D, 32.0D, 32.0D));

         for (int i = 0; i < list.size(); ++i) {
            Entity entity = list.get(i);
            if (entity instanceof EntityBeetle) {
               ((EntityBeetle)entity).setAngry(true);
            }
         }
      }

      super.onDeath(source);
   }

   @Override
   protected boolean isValidLightLevel() {
      return true;
   }

   @Override
   public void onLivingUpdate() {
      if (this.world.isRemote && this.isAngry() && this.rand.nextInt(20) == 0) {
         for (int i = 0; i < 2; ++i) {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.world.spawnParticle(EnumParticleTypes.VILLAGER_ANGRY, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
         }
      }

      if (this.getRidingEntity() != null) {
         Entity mount = this.getRidingEntity();
         this.rotationYaw = mount.rotationYaw;
         this.prevRotationYaw = mount.prevRotationYaw;
         this.rotationYawHead = mount.getRotationYawHead();
         this.rotationPitch = mount.rotationPitch;
         this.prevRotationPitch = mount.prevRotationPitch;
         this.setSize(0.5F, 0.4F);
      }

      if (!this.world.isRemote && !this.isChild() && this.getAttackTarget() != null && this.rand.nextInt(500) == 0) {
         for (int x = -1; x < 2; ++x) {
            for (int z = -1; z < 2; ++z) {
               BlockPos p = new BlockPos((int)this.posX + x, (int)this.posY, (int)this.posZ + z);
               IBlockState state = this.world.getBlockState(p);
               if (this.rand.nextBoolean() && ((BlockChewingGumPlate)CandyCraft.ChewingGumPlate).canBlockStay(this.world, p) && state.getMaterial() != Material.WATER && state.getBlock().isReplaceable(this.world, p)) {
                  this.world.setBlockState(p, CandyCraft.ChewingGumPlate.getDefaultState(), 3);
               }
            }
         }
      }

      super.onLivingUpdate();
   }

   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      CandyEntityRegistry.dropDefault(this, CandyCraft.ChewingGum, lootingModifier);
      if (this.rand.nextInt(80) == 0 && !this.isChild()) {
         this.dropItem(CandyCraft.BeetleEgg, 1);
      }
   }

   @Override
   public float getBlockPathWeight(BlockPos pos) {
      return 0.0F;
   }

   @Override
   public double getMountedYOffset() {
      return 0.62D;
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingData) {
      if (this.rand.nextInt(10) == 0) {
         EntityBeetle child = new EntityBeetle(this.world);
         child.setPosition(this.posX, this.posY, this.posZ);
         child.setChild(true);
         this.world.spawnEntity(child);
         child.startRiding(this);
         child.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5D);
      }

      return super.onInitialSpawn(difficulty, livingData);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(CHILD, 0);
      this.dataManager.register(ANGRY, 0);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("Child", this.isChild());
      compound.setBoolean("Angry", this.isAngry());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setChild(compound.getBoolean("Child"));
      this.setAngry(compound.getBoolean("Angry"));
   }

   public void setChild(boolean child) {
      this.dataManager.set(CHILD, child ? 1 : 0);
   }

   @Override
   public boolean isChild() {
      return this.dataManager.get(CHILD) == 1;
   }

   public void setAngry(boolean angry) {
      if (angry) {
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.5D);
         this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15.0D);
         if (this.isChild()) {
            this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
         }
      }

      this.dataManager.set(ANGRY, angry ? 1 : 0);
   }

   public boolean isAngry() {
      return this.dataManager.get(ANGRY) == 1;
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
