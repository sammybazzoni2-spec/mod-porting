package com.valentin4311.candycraftmod;

import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCandyForest extends BiomeGenCandy {
   public BiomeGenCandyForest(String par2) {
      super(props(par2, 0.1F, 0.15F, 16777062));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCandyWolf.class, 50, 4, 4));
      this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySuguard.class, 60, 4, 4));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCandyPig.class, 21, 4, 6));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityBunny.class, 21, 4, 6));
      this.theBiomeDecorator2.treesPerChunk = 9;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getGrassColorAtPos(BlockPos pos) {
      return 15641275;
   }
}
