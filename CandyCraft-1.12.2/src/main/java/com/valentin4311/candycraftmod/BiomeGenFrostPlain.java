package com.valentin4311.candycraftmod;

import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenFrostPlain extends BiomeGenCandy {
   public BiomeGenFrostPlain(String par2) {
      super(props(par2, 0.05F, 0.1F, 16777215));
      this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySuguard.class, 60, 4, 4));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWaffleSheep.class, 24, 4, 4));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityBunny.class, 40, 4, 6));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPingouin.class, 45, 4, 6));
      this.theBiomeDecorator2.reedsPerChunk = 0;
      this.theBiomeDecorator2.treesPerChunk = 0;
      this.theBiomeDecorator2.allowIceCream = true;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getSkyColorByTemp(float par1) {
      return new Color(220, 220, 220).getRGB();
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
