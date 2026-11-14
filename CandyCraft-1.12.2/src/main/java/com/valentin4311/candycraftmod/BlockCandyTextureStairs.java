package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;

public class BlockCandyTextureStairs extends BlockStairs {
   String TIndex;
   String T2Index;

   public BlockCandyTextureStairs(Block block, String top, String side, Material material) {
      super(block.getDefaultState());
      this.TIndex = top;
      this.T2Index = side;
      this.setLightOpacity(0);
   }
}
