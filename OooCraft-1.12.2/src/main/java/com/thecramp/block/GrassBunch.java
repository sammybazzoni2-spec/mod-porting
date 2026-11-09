package com.thecramp.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GrassBunch extends Block {
   public GrassBunch() {
      super(Material.GRASS);
      this.setSoundType(SoundType.PLANT);
   }
}
