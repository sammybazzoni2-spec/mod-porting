package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTallCandyGrass extends WorldGenerator {
   public WorldGenTallCandyGrass(Block par1) {
      
   }

   @Override
   public boolean generate(World par1World, Random par2Random, BlockPos pos) {
      int par3 = pos.getX();
      int par4 = pos.getY();
      int par5 = pos.getZ();
      Block block = null;

      do {
         block = CandyGenUtil.getBlock(par1World, par3, par4, par5);
         if (block != null && !block.isLeaves(block.getDefaultState(), par1World, new BlockPos(par3, par4, par5))) {
            break;
         }

         --par4;
      } while(par4 > 0);

      for(int var7 = 0; var7 < 128; ++var7) {
         int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
         int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
         int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
         if (CandyGenUtil.isAir(par1World, var8, var9, var10) && CandyGenUtil.canStay(CandyCraft.TallCandyGrass, par1World, var8, var9, var10)) {
            int r = par2Random.nextInt(5);
            if (r < 4) {
               CandyGenUtil.setBlock(par1World, var8, var9, var10, CandyCraft.TallCandyGrass, (new Random()).nextInt(4), 2);
            } else if (par2Random.nextBoolean()) {
               CandyGenUtil.setBlock(par1World, var8, var9, var10, CandyGenUtil.biome(par1World, par3, par5) == CandyCraft.CandyHellForest ? CandyCraft.PoisonousFlower : CandyCraft.CandyFlower, 0, 2);
            }

            if (CandyGenUtil.biome(par1World, par3, par5) == CandyCraft.CandyEnchantedForest && par2Random.nextInt(200) == 4) {
               CandyGenUtil.setBlock(par1World, var8, var9, var10, CandyCraft.GoldenFlower, 0, 2);
            }

            if (CandyGenUtil.biome(par1World, par3, par5) == CandyCraft.CandyFrostPlains && par2Random.nextInt(100) == 4) {
               CandyGenUtil.setBlock(par1World, var8, var9, var10, CandyCraft.GoldenFlower, 0, 2);
            }
         }
      }

      return true;
   }

}
