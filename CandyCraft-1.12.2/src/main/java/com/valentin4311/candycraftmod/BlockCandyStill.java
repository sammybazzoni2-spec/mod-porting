package com.valentin4311.candycraftmod;

import net.minecraft.block.material.Material;

public class BlockCandyStill extends BlockCandyLiquid {
   protected BlockCandyStill(Material material) {
      super(material);
      this.setTickRandomly(false);
   }
}
