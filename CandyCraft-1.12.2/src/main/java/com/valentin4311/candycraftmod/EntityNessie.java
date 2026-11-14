package com.valentin4311.candycraftmod;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityNessie extends EntityAnimal implements IEntityLockable, IEntityPowerMount {
   private static final DataParameter<Integer> TYPE = EntityDataManager.<Integer>createKey(EntityNessie.class, DataSerializers.VARINT);
   private static final DataParameter<Byte> SADDLED = EntityDataManager.<Byte>createKey(EntityNessie.class, DataSerializers.BYTE);
   private static final DataParameter<Integer> LOCKED = EntityDataManager.<Integer>createKey(EntityNessie.class, DataSerializers.VARINT);
   private static final DataParameter<Integer> POWER = EntityDataManager.<Integer>createKey(EntityNessie.class, DataSerializers.VARINT);
   public float current = 0.0F;
   private BlockPos currentFlightTarget;

   public EntityNessie(World world) {
      super(world);
      this.setSize(1.2F, 1.6F);
   }

   @Override
   protected void initEntityAI() {
      this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
   }

   @Override
   public int powerUsed() {
      return 1200;
   }

   @Override
   public int maxPower() {
      return 1200;
   }

   @Override
   public void unleashPower() {
      this.setPower(0);
      if (!this.getPassengers().isEmpty() && this.getPassengers().get(0) instanceof EntityLivingBase) {
         EntityLivingBase rider = (EntityLivingBase)this.getPassengers().get(0);
         rider.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 600, 0));
         rider.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 600, 0));
      }
   }

   @Override
   public boolean canBreatheUnderwater() {
      return true;
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(TYPE, 0);
      this.dataManager.register(SADDLED, (byte)0);
      this.dataManager.register(LOCKED, 0);
      this.dataManager.register(POWER, 0);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("type", this.getType());
      compound.setBoolean("Saddle", this.getSaddled());
      compound.setInteger("Power", this.getPower());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setType(compound.getInteger("type"));
      this.setSaddled(compound.getBoolean("Saddle"));
      this.setPower(compound.getInteger("Power"));
   }

   @Override
   public boolean isBreedingItem(ItemStack stack) {
      return stack.getItem() == CandyCraft.CranberryFish;
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      ItemStack held = player.getHeldItem(hand);
      if (super.processInteract(player, hand)) {
         return true;
      } else if (!this.world.isRemote && !this.isChild() && !this.getSaddled() && !held.isEmpty() && held.getItem() == Items.SADDLE) {
         held.shrink(1);
         this.setSaddled(true);
         return true;
      } else if (this.getSaddled() && !this.world.isRemote && !this.isBeingRidden()) {
         player.startRiding(this);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean shouldDismountInWater(Entity rider) {
      return false;
   }

   public void setType(int i) {
      this.dataManager.set(TYPE, i);
   }

   public int getType() {
      return this.dataManager.get(TYPE);
   }

   @Override
   protected boolean canDespawn() {
      return false;
   }

   @Override
   protected float getSoundVolume() {
      return 0.2F;
   }

   private Entity getRider() {
      return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
   }

   @Override
   protected void updateAITasks() {
      if (!this.world.isRemote && this.getPower() < this.maxPower()) {
         this.setPower(this.getPower() + 1);
      }
      Entity rider = this.getRider();
      if (this.isInLove() && this.isInWater()) {
         super.updateAITasks();
      }
      this.isJumping = false;
      if (this.isInWater() && !this.world.isRemote && !this.isInLove() && rider == null) {
         if (this.currentFlightTarget != null && (this.world.getBlockState(this.currentFlightTarget).getMaterial() != Material.WATER || this.currentFlightTarget.getY() < 1)) {
            this.currentFlightTarget = null;
         }
         if (this.currentFlightTarget == null || this.rand.nextInt(100) == 0 || this.currentFlightTarget.distanceSq((double)(int)this.posX, (double)(int)this.posY, (double)(int)this.posZ) < 4.0D) {
            this.currentFlightTarget = new BlockPos((int)this.posX + this.rand.nextInt(16) - this.rand.nextInt(16), (int)this.posY, (int)this.posZ + this.rand.nextInt(16) - this.rand.nextInt(16));
         }
         double d0 = (double)this.currentFlightTarget.getX() + 0.5D - this.posX;
         double d1 = (double)this.currentFlightTarget.getY() + 0.1D - this.posY;
         double d2 = (double)this.currentFlightTarget.getZ() + 0.5D - this.posZ;
         this.motionX += (Math.signum(d0) * 0.05D - this.motionX) * 0.1D;
         this.motionY += (Math.signum(d1) * 0.7D - this.motionY) * 0.005D;
         this.motionZ += (Math.signum(d2) * 0.05D - this.motionZ) * 0.1D;
         float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
         float f1 = MathHelper.wrapDegrees(f);
         this.moveForward = 0.5F;
         this.rotationYaw = f1;
      }
      if (!this.world.isRemote && rider instanceof EntityLivingBase) {
         EntityLivingBase living = (EntityLivingBase)rider;
         this.moveForward = living.moveForward;
         this.moveStrafing = living.moveStrafing;
         this.rotationYaw = living.rotationYawHead;
         this.prevRotationYaw = living.rotationYawHead;
         float nextMotionY = 0.0F;
         if ((living.rotationPitch < -10.0F || living.rotationPitch > 10.0F) && this.inWater && living.isInWater()) {
            nextMotionY = -living.rotationPitch / 1000.0F;
         }
         if (!this.isLocked()) {
            this.motionY += (double)nextMotionY;
         }
      }
   }

   @Override
   public boolean isPushedByWater() {
      return false;
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float amount) {
      Entity attacker = source.getTrueSource();
      return attacker != null && this.getPassengers().contains(attacker) ? false : super.attackEntityFrom(source, amount);
   }

   @Override
   protected void playStepSound(BlockPos pos, Block blockIn) {
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      this.setAir(100);
      this.current = (float)((double)this.current + 0.2D);
   }

   @Override
   public void travel(float strafe, float vertical, float forward) {
      if (this.isInWater()) {
         this.motionY += 0.021D;
      }
      super.travel(strafe, vertical, forward);
   }

   @Override
   public boolean canRiderInteract() {
      return false;
   }

   @Override
   public boolean getCanSpawnHere() {
      List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(32.0D, 32.0D, 32.0D));
      int n = 0;
      for (Entity entity : list) {
         if (entity instanceof EntityNessie) {
            ++n;
         }
      }
      return this.posY > 45.0D && this.posY < 63.0D && this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && n <= 2;
   }

   @Override
   public double getMountedYOffset() {
      return (double)this.height / 2.0D - 0.25D;
   }

   public boolean getSaddled() {
      return (this.dataManager.get(SADDLED) & 1) != 0;
   }

   public void setSaddled(boolean saddled) {
      this.dataManager.set(SADDLED, (byte)(saddled ? 1 : 0));
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return CandySounds.NESSIE;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource source) {
      return CandySounds.NESSIE_HURT;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return CandySounds.NESSIE_HURT;
   }

   private static int randomType(java.util.Random rand) {
      int type = rand.nextInt(4);
      if (rand.nextInt(20) == 0) {
         type = 5;
      }
      if (rand.nextInt(20) == 0) {
         type = 6;
      }
      if (rand.nextInt(40) == 0) {
         type = 7;
      }
      return type;
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      livingdata = super.onInitialSpawn(difficulty, livingdata);
      this.setType(randomType(this.rand));
      return livingdata;
   }

   @Override
   public EntityAgeable createChild(EntityAgeable ageable) {
      EntityNessie nessie = new EntityNessie(this.world);
      nessie.setType(randomType(nessie.rand));
      if (this.rand.nextInt(4) == 0) {
         this.dropItem(CandyCraft.WaterEmblem, 1);
      }
      return nessie;
   }

   @Override
   public boolean isLocked() {
      return this.dataManager.get(LOCKED) == 1;
   }

   @Override
   public void setLocked(boolean i) {
      this.dataManager.set(LOCKED, i ? 1 : 0);
   }

   @Override
   public void setPower(int i) {
      this.dataManager.set(POWER, i);
   }

   @Override
   public int getPower() {
      return this.dataManager.get(POWER);
   }
}
