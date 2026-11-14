package com.valentin4311.candycraftmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBossBeetle extends EntityGolem implements IMob, ICandyBoss {
   private static final DataParameter<Byte> AWAKE = EntityDataManager.<Byte>createKey(EntityBossBeetle.class, DataSerializers.BYTE);
   private static final DataParameter<Integer> TURN = EntityDataManager.<Integer>createKey(EntityBossBeetle.class, DataSerializers.VARINT);
   private int coolDown = 100;
   private int shoot = 0;
   private int turn = 0;

   public EntityBossBeetle(World world) {
      super(world);
      this.setSize(2.0F, 1.6F);
      this.isImmuneToFire = true;
      this.experienceValue = 500;
   }

   @Override
   public boolean canBreatheUnderwater() {
      return true;
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
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
      return this.dataManager.get(AWAKE) == 1;
   }

   public void setAwake(boolean awake) {
      this.dataManager.set(AWAKE, (byte)(awake ? 1 : 0));
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(AWAKE, (byte)0);
      this.dataManager.register(TURN, 0);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("Awake", this.getAwake());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setAwake(compound.getBoolean("Awake"));
   }

   @Override
   protected boolean canDespawn() {
      return false;
   }

   @Override
   public boolean canBeCollidedWith() {
      return true;
   }

   @Override
   public boolean isPushedByWater() {
      return false;
   }

   @Override
   protected void collideWithNearbyEntities() {
   }

   @Override
   public boolean canBePushed() {
      return false;
   }

   @Override
   protected boolean canTriggerWalking() {
      return false;
   }

   @Override
   public void knockBack(Entity entity, float strength, double xRatio, double zRatio) {
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (!this.getAwake() && !this.world.isRemote) {
         this.heal(5.0F);
      }

      if (this.dataManager.get(TURN) > 0 && this.world.isRemote) {
         for (int i = 0; i <= 16; ++i) {
            double d1 = (double)(-MathHelper.sin(((float)i * 11.25F + (float)this.ticksExisted) / 90.0F * (float)Math.PI) * MathHelper.cos((float)this.ticksExisted * 0.05F) * 2.5F) + this.posX;
            double d2 = (double)(MathHelper.cos(((float)i * 11.25F + (float)this.ticksExisted) / 90.0F * (float)Math.PI) * MathHelper.cos((float)this.ticksExisted * 0.05F) * 2.5F) + this.posZ;
            double d3;
            if (i % 2 == 0) {
               d3 = (double)MathHelper.cos((float)this.ticksExisted * 0.05F) + this.posY + 3.0D;
            } else {
               d3 = (double)MathHelper.cos((float)(this.ticksExisted + 10) * 0.05F) + this.posY + 3.0D;
            }

            this.world.spawnParticle(EnumParticleTypes.FLAME, d1, d3, d2, 0.0D, 0.0D, 0.0D);
         }
      }

      if (!this.world.isRemote) {
         EntityPlayer player = this.world.getClosestPlayerToEntity(this, 10.0D);
         if (player != null) {
            this.setAwake(true);
         }

         if (!this.getAwake()) {
            this.heal(5.0F);
            this.motionX = 0.0D;
            this.motionZ = 0.0D;
            this.rotationYaw = this.prevRotationYaw;
            this.rotationPitch = this.prevRotationPitch;
         } else {
            EntityPlayer player2 = this.world.getNearestAttackablePlayer(this, 48.0D, 48.0D);
            if (player2 != null) {
               this.setAttackTarget(player2);
               this.getLookHelper().setLookPosition(player2.posX, player2.posY + (double)player2.getEyeHeight(), player2.posZ, 10.0F, (float)this.getVerticalFaceSpeed());
               this.rotationYaw = this.rotationYawHead;
               if (this.shoot > 0 && this.ticksExisted % 2 == 0) {
                  --this.shoot;
                  EntityGummyBall ball = new EntityGummyBall(this.world);
                  ball.setPosition(this.posX, this.posY + 2.0D, this.posZ);
                  ball.setPowerful(3);
                  ball.airState = 1;
                  ball.motionY = 1.5D;
                  ball.motionX = ((double)((this.rand.nextBoolean() ? -1 : 1) * 3) + this.rand.nextDouble() * 6.0D) / 40.0D;
                  ball.motionZ = ((double)((this.rand.nextBoolean() ? -1 : 1) * 3) + this.rand.nextDouble() * 6.0D) / 40.0D;
                  ball.target = player2;
                  ball.beetle = this;
                  this.world.spawnEntity(ball);
                  this.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
               }

               if (this.dataManager.get(TURN) <= 0) {
                  --this.coolDown;
               } else {
                  --this.turn;
                  this.dataManager.set(TURN, this.turn);
                  if (this.turn < 100) {
                     this.attackEntityWithRangedAttack(this, true);
                  }
               }

               if (this.coolDown == 0) {
                  this.coolDown = (int)(40.0D - (35.0D - (double)this.getHealth() / (double)this.getMaxHealth() * 35.0D));
                  if ((double)this.getHealth() < 150.0D && this.rand.nextInt(6) == 0) {
                     this.shoot = 50;
                  } else if ((double)this.getHealth() < 250.0D && this.rand.nextInt(10) == 0) {
                     this.turn = 200;
                     this.dataManager.set(TURN, this.turn);
                  } else {
                     this.attackEntityWithRangedAttack(player2, false);
                  }
               }
            } else if (this.world.getClosestPlayerToEntity(this, 48.0D) == null) {
               this.setAwake(false);
               this.shoot = 0;
               this.turn = 0;
               this.dataManager.set(TURN, this.turn);
            }
         }
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (source.getImmediateSource() instanceof EntityGummyBall && ((EntityGummyBall)source.getImmediateSource()).getPowerful() == 3) {
         amount = 8.0F;
         return super.attackEntityFrom(source, amount);
      } else {
         return false;
      }
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
   public int bossStatue() {
      return 0;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public float lastDamage(float amount) {
      GuiBoss gui = CandyCraft.clientTicker.getBossGui();
      gui.lastLife += amount;
      return gui.lastLife;
   }

   @Override
   public double[] getBarColor() {
      return new double[]{0.65D, 0.3D, 0.35D};
   }

   public void attackEntityWithRangedAttack(EntityLivingBase target, boolean flag) {
      EntityGummyBall ball = new EntityGummyBall(this.world, this, flag ? 2 : 3);
      if (flag) {
         ball.airState = 3;
      }

      ball.beetle = this;
      this.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
      this.world.spawnEntity(ball);
   }

   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      this.dropItem(CandyCraft.CD4, 1);
      this.dropItem(CandyCraft.PurpleKey, 1);
   }

   @Override
   public void fall(float distance, float damageMultiplier) {
   }
}
