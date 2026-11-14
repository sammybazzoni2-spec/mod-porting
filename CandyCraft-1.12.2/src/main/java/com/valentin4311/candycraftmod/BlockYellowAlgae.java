package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockYellowAlgae extends BlockAlgae {
   public BlockYellowAlgae(Material material) {
      super(material);
   }

   @Override
   protected boolean belowOk(Block below) {
      return below == CandyCraft.FlourBlock || below == this;
   }
}
