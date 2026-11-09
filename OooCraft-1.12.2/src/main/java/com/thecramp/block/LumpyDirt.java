package com.thecramp.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class LumpyDirt extends Block {
   public LumpyDirt() {
      super(Material.GRASS);
      this.setSoundType(SoundType.PLANT);
      this.setHarvestLevel("shovel", 0);
   }
}
