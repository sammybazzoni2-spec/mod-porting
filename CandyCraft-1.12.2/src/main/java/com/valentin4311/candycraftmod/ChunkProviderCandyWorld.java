package com.valentin4311.candycraftmod;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderCandyWorld implements IChunkGenerator {
   private Random rand;
   private NoiseGeneratorOctaves field_147431_j;
   private NoiseGeneratorOctaves field_147432_k;
   private NoiseGeneratorOctaves field_147429_l;
   private NoiseGeneratorPerlin field_147430_m;
   public NoiseGeneratorOctaves noiseGen5;
   public NoiseGeneratorOctaves noiseGen6;
   public NoiseGeneratorOctaves mobSpawnerNoise;
   private World worldObj;
   private final double[] heightMap;
   private final float[] parabolicField;
   private double[] stoneNoise = new double[256];
   private MapGenBase caveGenerator = new MapGenCaves();
   private MapGenBase ravineGenerator = new MapGenRavine();
   private Biome[] biomesForGeneration;
   double[] field_147427_d;
   double[] field_147428_e;
   double[] field_147425_f;
   double[] field_147426_g;

   public ChunkProviderCandyWorld(World par1World, long par2, boolean par4) {
      this.caveGenerator = TerrainGen.getModdedMapGen(this.caveGenerator, EventType.CAVE);
      this.ravineGenerator = TerrainGen.getModdedMapGen(this.ravineGenerator, EventType.RAVINE);
      this.worldObj = par1World;
      this.rand = new Random(par2);
      this.field_147431_j = new NoiseGeneratorOctaves(this.rand, 16);
      this.field_147432_k = new NoiseGeneratorOctaves(this.rand, 16);
      this.field_147429_l = new NoiseGeneratorOctaves(this.rand, 8);
      this.field_147430_m = new NoiseGeneratorPerlin(this.rand, 4);
      this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
      this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
      this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
      this.heightMap = new double[825];
      this.parabolicField = new float[25];

      for(int j = -2; j <= 2; ++j) {
         for(int k = -2; k <= 2; ++k) {
            float f = 10.0F / MathHelper.sqrt((float)(j * j + k * k) + 0.2F);
            this.parabolicField[j + 2 + (k + 2) * 5] = f;
         }
      }
   }

   public void setBlocksInChunk(int p_147424_1_, int p_147424_2_, ChunkPrimer primer) {
      int seaLevel = 63;
      this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, p_147424_1_ * 4 - 2, p_147424_2_ * 4 - 2, 10, 10);
      this.generateHeightmap(p_147424_1_ * 4, 0, p_147424_2_ * 4);
      IBlockState stone = Blocks.STONE.getDefaultState();
      IBlockState water = Blocks.WATER.getDefaultState();

      for(int k = 0; k < 4; ++k) {
         int l = k * 5;
         int i1 = (k + 1) * 5;

         for(int j1 = 0; j1 < 4; ++j1) {
            int k1 = (l + j1) * 33;
            int l1 = (l + j1 + 1) * 33;
            int i2 = (i1 + j1) * 33;
            int j2 = (i1 + j1 + 1) * 33;

            for(int k2 = 0; k2 < 32; ++k2) {
               double d0 = 0.125D;
               double d1 = this.heightMap[k1 + k2];
               double d2 = this.heightMap[l1 + k2];
               double d3 = this.heightMap[i2 + k2];
               double d4 = this.heightMap[j2 + k2];
               double d5 = (this.heightMap[k1 + k2 + 1] - d1) * d0;
               double d6 = (this.heightMap[l1 + k2 + 1] - d2) * d0;
               double d7 = (this.heightMap[i2 + k2 + 1] - d3) * d0;
               double d8 = (this.heightMap[j2 + k2 + 1] - d4) * d0;

               for(int l2 = 0; l2 < 8; ++l2) {
                  double d9 = 0.25D;
                  double d10 = d1;
                  double d11 = d2;
                  double d12 = (d3 - d1) * d9;
                  double d13 = (d4 - d2) * d9;

                  for(int i3 = 0; i3 < 4; ++i3) {
                     double d14 = 0.25D;
                     double d16 = (d11 - d10) * d14;
                     double d15 = d10 - d16;

                     for(int k3 = 0; k3 < 4; ++k3) {
                        if ((d15 += d16) > 0.0D) {
                           primer.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + k3, stone);
                        } else if (k2 * 8 + l2 < seaLevel) {
                           primer.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + k3, water);
                        }
                     }

                     d10 += d12;
                     d11 += d13;
                  }

                  d1 += d5;
                  d2 += d6;
                  d3 += d7;
                  d4 += d8;
               }
            }
         }
      }

   }

   public void replaceBlocksForBiome(int chunkX, int chunkZ, ChunkPrimer primer, Biome[] biomes) {
      double d0 = 0.03125D;
      this.stoneNoise = this.field_147430_m.getRegion(this.stoneNoise, (double)(chunkX * 16), (double)(chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

      for(int k = 0; k < 16; ++k) {
         for(int l = 0; l < 16; ++l) {
            Biome biome = biomes[l + k * 16];
            this.genBiomeTerrain(this.worldObj, this.rand, primer, chunkX * 16 + k, chunkZ * 16 + l, this.stoneNoise[l + k * 16], biome);
         }
      }

   }

   public void genBiomeTerrain(World world, Random random, ChunkPrimer primer, int x, int z, double noise, Biome biome) {
      IBlockState air = Blocks.AIR.getDefaultState();
      IBlockState stone = Blocks.STONE.getDefaultState();
      IBlockState top = biome.topBlock;
      IBlockState filler = biome.fillerBlock;
      int k = -1;
      int l = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
      int i1 = x & 15;
      int j1 = z & 15;

      for(int l1 = 255; l1 >= 0; --l1) {
         if (l1 <= 0 + random.nextInt(5)) {
            primer.setBlockState(j1, l1, i1, Blocks.BEDROCK.getDefaultState());
         } else {
            IBlockState state = primer.getBlockState(j1, l1, i1);
            if (state.getMaterial() != Material.AIR) {
               if (state.getBlock() == Blocks.STONE) {
                  if (k == -1) {
                     if (l <= 0) {
                        top = air;
                        filler = stone;
                     } else if (l1 >= 59 && l1 <= 64) {
                        top = biome.topBlock;
                        filler = biome.fillerBlock;
                     }

                     if (l1 < 63 && (top == null || top.getMaterial() == Material.AIR)) {
                        if (biome.getTemperature(new BlockPos(x, l1, z)) >= 0.15F) {
                           top = Blocks.WATER.getDefaultState();
                        }
                     }

                     k = l;
                     if (l1 >= 62) {
                        primer.setBlockState(j1, l1, i1, top == null ? air : top);
                     } else if (l1 < 56 - l) {
                        top = air;
                        filler = stone;
                        primer.setBlockState(j1, l1, i1, CandyCraft.FlourBlock.getDefaultState());
                     } else {
                        primer.setBlockState(j1, l1, i1, filler);
                     }
                  } else if (k > 0) {
                     --k;
                     primer.setBlockState(j1, l1, i1, filler);
                     if (k == 0 && filler.getBlock() == Blocks.SAND) {
                        k = random.nextInt(4) + Math.max(0, l1 - 63);
                        filler = Blocks.SANDSTONE.getDefaultState();
                     }
                  }
               }
            } else {
               k = -1;
            }
         }
      }

   }

   @Override
   public Chunk generateChunk(int par1, int par2) {
      this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
      ChunkPrimer primer = new ChunkPrimer();
      this.setBlocksInChunk(par1, par2, primer);
      this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
      this.replaceBlocksForBiome(par1, par2, primer, this.biomesForGeneration);
      this.caveGenerator.generate(this.worldObj, par1, par2, primer);
      this.ravineGenerator.generate(this.worldObj, par1, par2, primer);
      Chunk chunk = new Chunk(this.worldObj, primer, par1, par2);
      byte[] abyte1 = chunk.getBiomeArray();

      for(int k = 0; k < abyte1.length; ++k) {
         abyte1[k] = (byte)Biome.getIdForBiome(this.biomesForGeneration[k]);
      }

      chunk.generateSkylightMap();
      return chunk;
   }

   private void generateHeightmap(int p_147423_1_, int p_147423_2_, int p_147423_3_) {
      this.field_147426_g = this.noiseGen6.generateNoiseOctaves(this.field_147426_g, p_147423_1_, p_147423_3_, 5, 5, 200.0D, 200.0D, 0.5D);
      this.field_147427_d = this.field_147429_l.generateNoiseOctaves(this.field_147427_d, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
      this.field_147428_e = this.field_147431_j.generateNoiseOctaves(this.field_147428_e, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D, 684.412D, 684.412D);
      this.field_147425_f = this.field_147432_k.generateNoiseOctaves(this.field_147425_f, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D, 684.412D, 684.412D);
      int l = 0;
      int i1 = 0;

      for(int j1 = 0; j1 < 5; ++j1) {
         for(int k1 = 0; k1 < 5; ++k1) {
            float f = 0.0F;
            float f1 = 0.0F;
            float f2 = 0.0F;
            int b0 = 2;
            Biome biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];

            for(int l1 = -b0; l1 <= b0; ++l1) {
               for(int i2 = -b0; i2 <= b0; ++i2) {
                  Biome biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                  float f3 = biomegenbase1.getBaseHeight();
                  float f4 = biomegenbase1.getHeightVariation();
                  float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0F);
                  if (biomegenbase1.getBaseHeight() > biomegenbase.getBaseHeight()) {
                     f5 /= 2.0F;
                  }

                  f += f4 * f5;
                  f1 += f3 * f5;
                  f2 += f5;
               }
            }

            f /= f2;
            f1 /= f2;
            f = f * 0.9F + 0.1F;
            f1 = (f1 * 4.0F - 1.0F) / 8.0F;
            double d13 = this.field_147426_g[i1] / 8000.0D;
            if (d13 < 0.0D) {
               d13 = -d13 * 0.3D;
            }

            d13 = d13 * 3.0D - 2.0D;
            if (d13 < 0.0D) {
               d13 /= 2.0D;
               if (d13 < -1.0D) {
                  d13 = -1.0D;
               }

               d13 /= 1.4D;
               d13 /= 2.0D;
            } else {
               if (d13 > 1.0D) {
                  d13 = 1.0D;
               }

               d13 /= 8.0D;
            }

            ++i1;
            double d12 = (double)f1;
            double d14 = (double)f;
            d12 += d13 * 0.2D;
            d12 = d12 * 8.5D / 8.0D;
            double d5 = 8.5D + d12 * 4.0D;

            for(int j2 = 0; j2 < 33; ++j2) {
               double d6 = ((double)j2 - d5) * 12.0D * 128.0D / 256.0D / d14;
               if (d6 < 0.0D) {
                  d6 *= 4.0D;
               }

               double d7 = this.field_147428_e[l] / 512.0D;
               double d8 = this.field_147425_f[l] / 512.0D;
               double d9 = (this.field_147427_d[l] / 10.0D + 1.0D) / 2.0D;
               double d10 = MathHelper.clampedLerp(d7, d8, d9) - d6;
               if (j2 > 29) {
                  double d11 = (double)((float)(j2 - 29) / 3.0F);
                  d10 = d10 * (1.0D - d11) + -10.0D * d11;
               }

               this.heightMap[l] = d10;
               ++l;
            }
         }
      }

   }

   @Override
   public void populate(int par2, int par3) {
      BlockFalling.fallInstantly = true;
      int k = par2 * 16;
      int l = par3 * 16;
      Biome biomegenbase = this.worldObj.getBiome(new BlockPos(k + 16, 0, l + 16));
      this.rand.setSeed(this.worldObj.getSeed());
      long i1 = this.rand.nextLong() / 2L * 2L + 1L;
      long j1 = this.rand.nextLong() / 2L * 2L + 1L;
      this.rand.setSeed((long)par2 * i1 + (long)par3 * j1 ^ this.worldObj.getSeed());
      boolean flag = false;
      if (!flag && this.rand.nextInt(20) == 0) {
         int k1 = k + this.rand.nextInt(16) + 8;
         int l1 = this.rand.nextInt(256);
         int i2 = l + this.rand.nextInt(16) + 8;
         BlockPos lp = new BlockPos(k1, l1, i2);
         if (this.rand.nextInt(4) == 0) {
            (new WorldGenLakes(CandyCraft.GrenadineStatic)).generate(this.worldObj, this.rand, lp);
         } else if (this.rand.nextInt(16) == 0) {
            (new WorldGenWebLakes(CandyCraft.CottonCandyWeb)).generate(this.worldObj, this.rand, lp);
         } else {
            (new WorldGenLakes(Blocks.WATER)).generate(this.worldObj, this.rand, lp);
         }
      }

      int k1 = k + this.rand.nextInt(16) + 8;
      int l1 = this.rand.nextInt(256);
      int i2 = l + this.rand.nextInt(16) + 8;
      if ((biomegenbase == CandyCraft.CandyForest || biomegenbase == CandyCraft.CandyColdForest || biomegenbase == CandyCraft.CandyEnchantedForest) && WorldProviderCandy.canGenHouses <= 0 && this.rand.nextInt(40) == 0) {
         (new WorldGenCandyHouse()).generate(this.worldObj, this.rand, new BlockPos(k1, l1, i2));
      }

      if (biomegenbase == CandyCraft.CandyFrostPlains && WorldProviderCandy.canGenIceTower <= 0 && this.rand.nextInt(40) == 0) {
         (new WorldGenIceTower()).generate(this.worldObj, this.rand, new BlockPos(k1, l1, i2));
      }

      if (TerrainGen.populate(this, this.worldObj, this.rand, par2, par3, flag, PopulateChunkEvent.Populate.EventType.LAVA) && !flag && this.rand.nextInt(8) == 0) {
         k1 = k + this.rand.nextInt(16) + 8;
         l1 = this.rand.nextInt(this.rand.nextInt(248) + 8);
         i2 = l + this.rand.nextInt(16) + 8;
         if (l1 < 63 || this.rand.nextInt(10) == 0) {
            (new WorldGenLakes(Blocks.LAVA)).generate(this.worldObj, this.rand, new BlockPos(k1, l1, i2));
         }
      }

      if (this.rand.nextInt(300) == 0) {
         k1 = k + this.rand.nextInt(16) + 8;
         l1 = 62;
         i2 = l + this.rand.nextInt(16) + 8;
         if (biomegenbase == CandyCraft.CandyOcean && WorldProviderCandy.canGenTemple <= 0) {
            (new WorldGenWaterTemple()).generate(this.worldObj, this.rand, new BlockPos(k1, l1, i2));
         }
      }

      if (this.rand.nextInt(250) == 0 && WorldProviderCandy.canGenGeyser <= 0) {
         for(int g = 0; g < this.rand.nextInt(10) + 10; ++g) {
            k1 = k + this.rand.nextInt(16) + 8;
            l1 = 62;
            i2 = l + this.rand.nextInt(16) + 8;
            if (biomegenbase == CandyCraft.CandyOcean) {
               (new WorldGenGeyser()).generate(this.worldObj, this.rand, new BlockPos(k1, l1, i2));
            }
         }
      }

      if (this.rand.nextInt(130) == 0) {
         k1 = k + this.rand.nextInt(16) + 8;
         l1 = 100;
         i2 = l + this.rand.nextInt(16) + 8;
         if (biomegenbase == CandyCraft.CandyPlains && WorldProviderCandy.canGenChewingGum <= 0) {
            (new WorldGenChewingGumTotem()).generate(this.worldObj, this.rand, new BlockPos(k1, l1, i2));
         }
      }

      if (this.rand.nextInt(500) == 0 && WorldProviderCandy.canGenIsland <= 0) {
         k1 = k + 8;
         l1 = this.rand.nextInt(60) + 120;
         i2 = l + 8;
         Chunk ch = this.worldObj.getChunk(new BlockPos(k1, 0, i2));
         this.worldObj.getChunkProvider().provideChunk(ch.x + 1, ch.z);
         this.worldObj.getChunkProvider().provideChunk(ch.x - 1, ch.z);
         this.worldObj.getChunkProvider().provideChunk(ch.x, ch.z + 1);
         this.worldObj.getChunkProvider().provideChunk(ch.x, ch.z - 1);
         this.worldObj.getChunkProvider().provideChunk(ch.x - 1, ch.z - 1);
         this.worldObj.getChunkProvider().provideChunk(ch.x + 1, ch.z - 1);
         this.worldObj.getChunkProvider().provideChunk(ch.x - 1, ch.z + 1);
         this.worldObj.getChunkProvider().provideChunk(ch.x + 1, ch.z + 1);
         if (CandyCraftConfig.generateFloatingIsland && biomegenbase != CandyCraft.CandyHellMountains && WorldProviderCandy.canGenIsland <= 0) {
            (new WorldGenFloatingIsland()).generate(this.worldObj, this.rand, new BlockPos(k1, l1, i2));
         }

      } else {
         boolean doGen = TerrainGen.populate(this, this.worldObj, this.rand, par2, par3, flag, PopulateChunkEvent.Populate.EventType.DUNGEON);

         for(int var24 = 0; doGen && var24 < 12; ++var24) {
            l1 = k + this.rand.nextInt(16) + 8;
            i2 = this.rand.nextInt(256);
            int j2 = l + this.rand.nextInt(16) + 8;
            (new WorldGenHoneyDungeons()).generate(this.worldObj, this.rand, new BlockPos(l1, i2, j2));
         }

         if (CandyCraftConfig.generateGingerbreadVillage && this.rand.nextInt(400) == 0 && WorldProviderCandy.canGenVillage <= 0) {
            flag = (new WorldGenUnderGroundVillage()).generate(this.worldObj, this.rand, new BlockPos(par2 + this.rand.nextInt(500) - 250, this.rand.nextInt(24) + 12, par3 + this.rand.nextInt(500) - 250));
         }

         biomegenbase.decorate(this.worldObj, this.rand, new BlockPos(k, 0, l));
         WorldEntitySpawner.performWorldGenSpawning(this.worldObj, biomegenbase, k + 8, l + 8, 16, 16, this.rand);
         k += 8;
         l += 8;
         doGen = TerrainGen.populate(this, this.worldObj, this.rand, par2, par3, flag, PopulateChunkEvent.Populate.EventType.ICE);

         for(int var25 = 0; doGen && var25 < 16; ++var25) {
            for(int var33 = 0; var33 < 16; ++var33) {
               BlockPos top = this.worldObj.getPrecipitationHeight(new BlockPos(k + var25, 0, l + var33));
               if (this.worldObj.canSnowAt(top, true)) {
                  this.worldObj.setBlockState(top, Blocks.SNOW_LAYER.getDefaultState(), 2);
               }
            }
         }

         BlockFalling.fallInstantly = false;
      }
   }

   @Override
   public boolean generateStructures(Chunk chunkIn, int x, int z) {
      return false;
   }

   @Override
   public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
      Biome biomegenbase = this.worldObj.getBiome(pos);
      return biomegenbase.getSpawnableList(creatureType);
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
