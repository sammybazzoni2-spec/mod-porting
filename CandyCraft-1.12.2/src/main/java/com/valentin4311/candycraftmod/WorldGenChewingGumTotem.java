package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenChewingGumTotem extends WorldGenerator {
   @Override
   public boolean generate(World var1, Random var2, BlockPos pos) {
      int var3 = pos.getX();
      int var4 = pos.getY();
      int var5 = pos.getZ();
      WorldProviderCandy.canGenChewingGum = 600;
      boolean exist = false;

      int i;
      for(i = 100; i > 58; --i) {
         if (CandyGenUtil.getBlock(var1, var3, i, var5) == CandyCraft.PuddingBlock) {
            exist = true;
            break;
         }
      }

      if (exist) {
         i += 4;
         CandyGenUtil.setBlock(var1, var3 + 3, i, var5, CandyCraft.ChewingGumBlock, 0, 3);
         CandyGenUtil.setBlock(var1, var3 - 3, i, var5, CandyCraft.ChewingGumBlock, 0, 3);
         CandyGenUtil.setBlock(var1, var3, i, var5 + 3, CandyCraft.ChewingGumBlock, 0, 3);
         CandyGenUtil.setBlock(var1, var3, i, var5 - 3, CandyCraft.ChewingGumBlock, 0, 3);
         CandyGenUtil.setBlock(var1, var3 + 2, i, var5 + 2, CandyCraft.ChewingGumBlock, 0, 3);
         CandyGenUtil.setBlock(var1, var3 - 2, i, var5 - 2, CandyCraft.ChewingGumBlock, 0, 3);
         CandyGenUtil.setBlock(var1, var3 + 2, i, var5 - 2, CandyCraft.ChewingGumBlock, 0, 3);
         CandyGenUtil.setBlock(var1, var3 - 2, i, var5 + 2, CandyCraft.ChewingGumBlock, 0, 3);
         this.generatePillar(var1, var2, var3 + 4, i - 1, var5);
         this.generatePillar(var1, var2, var3 - 4, i - 1, var5);
         this.generatePillar(var1, var2, var3, i - 1, var5 + 4);
         this.generatePillar(var1, var2, var3, i - 1, var5 - 4);
         this.generatePillar(var1, var2, var3 + 3, i - 1, var5 + 3);
         this.generatePillar(var1, var2, var3 - 3, i - 1, var5 - 3);
         this.generatePillar(var1, var2, var3 + 3, i - 1, var5 - 3);
         this.generatePillar(var1, var2, var3 - 3, i - 1, var5 + 3);
         CandyGenUtil.setBlock(var1, var3, i - 3, var5, Blocks.MOB_SPAWNER, 0, 2);
         TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)CandyGenUtil.getTile(var1, var3, i - 3, var5);
         if (tileentitymobspawner != null) {
            CandyGenUtil.setSpawner(tileentitymobspawner, "EntityBeetle");
         }
      }

      return true;
   }

   public void generatePillar(World var1, Random var2, int var3, int var4, int var5) {
      for(int j = var4; j > var4 - 20; --j) {
         Block block = CandyGenUtil.getBlock(var1, var3, j, var5);
         if (block != Blocks.AIR && block.getDefaultState().getCollisionBoundingBox(var1, new BlockPos(var3, j, var5)) != null) {
            break;
         }

         CandyGenUtil.setBlock(var1, var3, j, var5, CandyCraft.ChewingGumBlock, 0, 3);
      }

   }
}
