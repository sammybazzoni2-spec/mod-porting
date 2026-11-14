package com.valentin4311.candycraftmod;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityKingSlime extends EntityGolem implements IMob, ICandyBoss {
   private static final DataParameter<Byte> SIZE = EntityDataManager.<Byte>createKey(EntityKingSlime.class, DataSerializers.BYTE);
   private static final DataParameter<Integer> STATS = EntityDataManager.<Integer>createKey(EntityKingSlime.class, DataSerializers.VARINT);
   private static final DataParameter<Integer> HEALTH_INT = EntityDataManager.<Integer>createKey(EntityKingSlime.class, DataSerializers.VARINT);
   private static final DataParameter<Byte> AWAKE = EntityDataManager.<Byte>createKey(EntityKingSlime.class, DataSerializers.BYTE);
   private int slimeJumpDelay = 0;
   public float squishAmount;
   public float squishFactor;
   public float prevSquishFactor;
   public boolean isAwake = false;
   public double SX = 0.0D;
   public double SY = 0.0D;
   public double SZ = 0.0D;
   public boolean Start = false;

   public EntityKingSlime(World world) {
      super(world);
      this.isImmuneToFire = true;
      this.slimeJumpDelay = this.rand.nextInt(20) + 10;
      this.experienceValue = 800;
      this.enablePersistence();
      this.setSlimeSize(13, true);
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
   protected boolean canDespawn() {
      return false;
   }

   protected void setSlimeSize(int size, boolean health) {
      this.dataManager.set(SIZE, (byte)size);
      this.setSize(0.6F * (float)size, 0.6F * (float)size);
      if (health) {
         this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(800.0D);
         this.setHealth(this.getMaxHealth());
      }
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
      this.setSlimeSize(compound.getInteger("Size") + 1, false);
      this.isAwake = compound.getBoolean("Awake");
      this.setAwake();
   }

   protected EntityKingSlime createInstance() {
      return new EntityKingSlime(this.world);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(SIZE, (byte)1);
      this.dataManager.register(STATS, 0);
      this.dataManager.register(HEALTH_INT, 800);
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
         if (this.canEntityBeSeen(player) && this.getDistanceSq(player) < 0.6D * (double)i * 0.6D * (double)i && player.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackStrength())) {
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
      this.setHealth(this.getMaxHealth());
   }

   @Override
   public void fall(float distance, float damageMultiplier) {
   }

   @Override
   public void onLivingUpdate() {
      if (this.isJumping) {
         this.motionY = (double)(this.getSlimeSize() / 4);
      }
      if (!this.world.isRemote && this.world.getDifficulty().getId() == 0 && this.getSlimeSize() > 0) {
         this.isDead = true;
      }
      super.onLivingUpdate();
   }

   @Override
   public void onUpdate() {
      if (!this.isAwake && !this.world.isRemote) {
         if (this.Start) {
            this.setLocationAndAngles(this.SX, this.SY, this.SZ, 0.0F, 0.0F);
            this.setSlimeSize(13, true);
         }
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
      double percent = (double)(this.getHealth() / this.getMaxHealth()) * 12.0D;
      if (!this.world.isRemote && this.getSlimeSize() > (int)percent + 1) {
         this.setSlimeSize((int)percent + 1, false);
      }
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
      return this.getSlimeSize() > 1 ? SoundEvents.ENTITY_SLIME_JUMP : SoundEvents.ENTITY_SMALL_SLIME_JUMP;
   }

   @Override
   protected void updateAITasks() {
      if (!this.isAwake) {
         this.motionX = 0.0D;
         this.motionZ = 0.0D;
      }
      EntityPlayer player = this.world.getNearestAttackablePlayer(this.posX, this.posY, this.posZ, 48.0D, 48.0D, null, null);
      if (player != null && this.isAwake) {
         this.faceEntity(player, 10.0F, 20.0F);
         if (this.onGround && this.slimeJumpDelay-- <= 0) {
            this.slimeJumpDelay = this.getJumpDelay();
            this.slimeJumpDelay /= 3;
            if (this.rand.nextInt(10) == 0) {
               player.attackEntityFrom(DamageSource.causeMobDamage(this), 0.0F);
               player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 2));
               if (this.world.isRemote) {
                  for (int i = 0; i < 20; ++i) {
                     this.drawParticle(player);
                  }
               }
            }
            this.isJumping = true;
            boolean grief = this.world.getGameRules().getBoolean("mobGriefing");
            if (this.rand.nextInt(5) == 0) {
               this.world.createExplosion(this, this.posX, this.posY, this.posZ, 3.0F, grief);
            }
            if (this.rand.nextInt(3) == 0) {
               EntitySprinterSlime slime = new EntitySprinterSlime(this.world);
               slime.setPosition(this.posX, this.posY, this.posZ);
               this.world.spawnEntity(slime);
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

   @SideOnly(Side.CLIENT)
   public void drawParticle(EntityPlayer player) {
      this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, player.posX + (double)this.rand.nextFloat() - 0.5D, player.posY + (double)player.getEyeHeight(), player.posZ + (double)this.rand.nextFloat() - 0.5D, 0.0D, 0.0D, 0.0D, Item.getIdFromItem(CandyCraft.GummyBall));
   }

   @Override
   protected float getSoundVolume() {
      return 0.4F * (float)this.getSlimeSize();
   }

   protected int getJumpDelay() {
      return this.rand.nextInt(10) + 5;
   }

   @Override
   public int getTotalArmorValue() {
      return 4;
   }

   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      if (this.getSlimeSize() <= 1) {
         this.dropItem(CandyCraft.Jelly2Key, 1);
      }
   }

   protected float getAttackStrength() {
      return (float)this.getSlimeSize() * 2.5F;
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
      return 2;
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
      return new double[]{0.9D, 0.5D, 0.0D};
   }
}
