package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockIceCream extends Block {
   public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 3);

   protected BlockIceCream(Material material) {
      super(material);
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
   public int damageDropped(IBlockState state) {
      return state.getValue(VARIANT);
   }

   @Override
   public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
      if (this.getCreativeTab() == tab || tab == CreativeTabs.SEARCH) {
         for (int i = 0; i < 4; ++i) {
            items.add(new ItemStack(this, 1, i));
         }
      }
   }
}
