package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCandyWaterlily extends WorldGenerator {
   @Override
   public boolean generate(World par1World, Random par2Random, BlockPos pos) {
      int par3 = pos.getX();
      int par4 = pos.getY();
      int par5 = pos.getZ();
      for(int l = 0; l < 10; ++l) {
         int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
         int j1 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
         int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
         if (CandyGenUtil.isAir(par1World, i1, j1, k1) && CandyGenUtil.canPlace(CandyCraft.CandyWaterLily, par1World, i1, j1, k1)) {
            CandyGenUtil.setBlock(par1World, i1, j1, k1, par2Random.nextInt(15) == 0 ? CandyCraft.MarshmallowFlowerBlock : CandyCraft.CandyWaterLily, 0, 2);
         }
      }

      return true;
   }
}
