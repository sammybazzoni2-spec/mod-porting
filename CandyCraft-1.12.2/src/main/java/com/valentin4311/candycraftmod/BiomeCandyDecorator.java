package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;

public class BiomeCandyDecorator {
   public World currentWorld;
   public Random randomGenerator;
   public int chunk_X;
   public int chunk_Z;
   public BiomeGenCandy biome;
   public WorldGenerator clayGen = new WorldGenClay(4);
   public WorldGenerator sandGen;
   public WorldGenerator gravelAsSandGen;
   public WorldGenerator dirtGen;
   public WorldGenerator cobbleGen;
   public boolean allowIceCream = false;
   public WorldGenerator IceCreamGen;
   public WorldGenerator gravelGen;
   public WorldGenerator coalGen;
   public WorldGenerator ironGen;
   public WorldGenerator goldGen;
   public WorldGenerator redstoneGen;
   public WorldGenerator diamondGen;
   public WorldGenerator lapisGen;
   public WorldGenerator plantYellowGen;
   public WorldGenerator plantRedGen;
   public WorldGenerator reedGen;
   public WorldGenerator cactusGen;
   public WorldGenerator waterlilyGen;
   public int waterlilyPerChunk;
   public int treesPerChunk;
   public int flowersPerChunk;
   public int grassPerChunk;
   public int deadBushPerChunk;
   public int reedsPerChunk;
   public int cactiPerChunk;
   public int sandPerChunk;
   public int sandPerChunk2;
   public int clayPerChunk;
   public boolean generateLakes;

