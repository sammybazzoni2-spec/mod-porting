package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockCandyLog extends BlockLog {
   public static final String[] woodType = new String[]{"Guimauve"};
   public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 3);

   protected BlockCandyLog() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, 0).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
   }

   @Override
   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, LOG_AXIS, VARIANT);
   }

   @Override
   public IBlockState getStateFromMeta(int meta) {
      IBlockState state = this.getDefaultState().withProperty(VARIANT, meta & 3);
      switch (meta & 12) {
         case 0:
            return state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
         case 4:
            return state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
         case 8:
            return state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
         default:
            return state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int meta = state.getValue(VARIANT);
      switch (state.getValue(LOG_AXIS)) {
         case X:
            meta |= 4;
            break;
         case Z:
            meta |= 8;
            break;
         case NONE:
            meta |= 12;
            break;
         default:
            break;
      }

      return meta;
   }

   @Override
   public int quantityDropped(Random random) {
      return 1;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Item.getItemFromBlock(CandyCraft.CandyLog);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return state.getValue(VARIANT);
   }

   @Override
   protected ItemStack getSilkTouchDrop(IBlockState state) {
      return new ItemStack(Item.getItemFromBlock(this), 1, state.getValue(VARIANT));
   }

   @Override
   public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
      if (this.getCreativeTab() == tab || tab == CreativeTabs.SEARCH) {
         items.add(new ItemStack(this, 1, 0));
         items.add(new ItemStack(this, 1, 1));
         items.add(new ItemStack(this, 1, 2));
      }
   }

   public static int limitToValidMetadata(int meta) {
      return meta & 0;
   }

   @Override
   public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
      return true;
   }

   @Override
   public boolean isWood(IBlockAccess world, BlockPos pos) {
      return true;
   }
}
