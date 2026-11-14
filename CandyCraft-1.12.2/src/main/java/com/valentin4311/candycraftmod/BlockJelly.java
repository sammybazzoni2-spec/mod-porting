package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockJelly extends Block {
   public float fallDistance;
   private final double jump;

   public BlockJelly(String texture, double jump) {
      super(Material.SAND);
      this.setTickRandomly(true);
      this.jump = jump;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
      double var5 = 0.005D;
      if (this.jump == -1.0D) {
         var5 = 0.0D;
      }

      return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D - var5, 1.0D);
   }

   @Override
   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.TRANSLUCENT;
   }

   @Override
   public int quantityDropped(Random random) {
      return 1;
   }

   @Override
   public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance) {
      if ((this.jump == -1.0D || this.jump == 2.1D) && (entity instanceof EntityLivingBase || entity instanceof EntityPlayer)) {
         entity.fallDistance = 0.0F;
         entity.setInWeb();
      } else {
         super.onFallenUpon(world, pos, entity, fallDistance);
      }
   }

   @Override
   public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (this.jump != -1.0D && (entity instanceof EntityLiving || entity instanceof EntityPlayer) && entity.motionY <= 0.0D) {
         entity.motionY += this.jump;
      }
   }

   @Override
   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   @Override
   public boolean isFullCube(IBlockState state) {
      return false;
   }

   @Override
   public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
      return BlockFaceShape.SOLID;
   }

   @Override
   public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      Block block = world.getBlockState(pos.offset(side)).getBlock();
      return block == this ? false : super.shouldSideBeRendered(state, world, pos, side);
   }
}
