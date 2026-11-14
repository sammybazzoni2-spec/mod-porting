package com.valentin4311.candycraftmod;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBossSuguard extends EntityGolem implements IMob, ICandyBoss, IRangedAttackMob {
   private static final DataParameter<Integer> STATS = EntityDataManager.<Integer>createKey(EntityBossSuguard.class, DataSerializers.VARINT);
   private static final DataParameter<Byte> AWAKE = EntityDataManager.<Byte>createKey(EntityBossSuguard.class, DataSerializers.BYTE);
   private EntityAICandyArrow aiArrowAttack;
   private int counter = 300;

   public EntityBossSuguard(World world) {
      super(world);
      this.setSize(0.8F, 1.5F);
      this.isImmuneToFire = true;
      this.experienceValue = 500;
   }

   @Override
   protected void initEntityAI() {
      this.aiArrowAttack = new EntityAICandyArrow(this, 1.0D, 20, 30, 15.0F);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.tasks.addTask(4, this.aiArrowAttack);
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
   public void onLivingUpdate() {
      if (!this.getAwake()) {
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
      }
      super.onLivingUpdate();
   }

   public boolean getAwake() {
      return this.dataManager.get(AWAKE).byteValue() == 1;
   }

   public void setAwake(boolean p) {
      this.dataManager.set(AWAKE, (byte)(p ? 1 : 0));
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(STATS, 0);
      this.dataManager.register(AWAKE, (byte)0);
   }

   public int getStats() {
      return this.dataManager.get(STATS);
   }

   public void setStats(int stats) {
      this.dataManager.set(STATS, stats);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("CurrentStat", this.getStats());
      compound.setBoolean("Awake", this.getAwake());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setStats(compound.getInteger("CurrentStat"));
      this.setAwake(compound.getBoolean("Awake"));
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (source.isProjectile() && !this.getAwake()) {
         return false;
      }
      if (!this.getAwake() && !this.world.isRemote && source.getTrueSource() instanceof EntityPlayer) {
         this.setAwake(true);
      }
      return super.attackEntityFrom(source, amount);
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (!this.world.isRemote) {
         if (!this.getAwake()) {
            this.heal(5.0F);
            this.motionX = 0.0D;
            this.motionZ = 0.0D;
            this.rotationYaw = this.prevRotationYaw;
            this.rotationPitch = this.prevRotationPitch;
         } else {
            --this.counter;
            if (this.counter <= 0) {
               this.counter = 300;
               this.setStats(this.rand.nextInt(3) + 1);
            }
            EntityPlayer player = this.world.getClosestPlayerToEntity(this, 48.0D);
            if (player != null && player.capabilities.disableDamage) {
               player = null;
            }
            if (player != null && this.getDistance(player) < 3.0F) {
               for (int i = 0; i < 5; ++i) {
                  this.attackEntityWithRangedAttack(player, 1.0F);
               }
            }
            if (player != null && this.getAttackTarget() == null) {
               this.setAttackTarget(player);
               this.aiArrowAttack.attackTarget = player;
            } else if (this.world.getClosestPlayerToEntity(this, 48.0D) == null) {
               this.setAwake(false);
            }
         }
      }
   }

   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      this.dropItem(CandyCraft.CD2, 1);
      this.dropItem(CandyCraft.BlueKey, 1);
      this.dropItem(CandyCraft.SuguardEmblem, 1);
   }

   @Override
   public boolean canBreatheUnderwater() {
      return true;
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(400.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
   }

   @Override
   public int bossStatue() {
      return 0;
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data) {
      data = super.onInitialSpawn(difficulty, data);
      this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(CandyCraft.CaramelBow));
      return data;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public float lastDamage(float amount) {
      GuiBoss gui = (GuiBoss)CandyCraft.clientTicker.getBossGui();
      gui.lastLife += amount;
      return gui.lastLife;
   }

   @Override
   public double[] getBarColor() {
      return new double[]{0.8D, 0.8D, 0.8D};
   }

   @Override
   public void setSwingingArms(boolean swingingArms) {
   }

   @Override
   public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
      int count = 1;
      if (this.getStats() == 1) {
         count = 4;
      }
      int difficulty = this.world.getDifficulty().getId();
      for (int n = 0; n < count; ++n) {
         EntityCandyArrow arrow = new EntityCandyArrow(this.world, this, target, 1.6F, (float)(14 - difficulty * 4));
         arrow.maxTick = 120;
         ItemStack held = this.getHeldItemMainhand();
         int power = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, held);
         int punch = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, held);
         arrow.setDamage((double)(distanceFactor * 3.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)difficulty * 0.11F));
         if (power > 0) {
            arrow.setDamage(arrow.getDamage() + (double)power * 0.5D + 0.5D);
         }
         if (this.getDistance(target) < 3.0F) {
            punch = 2;
         }
         if (punch > 0) {
            arrow.setKnockbackStrength(punch);
         }
         if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, held) > 0 || this.getStats() == 3) {
            arrow.setFire(100);
         }
         if (this.getStats() == 2) {
            arrow.slow = 1;
         }
         this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
         this.world.spawnEntity(arrow);
      }
   }
}
