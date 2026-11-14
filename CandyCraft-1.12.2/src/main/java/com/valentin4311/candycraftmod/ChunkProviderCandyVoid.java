package com.valentin4311.candycraftmod;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

public class ChunkProviderCandyVoid implements IChunkGenerator {
   private Random rand;
   private World worldObj;
   private final boolean mapFeaturesEnabled;
   private Biome[] biomesForGeneration;

   public ChunkProviderCandyVoid(World par1World, long par2, boolean par4) {
      this.worldObj = par1World;
      this.mapFeaturesEnabled = par4;
      this.rand = new Random(par2);
   }

   @Override
   public Chunk generateChunk(int par1, int par2) {
      this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
      ChunkPrimer primer = new ChunkPrimer();
      for(int x = 0; x < 16; ++x) {
         for(int z = 0; z < 16; ++z) {
            primer.setBlockState(x, 0, z, Blocks.BEDROCK.getDefaultState());
         }
      }

      this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
      Chunk chunk = new Chunk(this.worldObj, primer, par1, par2);
      byte[] abyte1 = chunk.getBiomeArray();

      for(int k = 0; k < abyte1.length; ++k) {
         abyte1[k] = (byte)Biome.getIdForBiome(this.biomesForGeneration[k]);
      }

      chunk.generateSkylightMap();
      return chunk;
   }

   @Override
   public void populate(int x, int z) {
   }

   @Override
   public boolean generateStructures(Chunk chunkIn, int x, int z) {
      return false;
   }

   @Override
   public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
      return null;
   }

   @Override
   public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
      return null;
   }

   @Override
   public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
      return false;
   }

   @Override
   public void recreateStructures(Chunk chunkIn, int x, int z) {
   }
}
