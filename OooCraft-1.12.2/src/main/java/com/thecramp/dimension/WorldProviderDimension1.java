package com.thecramp.dimension;

import com.thecramp.biome.BiomeRegistry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderDimension1 extends WorldProvider {
   @Override
   protected void init() {
      this.hasSkyLight = true;
      this.biomeProvider = new BiomeProviderSingle(BiomeRegistry.biomeDimension1);
   }

   @Override
   public IChunkGenerator createChunkGenerator() {
      // The 1.7.10 version returned null (createChunkGeneration); use the vanilla overworld generator
      // so the dimension is usable. Surface blocks come from BiomeGenDimension1.
      return new ChunkGeneratorOverworld(this.world, this.world.getSeed(), this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getWorldInfo().getGeneratorOptions());
   }

   @Override
   public DimensionType getDimensionType() {
      return DimensionRegistry.dimensionType;
   }
}
