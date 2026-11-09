package com.thecramp.biome;

import com.thecramp.Main.MainRegistry;
import net.minecraft.world.biome.Biome;

public class BiomeGenDimension1 extends Biome {
   public BiomeGenDimension1(Biome.BiomeProperties properties) {
      super(properties);
      this.decorator.treesPerChunk = 0;
      this.decorator.grassPerChunk = 0;
      this.decorator.generateFalls = false;
      this.topBlock = MainRegistry.lumpyGrass.getDefaultState();
      this.fillerBlock = MainRegistry.lumpyDirt.getDefaultState();
      this.spawnableCreatureList.clear();
   }
}
