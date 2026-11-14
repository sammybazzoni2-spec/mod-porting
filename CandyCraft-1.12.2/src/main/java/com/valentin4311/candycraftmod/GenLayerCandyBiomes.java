package com.valentin4311.candycraftmod;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerEdge;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.GenLayerEdge.Mode;

public abstract class GenLayerCandyBiomes extends GenLayer {
   public GenLayerCandyBiomes(long par1) {
      super(par1);
   }

   public static GenLayer[] initBiomes(long par0, WorldType par1) {
      boolean flag = false;
      GenLayerCandyIsland genlayerisland = new GenLayerCandyIsland(1L);
      GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerisland);
      GenLayerCandyAddIsland genlayeraddisland = new GenLayerCandyAddIsland(1L, genlayerfuzzyzoom);
      GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
      genlayeraddisland = new GenLayerCandyAddIsland(2L, genlayerzoom);
      genlayeraddisland = new GenLayerCandyAddIsland(50L, genlayeraddisland);
      genlayeraddisland = new GenLayerCandyAddIsland(70L, genlayeraddisland);
      GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland);
      GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
      genlayeraddisland = new GenLayerCandyAddIsland(3L, genlayeraddsnow);
      GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland, Mode.COOL_WARM);
      genlayeredge = new GenLayerEdge(2L, genlayeredge, Mode.HEAT_ICE);
      genlayeredge = new GenLayerEdge(3L, genlayeredge, Mode.SPECIAL);
      genlayerzoom = new GenLayerZoom(2002L, genlayeredge);
      genlayerzoom = new GenLayerZoom(2003L, genlayerzoom);
      genlayeraddisland = new GenLayerCandyAddIsland(4L, genlayerzoom);
      GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland);
      GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddmushroomisland);
      GenLayer genlayer3 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
      byte b0 = 4;
      GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayer3, 0);
      GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
      Object object = getBiomeLayer(par0, genlayer3, par1);
      GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
      GenLayerHills genlayerhills = new GenLayerHills(1000L, (GenLayer)object, genlayer1);
      genlayer = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
      genlayer = GenLayerZoom.magnify(1000L, genlayer, b0);
      GenLayerCandyRiver genlayerriver = new GenLayerCandyRiver(1L, genlayer);
      GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
      object = new GenLayerRareBiome(1001L, genlayerhills);

      for(int j = 0; j < b0; ++j) {
         object = new GenLayerZoom((long)(1000 + j), (GenLayer)object);
         if (j == 0) {
            object = new GenLayerAddIsland(3L, (GenLayer)object);
         }

         if (j == 1) {
            object = new GenLayerShore(1000L, (GenLayer)object);
         }
      }

      GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer)object);
      GenLayerCandyRiverMix genlayerrivermix = new GenLayerCandyRiverMix(100L, genlayersmooth1, genlayersmooth);
      GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
      genlayerrivermix.initWorldGenSeed(par0);
      genlayervoronoizoom.initWorldGenSeed(par0);
      return new GenLayer[]{genlayerrivermix, genlayervoronoizoom, genlayerrivermix};
   }

   public static GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, WorldType type) {
      GenLayer ret = new GenLayerBiomeCandy(200L, parentLayer, type);
      GenLayer var5 = GenLayerZoom.magnify(1000L, ret, 2);
      var5 = new GenLayerBiomeEdge(1000L, var5);
      return var5;
   }

   public abstract int[] getInts(int var1, int var2, int var3, int var4);
}
