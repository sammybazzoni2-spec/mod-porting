package com.valentin4311.candycraftmod;

import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCandy extends Biome {
   public BiomeCandyDecorator theBiomeDecorator2;

   protected static Biome.BiomeProperties props(String name, float base, float variation, int water) {
      return new Biome.BiomeProperties(name).setBaseHeight(base).setHeightVariation(variation).setWaterColor(water).setRainDisabled();
   }

   public BiomeGenCandy(String par2) {
      this(props(par2, 0.1F, 0.2F, 16777062));
   }

   protected BiomeGenCandy(Biome.BiomeProperties properties) {
      super(properties);
      this.spawnableCreatureList.clear();
      this.spawnableMonsterList.clear();
      this.spawnableCaveCreatureList.clear();
      this.spawnableWaterCreatureList.clear();
      this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCottonCandySpider.class, 100, 4, 4));
      this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCandyCreeper.class, 100, 8, 15));
      this.spawnableWaterCreatureList.add(new Biome.SpawnListEntry(EntityFish.class, 50, 4, 4));
      this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySuguard.class, 100, 4, 4));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCandyPig.class, 12, 4, 6));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityBunny.class, 12, 4, 6));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWaffleSheep.class, 12, 4, 4));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityBunny.class, 12, 4, 4));
      this.topBlock = CandyCraft.PuddingBlock.getDefaultState();
      this.fillerBlock = CandyCraft.FlourBlock.getDefaultState();
      this.theBiomeDecorator2 = new BiomeCandyDecorator(this);
      this.theBiomeDecorator2.allowIceCream = false;
      this.theBiomeDecorator2.treesPerChunk = 6;
      this.theBiomeDecorator2.grassPerChunk = 4;
      this.theBiomeDecorator2.deadBushPerChunk = 0;
      this.theBiomeDecorator2.reedsPerChunk = 80;
      this.theBiomeDecorator2.cactiPerChunk = 0;
      this.theBiomeDecorator2.waterlilyPerChunk = 30;
      this.theBiomeDecorator2.flowersPerChunk = 40;
      this.theBiomeDecorator2.plantYellowGen = new WorldGenSeaweeds();
      this.theBiomeDecorator2.waterlilyGen = new WorldGenCandyWaterlily();
      this.theBiomeDecorator2.reedGen = new WorldGenTallCandyGrass(CandyCraft.TallCandyGrass);
      this.theBiomeDecorator2.dirtGen = new net.minecraft.world.gen.feature.WorldGenMinable(CandyCraft.FlourBlock.getDefaultState(), 32);
      this.theBiomeDecorator2.ironGen = new net.minecraft.world.gen.feature.WorldGenMinable(CandyCraft.LicoriceOre.getDefaultState(), 8);
      this.theBiomeDecorator2.goldGen = new net.minecraft.world.gen.feature.WorldGenMinable(CandyCraft.JellyOre.getDefaultState(), 12);
      this.theBiomeDecorator2.coalGen = new net.minecraft.world.gen.feature.WorldGenMinable(CandyCraft.HoneyOre.getDefaultState(), 16);
      this.theBiomeDecorator2.gravelGen = new net.minecraft.world.gen.feature.WorldGenMinable(CandyCraft.ChocolateStone.getDefaultState(), 32);
      this.theBiomeDecorator2.diamondGen = new net.minecraft.world.gen.feature.WorldGenMinable(CandyCraft.PEZOre.getDefaultState(), 8);
      this.theBiomeDecorator2.redstoneGen = new net.minecraft.world.gen.feature.WorldGenMinable(CandyCraft.NougatOre.getDefaultState(), 7);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getGrassColorAtPos(BlockPos pos) {
      return 16759756;
   }

   @Override
   public void decorate(net.minecraft.world.World par1World, Random par2Random, BlockPos pos) {
      if (this.theBiomeDecorator2.currentWorld == null) {
         this.theBiomeDecorator2.decorate(par1World, par2Random, pos.getX(), pos.getZ());
      }
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getSkyColorByTemp(float par1) {
      return (new Color(253, 216, 215)).getRGB();
   }

   public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
      return new WorldGenCandyTrees(false, true);
   }
}
