package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGreenAlgae extends BlockAlgae {
   public BlockGreenAlgae(Material material) {
      super(material);
   }

   @Override
   protected boolean aboveOk(Block above) {
      return isWater(above);
   }
}
