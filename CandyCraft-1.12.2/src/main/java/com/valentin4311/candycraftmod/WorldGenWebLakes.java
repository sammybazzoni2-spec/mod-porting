package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWebLakes extends WorldGenerator {
   private Block field_150556_a;

   public WorldGenWebLakes(Block p_i45455_1_) {
      this.field_150556_a = p_i45455_1_;
   }

   @Override
   public boolean generate(World p_76484_1_, Random p_76484_2_, BlockPos pos) {
      int p_76484_3_ = pos.getX();
      int p_76484_4_ = pos.getY();
      int p_76484_5_ = pos.getZ();
      p_76484_3_ -= 8;

      for(p_76484_5_ -= 8; p_76484_4_ > 5 && CandyGenUtil.isAir(p_76484_1_, p_76484_3_, p_76484_4_, p_76484_5_); --p_76484_4_) {
      }

      if (p_76484_4_ <= 4) {
         return false;
      } else {
         p_76484_4_ -= 4;
         boolean[] aboolean = new boolean[2048];
         int l = p_76484_2_.nextInt(4) + 4;

         for(int i1 = 0; i1 < l; ++i1) {
            double d0 = p_76484_2_.nextDouble() * (double)6.0F + (double)3.0F;
            double d1 = p_76484_2_.nextDouble() * (double)4.0F + (double)2.0F;
            double d2 = p_76484_2_.nextDouble() * (double)6.0F + (double)3.0F;
            double d3 = p_76484_2_.nextDouble() * ((double)16.0F - d0 - (double)2.0F) + (double)1.0F + d0 / (double)2.0F;
            double d4 = p_76484_2_.nextDouble() * ((double)8.0F - d1 - (double)4.0F) + (double)2.0F + d1 / (double)2.0F;
            double d5 = p_76484_2_.nextDouble() * ((double)16.0F - d2 - (double)2.0F) + (double)1.0F + d2 / (double)2.0F;

            for(int k1 = 1; k1 < 15; ++k1) {
               for(int l1 = 1; l1 < 15; ++l1) {
                  for(int i2 = 1; i2 < 7; ++i2) {
                     double d6 = ((double)k1 - d3) / (d0 / (double)2.0F);
                     double d7 = ((double)i2 - d4) / (d1 / (double)2.0F);
                     double d8 = ((double)l1 - d5) / (d2 / (double)2.0F);
                     double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                     if (d9 < 1.2) {
                        aboolean[(k1 * 16 + l1) * 8 + i2] = true;
                     }
                  }
               }
            }
         }

         for(int var35 = 0; var35 < 16; ++var35) {
            for(int j2 = 0; j2 < 16; ++j2) {
               for(int j1 = 0; j1 < 8; ++j1) {
                  boolean flag = !aboolean[(var35 * 16 + j2) * 8 + j1] && (var35 < 15 && aboolean[((var35 + 1) * 16 + j2) * 8 + j1] || var35 > 0 && aboolean[((var35 - 1) * 16 + j2) * 8 + j1] || j2 < 15 && aboolean[(var35 * 16 + j2 + 1) * 8 + j1] || j2 > 0 && aboolean[(var35 * 16 + (j2 - 1)) * 8 + j1] || j1 < 7 && aboolean[(var35 * 16 + j2) * 8 + j1 + 1] || j1 > 0 && aboolean[(var35 * 16 + j2) * 8 + (j1 - 1)]);
                  if (flag) {
                     Material material = CandyGenUtil.mat(p_76484_1_, p_76484_3_ + var35, p_76484_4_ + j1, p_76484_5_ + j2);
                     if (j1 >= 4 && material.isLiquid()) {
                        return false;
                     }

                     if (j1 < 4 && !material.isSolid() && CandyGenUtil.getBlock(p_76484_1_, p_76484_3_ + var35, p_76484_4_ + j1, p_76484_5_ + j2) != this.field_150556_a) {
                        return false;
                     }
                  }
               }
            }
         }

         for(int var36 = 0; var36 < 16; ++var36) {
            for(int j2 = 0; j2 < 16; ++j2) {
               for(int j1 = 0; j1 < 8; ++j1) {
                  if (aboolean[(var36 * 16 + j2) * 8 + j1]) {
                     CandyGenUtil.setBlock(p_76484_1_, p_76484_3_ + var36, p_76484_4_ + j1, p_76484_5_ + j2, j1 >= 4 && p_76484_2_.nextBoolean() ? Blocks.AIR : this.field_150556_a, 0, 2);
                  }
               }
            }
         }

         if (this.field_150556_a == CandyCraft.CottonCandyWeb) {
            CandyGenUtil.setBlock(p_76484_1_, p_76484_3_ + 8, p_76484_4_ + 2, p_76484_5_ + 8, Blocks.MOB_SPAWNER, 0, 2);
            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)CandyGenUtil.getTile(p_76484_1_, p_76484_3_ + 8, p_76484_4_ + 2, p_76484_5_ + 8);
            CandyGenUtil.setSpawner(tileentitymobspawner, "CottonCandySpider");
         }

         return true;
      }
   }
}
