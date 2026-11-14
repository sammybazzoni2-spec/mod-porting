package com.valentin4311.candycraftmod;

import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenHellMountains extends BiomeGenCandy {
   public BiomeGenHellMountains(String par2) {
      super(props(par2, 1.9F, 2.0F, 16777062));
      this.theBiomeDecorator2.treesPerChunk = 1;
      this.theBiomeDecorator2.reedsPerChunk = 20;
      this.theBiomeDecorator2.allowIceCream = true;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getSkyColorByTemp(float par1) {
      return Color.WHITE.getRGB();
   }
   @Override
   @SideOnly(Side.CLIENT)
   public int getGrassColorAtPos(BlockPos pos) {
      return 16777215;
   }
   @Override
   public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
      return new WorldGenCandyTrees(false, 2);
   }
}
