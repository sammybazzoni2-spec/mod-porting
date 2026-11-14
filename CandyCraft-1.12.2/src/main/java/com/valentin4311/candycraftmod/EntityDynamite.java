package com.valentin4311.candycraftmod;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDynamite extends EntityThrowable {
   public int fuse = 65;
   public boolean chocked = false;

   public EntityDynamite(World world) {
      super(world);
      this.setSize(0.25F, 0.25F);
   }

   public EntityDynamite(World world, EntityLivingBase thrower) {
      super(world, thrower);
      this.shoot(thrower, thrower.rotationPitch, thrower.rotationYaw, 0.0F, 1.5F, 1.0F);
   }

   public EntityDynamite(World world, double x, double y, double z) {
      super(world, x, y, z);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public boolean isInRangeToRenderDist(double distance) {
      double d1 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;
      d1 *= 64.0D;
      return distance < d1 * d1;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      --this.fuse;
      if (!this.world.isRemote && this.fuse <= 0) {
         boolean grief = this.world.getGameRules().getBoolean("mobGriefing");
         this.world.createExplosion(this, this.posX, this.posY, this.posZ, 3.0F, grief);
         this.setDead();
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
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
            this.chocked = true;
         }

         if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos pos = result.getBlockPos();
            IBlockState state = this.world.getBlockState(pos);
            if (state.getCollisionBoundingBox(this.world, pos) != null) {
               this.motionY = 0.0D;
               int side = result.sideHit.getIndex();
               if (side == 2 || side == 3) {
                  this.motionZ = 0.0D;
                  this.motionX /= 10.0D;
               }

               if (side == 4 || side == 5) {
                  this.motionX = 0.0D;
                  this.motionZ /= 10.0D;
               }

               if (side == 1) {
                  this.motionX /= 10.0D;
                  this.motionZ /= 10.0D;
               }

               if (side == 0) {
                  this.motionX /= 5.0D;
                  this.motionZ /= 5.0D;
               }

               this.chocked = true;
            }
         }
      }
   }
}
