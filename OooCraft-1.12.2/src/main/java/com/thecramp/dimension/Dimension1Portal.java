package com.thecramp.dimension;

import com.thecramp.Main.MainRegistry;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Dimension1Portal extends BlockPortal {
   private static Block blockAt(World world, int x, int y, int z) {
      return world.getBlockState(new BlockPos(x, y, z)).getBlock();
   }

   @Nullable
   @Override
   public AxisAlignedBB getCollisionBoundingBox(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos) {
      return NULL_AABB;
   }

   @Override
   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   // Disable the vanilla overworld portal behaviour (pigman spawning) inherited from BlockPortal.
   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
      int x = pos.getX();
      int y = pos.getY();
      int z = pos.getZ();
      byte b0 = 0;
      byte b1 = 1;
      if (blockAt(world, x - 1, y, z) == MainRegistry.hardGrass || blockAt(world, x + 1, y, z) == MainRegistry.hardGrass) {
         b0 = 1;
         b1 = 0;
      }
      int i1;
      for (i1 = y; blockAt(world, x, i1 - 1, z) == this; --i1) {
      }
      if (blockAt(world, x, i1 - 1, z) != MainRegistry.hardGrass) {
         world.setBlockToAir(pos);
      } else {
         int j1;
         for (j1 = 1; j1 < 4 && blockAt(world, x, i1 + j1, z) == this; ++j1) {
         }
         if (j1 == 3 && blockAt(world, x, i1 + j1, z) == MainRegistry.hardGrass) {
            boolean flag = blockAt(world, x - 1, y, z) == this || blockAt(world, x + 1, y, z) == this;
            boolean flag1 = blockAt(world, x, y, z - 1) == this || blockAt(world, x, y, z + 1) == this;
            if (flag && flag1) {
               world.setBlockToAir(pos);
            } else if (blockAt(world, x + b0, y, z + b1) != MainRegistry.hardGrass || blockAt(world, x - b0, y, z - b1) != this && blockAt(world, x - b0, y, z - b1) != MainRegistry.hardGrass || blockAt(world, x + b0, y, z + b1) != this) {
               world.setBlockToAir(pos);
            }
         } else {
            world.setBlockToAir(pos);
         }
      }
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }

   @Override
   public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (!world.isRemote && !entity.isRiding() && !entity.isBeingRidden() && entity instanceof EntityPlayerMP) {
         EntityPlayerMP thePlayer = (EntityPlayerMP)entity;
         MinecraftServer server = thePlayer.getServer();
         if (server == null) {
            return;
         }
         // Own cooldown (vanilla's timeUntilPortal is only read by the vanilla portal logic).
         net.minecraft.nbt.NBTTagCompound data = thePlayer.getEntityData();
         int now = thePlayer.ticksExisted;
         if (data.getInteger("ooo_portal_cd") > now) {
            data.setInteger("ooo_portal_cd", now + 10);
         } else if (thePlayer.dimension != DimensionRegistry.dimensionId) {
            data.setInteger("ooo_portal_cd", now + 10);
            server.getPlayerList().transferPlayerToDimension(thePlayer, DimensionRegistry.dimensionId, new TeleporterDimension1(server.getWorld(DimensionRegistry.dimensionId)));
         } else {
            data.setInteger("ooo_portal_cd", now + 10);
            server.getPlayerList().transferPlayerToDimension(thePlayer, 0, new TeleporterDimension1(server.getWorld(0)));
         }
      }
   }

   public boolean tryToCreatePortal(World world, int x, int y, int z) {
      byte b0 = 0;
      byte b1 = 0;
      if (blockAt(world, x - 1, y, z) == MainRegistry.hardGrass || blockAt(world, x + 1, y, z) == MainRegistry.hardGrass) {
         b0 = 1;
      }
      if (blockAt(world, x, y, z - 1) == MainRegistry.hardGrass || blockAt(world, x, y, z + 1) == MainRegistry.hardGrass) {
         b1 = 1;
      }
      if (b0 == b1) {
         return false;
      } else {
         if (blockAt(world, x - b0, y, z - b1) == Blocks.AIR) {
            x -= b0;
            z -= b1;
         }
         for (int l = -1; l <= 2; ++l) {
            for (int i1 = -1; i1 <= 3; ++i1) {
               boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;
               if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
                  Block j1 = blockAt(world, x + b0 * l, y + i1, z + b1 * l);
                  if (flag) {
                     if (j1 != MainRegistry.hardGrass) {
                        return false;
                     }
                  } else if (j1 != Blocks.AIR && j1 != Blocks.FIRE) {
                     return false;
                  }
               }
            }
         }
         IBlockState portalState = this.getDefaultState().withProperty(AXIS, b0 == 1 ? net.minecraft.util.EnumFacing.Axis.X : net.minecraft.util.EnumFacing.Axis.Z);
         for (int var11 = 0; var11 < 2; ++var11) {
            for (int i1 = 0; i1 < 3; ++i1) {
               world.setBlockState(new BlockPos(x + b0 * var11, y + i1, z + b1 * var11), portalState, 2);
            }
         }
         return true;
      }
   }
}
