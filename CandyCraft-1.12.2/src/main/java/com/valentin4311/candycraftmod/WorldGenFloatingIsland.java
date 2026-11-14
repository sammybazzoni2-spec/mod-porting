package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFloatingIsland extends WorldGenerator {
   ArrayList<ArrayBlock> blocks = new ArrayList();
   HashMap<String, ArrayBlock> prevBlock = new HashMap();
   ArrayList<Entity> entities = new ArrayList();
   public boolean finished;
   public int x;
   public int y;
   public int z;
   public World world;

   public boolean generate(World par1, Random par2, BlockPos pos, int mainIsland) {
      int par3 = pos.getX();
      int par4 = pos.getY();
      int par5 = pos.getZ();
      WorldProviderCandy.canGenIsland = 600;
      int nX = par2.nextInt(8) - 4;
      int nZ = par2.nextInt(8) - 4;
      int[][] lastLayer = new int[32][32];
      lastLayer[16][16] = 2;
      lastLayer[16 + nX][16 + nZ] = 2;
      int[][] newLayer = new int[32][32];
      int maxHeight = par2.nextInt(3) + 7;

      for(int y = 0; y < maxHeight; ++y) {
         newLayer = new int[32][32];

         for(int x = 0; x < 32; ++x) {
            for(int z = 0; z < 32; ++z) {
               if (lastLayer[x][z] != 0 && lastLayer[x][z] == 2) {
                  newLayer[x][z] = 2;
                  this.setBlock(par3 + x, par4 + y, par5 + z, this.getBlockForHeight(y, maxHeight, par2, CandyGenUtil.isAir(par1, par3 + x, par4 + y + 1, par5 + z)));
                  if (par2.nextInt(4) < 3 || y == maxHeight - 1) {
                     newLayer[x + 1][z] = 2;
                     this.setBlock(par3 + x + 1, par4 + y, par5 + z, this.getBlockForHeight(y, maxHeight, par2, CandyGenUtil.isAir(par1, par3 + x + 1, par4 + y + 1, par5 + z)));
                  }

                  if (par2.nextInt(4) < 3 || y == maxHeight - 1) {
                     newLayer[x - 1][z] = 2;
                     this.setBlock(par3 + x - 1, par4 + y, par5 + z, this.getBlockForHeight(y, maxHeight, par2, CandyGenUtil.isAir(par1, par3 + x - 1, par4 + y + 1, par5 + z)));
                  }

                  if (par2.nextInt(4) < 3 || y == maxHeight - 1) {
                     newLayer[x][z - 1] = 2;
                     this.setBlock(par3 + x, par4 + y, par5 + z - 1, this.getBlockForHeight(y, maxHeight, par2, CandyGenUtil.isAir(par1, par3 + x, par4 + y + 1, par5 + z - 1)));
                  }

                  if (par2.nextInt(4) < 3 || y == maxHeight - 1) {
                     newLayer[x][z + 1] = 2;
                     this.setBlock(par3 + x, par4 + y, par5 + z + 1, this.getBlockForHeight(y, maxHeight, par2, CandyGenUtil.isAir(par1, par3 + x, par4 + y + 1, par5 + z + 1)));
                  }

                  if (par2.nextInt(4) < 1) {
                     newLayer[x - 1][z - 1] = 2;
                     this.setBlock(par3 + x - 1, par4 + y, par5 + z - 1, this.getBlockForHeight(y, maxHeight, par2, CandyGenUtil.isAir(par1, par3 + x - 1, par4 + y + 1, par5 + z - 1)));
                  }

                  if (par2.nextInt(4) < 1) {
                     newLayer[x + 1][z + 1] = 2;
                     this.setBlock(par3 + x + 1, par4 + y, par5 + z + 1, this.getBlockForHeight(y, maxHeight, par2, CandyGenUtil.isAir(par1, par3 + x + 1, par4 + y + 1, par5 + z + 1)));
                  }

                  if (par2.nextInt(4) < 1) {
                     newLayer[x + 1][z - 1] = 2;
                     this.setBlock(par3 + x + 1, par4 + y, par5 + z - 1, this.getBlockForHeight(y, maxHeight, par2, CandyGenUtil.isAir(par1, par3 + x + 1, par4 + y + 1, par5 + z - 1)));
                  }

                  if (par2.nextInt(4) < 1) {
                     newLayer[x - 1][z + 1] = 2;
                     this.setBlock(par3 + x - 1, par4 + y, par5 + z + 1, this.getBlockForHeight(y, maxHeight, par2, CandyGenUtil.isAir(par1, par3 + x - 1, par4 + y + 1, par5 + z + 1)));
                  }
               }
            }
         }

         lastLayer = newLayer;
      }

      this.decorateIsland(par1, lastLayer, par3, par4 + maxHeight, par5, par2);
      return true;
   }

   public void finishGeneration(World world, BlockPos pos) {
      for(int i = 0; i < this.blocks.size(); ++i) {
         ArrayBlock bl = (ArrayBlock)this.blocks.get(i);
         CandyGenUtil.setBlock(world, bl.x, bl.y, bl.z, bl.block, bl.metadata, 3);
      }

      for(int i = 0; i < this.entities.size(); ++i) {
         world.spawnEntity((Entity)this.entities.get(i));
      }

   }

   private void setBlock(int x, int y, int z, Block block) {
      this.blocks.add(new ArrayBlock(x, y, z, block, 0));
   }

   private void setBlock(int x, int y, int z, Block block, int metadata) {
      this.blocks.add(new ArrayBlock(x, y, z, block, metadata));
   }

   private void decorateIsland(World par1, int[][] lastLayer, int par3, int par4, int par5, Random par2) {
      int type = par2.nextInt(3);
      if (type == 0 || type == 1) {
         for(int x = 0; x < 32; ++x) {
            for(int z = 0; z < 32; ++z) {
               if (lastLayer[x][z] == 2) {
                  if (par2.nextInt(3) == 0) {
                     this.setBlock(par3 + x, par4 - 1, par5 + z, CandyCraft.CandySoil);
                     this.setBlock(par3 + x, par4, par5 + z, CandyCraft.DragiCrops, par2.nextInt(3) + 6);
                  } else {
                     this.setBlock(par3 + x, par4, par5 + z, CandyCraft.TallCandyGrass, par2.nextInt(3));
                  }
               }
            }
         }
      }

      if (type == 1) {
         this.genHouse(par1, par2, par3 + 16 + par2.nextInt(4) - 2, par4 - 1, par5 + 16 + par2.nextInt(4) - 2, par2.nextInt(3), true);
      }

      if (type == 2) {
         for(int x = 0; x < 32; ++x) {
            for(int z = 0; z < 32; ++z) {
               if (lastLayer[x][z] == 2) {
                  if (par2.nextInt(2) == 0) {
                     this.setBlock(par3 + x, par4, par5 + z, CandyCraft.TallCandyGrass, par2.nextInt(3));
                  } else {
                     this.setBlock(par3 + x, par4, par5 + z, CandyCraft.ChewingGumPlate, par2.nextInt(3));
                  }
               }
            }
         }

         EntityBossBeetle boss = new EntityBossBeetle(par1);
         boss.setPosition((double)(par3 + 16), (double)(par4 + 2), (double)(par5 + 16));
         this.entities.add(boss);
      }

   }

   public void genHouse(World world, Random random, int i, int j, int k, int side, boolean b) {
      for(int i2 = i; i2 < 5 + i; ++i2) {
         for(int k2 = k; k2 < 5 + k; ++k2) {
            this.setBlock(i2, j, k2, CandyCraft.ChocolateStone);
            this.setBlock(i2, j + 3, k2, CandyCraft.CandyPlanks);
         }
      }

      this.setBlock(i, j + 1, k, CandyCraft.CandyLog);
      this.setBlock(i, j + 2, k, CandyCraft.CandyLog);
      this.setBlock(i + 4, j + 1, k, CandyCraft.CandyLog);
      this.setBlock(i + 4, j + 2, k, CandyCraft.CandyLog);
      this.setBlock(i + 4, j + 1, k + 4, CandyCraft.CandyLog);
      this.setBlock(i + 4, j + 2, k + 4, CandyCraft.CandyLog);
      this.setBlock(i, j + 1, k + 4, CandyCraft.CandyLog);
      this.setBlock(i, j + 2, k + 4, CandyCraft.CandyLog);
      this.setBlock(i + 1, j + 3, k, CandyCraft.CandyLog, 4);
      this.setBlock(i + 2, j + 3, k, CandyCraft.CandyLog, 4);
      this.setBlock(i + 3, j + 3, k, CandyCraft.CandyLog, 4);
      this.setBlock(i + 1, j + 3, k + 4, CandyCraft.CandyLog, 4);
      this.setBlock(i + 2, j + 3, k + 4, CandyCraft.CandyLog, 4);
      this.setBlock(i + 3, j + 3, k + 4, CandyCraft.CandyLog, 4);
      this.setBlock(i, j + 3, k + 1, CandyCraft.CandyLog, 8);
      this.setBlock(i, j + 3, k + 2, CandyCraft.CandyLog, 8);
      this.setBlock(i, j + 3, k + 3, CandyCraft.CandyLog, 8);
      this.setBlock(i + 4, j + 3, k + 1, CandyCraft.CandyLog, 8);
      this.setBlock(i + 4, j + 3, k + 2, CandyCraft.CandyLog, 8);
      this.setBlock(i + 4, j + 3, k + 3, CandyCraft.CandyLog, 8);
      this.setBlock(i + 1, j + 1, k, CandyCraft.CandyPlanks);
      this.setBlock(i + 1, j + 2, k, CandyCraft.CandyPlanks);
      this.setBlock(i + 2, j + 1, k, CandyCraft.CandyPlanks);
      this.setBlock(i + 2, j + 2, k, CandyCraft.CandyPlanks);
      this.setBlock(i + 3, j + 1, k, CandyCraft.CandyPlanks);
      this.setBlock(i + 3, j + 2, k, CandyCraft.CandyPlanks);
      this.setBlock(i, j + 1, k + 1, CandyCraft.CandyPlanks);
      this.setBlock(i, j + 2, k + 1, CandyCraft.CandyPlanks);
      this.setBlock(i, j + 1, k + 2, CandyCraft.CandyPlanks);
      this.setBlock(i, j + 2, k + 2, CandyCraft.CandyPlanks);
      this.setBlock(i, j + 1, k + 3, CandyCraft.CandyPlanks);
      this.setBlock(i, j + 2, k + 3, CandyCraft.CandyPlanks);
      this.setBlock(i + 4, j + 1, k + 1, CandyCraft.CandyPlanks);
      this.setBlock(i + 4, j + 2, k + 1, CandyCraft.CandyPlanks);
      this.setBlock(i + 4, j + 1, k + 2, CandyCraft.CandyPlanks);
      this.setBlock(i + 4, j + 2, k + 2, CandyCraft.CandyPlanks);
      this.setBlock(i + 4, j + 1, k + 3, CandyCraft.CandyPlanks);
      this.setBlock(i + 4, j + 2, k + 3, CandyCraft.CandyPlanks);
      this.setBlock(i + 1, j + 1, k + 4, CandyCraft.CandyPlanks);
      this.setBlock(i + 1, j + 2, k + 4, CandyCraft.CandyPlanks);
      this.setBlock(i + 2, j + 1, k + 4, CandyCraft.CandyPlanks);
      this.setBlock(i + 2, j + 2, k + 4, CandyCraft.CandyPlanks);
      this.setBlock(i + 3, j + 1, k + 4, CandyCraft.CandyPlanks);
      this.setBlock(i + 3, j + 2, k + 4, CandyCraft.CandyPlanks);
      if (b) {
         int window = random.nextInt(3);
         int x = i;
         int z = k;
         if (side == 2) {
            z = k + 1 + window;
         }

         if (side == 3) {
            x = i + 1 + window;
         }

         if (side == 0) {
            z = k + 1 + window;
            x = i + 4;
         }

         if (side == 1) {
            x = i + 1 + window;
            z = k + 4;
         }

         this.setBlock(x, j + 2, z, CandyCraft.CaramelPane2);
      }

      int window = random.nextInt(3);
      int x = i;
      int z = k;
      if (side == 0) {
         z = k + 1 + window;
      }

      if (side == 1) {
         x = i + 1 + window;
      }

      if (side == 2) {
         z = k + 1 + window;
         x = i + 4;
      }

      if (side == 3) {
         x = i + 1 + window;
         z = k + 4;
      }

      this.setBlock(x, j + 1, z, Blocks.AIR);
      this.setBlock(x, j + 2, z, Blocks.AIR);
      this.setBlock(i, j + 3, k, Blocks.AIR);
      this.setBlock(i + 4, j + 3, k, Blocks.AIR);
      this.setBlock(i + 4, j + 3, k + 4, Blocks.AIR);
      this.setBlock(i, j + 3, k + 4, Blocks.AIR);
      EntityGingerBreadMan man = new EntityGingerBreadMan(world);
      man.setPosition((double)(i + 2), (double)(j + 1), (double)(k + 2));
      man.onInitialSpawn(man.world.getDifficultyForLocation(new BlockPos(man)), (IEntityLivingData)null);
      if (j > 100) {
         man.setProfession(3);
      }

      this.entities.add(man);
   }

   public Block getBlockForHeight(int height, int maxHeight, Random rand, boolean topBlock) {
      int distance = maxHeight - height;
      if (distance == 1) {
         return !topBlock ? CandyCraft.FlourBlock : CandyCraft.PuddingBlock;
      } else if (distance == 2) {
         return CandyCraft.FlourBlock;
      } else if (distance > 2 && distance <= 6) {
         return rand.nextInt(5) < distance ? CandyCraft.ChocolateStone : CandyCraft.FlourBlock;
      } else {
         return CandyCraft.ChocolateStone;
      }
   }

   @Override
   public boolean generate(World p_76484_1_, Random p_76484_2_, BlockPos pos) {
      int p_76484_3_ = pos.getX();
      int p_76484_4_ = pos.getY();
      int p_76484_5_ = pos.getZ();
      this.x = p_76484_3_;
      this.y = p_76484_4_;
      this.z = p_76484_5_;
      this.world = p_76484_1_;
      System.out.println("Floating island is generating at " + this.x + " " + this.y + " " + this.z);
      ServerTick.floatingIsland.add(this);
      ThreadFloatingIsland is = new ThreadFloatingIsland(this, p_76484_1_, p_76484_2_, p_76484_3_, p_76484_4_, p_76484_5_);
      is.start();
      return true;
   }
}
