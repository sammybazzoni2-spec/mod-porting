package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockCandyStairs extends BlockStairs {
   protected BlockCandyStairs(Block block, int meta) {
      super(block.getStateFromMeta(meta));
   }
}
