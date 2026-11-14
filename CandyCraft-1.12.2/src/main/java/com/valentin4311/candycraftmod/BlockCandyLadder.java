package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.BlockLadder;

public class BlockCandyLadder extends BlockLadder {
   protected BlockCandyLadder() {
   }

   @Override
   public int quantityDropped(Random random) {
      return 1;
   }
}
