package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGeyser extends WorldGenerator {
   @Override
   public boolean generate(World var1, Random var2, BlockPos pos) {
      int var3 = pos.getX();
      int var4 = pos.getY();
      int var5 = pos.getZ();
      WorldProviderCandy.canGenGeyser = 300;
      if (CandyGenUtil.mat(var1, var3, 59, var5) == Material.WATER) {
         if (var2.nextBoolean()) {
            CandyGenUtil.setBlock(var1, var3, 64 + var2.nextInt(8), var5, Blocks.FLOWING_WATER, 0, 3);
         } else {
            int i = var2.nextInt(8);
            CandyGenUtil.setBlock(var1, var3, 66 + i, var5, Blocks.FLOWING_WATER, 0, 3);
            CandyGenUtil.setBlock(var1, var3, 64 + i, var5, Blocks.FLOWING_WATER, 0, 3);
            CandyGenUtil.setBlock(var1, var3 + 1, 64 + i, var5, Blocks.FLOWING_WATER, 0, 3);
            CandyGenUtil.setBlock(var1, var3 - 1, 64 + i, var5, Blocks.FLOWING_WATER, 0, 3);
            CandyGenUtil.setBlock(var1, var3, 64 + i, var5 + 1, Blocks.FLOWING_WATER, 0, 3);
            CandyGenUtil.setBlock(var1, var3, 64 + i, var5 - 1, Blocks.FLOWING_WATER, 0, 3);
         }

         return true;
      } else {
         return false;
      }
   }
}
