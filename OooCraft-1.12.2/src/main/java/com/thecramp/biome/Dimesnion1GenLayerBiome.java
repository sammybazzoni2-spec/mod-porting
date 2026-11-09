package com.thecramp.biome;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Biomes;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;

public class Dimesnion1GenLayerBiome extends GenLayer {
   private List<BiomeManager.BiomeEntry> desertBiomes = new ArrayList<BiomeManager.BiomeEntry>();
   private List<BiomeManager.BiomeEntry> warmBiomes = new ArrayList<BiomeManager.BiomeEntry>();
   private List<BiomeManager.BiomeEntry> coolBiomes = new ArrayList<BiomeManager.BiomeEntry>();
   private List<BiomeManager.BiomeEntry> icyBiomes = new ArrayList<BiomeManager.BiomeEntry>();

   public Dimesnion1GenLayerBiome(long seed, GenLayer parentLayer, WorldType worldType) {
      super(seed);
      this.parent = parentLayer;
      addAll(this.desertBiomes, BiomeManager.BiomeType.DESERT);
      addAll(this.warmBiomes, BiomeManager.BiomeType.WARM);
      addAll(this.coolBiomes, BiomeManager.BiomeType.COOL);
      addAll(this.icyBiomes, BiomeManager.BiomeType.ICY);
      if (worldType == WorldType.DEFAULT_1_1) {
         this.desertBiomes.add(new BiomeManager.BiomeEntry(Biomes.DESERT, 10));
         this.desertBiomes.add(new BiomeManager.BiomeEntry(Biomes.FOREST, 10));
         this.desertBiomes.add(new BiomeManager.BiomeEntry(Biomes.EXTREME_HILLS, 10));
         this.desertBiomes.add(new BiomeManager.BiomeEntry(Biomes.SWAMPLAND, 10));
         this.desertBiomes.add(new BiomeManager.BiomeEntry(Biomes.PLAINS, 10));
         this.desertBiomes.add(new BiomeManager.BiomeEntry(Biomes.TAIGA, 10));
         this.desertBiomes.add(new BiomeManager.BiomeEntry(BiomeRegistry.biomeLemon, 10));
      } else {
         this.desertBiomes.add(new BiomeManager.BiomeEntry(Biomes.DESERT, 30));
         this.desertBiomes.add(new BiomeManager.BiomeEntry(Biomes.PLAINS, 10));
         this.desertBiomes.add(new BiomeManager.BiomeEntry(BiomeRegistry.biomeLemon, 10));
      }
   }

   private static void addAll(List<BiomeManager.BiomeEntry> target, BiomeManager.BiomeType type) {
      List<BiomeManager.BiomeEntry> src = BiomeManager.getBiomes(type);
      if (src != null) {
         target.addAll(src);
      }
   }

   private int pick(List<BiomeManager.BiomeEntry> list) {
      int total = WeightedRandom.getTotalWeight(list) / 10;
      BiomeManager.BiomeEntry entry = (BiomeManager.BiomeEntry)WeightedRandom.getRandomItem(list, this.nextInt(Math.max(1, total)) * 10);
      return Biome.getIdForBiome(entry.biome);
   }

   @Override
   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
      int[] parentInts = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
      int[] out = IntCache.getIntCache(areaWidth * areaHeight);
      for (int y = 0; y < areaHeight; ++y) {
         for (int x = 0; x < areaWidth; ++x) {
            this.initChunkSeed((long)(x + areaX), (long)(y + areaY));
            int k1 = parentInts[x + y * areaWidth];
            int l1 = (k1 & 3840) >> 8;
            k1 &= -3841;
            if (isBiomeOceanic(k1)) {
               out[x + y * areaWidth] = k1;
            } else if (k1 == Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND)) {
               out[x + y * areaWidth] = k1;
            } else if (k1 == 1) {
               if (l1 > 0) {
                  if (this.nextInt(3) == 0) {
                     out[x + y * areaWidth] = Biome.getIdForBiome(Biomes.MESA_CLEAR_ROCK);
                  } else {
                     out[x + y * areaWidth] = Biome.getIdForBiome(Biomes.MESA_ROCK);
                  }
               } else {
                  out[x + y * areaWidth] = pick(this.desertBiomes);
               }
            } else if (k1 == 2) {
               if (l1 > 0) {
                  out[x + y * areaWidth] = Biome.getIdForBiome(Biomes.JUNGLE);
               } else {
                  out[x + y * areaWidth] = pick(this.warmBiomes);
               }
            } else if (k1 == 3) {
               if (l1 > 0) {
                  out[x + y * areaWidth] = Biome.getIdForBiome(Biomes.REDWOOD_TAIGA);
               } else {
                  out[x + y * areaWidth] = pick(this.coolBiomes);
               }
            } else if (k1 == 4) {
               out[x + y * areaWidth] = pick(this.icyBiomes);
            } else {
               out[x + y * areaWidth] = Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND);
            }
         }
      }
      return out;
   }
}
