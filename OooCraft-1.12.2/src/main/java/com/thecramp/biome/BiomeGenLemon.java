package com.thecramp.biome;

import com.thecramp.Main.MainRegistry;
import net.minecraft.world.biome.Biome;

public class BiomeGenLemon extends Biome {
   public BiomeGenLemon(Biome.BiomeProperties properties) {
      super(properties);
      this.decorator.treesPerChunk = 3;
      this.decorator.grassPerChunk = 0;
      this.decorator.generateFalls = false;
      this.topBlock = MainRegistry.lemonGrass.getDefaultState();
      this.fillerBlock = MainRegistry.lemonDirt.getDefaultState();
      this.spawnableCreatureList.clear();
   }
}
