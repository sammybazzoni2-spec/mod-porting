package com.valentin4311.candycraftmod;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGlueDynamite extends EntityThrowable {
   private static final DataParameter<Boolean> GLUED_SYNC = EntityDataManager.<Boolean>createKey(EntityGlueDynamite.class, DataSerializers.BOOLEAN);
   public int fuse = 65;
   public boolean glued = false;
   public boolean gluedEntity = false;
   public boolean chocked = false;

   public EntityGlueDynamite(World world) {
      super(world);
      this.setSize(0.05F, 0.05F);
   }

   public EntityGlueDynamite(World world, EntityLivingBase thrower) {
      super(world, thrower);
      this.shoot(thrower, thrower.rotationPitch, thrower.rotationYaw, 0.0F, 1.5F, 1.0F);
   }

   public EntityGlueDynamite(World world, double x, double y, double z) {
      super(world, x, y, z);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(GLUED_SYNC, false);
   }

   private void setGluedSync() {
      this.glued = true;
      this.dataManager.set(GLUED_SYNC, true);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public boolean isInRangeToRenderDist(double distance) {
      double d1 = this.getEntityBoundingBox().getAverageEdgeLength() * 5.0D * 4.0D;
      d1 *= 64.0D;
      return distance < d1 * d1;
   }

   @Override
   public void onUpdate() {
      if (this.world.isRemote && this.dataManager.get(GLUED_SYNC) && !this.glued) {
         this.glued = true;
      }

      if (this.world.isRemote && this.glued) {
         for (int i = 0; i < 10; ++i) {
            this.world.spawnParticle(EnumParticleTypes.REDSTONE, this.posX + (this.rand.nextDouble() * 2.0D - 1.0D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() * 2.0D - 1.0D) * (double)this.width, 1.0D, 0.5D, 0.5D);
         }
      }

      if (this.glued && this.gluedEntity) {
         this.setDead();
      }

      super.onUpdate();
      --this.fuse;
      if (!this.world.isRemote && this.fuse <= 0) {
         boolean grief = this.world.getGameRules().getBoolean("mobGriefing");
         this.world.createExplosion(this, this.posX, this.posY, this.posZ, 3.0F, grief);
         this.setDead();
      }

      if (this.glued) {
         this.posY = this.prevPosY;
      }

      if (this.world.isRemote) {
         for (int i = 0; i < 2; ++i) {
            this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
         }
      }
   }

   @Override
   protected void onImpact(RayTraceResult result) {
      if (!this.world.isRemote) {
         if (result.entityHit != null && !this.chocked) {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
         }

         if (result.typeOfHit == RayTraceResult.Type.ENTITY && result.entityHit instanceof EntityLivingBase) {
            ServerTick.dynamiteCallBack.add(new DynamiteCallBack(result.entityHit, this.fuse));
            this.setGluedSync();
            this.gluedEntity = true;
         }

         if (result.typeOfHit == RayTraceResult.Type.BLOCK && !this.chocked) {
            BlockPos pos = result.getBlockPos();
            IBlockState state = this.world.getBlockState(pos);
            if (state.getCollisionBoundingBox(this.world, pos) != null) {
               this.setDead();
               EntityGlueDynamite dyna = new EntityGlueDynamite(this.world);
               dyna.setPosition(this.posX, this.posY, this.posZ);
               dyna.setGluedSync();
               dyna.fuse = this.fuse;
               dyna.motionX = 0.0D;
               dyna.motionY = 0.0D;
               dyna.motionZ = 0.0D;
               dyna.chocked = true;
               this.world.spawnEntity(dyna);
            }
         }
      }
   }
}
