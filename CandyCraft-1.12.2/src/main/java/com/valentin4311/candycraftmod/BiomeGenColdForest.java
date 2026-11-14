package com.valentin4311.candycraftmod;

import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenColdForest extends BiomeGenCandy {
   public BiomeGenColdForest(String par2) {
      super(props(par2, 0.1F, 0.3F, 16777062));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCandyWolf.class, 54, 4, 4));
      this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySuguard.class, 60, 4, 4));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWaffleSheep.class, 44, 4, 4));
      this.theBiomeDecorator2.treesPerChunk = 9;
      this.theBiomeDecorator2.reedsPerChunk = 40;
      this.theBiomeDecorator2.allowIceCream = true;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getSkyColorByTemp(float par1) {
      return new Color(220, 220, 220).getRGB();
   }
   @Override
   public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
      return new WorldGenCandyTrees(false, 2);
   }
   @Override
   @SideOnly(Side.CLIENT)
   public int getGrassColorAtPos(BlockPos pos) {
      return 16768494;
   }
}
