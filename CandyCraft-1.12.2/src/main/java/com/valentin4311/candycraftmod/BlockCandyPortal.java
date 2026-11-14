package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCandyPortal extends BlockPortal {
   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
   }

   public boolean tryToCreatePortal(World world, BlockPos origin) {
      int b0 = 0;
      int b1 = 0;
      int px = origin.getX();
      int py = origin.getY();
      int pz = origin.getZ();
      if (world.getBlockState(new BlockPos(px - 1, py, pz)).getBlock() == CandyCraft.SugarBlock || world.getBlockState(new BlockPos(px + 1, py, pz)).getBlock() == CandyCraft.SugarBlock) {
         b0 = 1;
      }

      if (world.getBlockState(new BlockPos(px, py, pz - 1)).getBlock() == CandyCraft.SugarBlock || world.getBlockState(new BlockPos(px, py, pz + 1)).getBlock() == CandyCraft.SugarBlock) {
         b1 = 1;
      }

      if (b0 == b1) {
         return false;
      } else {
         if (world.isAirBlock(new BlockPos(px - b0, py, pz - b1))) {
            px -= b0;
            pz -= b1;
         }

         for (int l = -1; l <= 2; ++l) {
            for (int i1 = -1; i1 <= 3; ++i1) {
               boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;
               if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
                  BlockPos p = new BlockPos(px + b0 * l, py + i1, pz + b1 * l);
                  boolean isAirBlock = world.isAirBlock(p);
                  if (flag) {
                     if (world.getBlockState(p).getBlock() != CandyCraft.SugarBlock) {
                        return false;
                     }
                  } else if (!isAirBlock) {
                     return false;
                  }
               }
            }
         }

         IBlockState portal = CandyCraft.CandyPortal.getDefaultState().withProperty(AXIS, b0 == 1 ? EnumFacing.Axis.X : EnumFacing.Axis.Z);
         for (int var11 = 0; var11 < 2; ++var11) {
            for (int i1 = 0; i1 < 3; ++i1) {
               world.setBlockState(new BlockPos(px + b0 * var11, py + i1, pz + b1 * var11), portal, 2);
            }
         }

         return true;
      }
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
      for (int l = 0; l < 4; ++l) {
         double d0 = (double)((float)pos.getX() + rand.nextFloat());
         double d1 = (double)((float)pos.getY() + rand.nextFloat());
         double d2 = (double)((float)pos.getZ() + rand.nextFloat());
         int i1 = rand.nextInt(2) * 2 - 1;
         double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
         double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
         double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
         if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
            d0 = (double)pos.getX() + 0.5D + 0.25D * (double)i1;
            d3 = (double)(rand.nextFloat() * 2.0F * (float)i1);
         } else {
            d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)i1;
            d5 = (double)(rand.nextFloat() * 2.0F * (float)i1);
         }

         EntityCandyPortalFX fx = new EntityCandyPortalFX(world, d0, d1, d2, d3, d4, d5);
         Minecraft.getMinecraft().effectRenderer.addEffect(fx);
      }
   }

   @Override
   public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (!world.isRemote && !entity.isRiding() && !entity.isBeingRidden()) {
         this.travelToDimension(world.provider.getDimension() != CandyCraft.idDimension ? CandyCraft.idDimension : 0, entity, world);
      }
   }

   public void travelToDimension(int dim, Entity ent, World world) {
      if (!world.isRemote && !ent.isDead) {
         MinecraftServer server = world.getMinecraftServer();
         if (server == null) {
            return;
         }

         WorldServer target = server.getWorld(dim);
         if (ent instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP)ent;
            server.getPlayerList().transferPlayerToDimension(player, dim, new TeleporterCandy(target));
         } else {
            ent.changeDimension(dim, new TeleporterCandy(target));
         }
      }
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
      int b0 = 0;
      int b1 = 1;
      if (world.getBlockState(pos.west()).getBlock() == this || world.getBlockState(pos.east()).getBlock() == this) {
         b0 = 1;
         b1 = 0;
      }

      int i1;
      for (i1 = pos.getY(); world.getBlockState(new BlockPos(pos.getX(), i1 - 1, pos.getZ())).getBlock() == this; --i1) {
      }

      if (world.getBlockState(new BlockPos(pos.getX(), i1 - 1, pos.getZ())).getBlock() != CandyCraft.SugarBlock) {
         world.setBlockToAir(pos);
      } else {
         int j1;
         for (j1 = 1; j1 < 4 && world.getBlockState(new BlockPos(pos.getX(), i1 + j1, pos.getZ())).getBlock() == this; ++j1) {
         }

         if (j1 == 3 && world.getBlockState(new BlockPos(pos.getX(), i1 + j1, pos.getZ())).getBlock() == CandyCraft.SugarBlock) {
            boolean flag = world.getBlockState(pos.west()).getBlock() == this || world.getBlockState(pos.east()).getBlock() == this;
            boolean flag1 = world.getBlockState(pos.north()).getBlock() == this || world.getBlockState(pos.south()).getBlock() == this;
            if (flag && flag1) {
               world.setBlockToAir(pos);
            } else {
               Block a = world.getBlockState(pos.add(b0, 0, b1)).getBlock();
               Block b = world.getBlockState(pos.add(-b0, 0, -b1)).getBlock();
               if ((a != CandyCraft.SugarBlock || b != this) && (b != CandyCraft.SugarBlock || a != this)) {
                  world.setBlockToAir(pos);
               }
            }
         } else {
            world.setBlockToAir(pos);
         }
      }
   }
}
