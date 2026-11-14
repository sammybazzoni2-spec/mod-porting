package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenUnderGroundVillage extends WorldGenerator {
   ArrayList<ArrayBlock> blocks = new ArrayList();
   ArrayList<Entity> entities = new ArrayList();

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      WorldProviderCandy.canGenVillage = 1200;
      int meta = random.nextInt(3);
      int meta2 = random.nextInt(3);
      int meta3 = random.nextInt(3);
      int meta4 = random.nextInt(3);
      i -= 32;
      k -= 32;
      this.tryCall(world, i + 32, j + 2, k + 32);
      boolean lamp = false;

      for(int i2 = i; i2 < i + 64; ++i2) {
         lamp = !lamp;

         for(int j2 = j; j2 < j + 7; ++j2) {
            for(int k2 = k; k2 < k + 64; ++k2) {
               if (j2 < j + 2) {
                  this.setBlock(world, i2, j2, k2, Blocks.STONE);
               } else if (i2 != i && k2 != k && i2 != i + 63 && k2 != k + 63) {
                  if (j2 != j + 2 && j2 != j + 5 || i2 != i + 1 && k2 != k + 1 && i2 != i + 62 && k2 != k + 62) {
                     this.setBlock(world, i2, j2, k2, j2 < j + 6 ? null : (!lamp ? CandyCraft.ChocolateCobbleStone : CandyCraft.HoneyLamp));
                     lamp = !lamp;
                  } else {
                     this.setBlock(world, i2, j2, k2, CandyCraft.BarleyBlock);
                  }
               } else {
                  this.setBlock(world, i2, j2, k2, CandyCraft.ChocolateCobbleStone);
               }
            }
         }
      }

      for(int i2 = i; i2 < i + 64; ++i2) {
         for(int j2 = j; j2 < j + 7; ++j2) {
            for(int k2 = k; k2 < k + 64; ++k2) {
               if (j2 != j + 1 || i2 > i + 5 && i2 < i + 58 && k2 > k + 5 && k2 < k + 58) {
                  if (j2 != j + 1 || i2 != i + 6 && i2 != i + 57 && k2 != k + 6 && k2 != k + 57) {
                     if (j2 == j + 1) {
                        this.setBlock(world, i2, j2, k2, CandyCraft.PuddingBlock);
                     }
                  } else {
                     this.setBlock(world, i2, j2, k2, CandyCraft.CandyLog);
                  }
               } else {
                  this.setBlock(world, i2, j2, k2, CandyCraft.CandyPlanks);
               }
            }
         }
      }

      for(int i2 = i + 6; i2 < i + 58; ++i2) {
         for(int j2 = j; j2 < j + 7; ++j2) {
            for(int k2 = k + 6; k2 < k + 58; ++k2) {
               if (j2 != j + 1 || i2 != i + 6 || (k2 <= k + 20 || k2 >= k + 24) && (k2 <= k + 39 || k2 >= k + 43)) {
                  if (j2 != j + 1 || i2 != i + 57 || (k2 <= k + 20 || k2 >= k + 24) && (k2 <= k + 39 || k2 >= k + 43)) {
                     if (j2 != j + 1 || k2 != k + 6 || (i2 <= i + 20 || i2 >= i + 24) && (i2 <= i + 39 || i2 >= i + 43)) {
                        if (j2 != j + 1 || k2 != k + 57 || (i2 <= i + 20 || i2 >= i + 24) && (i2 <= i + 39 || i2 >= i + 43)) {
                           if (j2 != j + 1 || (i2 < i + 21 || i2 > i + 23) && (i2 < i + 40 || i2 > i + 42) && (k2 < k + 21 || k2 > 23 + k) && (k2 < k + 40 || k2 > 42 + k)) {
                              if (j2 == j + 1 && (i2 == i + 20 || i2 == i + 43 || k2 == k + 20 || k2 == k + 43 || i2 == i + 24 || k2 == k + 24 || i2 == i + 39 || k2 == k + 39)) {
                                 this.setBlock(world, i2, j2, k2, CandyCraft.CandyLog);
                              }
                           } else {
                              this.setBlock(world, i2, j2, k2, random.nextBoolean() ? CandyCraft.ChocolateStone : CandyCraft.ChocolateCobbleStone);
                           }
                        } else {
                           this.setBlock(world, i2, j2, k2, CandyCraft.CandyWorkbench);
                        }
                     } else {
                        this.setBlock(world, i2, j2, k2, CandyCraft.CandyWorkbench);
                     }
                  } else {
                     this.setBlock(world, i2, j2, k2, CandyCraft.CandyWorkbench);
                  }
               } else {
                  this.setBlock(world, i2, j2, k2, CandyCraft.CandyWorkbench);
               }
            }
         }
      }

      this.genHouse(world, random, i + 8, j + 1, k + 8, random.nextInt(2), false);
      this.genHouse(world, random, i + 14, j + 1, k + 8, 1, true);
      this.genHouse(world, random, i + 8, j + 1, k + 14, 0, true);
      this.genHouse(world, random, i + 26, j + 1, k + 8, 3, random.nextBoolean());
      this.genHouse(world, random, i + 33, j + 1, k + 8, 3, random.nextBoolean());
      this.genHouse(world, random, i + 51, j + 1, k + 8, random.nextInt(2) + 1, false);
      this.genHouse(world, random, i + 45, j + 1, k + 8, 1, true);
      this.genHouse(world, random, i + 51, j + 1, k + 14, 2, true);
      this.genHouse(world, random, i + 26, j + 1, k + 51, 1, random.nextBoolean());
      this.genHouse(world, random, i + 33, j + 1, k + 51, 1, random.nextBoolean());
      this.genHouse(world, random, i + 51, j + 1, k + 51, random.nextInt(2) + 2, false);
      this.genHouse(world, random, i + 51, j + 1, k + 45, 2, true);
      this.genHouse(world, random, i + 45, j + 1, k + 51, 3, true);
      this.genHouse(world, random, i + 8, j + 1, k + 26, 2, random.nextBoolean());
      this.genHouse(world, random, i + 8, j + 1, k + 33, 2, random.nextBoolean());
      this.genHouse(world, random, i + 8, j + 1, k + 51, random.nextInt(2) == 0 ? 3 : 0, false);
      this.genHouse(world, random, i + 8, j + 1, k + 45, 0, true);
      this.genHouse(world, random, i + 14, j + 1, k + 51, 3, true);
      this.genHouse(world, random, i + 51, j + 1, k + 26, 0, random.nextBoolean());
      this.genHouse(world, random, i + 51, j + 1, k + 33, 0, random.nextBoolean());

      for(int z = 0; z < 6; ++z) {
         this.setBlock(world, i + 26, j + 2, k + 50 - z, CandyCraft.BarleyFence);
         this.setBlock(world, i + 37, j + 2, k + 50 - z, CandyCraft.BarleyFence);
         this.setBlock(world, i + 26, j + 2, k + 13 + z, CandyCraft.BarleyFence);
         this.setBlock(world, i + 37, j + 2, k + 13 + z, CandyCraft.BarleyFence);
         this.setBlock(world, i + 50 - z, j + 2, k + 26, CandyCraft.BarleyFence);
         this.setBlock(world, i + 50 - z, j + 2, k + 37, CandyCraft.BarleyFence);
         this.setBlock(world, i + 13 + z, j + 2, k + 26, CandyCraft.BarleyFence);
         this.setBlock(world, i + 13 + z, j + 2, k + 37, CandyCraft.BarleyFence);
      }

      this.setBlock(world, i + 18, j + 2, k + 27, CandyCraft.BarleyFence);
      this.setBlock(world, i + 18, j + 2, k + 36, CandyCraft.BarleyFence);
      this.setBlock(world, i + 45, j + 2, k + 27, CandyCraft.BarleyFence);
      this.setBlock(world, i + 45, j + 2, k + 36, CandyCraft.BarleyFence);
      this.setBlock(world, i + 27, j + 2, k + 18, CandyCraft.BarleyFence);
      this.setBlock(world, i + 36, j + 2, k + 18, CandyCraft.BarleyFence);
      this.setBlock(world, i + 27, j + 2, k + 45, CandyCraft.BarleyFence);
      this.setBlock(world, i + 36, j + 2, k + 45, CandyCraft.BarleyFence);
      this.setBlockAndMetadata(world, i + 14, j + 2, k + 14, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 15, j + 2, k + 14, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 16, j + 2, k + 14, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 17, j + 2, k + 14, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 18, j + 2, k + 14, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 14, j + 2, k + 15, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 14, j + 2, k + 16, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 14, j + 2, k + 17, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 14, j + 2, k + 18, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 49, j + 2, k + 14, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 48, j + 2, k + 14, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 47, j + 2, k + 14, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 46, j + 2, k + 14, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 45, j + 2, k + 14, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 49, j + 2, k + 15, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 49, j + 2, k + 16, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 49, j + 2, k + 17, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 49, j + 2, k + 18, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 49, j + 2, k + 49, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 48, j + 2, k + 49, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 47, j + 2, k + 49, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 46, j + 2, k + 49, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 45, j + 2, k + 49, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 49, j + 2, k + 48, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 49, j + 2, k + 47, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 49, j + 2, k + 46, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 49, j + 2, k + 45, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 14, j + 2, k + 49, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 14, j + 2, k + 48, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 14, j + 2, k + 47, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 14, j + 2, k + 46, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 14, j + 2, k + 45, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 15, j + 2, k + 49, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 16, j + 2, k + 49, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 17, j + 2, k + 49, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 18, j + 2, k + 49, CandyCraft.CandyLeave, meta);
      this.setBlockAndMetadata(world, i + 16, j + 2, k + 16, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 16, j + 2, k + 17, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 16, j + 2, k + 18, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 17, j + 2, k + 16, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 18, j + 2, k + 16, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 16, j + 2, k + 47, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 16, j + 2, k + 46, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 16, j + 2, k + 45, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 17, j + 2, k + 47, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 18, j + 2, k + 47, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 47, j + 2, k + 47, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 47, j + 2, k + 46, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 47, j + 2, k + 45, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 46, j + 2, k + 47, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 45, j + 2, k + 47, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 47, j + 2, k + 16, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 46, j + 2, k + 16, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 45, j + 2, k + 16, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 47, j + 2, k + 17, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 47, j + 2, k + 18, CandyCraft.CandyLeave, meta2);
      this.setBlockAndMetadata(world, i + 18, j + 2, k + 18, CandyCraft.CandyLeave, meta3);
      this.setBlockAndMetadata(world, i + 18, j + 2, k + 45, CandyCraft.CandyLeave, meta3);
      this.setBlockAndMetadata(world, i + 45, j + 2, k + 18, CandyCraft.CandyLeave, meta3);
      this.setBlockAndMetadata(world, i + 45, j + 2, k + 45, CandyCraft.CandyLeave, meta3);
      this.setBlockAndMetadata(world, i + 26, j + 2, k + 26, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 26, j + 2, k + 27, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 26, j + 2, k + 28, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 27, j + 2, k + 26, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 28, j + 2, k + 26, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 37, j + 2, k + 26, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 37, j + 2, k + 27, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 37, j + 2, k + 28, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 36, j + 2, k + 26, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 35, j + 2, k + 26, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 37, j + 2, k + 37, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 37, j + 2, k + 36, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 36, j + 2, k + 37, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 35, j + 2, k + 37, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 37, j + 2, k + 35, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 26, j + 2, k + 37, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 26, j + 2, k + 36, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 26, j + 2, k + 35, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 27, j + 2, k + 37, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 28, j + 2, k + 37, CandyCraft.CandyLeave, meta4);
      this.setBlockAndMetadata(world, i + 31, j + 2, k + 31, CandyCraft.PuddingBlock, meta4);
      this.setBlockAndMetadata(world, i + 32, j + 2, k + 31, CandyCraft.PuddingBlock, meta4);
      this.setBlockAndMetadata(world, i + 31, j + 2, k + 32, CandyCraft.PuddingBlock, meta4);
      this.setBlockAndMetadata(world, i + 32, j + 2, k + 32, CandyCraft.PuddingBlock, meta4);
      this.setBlockAndMetadata(world, i + 30, j + 2, k + 31, CandyCraft.PuddingBlock, meta4);
      this.setBlockAndMetadata(world, i + 30, j + 2, k + 32, CandyCraft.PuddingBlock, meta4);
      this.setBlockAndMetadata(world, i + 33, j + 2, k + 31, CandyCraft.PuddingBlock, meta4);
      this.setBlockAndMetadata(world, i + 33, j + 2, k + 32, CandyCraft.PuddingBlock, meta4);
      this.setBlockAndMetadata(world, i + 31, j + 2, k + 30, CandyCraft.PuddingBlock, meta4);
      this.setBlockAndMetadata(world, i + 32, j + 2, k + 30, CandyCraft.PuddingBlock, meta4);
      this.setBlockAndMetadata(world, i + 31, j + 2, k + 33, CandyCraft.PuddingBlock, meta4);
      this.setBlockAndMetadata(world, i + 32, j + 2, k + 33, CandyCraft.PuddingBlock, meta4);
      this.setBlockAndMetadata(world, i + 30, j + 2, k + 30, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 33, j + 2, k + 30, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 30, j + 2, k + 33, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 33, j + 2, k + 33, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 34, j + 2, k + 33, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 33, j + 2, k + 34, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 30, j + 2, k + 29, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 29, j + 2, k + 30, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 33, j + 2, k + 29, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 34, j + 2, k + 30, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 29, j + 2, k + 33, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 30, j + 2, k + 34, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 31, j + 2, k + 29, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 32, j + 2, k + 29, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 31, j + 2, k + 34, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 32, j + 2, k + 34, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 29, j + 2, k + 31, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 29, j + 2, k + 32, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 34, j + 2, k + 31, CandyCraft.CandyHalfStep, meta4);
      this.setBlockAndMetadata(world, i + 34, j + 2, k + 32, CandyCraft.CandyHalfStep, meta4);

      for(int p = 0; p < this.blocks.size(); ++p) {
         ArrayBlock bl = (ArrayBlock)this.blocks.get(p);
         CandyGenUtil.setBlock(world, bl.x, bl.y, bl.z, bl.block, bl.metadata, 3);
      }

      for(int p = 0; p < this.entities.size(); ++p) {
         world.spawnEntity((Entity)this.entities.get(p));
      }

      for(int i2 = i; i2 < i + 64; ++i2) {
         for(int k2 = k; k2 < 64 + k; ++k2) {
            if (CandyGenUtil.isAir(world, i2, j + 2, k2) && CandyGenUtil.canStay(CandyCraft.TallCandyGrass, world, i2, j + 2, k2) && random.nextInt(3) == 0) {
               CandyGenUtil.setBlock(world, i2, j + 2, k2, CandyCraft.TallCandyGrass, (new Random()).nextInt(4), 3);
            }
         }
      }

      EntityBossSuguard boss = new EntityBossSuguard(world);
      boss.setPosition((double)(i + 32), (double)(j + 3), (double)(k + 32));
      boss.onInitialSpawn(boss.world.getDifficultyForLocation(new BlockPos(boss)), (IEntityLivingData)null);
      world.spawnEntity(boss);
      return true;
   }

   private void setBlock(int x, int y, int z, Block block) {
      this.blocks.add(new ArrayBlock(x, y, z, block, 0));
   }

   private void setBlock(int x, int y, int z, Block block, int metadata) {
      this.blocks.add(new ArrayBlock(x, y, z, block, metadata));
   }

   public void setBlockAndMetadata(World world, int x, int y, int z, Block bl, int m) {
      if (bl != null) {
         this.setBlock(x, y, z, bl, m);
      } else {
         this.setBlock(x, y, z, Blocks.AIR);
      }

   }

   public void setBlock(World world, int x, int y, int z, Block bl) {
      if (bl != null) {
         this.setBlock(x, y, z, bl);
      } else {
         this.setBlock(x, y, z, Blocks.AIR);
      }

   }

   private void tryCall(World world, int x, int y, int z) {
      for(int i = 0; i < world.playerEntities.size(); ++i) {
         Entity entity1 = (Entity)world.playerEntities.get(i);
         if (entity1 instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP)entity1;
            if (CandyGenUtil.hasItem(player, CandyCraft.HoneyEmblem)) {
               ITextComponent msg = new TextComponentString("§2");
               msg.appendSibling(new TextComponentTranslation("Msg.HoneyEmblemFound0"));
               msg.appendText("§2" + x + " " + y + " " + z);
               msg.appendSibling(new TextComponentTranslation("Msg.HoneyEmblemFound1"));
               player.sendMessage(msg);
            }
         }
      }

   }

   public void genHouse(World world, Random random, int i, int j, int k, int side, boolean b) {
      for(int i2 = i; i2 < 5 + i; ++i2) {
         for(int k2 = k; k2 < 5 + k; ++k2) {
            this.setBlock(world, i2, j, k2, CandyCraft.ChocolateStone);
            this.setBlock(world, i2, j + 3, k2, CandyCraft.CandyPlanks);
         }
      }

      this.setBlock(world, i, j + 1, k, CandyCraft.CandyLog);
      this.setBlock(world, i, j + 2, k, CandyCraft.CandyLog);
      this.setBlock(world, i + 4, j + 1, k, CandyCraft.CandyLog);
      this.setBlock(world, i + 4, j + 2, k, CandyCraft.CandyLog);
      this.setBlock(world, i + 4, j + 1, k + 4, CandyCraft.CandyLog);
      this.setBlock(world, i + 4, j + 2, k + 4, CandyCraft.CandyLog);
      this.setBlock(world, i, j + 1, k + 4, CandyCraft.CandyLog);
      this.setBlock(world, i, j + 2, k + 4, CandyCraft.CandyLog);
      this.setBlockAndMetadata(world, i + 1, j + 3, k, CandyCraft.CandyLog, 4);
      this.setBlockAndMetadata(world, i + 2, j + 3, k, CandyCraft.CandyLog, 4);
      this.setBlockAndMetadata(world, i + 3, j + 3, k, CandyCraft.CandyLog, 4);
      this.setBlockAndMetadata(world, i + 1, j + 3, k + 4, CandyCraft.CandyLog, 4);
      this.setBlockAndMetadata(world, i + 2, j + 3, k + 4, CandyCraft.CandyLog, 4);
      this.setBlockAndMetadata(world, i + 3, j + 3, k + 4, CandyCraft.CandyLog, 4);
      this.setBlockAndMetadata(world, i, j + 3, k + 1, CandyCraft.CandyLog, 8);
      this.setBlockAndMetadata(world, i, j + 3, k + 2, CandyCraft.CandyLog, 8);
      this.setBlockAndMetadata(world, i, j + 3, k + 3, CandyCraft.CandyLog, 8);
      this.setBlockAndMetadata(world, i + 4, j + 3, k + 1, CandyCraft.CandyLog, 8);
      this.setBlockAndMetadata(world, i + 4, j + 3, k + 2, CandyCraft.CandyLog, 8);
      this.setBlockAndMetadata(world, i + 4, j + 3, k + 3, CandyCraft.CandyLog, 8);
      this.setBlock(world, i + 1, j + 1, k, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 1, j + 2, k, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 2, j + 1, k, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 2, j + 2, k, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 3, j + 1, k, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 3, j + 2, k, CandyCraft.CandyPlanks);
      this.setBlock(world, i, j + 1, k + 1, CandyCraft.CandyPlanks);
      this.setBlock(world, i, j + 2, k + 1, CandyCraft.CandyPlanks);
      this.setBlock(world, i, j + 1, k + 2, CandyCraft.CandyPlanks);
      this.setBlock(world, i, j + 2, k + 2, CandyCraft.CandyPlanks);
      this.setBlock(world, i, j + 1, k + 3, CandyCraft.CandyPlanks);
      this.setBlock(world, i, j + 2, k + 3, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 4, j + 1, k + 1, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 4, j + 2, k + 1, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 4, j + 1, k + 2, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 4, j + 2, k + 2, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 4, j + 1, k + 3, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 4, j + 2, k + 3, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 1, j + 1, k + 4, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 1, j + 2, k + 4, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 2, j + 1, k + 4, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 2, j + 2, k + 4, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 3, j + 1, k + 4, CandyCraft.CandyPlanks);
      this.setBlock(world, i + 3, j + 2, k + 4, CandyCraft.CandyPlanks);
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

         int type = random.nextInt(3);
         this.setBlock(world, x, j + 2, z, type == 0 ? CandyCraft.CaramelPane0 : (type == 1 ? CandyCraft.CaramelPane1 : CandyCraft.CaramelPane2));
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

      this.setBlock(world, x, j + 1, z, (Block)null);
      this.setBlockAndMetadata(world, x, j + 2, z, CandyCraft.CandyHalfStep, 8);
      this.setBlock(world, i, j + 3, k, (Block)null);
      this.setBlock(world, i + 4, j + 3, k, (Block)null);
      this.setBlock(world, i + 4, j + 3, k + 4, (Block)null);
      this.setBlock(world, i, j + 3, k + 4, (Block)null);
      EntityGingerBreadMan man = new EntityGingerBreadMan(world);
      man.setPosition((double)(i + 2), (double)(j + 2), (double)(k + 2));
      man.onInitialSpawn(man.world.getDifficultyForLocation(new BlockPos(man)), (IEntityLivingData)null);
      if (j > 100) {
         man.setProfession(3);
      }

      this.entities.add(man);
   }
}
