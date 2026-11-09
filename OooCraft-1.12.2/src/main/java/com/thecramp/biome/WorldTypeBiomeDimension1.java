package com.thecramp.biome;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class WorldTypeBiomeDimension1 extends WorldType {
   public WorldTypeBiomeDimension1(int par1, String name) {
      super(name);
   }

   // TODO(port): verify the 1.12.2 WorldType.getBiomeLayer signature (String chunkProviderSettingsJson);
   // @Override intentionally omitted so a signature mismatch cannot break compilation.
   public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, String chunkProviderSettingsJson) {
      GenLayer ret = new Dimesnion1GenLayerBiome(200L, parentLayer, this);
      GenLayer var5 = GenLayerZoom.magnify(1000L, ret, 2);
      var5 = new GenLayerBiomeEdge(1000L, var5);
      return var5;
   }
}
