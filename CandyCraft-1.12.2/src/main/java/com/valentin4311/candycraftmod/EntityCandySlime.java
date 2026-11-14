package com.valentin4311.candycraftmod;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCandySlime extends EntityGolem implements IMob, ICandyBoss {
   private static final DataParameter<Byte> SIZE = EntityDataManager.<Byte>createKey(EntityCandySlime.class, DataSerializers.BYTE);
   private static final DataParameter<Integer> STATS = EntityDataManager.<Integer>createKey(EntityCandySlime.class, DataSerializers.VARINT);
   private static final DataParameter<Integer> HEALTH_INT = EntityDataManager.<Integer>createKey(EntityCandySlime.class, DataSerializers.VARINT);
   private static final DataParameter<Byte> AWAKE = EntityDataManager.<Byte>createKey(EntityCandySlime.class, DataSerializers.BYTE);
   private int slimeJumpDelay = 0;
   public float squishAmount;
   public float squishFactor;
   public float prevSquishFactor;
   public boolean isAwake = false;

   public EntityCandySlime(World world) {
      super(world);
      this.isImmuneToFire = true;
      this.slimeJumpDelay = this.rand.nextInt(20) + 10;
      this.experienceValue = 500;
      this.enablePersistence();
      this.setSlimeSize(6);
   }

   @Override
   protected void initEntityAI() {
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
   public boolean canBreatheUnderwater() {
      return true;
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.7D);
   }

   protected void setSlimeSize(int size) {
      this.dataManager.set(SIZE, (byte)size);
      this.setSize(0.6F * (float)size, 0.6F * (float)size);
      this.setPosition(this.posX, this.posY, this.posZ);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("CurrentStat", this.getStats());
      compound.setInteger("Size", this.getSlimeSize() - 1);
      compound.setBoolean("Awake", this.isAwake);
      this.setAwake();
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setStats(compound.getInteger("CurrentStat"));
      this.setSlimeSize(compound.getInteger("Size") + 1);
      this.isAwake = compound.getBoolean("Awake");
      this.setAwake();
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(SIZE, (byte)1);
      this.dataManager.register(STATS, 0);
      this.dataManager.register(HEALTH_INT, 300);
      this.dataManager.register(AWAKE, (byte)0);
   }

   public byte getAwake() {
      return this.dataManager.get(AWAKE);
   }

   public void setAwake() {
      this.dataManager.set(AWAKE, (byte)(this.isAwake ? 1 : 0));
   }

   public int getStats() {
      return this.dataManager.get(STATS);
   }

   public void setStats(int stats) {
      this.dataManager.set(STATS, stats);
   }

   public int getSlimeSize() {
      return this.dataManager.get(SIZE);
   }

   @Override
   public void onCollideWithPlayer(EntityPlayer player) {
      if (this.canDamagePlayer()) {
         int i = this.getSlimeSize();
         if (this.canEntityBeSeen(player) && this.getDistanceSq(player) < 0.6D * (double)i * 0.6D * (double)i && player.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength())) {
            this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         }
      }
   }

   protected boolean canDamagePlayer() {
      return true;
   }

   public void setHealthInt(int value) {
      this.dataManager.set(HEALTH_INT, value);
   }

   public int getHealthInt() {
      return this.dataManager.get(HEALTH_INT);
   }

   public void resetHealth() {
      this.setHealthInt(300);
      this.setHealth(this.getMaxHealth());
   }

   @Override
   public void fall(float distance, float damageMultiplier) {
   }

   @Override
   public void onLivingUpdate() {
      if (!this.isAwake) {
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.7D);
      }
      if (this.isJumping) {
         this.motionY = 1.5D;
         if (this.inWater) {
            this.motionY = 3.0D;
         }
      }
      if (!this.world.isRemote && this.world.getDifficulty().getId() == 0 && this.getSlimeSize() > 0) {
         this.isDead = true;
      }
      if (!this.world.isRemote) {
         if (this.getHealth() <= this.getMaxHealth() / 2.0F && this.getHealth() > this.getMaxHealth() / 4.0F) {
            this.setStats(1);
         } else if (this.getHealth() <= this.getMaxHealth() / 4.0F) {
            this.setStats(2);
         } else {
            this.setStats(0);
         }
      }
      super.onLivingUpdate();
   }

   @Override
   public void onUpdate() {
      if (!this.isAwake && !this.world.isRemote) {
         this.heal(5.0F);
      }
      if (!this.world.isRemote && this.world.getDifficulty().getId() == 0 && this.getSlimeSize() > 0) {
         this.isDead = true;
      }
      this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
      this.prevSquishFactor = this.squishFactor;
      boolean wasOnGround = this.onGround;
      super.onUpdate();
      if (this.onGround && !wasOnGround) {
         int size = this.getSlimeSize();
         for (int j = 0; j < size * 8; ++j) {
            float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
            float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
            float f2 = MathHelper.sin(f) * (float)size * 0.5F * f1;
            float f3 = MathHelper.cos(f) * (float)size * 0.5F * f1;
            this.world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, this.posX + (double)f2, this.getEntityBoundingBox().minY, this.posZ + (double)f3, 0.0D, 0.0D, 0.0D);
         }
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

   protected void alterSquishAmount() {
      this.squishAmount *= 0.6F;
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (source.isProjectile()) {
         return false;
      }
      if (!this.isAwake && !this.world.isRemote && source.getTrueSource() != null) {
         this.motionY = 2.0D;
         this.isAwake = true;
         this.setAwake();
      }
      if (source.getTrueSource() instanceof EntityPlayer && amount > 1.0F && !((EntityPlayer)source.getTrueSource()).capabilities.isCreativeMode) {
         double d0 = source.getTrueSource().posX - this.posX;
         double d1;
         for (d1 = source.getTrueSource().posZ - this.posZ; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
            d0 = (Math.random() - Math.random()) * 0.01D;
         }
         ((EntityPlayer)source.getTrueSource()).knockBack(this, 2.0F, -d0, -d1);
      }
      return super.attackEntityFrom(source, amount);
   }

   protected SoundEvent getJumpSound() {
      return SoundEvents.ENTITY_SLIME_JUMP;
   }

   @Override
   protected void updateEntityActionState() {
      if (!this.isAwake) {
         this.motionX = 0.0D;
         this.motionZ = 0.0D;
      }
      EntityPlayer player = this.world.getNearestAttackablePlayer(this.posX, this.posY, this.posZ, 48.0D, 48.0D, null, null);
      if (player != null && this.isAwake) {
         this.faceEntity(player, 10.0F, 20.0F);
         if (this.onGround && this.slimeJumpDelay-- <= 0) {
            this.slimeJumpDelay = this.getJumpDelay() / 3;
            this.isJumping = true;
            boolean grief = this.world.getGameRules().getBoolean("mobGriefing");
            if (this.getStats() == 2) {
               this.world.createExplosion(this, this.posX, this.posY, this.posZ, 3.0F, grief);
               this.world.createExplosion(this, this.posX, this.posY + 2.0D, this.posZ, 3.0F, grief);
            }
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
      } else if (!this.world.isRemote && player == null) {
         EntityPlayer any = this.world.getClosestPlayerToEntity(this, 48.0D);
         if (any == null || any == this.getAttackTarget()) {
            this.motionX = 0.0D;
            this.motionZ = 0.0D;
            this.isAwake = false;
            this.setAwake();
         }
      }
   }

   @Override
   protected float getSoundVolume() {
      return 0.4F * (float)this.getSlimeSize();
   }

   protected int getJumpDelay() {
      return this.getStats() != 1 ? this.rand.nextInt(40) + 5 : this.rand.nextInt(10) + 5;
   }

   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      this.dropItem(CandyCraft.CD1, 1);
      this.dropItem(CandyCraft.OrangeKey, 1);
      this.dropItem(CandyCraft.JellyEmblem, 1);
   }

   protected int getAttackStrength() {
      return this.getSlimeSize() * 2;
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

   @Override
   public int bossStatue() {
      return 0;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public float lastDamage(float amount) {
      GuiBoss gui = CandyCraft.clientTicker.getBossGui();
      gui.lastLife += amount;
      return amount;
   }

   @Override
   public double[] getBarColor() {
      if (this.getStats() == 0) {
         return new double[]{1.0D, 0.6D, 0.8D};
      }
      return this.getStats() == 1 ? new double[]{0.2D, 0.6D, 1.0D} : new double[]{0.92D, 0.65D, 0.6D};
   }
}
