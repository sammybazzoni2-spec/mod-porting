package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSeaweeds extends WorldGenerator {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      for(int l = 0; l < 64; ++l) {
         int i1 = i + random.nextInt(8) - random.nextInt(8);
         int j1 = j + random.nextInt(4) - random.nextInt(4);
         int k1 = k + random.nextInt(8) - random.nextInt(8);
         if (random.nextBoolean()) {
            if (CandyGenUtil.getBlock(world, i1, j1, k1) == Blocks.WATER && CandyGenUtil.canStay(CandyCraft.GreenAlgae, world, i1, j1, k1)) {
               if (random.nextBoolean()) {
                  CandyGenUtil.setBlock(world, i1, j1, k1, CandyCraft.GreenAlgae, 0, 2);
               } else {
                  CandyGenUtil.setBlock(world, i1, j1, k1, CandyCraft.BananaSeaweeds, 0, 2);
               }
            }
         } else {
            int h = random.nextInt(4) + 1;

            for(int top = 0; top < h; ++top) {
               if (CandyGenUtil.getBlock(world, i1, j1 + top, k1) == Blocks.WATER && CandyGenUtil.canStay(CandyCraft.PinkAlgae, world, i1, j1 + top, k1)) {
                  CandyGenUtil.setBlock(world, i1, j1 + top, k1, CandyCraft.PinkAlgae, 0, 2);
               }
            }
         }
      }

      return true;
   }
}
