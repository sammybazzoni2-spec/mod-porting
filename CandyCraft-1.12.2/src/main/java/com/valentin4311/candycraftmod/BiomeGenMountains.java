package com.valentin4311.candycraftmod;

import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenMountains extends BiomeGenCandy {
   public BiomeGenMountains(String par2) {
      super(props(par2, 0.5F, 0.8F, 16777062));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCandyWolf.class, 12, 4, 4));
      this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySuguard.class, 4, 4, 4));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCandyPig.class, 8, 4, 6));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityBunny.class, 8, 4, 6));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWaffleSheep.class, 24, 4, 4));
      this.theBiomeDecorator2.treesPerChunk = 2;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getSkyColorByTemp(float par1) {
      return new Color(245, 220, 220).getRGB();
   }
   @Override
   @SideOnly(Side.CLIENT)
   public int getGrassColorAtPos(BlockPos pos) {
      return 15645644;
   }
}
