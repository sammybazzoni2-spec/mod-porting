package com.valentin4311.candycraftmod;

import net.minecraft.world.biome.Biome;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerCandyAddIsland extends GenLayer {
   private static final String __OBFID = "CL_00000551";

   public GenLayerCandyAddIsland(long p_i2119_1_, GenLayer p_i2119_3_) {
      super(p_i2119_1_);
      this.parent = p_i2119_3_;
   }

   public int[] getInts(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int i1 = p_75904_1_ - 1;
      int j1 = p_75904_2_ - 1;
      int k1 = p_75904_3_ + 2;
      int l1 = p_75904_4_ + 2;
      int[] aint = this.parent.getInts(i1, j1, k1, l1);
      int[] aint1 = IntCache.getIntCache(p_75904_3_ * p_75904_4_);

      for(int i2 = 0; i2 < p_75904_4_; ++i2) {
         for(int j2 = 0; j2 < p_75904_3_; ++j2) {
            int k2 = aint[j2 + 0 + (i2 + 0) * k1];
            int l2 = aint[j2 + 2 + (i2 + 0) * k1];
            int i3 = aint[j2 + 0 + (i2 + 2) * k1];
            int j3 = aint[j2 + 2 + (i2 + 2) * k1];
            int k3 = aint[j2 + 1 + (i2 + 1) * k1];
            this.initChunkSeed((long)(j2 + p_75904_1_), (long)(i2 + p_75904_2_));
            if (k3 != Biome.getIdForBiome(CandyCraft.CandyOcean) || k2 == Biome.getIdForBiome(CandyCraft.CandyOcean) && l2 == Biome.getIdForBiome(CandyCraft.CandyOcean) && i3 == Biome.getIdForBiome(CandyCraft.CandyOcean) && j3 == Biome.getIdForBiome(CandyCraft.CandyOcean)) {
               if (k3 != Biome.getIdForBiome(CandyCraft.CandyOcean) && (k2 == Biome.getIdForBiome(CandyCraft.CandyOcean) || l2 == Biome.getIdForBiome(CandyCraft.CandyOcean) || i3 == Biome.getIdForBiome(CandyCraft.CandyOcean) || j3 == Biome.getIdForBiome(CandyCraft.CandyOcean))) {
                  if (this.nextInt(5) == 0) {
                     if (k3 == Biome.getIdForBiome(CandyCraft.CandyForest)) {
                        aint1[j2 + i2 * p_75904_3_] = Biome.getIdForBiome(CandyCraft.CandyForest);
                     } else {
                        aint1[j2 + i2 * p_75904_3_] = Biome.getIdForBiome(CandyCraft.CandyOcean);
                     }
                  } else {
                     aint1[j2 + i2 * p_75904_3_] = k3;
                  }
               } else {
                  aint1[j2 + i2 * p_75904_3_] = k3;
               }
            } else {
               int l3 = 1;
               int i4 = 1;
               if (k2 != Biome.getIdForBiome(CandyCraft.CandyOcean) && this.nextInt(l3++) == 0) {
                  i4 = k2;
               }

               if (l2 != Biome.getIdForBiome(CandyCraft.CandyOcean) && this.nextInt(l3++) == 0) {
                  i4 = l2;
               }

               if (i3 != Biome.getIdForBiome(CandyCraft.CandyOcean) && this.nextInt(l3++) == 0) {
                  i4 = i3;
               }

               if (j3 != Biome.getIdForBiome(CandyCraft.CandyOcean) && this.nextInt(l3++) == 0) {
                  i4 = j3;
               }

               if (this.nextInt(3) == 0) {
                  aint1[j2 + i2 * p_75904_3_] = i4;
               } else if (i4 == Biome.getIdForBiome(CandyCraft.CandyForest)) {
                  aint1[j2 + i2 * p_75904_3_] = Biome.getIdForBiome(CandyCraft.CandyForest);
               } else {
                  aint1[j2 + i2 * p_75904_3_] = Biome.getIdForBiome(CandyCraft.CandyOcean);
               }
            }
         }
      }

      return aint1;
   }
}
