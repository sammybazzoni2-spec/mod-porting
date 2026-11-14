package com.valentin4311.candycraftmod;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleporterCandy extends Teleporter {
   private final WorldServer worldServerInstance;
   private final Random random;
   private final Map<Long, BlockPos> destinationCoordinateCache = new HashMap<Long, BlockPos>();

   public TeleporterCandy(WorldServer par1WorldServer) {
      super(par1WorldServer);
      this.worldServerInstance = par1WorldServer;
      this.random = new Random(par1WorldServer.getSeed());
   }

   public void placeEntity(World world, Entity entity, float yaw) {
      this.placeInPortal(entity, yaw);
   }

   @Override
   public void placeInPortal(Entity par1Entity, float par8) {
      int dim = this.worldServerInstance.provider.getDimension();
      if (dim != CandyCraft.idDimension && dim != 0) {
         int i = MathHelper.floor(par1Entity.posX);
         int j = MathHelper.floor(par1Entity.posY) - 1;
         int k = MathHelper.floor(par1Entity.posZ);

         for(int l = -2; l <= 2; ++l) {
            for(int i1 = -2; i1 <= 2; ++i1) {
               for(int j1 = -1; j1 < 3; ++j1) {
                  BlockPos p = new BlockPos(i + i1, j + j1, k - l);
                  boolean flag = j1 < 0;
                  if (flag) {
                     this.worldServerInstance.setBlockState(p, CandyCraft.SugarBlock.getDefaultState());
                  } else {
                     this.worldServerInstance.setBlockToAir(p);
                  }
               }
            }
         }

         if (par1Entity instanceof EntityLivingBase) {
            ((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 300, 30));
         }
         par1Entity.setLocationAndAngles((double)i, 300.0D, (double)k, par1Entity.rotationYaw, par1Entity.rotationPitch);
         par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
      } else {
         // TODO(port): the 1.7 'enterCandyWorld' achievement was removed (achievements no longer exist in 1.12).
         this.placeInExistingPortal(par1Entity, par8);
      }

   }

   @Override
   public boolean placeInExistingPortal(Entity par1Entity, float par8) {
      int range = 128;
      double best = -1.0D;
      int i = 0;
      int j = 0;
      int k = 0;
      int l = MathHelper.floor(par1Entity.posX);
      int i1 = MathHelper.floor(par1Entity.posZ);
      long key = ChunkPos.asLong(l, i1);
      if (this.destinationCoordinateCache.containsKey(key)) {
         BlockPos cached = this.destinationCoordinateCache.get(key);
         best = 0.0D;
         i = cached.getX();
         j = cached.getY();
         k = cached.getZ();
      } else {
         for(int k1 = l - range; k1 <= l + range; ++k1) {
            double d5 = (double)k1 + 0.5D - par1Entity.posX;

            for(int l1 = i1 - range; l1 <= i1 + range; ++l1) {
               double d6 = (double)l1 + 0.5D - par1Entity.posZ;

               for(int i2 = this.worldServerInstance.getActualHeight() - 1; i2 >= 0; --i2) {
                  if (this.worldServerInstance.getBlockState(new BlockPos(k1, i2, l1)).getBlock() == CandyCraft.CandyPortal) {
                     while(this.worldServerInstance.getBlockState(new BlockPos(k1, i2 - 1, l1)).getBlock() == CandyCraft.CandyPortal) {
                        --i2;
                     }

                     double d4 = (double)i2 + 0.5D - par1Entity.posY;
                     double d7 = d5 * d5 + d4 * d4 + d6 * d6;
                     if (best < 0.0D || d7 < best) {
                        best = d7;
                        i = k1;
                        j = i2;
                        k = l1;
                     }
                  }
               }
            }
         }

         if (best >= 0.0D) {
            this.destinationCoordinateCache.put(key, new BlockPos(i, j, k));
         }
      }

      if (par1Entity instanceof EntityLivingBase) {
         ((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 300, 30));
      }

      par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
      if (best >= 0.0D) {
         par1Entity.setLocationAndAngles((double)i + 0.5D + 2.0D, this.groundY(i + 2, k + 2), (double)k + 0.5D + 2.0D, par1Entity.rotationYaw, par1Entity.rotationPitch);
         return true;
      } else {
         par1Entity.setLocationAndAngles((double)l + 0.5D, this.groundY(l, i1), (double)i1 + 0.5D, par1Entity.rotationYaw, par1Entity.rotationPitch);
         return false;
      }
   }

   private double groundY(int x, int z) {
      this.worldServerInstance.getChunk(x >> 4, z >> 4);
      BlockPos top = this.worldServerInstance.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));
      return (double)Math.max(top.getY(), 1) + 1.0D;
   }

   @Override
   public boolean makePortal(Entity par1Entity) {
      return false;
   }
}