   public BiomeCandyDecorator(BiomeGenCandy par1BiomeGenBase) {
      this.sandGen = new WorldGenSand(Blocks.SAND, 7);
      this.gravelAsSandGen = new WorldGenSand(Blocks.GRAVEL, 6);
      this.dirtGen = new WorldGenMinable(Blocks.DIRT.getDefaultState(), 32);
      this.cobbleGen = new WorldGenMinable(Blocks.COBBLESTONE.getDefaultState(), 32);
      this.IceCreamGen = new WorldGenMinable(CandyCraft.IceCream.getStateFromMeta(3), 24, state -> state != null && state.getBlock() == Blocks.STONE);
      this.gravelGen = new WorldGenMinable(Blocks.GRAVEL.getDefaultState(), 32);
      this.coalGen = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), 16);
      this.ironGen = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), 8);
      this.goldGen = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), 8);
      this.redstoneGen = new WorldGenMinable(Blocks.REDSTONE_ORE.getDefaultState(), 7);
      this.diamondGen = new WorldGenMinable(Blocks.DIAMOND_ORE.getDefaultState(), 7);
      this.lapisGen = new WorldGenMinable(Blocks.LAPIS_ORE.getDefaultState(), 6);
      this.plantYellowGen = new WorldGenFlowers(Blocks.YELLOW_FLOWER, BlockFlower.EnumFlowerType.DANDELION);
      this.plantRedGen = new WorldGenFlowers(Blocks.RED_FLOWER, BlockFlower.EnumFlowerType.POPPY);
      this.reedGen = new WorldGenReed();
      this.cactusGen = new WorldGenCactus();
      this.waterlilyGen = new WorldGenWaterlily();
      this.flowersPerChunk = 2;
      this.grassPerChunk = 1;
      this.sandPerChunk = 1;
      this.sandPerChunk2 = 3;
      this.clayPerChunk = 1;
      this.generateLakes = true;
      this.biome = par1BiomeGenBase;
   }

   public void decorate(World par1World, Random par2Random, int par3, int par4) {
      if (this.currentWorld != null) {
         throw new RuntimeException("Already decorating!!");
      } else {
         this.currentWorld = par1World;
         this.randomGenerator = par2Random;
         this.chunk_X = par3;
         this.chunk_Z = par4;
         this.decorate();
         this.currentWorld = null;
         this.randomGenerator = null;
      }
   }

   protected void decorate() {
      this.generateOres();
      int i = this.treesPerChunk;
      if (this.randomGenerator.nextInt(10) == 0) {
         ++i;
      }

      BlockPos chunkPos = new BlockPos(this.chunk_X, 0, this.chunk_Z);
      boolean doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, chunkPos, EventType.TREE);

      for(int j = 0; doGen && j < i; ++j) {
         int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
         int l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
         WorldGenerator worldgenerator = this.biome.getRandomWorldGenForTrees(this.randomGenerator);
         if (worldgenerator instanceof WorldGenBigCandyTree) {
            ((WorldGenBigCandyTree)worldgenerator).setGeneratorScale(1.0D, 1.0D, 1.0D);
         }
         worldgenerator.generate(this.currentWorld, this.randomGenerator, this.currentWorld.getHeight(new BlockPos(k, 0, l)));
      }

      doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, chunkPos, EventType.FLOWERS);

      for(int var7 = 0; doGen && var7 < this.flowersPerChunk; ++var7) {
         int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
         int l = this.randomGenerator.nextInt(128);
         int i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
         this.plantYellowGen.generate(this.currentWorld, this.randomGenerator, new BlockPos(k, l, i1));
         if (this.randomGenerator.nextInt(4) == 0) {
            k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            l = this.randomGenerator.nextInt(128);
            i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantRedGen.generate(this.currentWorld, this.randomGenerator, new BlockPos(k, l, i1));
         }
      }

      doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, chunkPos, EventType.LILYPAD);

      for(int var8 = 0; doGen && var8 < this.waterlilyPerChunk; ++var8) {
         int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
         int l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;

         int i1;
         for(i1 = this.randomGenerator.nextInt(128); i1 > 0 && this.currentWorld.isAirBlock(new BlockPos(k, i1 - 1, l)); --i1) {
         }

         this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, new BlockPos(k, i1, l));
      }

      doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, chunkPos, EventType.REED);

      for(int var9 = 0; doGen && var9 < this.reedsPerChunk; ++var9) {
         int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
         int l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
         int i1 = this.randomGenerator.nextInt(128);
         this.reedGen.generate(this.currentWorld, this.randomGenerator, new BlockPos(k, i1, l));
      }

      for(int var10 = 0; doGen && var10 < 10; ++var10) {
         int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
         int l = this.randomGenerator.nextInt(128);
         int i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
         this.reedGen.generate(this.currentWorld, this.randomGenerator, new BlockPos(k, l, i1));
      }

      doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, chunkPos, EventType.LAKE_WATER);
      if (doGen && this.generateLakes) {
         for(int var11 = 0; var11 < 50; ++var11) {
            int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            int l = this.randomGenerator.nextInt(this.randomGenerator.nextInt(120) + 8);
            int i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(this.currentWorld, this.randomGenerator, new BlockPos(k, l, i1));
         }

         for(int var12 = 0; var12 < 20; ++var12) {
            int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            int l = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
            int i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            (new WorldGenLiquids(CandyCraft.GrenadineFlow)).generate(this.currentWorld, this.randomGenerator, new BlockPos(k, l, i1));
         }
      }

      MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.currentWorld, this.randomGenerator, chunkPos));
   }

   protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4) {
      for(int l = 0; l < par1; ++l) {
         int i1 = this.chunk_X + this.randomGenerator.nextInt(16);
         int j1 = this.randomGenerator.nextInt(par4 - par3) + par3;
         int k1 = this.chunk_Z + this.randomGenerator.nextInt(16);
         par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, new BlockPos(i1, j1, k1));
      }

   }

   protected void genStandardOre2(int par1, WorldGenerator par2WorldGenerator, int par3, int par4) {
      for(int l = 0; l < par1; ++l) {
         int i1 = this.chunk_X + this.randomGenerator.nextInt(16);
         int j1 = this.randomGenerator.nextInt(par4) + this.randomGenerator.nextInt(par4) + (par3 - par4);
         int k1 = this.chunk_Z + this.randomGenerator.nextInt(16);
         par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, new BlockPos(i1, j1, k1));
      }

   }

   protected void generateOres() {
      this.genStandardOre1(20, this.dirtGen, 0, 128);
      this.genStandardOre1(20, this.cobbleGen, 0, 48);
      if (this.allowIceCream) {
         this.genStandardOre1(10, this.IceCreamGen, 0, 64);
      }

      this.genStandardOre1(10, this.gravelGen, 0, 128);
      this.genStandardOre1(20, this.coalGen, 0, 128);
      this.genStandardOre1(20, this.ironGen, 0, 64);
      this.genStandardOre1(2, this.goldGen, 0, 32);
      this.genStandardOre1(8, this.redstoneGen, 75, 256);
      this.genStandardOre1(2, this.diamondGen, 5, 16);
      this.genStandardOre2(1, this.lapisGen, 16, 16);
   }
}
