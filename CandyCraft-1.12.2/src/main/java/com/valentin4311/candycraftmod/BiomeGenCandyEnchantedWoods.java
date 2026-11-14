package com.valentin4311.candycraftmod;

import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCandyEnchantedWoods extends BiomeGenCandy {
   public BiomeGenCandyEnchantedWoods(String par2) {
      super(props(par2, 0.23F, 0.25F, 16777062));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityBee.class, 60, 4, 6));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCandyPig.class, 50, 4, 6));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityBunny.class, 60, 4, 6));
      this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityMageSuguard.class, 45, 1, 1));
      this.theBiomeDecorator2.reedsPerChunk = 60;
      this.theBiomeDecorator2.treesPerChunk = 11;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getSkyColorByTemp(float par1) {
      return new Color(176, 208, 210).getRGB();
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getGrassColorAtPos(BlockPos pos) {
      double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225, (double)pos.getZ() * 0.0225);
      return d0 < -0.5D ? 11595007 : (d0 < -0.1D ? 11579647 : 10712794);
   }

   @Override
   public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
      return new WorldGenEnchantedTree(false, 10, 0, 3, 3);
   }
}
