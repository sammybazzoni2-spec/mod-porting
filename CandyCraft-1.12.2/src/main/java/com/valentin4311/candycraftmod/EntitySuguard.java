package com.valentin4311.candycraftmod;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntitySuguard extends EntityMob {
   public boolean isAngry = false;

   public EntitySuguard(World world) {
      super(world);
      this.setSize(0.5F, 0.7F);
   }

   @Override
   protected void initEntityAI() {
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
      this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, 0, true, false, new Predicate<EntityPlayer>() {
         @Override
         public boolean apply(@Nullable EntityPlayer player) {
            return EntitySuguard.this.canTargetPlayers();
         }
      }));
      this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
   }

   protected boolean canTargetPlayers() {
      return this.world.getBiome(new BlockPos(this)) == CandyCraft.CandyHellForest || this.isRiding() || this.isAngry;
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return null;
   }

   @Override
   public boolean isPotionApplicable(PotionEffect effect) {
      return effect.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(effect);
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
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data) {
      data = super.onInitialSpawn(difficulty, data);
      if (this.world.rand.nextInt(20) == 0) {
         EntityBee bee = new EntityBee(this.world);
         bee.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         bee.isAngry = true;
         bee.onInitialSpawn(difficulty, (IEntityLivingData)null);
         this.world.spawnEntity(bee);
         this.startRiding(bee);
      }
      return data;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("Angry", this.isAngry);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.isAngry = compound.getBoolean("Angry");
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
   }

   @Override
   public boolean attackEntityAsMob(Entity target) {
      ItemStack held = this.getHeldItemMainhand();
      if (!held.isEmpty() && held.getItem() == CandyCraft.Dynamite) {
         boolean grief = this.world.getGameRules().getBoolean("mobGriefing");
         this.world.createExplosion(this, this.posX, this.posY, this.posZ, 1.0F, grief);
         this.setDead();
         return true;
      }
      return super.attackEntityAsMob(target);
   }

   @Override
   public void onLivingUpdate() {
      if (this.getHeldItemMainhand().isEmpty()) {
         this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(CandyCraft.LicoriceSpear));
      }
      if (this.isWet()) {
         this.attackEntityFrom(DamageSource.DROWN, 1.0F);
      }
      EntityLivingBase target = this.getAttackTarget();
      if (target != null && this.onGround && this.rand.nextInt(10) == 0) {
         float dist = this.getDistance(target);
         if (dist > 2.0F && dist < 6.0F) {
            double dx = target.posX - this.posX;
            double dz = target.posZ - this.posZ;
            float len = MathHelper.sqrt(dx * dx + dz * dz);
            if (len > 0.0F) {
               this.motionX = dx / (double)len * 0.4D + this.motionX * 0.2D;
               this.motionZ = dz / (double)len * 0.4D + this.motionZ * 0.2D;
               this.motionY = 0.6D;
            }
         }
      }
      if (this.world.isRemote && this.rand.nextInt(30) == 0) {
         for (int i = 0; i < 2; ++i) {
            this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
         }
      }
      super.onLivingUpdate();
   }

   @Override
   protected void playStepSound(BlockPos pos, net.minecraft.block.Block block) {
   }

   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      ItemStack held = this.getHeldItemMainhand();
      if (!held.isEmpty()) {
         this.dropItem(held.getItem(), 1);
      }
   }
}
