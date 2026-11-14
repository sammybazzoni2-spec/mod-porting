package com.valentin4311.candycraftmod;

import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenHellForest extends BiomeGenCandy {
   public BiomeGenHellForest(String par2) {
      super(props(par2, 0.0F, 0.3F, 16772659));
      this.spawnableCreatureList.clear();
      this.spawnableMonsterList.clear();
      this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySuguard.class, 100, 8, 12));
      this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCottonCandySpider.class, 100, 4, 12));
      this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCandyCreeper.class, 100, 8, 15));
      this.theBiomeDecorator2.treesPerChunk = 10;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getSkyColorByTemp(float par1) {
      return new Color(1.0F, 0.8F, 0.5F).getRGB();
   }
   @Override
   @SideOnly(Side.CLIENT)
   public int getGrassColorAtPos(BlockPos pos) {
      return 11557928;
   }
   @Override
   public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
      return new WorldGenCandyTrees2(false, true);
   }
}
