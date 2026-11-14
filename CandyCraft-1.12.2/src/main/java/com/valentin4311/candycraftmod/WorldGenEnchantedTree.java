package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.block.material.Material;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenEnchantedTree extends WorldGenAbstractTree {
   private final int baseHeight;

   protected int getTreeHeight(Random rand) {
      return rand.nextInt(3) + this.baseHeight;
   }

   protected boolean isReplaceable(World world, int x, int y, int z) {
      Block block = CandyGenUtil.getBlock(world, x, y, z);
      return block.isAir(block.getDefaultState(), world, new BlockPos(x, y, z)) || block.isLeaves(block.getDefaultState(), world, new BlockPos(x, y, z)) || block.isWood(world, new BlockPos(x, y, z)) || CandyGenUtil.mat(block) == Material.AIR || block == CandyCraft.CandyLeave || block == CandyCraft.CandyLeave2 || block == CandyCraft.PuddingBlock || block == CandyCraft.FlourBlock || block == CandyCraft.CandyLog || block == CandyCraft.CandySapling;
   }

   public WorldGenEnchantedTree(boolean p_i45456_1_, int p_i45456_2_, int p_i45456_3_, int p_i45456_4_, int p_i45456_5_) {
      super(p_i45456_1_);
      this.baseHeight = p_i45456_2_;
   }

   private boolean func_150532_c(World p_150532_1_, Random p_150532_2_, int p_150532_3_, int p_150532_4_, int p_150532_5_) {
      Block block = CandyGenUtil.getBlock(p_150532_1_, p_150532_3_, p_150532_4_ - 1, p_150532_5_);
      if (block == CandyCraft.PuddingBlock) {
         this.onPlantGrow(p_150532_1_, p_150532_3_, p_150532_4_ - 1, p_150532_5_, p_150532_3_, p_150532_4_, p_150532_5_);
         this.onPlantGrow(p_150532_1_, p_150532_3_ + 1, p_150532_4_ - 1, p_150532_5_, p_150532_3_, p_150532_4_, p_150532_5_);
         this.onPlantGrow(p_150532_1_, p_150532_3_, p_150532_4_ - 1, p_150532_5_ + 1, p_150532_3_, p_150532_4_, p_150532_5_);
         this.onPlantGrow(p_150532_1_, p_150532_3_ + 1, p_150532_4_ - 1, p_150532_5_ + 1, p_150532_3_, p_150532_4_, p_150532_5_);
         return true;
      } else {
         return false;
      }
   }

   private void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ) {
      CandyGenUtil.setBlock(world, x, y, z, CandyCraft.FlourBlock, 0, 3);
   }

   protected boolean func_150537_a(World p_150537_1_, Random p_150537_2_, int p_150537_3_, int p_150537_4_, int p_150537_5_, int p_150537_6_) {
      return this.func_150536_b(p_150537_1_, p_150537_2_, p_150537_3_, p_150537_4_, p_150537_5_, p_150537_6_) && this.func_150532_c(p_150537_1_, p_150537_2_, p_150537_3_, p_150537_4_, p_150537_5_);
   }

   private boolean func_150536_b(World p_150536_1_, Random p_150536_2_, int p_150536_3_, int p_150536_4_, int p_150536_5_, int p_150536_6_) {
      boolean flag = true;
      if (p_150536_4_ >= 1 && p_150536_4_ + p_150536_6_ + 1 <= 256) {
         for(int i1 = p_150536_4_; i1 <= p_150536_4_ + 1 + p_150536_6_; ++i1) {
            byte b0 = 2;
            if (i1 == p_150536_4_) {
               b0 = 1;
            }

            if (i1 >= p_150536_4_ + 1 + p_150536_6_ - 2) {
               b0 = 2;
            }

            for(int j1 = p_150536_3_ - b0; j1 <= p_150536_3_ + b0 && flag; ++j1) {
               for(int k1 = p_150536_5_ - b0; k1 <= p_150536_5_ + b0 && flag; ++k1) {
                  if (i1 >= 0 && i1 < 256) {
                     CandyGenUtil.getBlock(p_150536_1_, j1, i1, k1);
                     if (!this.isReplaceable(p_150536_1_, j1, i1, k1)) {
                        flag = false;
                     }
                  } else {
                     flag = false;
                  }
               }
            }
         }

         return flag;
      } else {
         return false;
      }
   }

   protected void func_150535_a(World p_150535_1_, int p_150535_2_, int p_150535_3_, int p_150535_4_, int p_150535_5_, Random p_150535_6_) {
      int i1 = p_150535_5_ * p_150535_5_;

      for(int j1 = p_150535_2_ - p_150535_5_; j1 <= p_150535_2_ + p_150535_5_ + 1; ++j1) {
         int k1 = j1 - p_150535_2_;

         for(int l1 = p_150535_4_ - p_150535_5_; l1 <= p_150535_4_ + p_150535_5_ + 1; ++l1) {
            int i2 = l1 - p_150535_4_;
            int j2 = k1 - 1;
            int k2 = i2 - 1;
            if (k1 * k1 + i2 * i2 <= i1 || j2 * j2 + k2 * k2 <= i1 || k1 * k1 + k2 * k2 <= i1 || j2 * j2 + i2 * i2 <= i1) {
               Block block = CandyGenUtil.getBlock(p_150535_1_, j1, p_150535_3_, l1);
               if (block.isAir(block.getDefaultState(), p_150535_1_, new BlockPos(j1, p_150535_3_, l1)) || block.isLeaves(block.getDefaultState(), p_150535_1_, new BlockPos(j1, p_150535_3_, l1))) {
                  CandyGenUtil.setBlock(p_150535_1_, j1, p_150535_3_, l1, CandyCraft.CandyLeave2, 0, 3);
               }
            }
         }
      }

   }

   protected void func_150534_b(World p_150534_1_, int p_150534_2_, int p_150534_3_, int p_150534_4_, int p_150534_5_, Random p_150534_6_) {
      int i1 = p_150534_5_ * p_150534_5_;

      for(int j1 = p_150534_2_ - p_150534_5_; j1 <= p_150534_2_ + p_150534_5_; ++j1) {
         int k1 = j1 - p_150534_2_;

         for(int l1 = p_150534_4_ - p_150534_5_; l1 <= p_150534_4_ + p_150534_5_; ++l1) {
            int i2 = l1 - p_150534_4_;
            if (k1 * k1 + i2 * i2 <= i1) {
               Block block = CandyGenUtil.getBlock(p_150534_1_, j1, p_150534_3_, l1);
               if (block.isAir(block.getDefaultState(), p_150534_1_, new BlockPos(j1, p_150534_3_, l1)) || block.isLeaves(block.getDefaultState(), p_150534_1_, new BlockPos(j1, p_150534_3_, l1))) {
                  CandyGenUtil.setBlock(p_150534_1_, j1, p_150534_3_, l1, CandyCraft.CandyLeave2, 0, 3);
               }
            }
         }
      }

   }

   @Override
   public boolean generate(World p_76484_1_, Random p_76484_2_, BlockPos pos) {
      int p_76484_3_ = pos.getX();
      int p_76484_4_ = pos.getY();
      int p_76484_5_ = pos.getZ();
      int l = this.getTreeHeight(p_76484_2_);
      if (!this.func_150537_a(p_76484_1_, p_76484_2_, p_76484_3_, p_76484_4_, p_76484_5_, l)) {
         return false;
      } else {
         this.func_150543_c(p_76484_1_, p_76484_3_, p_76484_5_, p_76484_4_ + l, 2, p_76484_2_);

         for(int i1 = p_76484_4_ + l - 2 - p_76484_2_.nextInt(4); i1 > p_76484_4_ + l / 2; i1 -= 2 + p_76484_2_.nextInt(4)) {
            float f = p_76484_2_.nextFloat() * (float)Math.PI * 2.0F;
            int j1 = p_76484_3_ + (int)(0.5F + MathHelper.cos(f) * 4.0F);
            int k1 = p_76484_5_ + (int)(0.5F + MathHelper.sin(f) * 4.0F);

            for(int l1 = 0; l1 < 5; ++l1) {
               j1 = p_76484_3_ + (int)(1.5F + MathHelper.cos(f) * (float)l1);
               k1 = p_76484_5_ + (int)(1.5F + MathHelper.sin(f) * (float)l1);
               CandyGenUtil.setBlock(p_76484_1_, j1, i1 - 3 + l1 / 2, k1, CandyCraft.CandyLog, 2, 3);
            }

            int var20 = 1 + p_76484_2_.nextInt(2);
            int i2 = i1;

            for(int j2 = i1 - var20; j2 <= i2; ++j2) {
               int k2 = j2 - i2;
               this.func_150534_b(p_76484_1_, j1, j2, k1, 1 - k2, p_76484_2_);
            }
         }

         for(int l2 = 0; l2 < l; ++l2) {
            Block block = CandyGenUtil.getBlock(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_);
            if (block.isAir(block.getDefaultState(), p_76484_1_, new BlockPos(p_76484_3_, p_76484_4_ + l2, p_76484_5_)) || block.isLeaves(block.getDefaultState(), p_76484_1_, new BlockPos(p_76484_3_, p_76484_4_ + l2, p_76484_5_))) {
               CandyGenUtil.setBlock(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_, CandyCraft.CandyLog, 2, 3);
            }

            if (l2 < l - 1) {
               block = CandyGenUtil.getBlock(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_);
               if (block.isAir(block.getDefaultState(), p_76484_1_, new BlockPos(p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_)) || block.isLeaves(block.getDefaultState(), p_76484_1_, new BlockPos(p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_))) {
                  CandyGenUtil.setBlock(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_, CandyCraft.CandyLog, 2, 3);
               }

               block = CandyGenUtil.getBlock(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ + 1);
               if (block.isAir(block.getDefaultState(), p_76484_1_, new BlockPos(p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ + 1)) || block.isLeaves(block.getDefaultState(), p_76484_1_, new BlockPos(p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ + 1))) {
                  CandyGenUtil.setBlock(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ + 1, CandyCraft.CandyLog, 2, 3);
               }

               block = CandyGenUtil.getBlock(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_ + 1);
               if (block.isAir(block.getDefaultState(), p_76484_1_, new BlockPos(p_76484_3_, p_76484_4_ + l2, p_76484_5_ + 1)) || block.isLeaves(block.getDefaultState(), p_76484_1_, new BlockPos(p_76484_3_, p_76484_4_ + l2, p_76484_5_ + 1))) {
                  CandyGenUtil.setBlock(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_ + 1, CandyCraft.CandyLog, 2, 3);
               }
            }
         }

         return true;
      }
   }

   private void func_150543_c(World p_150543_1_, int p_150543_2_, int p_150543_3_, int p_150543_4_, int p_150543_5_, Random p_150543_6_) {
      byte b0 = 2;

      for(int i1 = p_150543_4_ - b0; i1 <= p_150543_4_; ++i1) {
         int j1 = i1 - p_150543_4_;
         this.func_150535_a(p_150543_1_, p_150543_2_, i1, p_150543_3_, p_150543_5_ + 1 - j1, p_150543_6_);
      }

   }
}
