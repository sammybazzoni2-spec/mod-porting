package com.valentin4311.candycraftmod;

import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenVoid extends Biome {
   public BiomeGenVoid(String par2) {
      super(new Biome.BiomeProperties(par2).setWaterColor(16777062).setRainDisabled());
      this.spawnableCreatureList.clear();
      this.spawnableMonsterList.clear();
      this.spawnableCaveCreatureList.clear();
      this.spawnableWaterCreatureList.clear();
      this.topBlock = CandyCraft.PuddingBlock.getDefaultState();
      this.fillerBlock = CandyCraft.FlourBlock.getDefaultState();
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getSkyColorByTemp(float par1) {
      return Color.PINK.getRGB();
   }

   public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
      return new WorldGenCandyTrees(false, true);
   }
}
