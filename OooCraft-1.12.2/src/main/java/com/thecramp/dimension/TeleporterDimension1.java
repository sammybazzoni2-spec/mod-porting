package com.thecramp.dimension;

import com.thecramp.Main.MainRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterDimension1 extends Teleporter {
   private final WorldServer worldServerInstance;

   public TeleporterDimension1(WorldServer worldserver) {
      super(worldserver);
      this.worldServerInstance = worldserver;
   }

   @Override
   public void placeInPortal(Entity entity, float rotationYaw) {
      // The 1.7.10 code also had a branch for dimension id 1 (build a platform); it was unreachable for dimension 8/0.
      if (!this.placeInExistingPortal(entity, rotationYaw)) {
         this.makePortal(entity);
         this.placeInExistingPortal(entity, rotationYaw);
      }
   }

   @Override
   public boolean placeInExistingPortal(Entity entity, float rotationYaw) {
      int radius = 64;
      double bestDist = -1.0D;
      int bx = 0;
      int by = 0;
      int bz = 0;
      int cx = MathHelper.floor(entity.posX);
      int cz = MathHelper.floor(entity.posZ);
      for (int x = cx - radius; x <= cx + radius; ++x) {
         double dx = (double)x + 0.5D - entity.posX;
         for (int z = cz - radius; z <= cz + radius; ++z) {
            if (!this.worldServerInstance.isBlockLoaded(new BlockPos(x, 64, z))) {
               continue;
            }
            double dz = (double)z + 0.5D - entity.posZ;
            for (int y = this.worldServerInstance.getActualHeight() - 1; y >= 0; --y) {
               if (this.worldServerInstance.getBlockState(new BlockPos(x, y, z)).getBlock() == MainRegistry.dimension1Portal) {
                  while (this.worldServerInstance.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == MainRegistry.dimension1Portal) {
                     --y;
                  }
                  double dy = (double)y + 0.5D - entity.posY;
                  double dist = dx * dx + dy * dy + dz * dz;
                  if (bestDist < 0.0D || dist < bestDist) {
                     bestDist = dist;
                     bx = x;
                     by = y;
                     bz = z;
                  }
               }
            }
         }
      }
      if (bestDist >= 0.0D) {
         // The 1.7.10 version used the z coordinate as the y position here (a bug); the portal's own y is used instead.
         entity.motionX = entity.motionY = entity.motionZ = 0.0D;
         entity.setLocationAndAngles((double)bx + 0.5D, (double)by, (double)bz + 0.5D, entity.rotationYaw, entity.rotationPitch);
         return true;
      }
      return false;
   }

   // The 1.7.10 version inherited vanilla's makePortal (an obsidian nether portal). Build a hardened grass portal instead.
   @Override
   public boolean makePortal(Entity entity) {
      int x = MathHelper.floor(entity.posX);
      int z = MathHelper.floor(entity.posZ);
      int y = this.worldServerInstance.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
      IBlockState frame = MainRegistry.hardGrass.getDefaultState();
      IBlockState air = Blocks.AIR.getDefaultState();
      IBlockState portal = MainRegistry.dimension1Portal.getDefaultState().withProperty(Dimension1Portal.AXIS, EnumFacing.Axis.X);
      // Clear the walk-through space on both sides, lay a floor.
      for (int l = -1; l <= 2; ++l) {
         for (int dz = -1; dz <= 1; ++dz) {
            this.worldServerInstance.setBlockState(new BlockPos(x + l, y - 1, z + dz), frame, 2);
            for (int i = 0; i <= 3; ++i) {
               if (dz != 0) {
                  this.worldServerInstance.setBlockState(new BlockPos(x + l, y + i, z + dz), air, 2);
               }
            }
         }
      }
      for (int l = -1; l <= 2; ++l) {
         for (int i = -1; i <= 3; ++i) {
            boolean edge = l == -1 || l == 2 || i == -1 || i == 3;
            if ((l != -1 && l != 2) || (i != -1 && i != 3)) {
               BlockPos pos = new BlockPos(x + l, y + i, z);
               this.worldServerInstance.setBlockState(pos, edge ? frame : portal, 2);
            }
         }
      }
      return true;
   }
}
