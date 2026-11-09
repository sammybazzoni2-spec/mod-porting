package com.thecramp.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DungeonBlock1 extends Block {
   public DungeonBlock1() {
      super(Material.GRASS);
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
      super.onBlockPlacedBy(world, pos, state, placer, stack);
      world.setBlockToAir(pos);
      world.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState(), 3);
   }
}
