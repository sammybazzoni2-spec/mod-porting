package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCandyTrees2 extends WorldGenerator {
   private final int minTreeHeight;
   private final boolean vinesGrow;
   private final int metaWood;
   private int metaLeaves;
   private boolean metaChange;

   public WorldGenCandyTrees2(boolean par1, int par2) {
      this(par1, 1, 0, par2, false);
      this.metaChange = false;
   }

   public WorldGenCandyTrees2(boolean par1, boolean par2) {
      this(par1, 0);
      this.metaChange = par2;
   }

   public WorldGenCandyTrees2(boolean par1, int par2, int par3, int par4, boolean par5) {
      super(par1);
      this.metaChange = false;
      this.metaChange = false;
      this.minTreeHeight = par2;
      this.metaWood = par3;
      this.metaLeaves = par4;
      this.vinesGrow = par5;
   }

   @Override
   public boolean generate(World par1World, Random par2Random, BlockPos pos) {
      int par3 = pos.getX();
      int par4 = pos.getY();
      int par5 = pos.getZ();
      if (par2Random.nextInt(5) > 0) {
         return this.generate2(par1World, par2Random, par3, par4, par5);
      } else {
         int l = par2Random.nextInt(5) + this.minTreeHeight;
         boolean flag = true;
         if (this.metaChange) {
            int i = par2Random.nextInt(3);
            if (i == 0) {
               this.metaLeaves = 1;
            } else {
               this.metaLeaves = 0;
            }
         }

         if (par4 >= 1 && par4 + l + 1 <= 256) {
            for(int i1 = par4; i1 <= par4 + 1 + l; ++i1) {
               byte b0 = 1;
               if (i1 == par4) {
                  b0 = 0;
               }

               if (i1 >= par4 + 1 + l - 2) {
                  b0 = 2;
               }

               for(int j1 = par3 - b0; j1 <= par3 + b0 && flag; ++j1) {
                  for(int k1 = par5 - b0; k1 <= par5 + b0 && flag; ++k1) {
                     if (i1 >= 0 && i1 < 256) {
                        CandyGenUtil.getBlock(par1World, j1, i1, k1);
                        if (!this.isReplaceable(par1World, j1, i1, k1)) {
                           flag = false;
                        }
                     } else {
                        flag = false;
                     }
                  }
               }
            }

            if (!flag) {
               return false;
            } else {
               Block block2 = CandyGenUtil.getBlock(par1World, par3, par4 - 1, par5);
               boolean isSoil = block2.canSustainPlant(block2.getDefaultState(), par1World, new BlockPos(par3, par4 - 1, par5), EnumFacing.UP, (BlockCandySapling)CandyCraft.CandySapling);
               if (isSoil && par4 < 256 - l - 1) {
                  block2.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
                  byte b0 = 3;
                  byte b1 = 0;

                  for(int k1 = par4 - b0 + l; k1 <= par4 + l; ++k1) {
                     int i3 = k1 - (par4 + l);
                     int l1 = b1 + 1 - i3 / 2;

                     for(int i2 = par3 - l1; i2 <= par3 + l1; ++i2) {
                        int j2 = i2 - par3;

                        for(int k2 = par5 - l1; k2 <= par5 + l1; ++k2) {
                           int l2 = k2 - par5;
                           if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || par2Random.nextInt(2) != 0 && i3 != 0) {
                              Block block1 = CandyGenUtil.getBlock(par1World, i2, k1, k2);
                              if (block1.isAir(block1.getDefaultState(), par1World, new BlockPos(i2, k1, k2)) || block1.isLeaves(block1.getDefaultState(), par1World, new BlockPos(i2, k1, k2))) {
                                 CandyGenUtil.setBlock(par1World, i2, k1, k2, CandyCraft.CandyLeave, 1, 3);
                              }
                           }
                        }
                     }
                  }

                  for(int var24 = 0; var24 < l; ++var24) {
                     Block block = CandyGenUtil.getBlock(par1World, par3, par4 + var24, par5);
                     if (block.isAir(block.getDefaultState(), par1World, new BlockPos(par3, par4 + var24, par5)) || block.isLeaves(block.getDefaultState(), par1World, new BlockPos(par3, par4 + var24, par5))) {
                        CandyGenUtil.setBlock(par1World, par3, par4 + var24, par5, CandyCraft.CandyLog, 1, 3);
                     }
                  }

                  return true;
               } else {
                  return false;
               }
            }
         } else {
            return false;
         }
      }
   }

   protected boolean isReplaceable(World world, int x, int y, int z) {
      Block block = CandyGenUtil.getBlock(world, x, y, z);
      return block.isAir(block.getDefaultState(), world, new BlockPos(x, y, z)) || block.isLeaves(block.getDefaultState(), world, new BlockPos(x, y, z)) || block.isWood(world, new BlockPos(x, y, z)) || this.func_150523_a(block);
   }

   protected boolean func_150523_a(Block p_150523_1_) {
      return CandyGenUtil.mat(p_150523_1_) == Material.AIR || p_150523_1_ == CandyCraft.CandyLeave || p_150523_1_ == CandyCraft.PuddingBlock || p_150523_1_ == CandyCraft.FlourBlock || p_150523_1_ == CandyCraft.CandyLog || p_150523_1_ == CandyCraft.CandySapling;
   }

   public boolean generate2(World par1World, Random par2Random, int par3, int par4, int par5) {
      int l = par2Random.nextInt(8) + 9;
      int i1 = 1 + par2Random.nextInt(2);
      int j1 = l - i1;
      int k1 = 2 + par2Random.nextInt(2);
      boolean flag = true;
      if (par4 >= 1 && par4 + l + 1 <= 256) {
         for(int l1 = par4; l1 <= par4 + 1 + l && flag; ++l1) {
            boolean flag1 = true;
            int l3;
            if (l1 - par4 < i1) {
               l3 = 0;
            } else {
               l3 = k1;
            }

            for(int i2 = par3 - l3; i2 <= par3 + l3 && flag; ++i2) {
               for(int j2 = par5 - l3; j2 <= par5 + l3 && flag; ++j2) {
                  if (l1 >= 0 && l1 < 256) {
                     Block block = CandyGenUtil.getBlock(par1World, i2, l1, j2);
                     if (!block.isAir(block.getDefaultState(), par1World, new BlockPos(i2, l1, j2)) && !block.isLeaves(block.getDefaultState(), par1World, new BlockPos(i2, l1, j2))) {
                        flag = false;
                     }
                  } else {
                     flag = false;
                  }
               }
            }
         }

         if (!flag) {
            return false;
         } else {
            Block block1 = CandyGenUtil.getBlock(par1World, par3, par4 - 1, par5);
            boolean isSoil = block1.canSustainPlant(block1.getDefaultState(), par1World, new BlockPos(par3, par4 - 1, par5), EnumFacing.UP, (BlockCandySapling)CandyCraft.CandySapling);
            if (isSoil && par4 < 256 - l - 1) {
               block1.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
               int l3 = par2Random.nextInt(3) + 1;
               int i2 = par2Random.nextInt(4) + 1;
               byte b0 = 0;

               for(int i4 = 0; i4 <= j1; ++i4) {
                  int k2 = par4 + l - i4;

                  for(int l2 = par3 - l3; l2 <= par3 + l3; ++l2) {
                     int i3 = l2 - par3;

                     for(int j3 = par5 - l3; j3 <= par5 + l3; ++j3) {
                        int k3 = j3 - par5;
                        if ((Math.sin((double)i3) != (double)l3 || Math.sin((double)k3) != (double)l3 || l3 <= 0) && CandyGenUtil.getBlock(par1World, l2, k2, j3).canBeReplacedByLeaves(par1World, l2, k2, j3)) {
                           CandyGenUtil.setBlock(par1World, l2, k2, j3, CandyCraft.CandyLeave, 1, 3);
                        }
                     }
                  }

                  if (l3 >= i2) {
                     l3 = b0;
                     b0 = 1;
                     ++i2;
                     if (i2 > k1) {
                        i2 = k1;
                     }
                  } else {
                     ++l3;
                  }
               }

               int var29 = par2Random.nextInt(3);

               for(int k2 = 0; k2 < l - var29; ++k2) {
                  Block block2 = CandyGenUtil.getBlock(par1World, par3, par4 + k2, par5);
                  if (block2.isAir(block2.getDefaultState(), par1World, new BlockPos(par3, par4 + k2, par5)) || block2.isLeaves(block2.getDefaultState(), par1World, new BlockPos(par3, par4 + k2, par5))) {
                     CandyGenUtil.setBlock(par1World, par3, par4 + k2, par5, CandyCraft.CandyLog, 1, 3);
                  }
               }

               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }
}
