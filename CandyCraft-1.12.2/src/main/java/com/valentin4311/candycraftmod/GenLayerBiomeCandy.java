package com.valentin4311.candycraftmod;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.init.Biomes;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeCandy extends GenLayer {
   private Biome[] field_151623_c;
   private Biome[] field_151621_d;
   private Biome[] field_151622_e;
   private Biome[] field_151620_f;
   private static final String __OBFID = "CL_00000555";

   public GenLayerBiomeCandy(long par1, GenLayer par3GenLayer, WorldType par4WorldType) {
      super(par1);
      this.field_151623_c = new Biome[]{CandyCraft.CandyEnchantedForest, CandyCraft.CandyHellForest};
      this.field_151621_d = new Biome[]{CandyCraft.CandyForest, CandyCraft.CandyPlains, CandyCraft.CandyOcean};
      this.field_151622_e = new Biome[]{CandyCraft.CandyColdForest, CandyCraft.CandyMountains, CandyCraft.CandyFrostPlains, CandyCraft.CandyOcean};
      this.field_151620_f = new Biome[]{CandyCraft.CandyHellMountains};
      this.parent = par3GenLayer;
   }

   public int[] getInts(int par1, int par2, int par3, int par4) {
      int[] aint = this.parent.getInts(par1, par2, par3, par4);
      int[] aint1 = IntCache.getIntCache(par3 * par4);

      for(int i1 = 0; i1 < par4; ++i1) {
         for(int j1 = 0; j1 < par3; ++j1) {
            this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
            int k1 = aint[j1 + i1 * par3];
            int l1 = (k1 & 3840) >> 8;
            k1 &= -3841;
            if (k1 == Biome.getIdForBiome(CandyCraft.CandyOcean)) {
               aint1[j1 + i1 * par3] = k1;
            } else if (k1 == Biome.getIdForBiome(CandyCraft.CandyMountains)) {
               aint1[j1 + i1 * par3] = k1;
            } else if (k1 == 1) {
               if (l1 > 0) {
                  if (this.nextInt(3) == 0) {
                     aint1[j1 + i1 * par3] = Biome.getIdForBiome(CandyCraft.CandyPlains);
                  } else {
                     aint1[j1 + i1 * par3] = Biome.getIdForBiome(CandyCraft.CandyEnchantedForest);
                  }
               } else {
                  aint1[j1 + i1 * par3] = Biome.getIdForBiome(this.field_151623_c[this.nextInt(this.field_151623_c.length)]);
               }
            } else if (k1 == 2) {
               if (l1 > 0) {
                  aint1[j1 + i1 * par3] = Biome.getIdForBiome(CandyCraft.CandyForest);
               } else {
                  aint1[j1 + i1 * par3] = Biome.getIdForBiome(this.field_151621_d[this.nextInt(this.field_151621_d.length)]);
               }
            } else if (k1 == 3) {
               if (l1 > 0) {
                  aint1[j1 + i1 * par3] = Biome.getIdForBiome(CandyCraft.CandyMountains);
               } else if (this.nextInt(12) == 0) {
                  aint1[j1 + i1 * par3] = Biome.getIdForBiome(CandyCraft.CandyHellMountains);
               } else {
                  aint1[j1 + i1 * par3] = Biome.getIdForBiome(this.field_151622_e[this.nextInt(this.field_151622_e.length)]);
               }
            } else if (k1 == 4) {
               aint1[j1 + i1 * par3] = Biome.getIdForBiome(this.field_151620_f[this.nextInt(this.field_151620_f.length)]);
            } else {
               aint1[j1 + i1 * par3] = Biome.getIdForBiome(CandyCraft.CandyMountains);
            }
         }
      }

      return aint1;
   }
}
