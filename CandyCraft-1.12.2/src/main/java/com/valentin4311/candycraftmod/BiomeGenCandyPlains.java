package com.valentin4311.candycraftmod;

import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCandyPlains extends BiomeGenCandy {
   public BiomeGenCandyPlains(String par2) {
      super(props(par2, 0.05F, 0.1F, 16777062));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityBee.class, 60, 4, 6));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCandyPig.class, 40, 4, 6));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityBunny.class, 55, 4, 6));
      this.theBiomeDecorator2.reedsPerChunk = 120;
      this.theBiomeDecorator2.treesPerChunk = 0;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getGrassColorAtPos(BlockPos pos) {
      return 15641275;
   }
}
