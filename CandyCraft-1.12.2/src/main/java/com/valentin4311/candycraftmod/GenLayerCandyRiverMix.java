package com.valentin4311.candycraftmod;

import net.minecraft.world.biome.Biome;
import net.minecraft.init.Biomes;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerCandyRiverMix extends GenLayer {
   private GenLayer biomePatternGeneratorChain;
   private GenLayer riverPatternGeneratorChain;
   private static final String __OBFID = "CL_00000567";

   public GenLayerCandyRiverMix(long p_i2129_1_, GenLayer p_i2129_3_, GenLayer p_i2129_4_) {
      super(p_i2129_1_);
      this.biomePatternGeneratorChain = p_i2129_3_;
      this.riverPatternGeneratorChain = p_i2129_4_;
   }

   public void initWorldGenSeed(long p_75905_1_) {
      this.biomePatternGeneratorChain.initWorldGenSeed(p_75905_1_);
      this.riverPatternGeneratorChain.initWorldGenSeed(p_75905_1_);
      super.initWorldGenSeed(p_75905_1_);
   }

   public int[] getInts(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int[] aint = this.biomePatternGeneratorChain.getInts(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
      int[] aint1 = this.riverPatternGeneratorChain.getInts(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
      int[] aint2 = IntCache.getIntCache(p_75904_3_ * p_75904_4_);

      for(int i1 = 0; i1 < p_75904_3_ * p_75904_4_; ++i1) {
         if (aint[i1] != Biome.getIdForBiome(CandyCraft.CandyOcean) && aint[i1] != Biome.getIdForBiome(CandyCraft.CandyHellForest)) {
            if (aint1[i1] == Biome.getIdForBiome(CandyCraft.CandyRiver)) {
               if (aint[i1] == Biome.getIdForBiome(Biomes.ICE_PLAINS)) {
                  aint2[i1] = Biome.getIdForBiome(Biomes.FROZEN_OCEAN);
               } else if (aint[i1] != Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND) && aint[i1] != Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE)) {
                  aint2[i1] = aint1[i1] & 255;
               } else {
                  aint2[i1] = Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE);
               }
            } else {
               aint2[i1] = aint[i1];
            }
         } else {
            aint2[i1] = aint[i1];
         }
      }

      return aint2;
   }
}
