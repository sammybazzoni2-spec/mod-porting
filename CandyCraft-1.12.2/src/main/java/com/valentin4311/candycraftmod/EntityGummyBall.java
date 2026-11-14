package com.valentin4311.candycraftmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.item.Item;

public class EntityGummyBall extends EntityThrowable {
   private static final DataParameter<Integer> POWER = EntityDataManager.<Integer>createKey(EntityGummyBall.class, DataSerializers.VARINT);
   public int airState = 0;
   public EntityPlayer target = null;
   public EntityBossBeetle beetle = null;

   public EntityGummyBall(World world) {
      super(world);
      this.setSize(0.45F, 0.45F);
   }

   public EntityGummyBall(World world, EntityLivingBase thrower, int power) {
      super(world, thrower);
      this.setLocationAndAngles(thrower.posX, thrower.posY + (double)thrower.getEyeHeight(), thrower.posZ, thrower.rotationYaw, thrower.rotationPitch);
      this.setPowerful(power);
      float r = this.rand.nextFloat() / 20.0F - 0.05F;
      float r2 = this.rand.nextFloat() / 20.0F - 0.05F;
      if (this.getPowerful() == 0 || this.getPowerful() == 3) {
         r = 0.0F;
         r2 = 0.0F;
      }

      this.setSize(0.25F, 0.25F);
      this.setLocationAndAngles(thrower.posX, thrower.posY + (double)thrower.getEyeHeight(), thrower.posZ, thrower.rotationYaw, thrower.rotationPitch);
      this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
      this.posY -= 0.1D;
      this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
      this.setPosition(this.posX, this.posY, this.posZ);
      float f = this.getPowerful() == 3 ? 0.002F : 0.4F;
      this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
      this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
      this.motionY = (double)(-MathHelper.sin((this.rotationPitch + 0.0F) / 180.0F * (float)Math.PI) * f);
      this.shoot(this.motionX + (double)r, this.motionY, this.motionZ + (double)r2, this.getThrowVelocity(), 1.0F);
   }

   public EntityGummyBall(World world, double x, double y, double z) {
      super(world, x, y, z);
   }

   @Override
   protected void entityInit() {
      this.dataManager.register(POWER, 0);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("power", this.getPowerful());
      compound.setInteger("airState", this.airState);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setPowerful(compound.getInteger("power"));
      this.airState = compound.getInteger("airState");
   }

   protected float getThrowVelocity() {
      return this.getPowerful() != 3 && this.airState != 3 ? 1.5F : 0.8F;
   }

   @Override
   public boolean canBeCollidedWith() {
      return true;
   }

   @Override
   public float getCollisionBorderSize() {
      return 1.0F;
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (!this.isEntityInvulnerable(source) && this.getPowerful() == 3) {
         this.markVelocityChanged();
         if (source.getTrueSource() != null) {
            Vec3d vec3 = source.getTrueSource().getLookVec();
            if (vec3 != null) {
               this.motionX = vec3.x;
               this.motionY = vec3.y;
               this.motionZ = vec3.z;
            }
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Override
   protected float getGravityVelocity() {
      return this.airState == 1 ? 0.05F : (this.getPowerful() != 2 && this.getPowerful() != 3 ? 0.03F : 5.0E-4F);
   }

   public void setPowerful(int power) {
      this.dataManager.set(POWER, power);
   }

   public int getPowerful() {
      return this.dataManager.get(POWER);
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.world.isRemote && this.getPowerful() == 1) {
         this.spawnParticle();
      }

      if (this.world.isRemote && this.getPowerful() == 2) {
         this.spawnParticle2();
      }

      if (this.world.isRemote && this.getPowerful() == 3) {
         this.spawnParticle3();
      }
   }

   @Override
   public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
      float f2 = MathHelper.sqrt(x * x + y * y + z * z);
      x /= (double)f2;
      y /= (double)f2;
      z /= (double)f2;
      if (this.getPowerful() > 0 && this.getPowerful() != 3) {
         x += this.rand.nextGaussian() * 0.0075D * (double)inaccuracy;
         y += this.rand.nextGaussian() * 0.0075D * (double)inaccuracy;
         z += this.rand.nextGaussian() * 0.0075D * (double)inaccuracy;
      }

      x *= (double)velocity;
      y *= (double)velocity;
      z *= (double)velocity;
      this.motionX = x;
      this.motionY = y;
      this.motionZ = z;
      float f3 = MathHelper.sqrt(x * x + z * z);
      this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
      this.rotationPitch = (float)(Math.atan2(y, (double)f3) * 180.0D / Math.PI);
      this.prevRotationYaw = this.rotationYaw;
      this.prevRotationPitch = this.rotationPitch;
   }

   @Override
   protected void onImpact(RayTraceResult result) {
      if (result.entityHit instanceof EntityLivingBase) {
         Entity hit = result.entityHit;
         float damage = this.getPowerful() == 1 ? 6.0F : (this.getPowerful() == 2 ? 4.0F : 0.1F);
         hit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
         if (this.getPowerful() < 2) {
            ((EntityLivingBase)hit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 2));
         } else if (this.getPowerful() == 2) {
            hit.setFire(7);
         } else if (!(hit instanceof EntityBossBeetle)) {
            float f4 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            hit.addVelocity(this.motionX * 0.6D / (double)f4, 0.1D, this.motionZ * 0.6D / (double)f4);
         }

         this.setDead();
      }

      for (int i = 0; i < 8; ++i) {
         if (this.world.isRemote && this.getPowerful() < 2) {
            this.spawnParticle();
         }

         if (this.world.isRemote && this.getPowerful() == 2) {
            this.spawnParticle2();
         }

         if (this.world.isRemote && this.getPowerful() == 3) {
            this.spawnParticle3();
         }
      }

      if (!this.world.isRemote) {
         this.setDead();
      }
   }

   @SideOnly(Side.CLIENT)
   public void spawnParticle() {
      this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, Item.getIdFromItem(CandyCraft.GummyBall), 0);
   }

   @SideOnly(Side.CLIENT)
   public void spawnParticle2() {
      this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX - 0.5D + this.rand.nextDouble(), this.posY - 0.5D + this.rand.nextDouble(), this.posZ - 0.5D + this.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
   }

   @SideOnly(Side.CLIENT)
   public void spawnParticle3() {
      this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, Item.getIdFromItem(CandyCraft.GummyBall), 1);
   }
}
