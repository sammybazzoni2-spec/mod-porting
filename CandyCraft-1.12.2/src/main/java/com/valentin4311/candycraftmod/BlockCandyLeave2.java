package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockCandyLeave2 extends BlockCandyLeave {
   public static final String[][] field_150130_N = new String[][]{{"B100"}, {"B100_1"}};

   @Override
   public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
      if (this.getCreativeTab() == tab || tab == CreativeTabs.SEARCH) {
         items.add(new ItemStack(this, 1, 0));
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Items.SUGAR;
   }

   @Override
   public int damageDropped(IBlockState state) {
      return 0;
   }

   @Override
   public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
      if (this.rand(world).nextInt(30) == 0) {
         drops.add(new ItemStack(Items.SUGAR, 1, 0));
      }
   }

   @Override
   public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
      return NonNullList.withSize(1, new ItemStack(this, 1, 0));
   }

   public static int getFoliageColorBasic() {
      return Integer.MAX_VALUE;
   }
}
