package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCherry extends Block {
   private static final AxisAlignedBB BOX = new AxisAlignedBB(0.3D, 0.6D, 0.3D, 0.7D, 1.0D, 0.7D);

   protected BlockCherry(Material material) {
      super(material);
   }

   @Override
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return BOX;
   }

   @Override
   public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
      return true;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return world.getBlockState(pos.up()).getBlock() == CandyCraft.CandyLeave;
   }

   public boolean canBlockStay(World world, BlockPos pos) {
      return world.getBlockState(pos.up()).getBlock() == CandyCraft.CandyLeave;
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
      if (!this.canBlockStay(world, pos)) {
         world.setBlockToAir(pos);
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
      return BlockFaceShape.UNDEFINED;
   }

   @Override
   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return CandyCraft.CandiedCherry;
   }
}
