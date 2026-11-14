package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockTallCandyGrass extends BlockBush implements IShearable {
   public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 3);
   private static final AxisAlignedBB BOX = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);

   protected BlockTallCandyGrass() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, 0));
   }

   @Override
   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, VARIANT);
   }

   @Override
   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, meta & 3);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return state.getValue(VARIANT);
   }

   @Override
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return BOX;
   }

   @Override
   protected boolean canSustainBush(IBlockState state) {
      Block block = state.getBlock();
      return block == CandyCraft.PuddingBlock || block == CandyCraft.FlourBlock || block == CandyCraft.CandySoil;
   }

   @Override
   public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
      java.util.Random rand = world instanceof World ? ((World)world).rand : new java.util.Random();
      if (rand.nextInt(40) == 0) {
         drops.add(new ItemStack(CandyCraft.Dragibus));
      }
   }

   @Override
   public boolean isReplaceable(IBlockAccess world, BlockPos pos) {
      return true;
   }

   @Override
   public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
      if (this.getCreativeTab() == tab || tab == CreativeTabs.SEARCH) {
         items.add(new ItemStack(this, 1, 0));
         items.add(new ItemStack(this, 1, 1));
         items.add(new ItemStack(this, 1, 2));
         items.add(new ItemStack(this, 1, 3));
      }
   }

   @Override
   public int damageDropped(IBlockState state) {
      return state.getValue(VARIANT);
   }

   @Override
   public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
      return true;
   }

   @Override
   public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
      return NonNullList.withSize(1, new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT)));
   }

   @Override
   public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
      return this.canSustainBush(world.getBlockState(pos.down()));
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && this.canBlockStay(world, pos, this.getDefaultState());
   }
}
