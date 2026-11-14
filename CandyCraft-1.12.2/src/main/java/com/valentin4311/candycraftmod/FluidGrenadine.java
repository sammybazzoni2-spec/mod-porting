package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidGrenadine extends Fluid {
   public FluidGrenadine(String fluidName) {
      super(fluidName, new ResourceLocation("candycraftmod", "blocks/b64"), new ResourceLocation("candycraftmod", "blocks/b64"));
   }

   public Fluid setBlock(Block block) {
      this.block = block;
      return this;
   }
}
