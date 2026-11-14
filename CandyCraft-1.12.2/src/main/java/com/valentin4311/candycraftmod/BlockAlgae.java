package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockAlgae extends Block implements IShearable {
   protected BlockAlgae(Material material) {
      super(material);
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
      return NULL_AABB;
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
   public int quantityDropped(java.util.Random random) {
      return 0;
   }

   @Override
   public boolean isReplaceable(IBlockAccess world, BlockPos pos) {
      return false;
   }

   protected static boolean isWater(Block block) {
      return block == Blocks.WATER || block == Blocks.FLOWING_WATER;
   }

   public boolean getChangeOpacity(IBlockState state) {
      Block i = state.getBlock();
      return (state.isOpaqueCube() && state.isFullCube() && !(i instanceof BlockLeaves)) || isWater(i) || i instanceof BlockAlgae;
   }

   /** Block that may sit directly above this algae (water or the algae itself). */
   protected boolean aboveOk(Block above) {
      return isWater(above) || above == this;
   }

   /** Block that may sit directly below this algae. */
   protected boolean belowOk(Block below) {
      return below == CandyCraft.FlourBlock;
   }

   public boolean canBlockStay(World world, BlockPos pos) {
      IBlockState n = world.getBlockState(pos.north());
      IBlockState s = world.getBlockState(pos.south());
      IBlockState w = world.getBlockState(pos.west());
      IBlockState e = world.getBlockState(pos.east());
      return this.getChangeOpacity(n) && this.getChangeOpacity(s) && this.getChangeOpacity(w) && this.getChangeOpacity(e) && this.aboveOk(world.getBlockState(pos.up()).getBlock()) && this.belowOk(world.getBlockState(pos.down()).getBlock());
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      Block i = world.getBlockState(pos.up()).getBlock();
      if (isWater(i) || i == this) {
         world.setBlockState(pos, Blocks.FLOWING_WATER.getDefaultState());
      }
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
      super.neighborChanged(state, world, pos, block, fromPos);
      this.checkChange(world, pos);
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return super.canPlaceBlockAt(world, pos) && this.canBlockStay(world, pos);
   }

   protected final void checkChange(World world, BlockPos pos) {
      if (!this.canBlockStay(world, pos)) {
         this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
         world.setBlockToAir(pos);
      }
   }

   @Override
   public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
      return true;
   }

   @Override
   public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
      return NonNullList.withSize(1, new ItemStack(this, 1, 0));
   }
}
