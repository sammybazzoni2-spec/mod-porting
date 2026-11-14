package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockTexturedWall extends BlockWall {
   String TIndex;
   String T2Index;

   public BlockTexturedWall(String top, String side, Block block) {
      super(block);
      this.TIndex = top;
      this.T2Index = side;
      this.setLightOpacity(0);
   }

   @Override
   public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
      if (this.getCreativeTab() == tab || tab == CreativeTabs.SEARCH) {
         items.add(new ItemStack(this, 1, 0));
      }
   }

   @Override
   public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
      return true;
   }
}
