package com.valentin4311.candycraftmod;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockTexturedFence extends BlockFence {
   String TIndex;
   String T2Index;

   public BlockTexturedFence(String top, String side, Material material) {
      super(material, material.getMaterialMapColor());
      this.TIndex = top;
      this.T2Index = side;
      this.setLightOpacity(0);
   }

   @Override
   public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
      return true;
   }
}
