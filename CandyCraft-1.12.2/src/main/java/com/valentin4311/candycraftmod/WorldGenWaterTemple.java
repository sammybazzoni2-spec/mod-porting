package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWaterTemple extends WorldGenerator {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      WorldProviderCandy.canGenTemple = 400;
      j += 10;

      for(int o = 0; CandyGenUtil.getBlock(world, i, j - 1, k) != CandyCraft.FlourBlock; --j) {
         if (j < 20) {
            return false;
         }
      }

      if (CandyGenUtil.getBlock(world, i, j + 12, k) != Blocks.WATER) {
         return false;
      } else {
         for(int v = 0; v <= 3; ++v) {
            CandyGenUtil.setAir(world, i, j + v, k);
            CandyGenUtil.setAir(world, i - 1, j + v, k);
            CandyGenUtil.setAir(world, i + 1, j + v, k);
            CandyGenUtil.setAir(world, i, j + v, k - 1);
            CandyGenUtil.setAir(world, i, j + v, k + 1);
            CandyGenUtil.setAir(world, i - 1, j + v, k - 1);
            CandyGenUtil.setAir(world, i - 1, j + v, k + 1);
            CandyGenUtil.setAir(world, i + 1, j + v, k - 1);
            CandyGenUtil.setAir(world, i + 1, j + v, k + 1);
            CandyGenUtil.setAir(world, i - 2, j + v, k);
            CandyGenUtil.setAir(world, i + 2, j + v, k);
            CandyGenUtil.setAir(world, i, j + v, k + 2);
            CandyGenUtil.setAir(world, i, j + v, k - 2);
            CandyGenUtil.setAir(world, i - 2, j + v, k + 1);
            CandyGenUtil.setAir(world, i - 2, j + v, k - 1);
            CandyGenUtil.setAir(world, i + 2, j + v, k - 1);
            CandyGenUtil.setAir(world, i + 2, j + v, k + 1);
            CandyGenUtil.setAir(world, i - 1, j + v, k + 2);
            CandyGenUtil.setAir(world, i - 1, j + v, k - 2);
            CandyGenUtil.setAir(world, i + 1, j + v, k + 2);
            CandyGenUtil.setAir(world, i + 1, j + v, k - 2);
            CandyGenUtil.setAir(world, i, j + v, k - 3);
            CandyGenUtil.setAir(world, i, j + v, k + 3);
            CandyGenUtil.setAir(world, i + 3, j + v, k);
            CandyGenUtil.setAir(world, i - 3, j + v, k);
            CandyGenUtil.setAir(world, i - 3, j + v, k + 1);
            CandyGenUtil.setAir(world, i - 3, j + v, k - 1);
            CandyGenUtil.setAir(world, i + 3, j + v, k + 1);
            CandyGenUtil.setAir(world, i + 3, j + v, k - 1);
            CandyGenUtil.setAir(world, i - 1, j + v, k - 3);
            CandyGenUtil.setAir(world, i + 1, j + v, k - 3);
            CandyGenUtil.setAir(world, i - 1, j + v, k + 3);
            CandyGenUtil.setAir(world, i + 1, j + v, k + 3);
            CandyGenUtil.setAir(world, i + 2, j + v, k - 2);
            CandyGenUtil.setAir(world, i + 2, j + v, k + 2);
            CandyGenUtil.setAir(world, i - 2, j + v, k - 2);
            CandyGenUtil.setAir(world, i - 2, j + v, k + 2);
         }

         CandyGenUtil.setBlock(world, i, j, k, CandyCraft.CandyChest, 0, 2);
         CandyGenUtil.setBlock(world, i, j - 1, k, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 1, j - 1, k, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 1, j - 1, k, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i, j - 1, k - 1, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i, j - 1, k + 1, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 1, j - 1, k - 1, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 1, j - 1, k + 1, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 1, j - 1, k - 1, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 1, j - 1, k + 1, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 2, j - 1, k, CandyCraft.HoneyLamp, 0, 3);
         CandyGenUtil.setBlock(world, i + 2, j - 1, k, CandyCraft.HoneyLamp, 0, 3);
         CandyGenUtil.setBlock(world, i, j - 1, k + 2, CandyCraft.HoneyLamp, 0, 3);
         CandyGenUtil.setBlock(world, i, j - 1, k - 2, CandyCraft.HoneyLamp, 0, 3);
         CandyGenUtil.setBlock(world, i - 2, j - 1, k + 1, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 2, j - 1, k - 1, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 2, j - 1, k - 1, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 2, j - 1, k + 1, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 1, j - 1, k + 2, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 1, j - 1, k - 2, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 1, j - 1, k + 2, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 1, j - 1, k - 2, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i, j - 1, k - 3, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i, j - 1, k + 3, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 3, j - 1, k, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 3, j - 1, k, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 3, j - 1, k + 1, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 3, j - 1, k - 1, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 3, j - 1, k + 1, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 3, j - 1, k - 1, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 1, j - 1, k - 3, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 1, j - 1, k - 3, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 1, j - 1, k + 3, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 1, j - 1, k + 3, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 2, j - 1, k - 2, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 2, j - 1, k + 2, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 2, j - 1, k - 2, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 2, j - 1, k + 2, CandyCraft.ChocolateStone, 0, 2);
         CandyGenUtil.setBlock(world, i, j - 1, k + 4, CandyCraft.FlourBlock, 0, 3);
         CandyGenUtil.setBlock(world, i + 1, j - 1, k + 4, CandyCraft.FlourBlock, 0, 3);
         CandyGenUtil.setBlock(world, i - 1, j - 1, k + 4, CandyCraft.FlourBlock, 0, 3);
         CandyGenUtil.setBlock(world, i, j - 1, k - 4, CandyCraft.FlourBlock, 0, 3);
         CandyGenUtil.setBlock(world, i + 1, j - 1, k - 4, CandyCraft.FlourBlock, 0, 3);
         CandyGenUtil.setBlock(world, i - 1, j - 1, k - 4, CandyCraft.FlourBlock, 0, 3);
         CandyGenUtil.setBlock(world, i + 4, j - 1, k, CandyCraft.FlourBlock, 0, 3);
         CandyGenUtil.setBlock(world, i + 4, j - 1, k + 1, CandyCraft.FlourBlock, 0, 3);
         CandyGenUtil.setBlock(world, i + 4, j - 1, k - 1, CandyCraft.FlourBlock, 0, 3);
         CandyGenUtil.setBlock(world, i - 4, j - 1, k, CandyCraft.FlourBlock, 0, 3);
         CandyGenUtil.setBlock(world, i - 4, j - 1, k + 1, CandyCraft.FlourBlock, 0, 3);
         CandyGenUtil.setBlock(world, i - 4, j - 1, k - 1, CandyCraft.FlourBlock, 0, 3);
         CandyGenUtil.setBlock(world, i + 2, j - 1, k + 3, CandyCraft.FlourBlock, 0, 2);
         CandyGenUtil.setBlock(world, i - 2, j - 1, k + 3, CandyCraft.FlourBlock, 0, 2);
         CandyGenUtil.setBlock(world, i + 2, j - 1, k - 3, CandyCraft.FlourBlock, 0, 2);
         CandyGenUtil.setBlock(world, i - 2, j - 1, k - 3, CandyCraft.FlourBlock, 0, 2);
         CandyGenUtil.setBlock(world, i + 3, j - 1, k - 2, CandyCraft.FlourBlock, 0, 2);
         CandyGenUtil.setBlock(world, i + 3, j - 1, k + 2, CandyCraft.FlourBlock, 0, 2);
         CandyGenUtil.setBlock(world, i - 3, j - 1, k - 2, CandyCraft.FlourBlock, 0, 2);
         CandyGenUtil.setBlock(world, i - 3, j - 1, k + 2, CandyCraft.FlourBlock, 0, 2);

         for(int y = 0; y <= 1; ++y) {
            CandyGenUtil.setBlock(world, i, j + y * 2, k + 4, CandyCraft.ChocolateCobbleStone, 0, 3);
            CandyGenUtil.setBlock(world, i + 1, j + y * 2, k + 4, CandyCraft.ChocolateCobbleStone, 0, 3);
            CandyGenUtil.setBlock(world, i - 1, j + y * 2, k + 4, CandyCraft.ChocolateCobbleStone, 0, 3);
            CandyGenUtil.setBlock(world, i, j + y * 2, k - 4, CandyCraft.ChocolateCobbleStone, 0, 3);
            CandyGenUtil.setBlock(world, i + 1, j + y * 2, k - 4, CandyCraft.ChocolateCobbleStone, 0, 3);
            CandyGenUtil.setBlock(world, i - 1, j + y * 2, k - 4, CandyCraft.ChocolateCobbleStone, 0, 3);
            CandyGenUtil.setBlock(world, i + 4, j + y * 2, k, CandyCraft.ChocolateCobbleStone, 0, 3);
            CandyGenUtil.setBlock(world, i + 4, j + y * 2, k + 1, CandyCraft.ChocolateCobbleStone, 0, 3);
            CandyGenUtil.setBlock(world, i + 4, j + y * 2, k - 1, CandyCraft.ChocolateCobbleStone, 0, 3);
            CandyGenUtil.setBlock(world, i - 4, j + y * 2, k, CandyCraft.ChocolateCobbleStone, 0, 3);
            CandyGenUtil.setBlock(world, i - 4, j + y * 2, k + 1, CandyCraft.ChocolateCobbleStone, 0, 3);
            CandyGenUtil.setBlock(world, i - 4, j + y * 2, k - 1, CandyCraft.ChocolateCobbleStone, 0, 3);
         }

         CandyGenUtil.setBlock(world, i, j + 1, k + 4, CandyCraft.HoneyLamp, 0, 3);
         CandyGenUtil.setBlock(world, i + 1, j + 1, k + 4, CandyCraft.CaramelGlass1, 0, 2);
         CandyGenUtil.setBlock(world, i - 1, j + 1, k + 4, CandyCraft.CaramelGlass1, 0, 2);
         CandyGenUtil.setBlock(world, i, j + 1, k - 4, CandyCraft.HoneyLamp, 0, 3);
         CandyGenUtil.setBlock(world, i + 1, j + 1, k - 4, CandyCraft.CaramelGlass1, 0, 2);
         CandyGenUtil.setBlock(world, i - 1, j + 1, k - 4, CandyCraft.CaramelGlass1, 0, 2);
         CandyGenUtil.setBlock(world, i + 4, j + 1, k, CandyCraft.HoneyLamp, 0, 3);
         CandyGenUtil.setBlock(world, i + 4, j + 1, k + 1, CandyCraft.CaramelGlass1, 0, 2);
         CandyGenUtil.setBlock(world, i + 4, j + 1, k - 1, CandyCraft.CaramelGlass1, 0, 2);
         CandyGenUtil.setBlock(world, i - 4, j + 1, k, CandyCraft.HoneyLamp, 0, 3);
         CandyGenUtil.setBlock(world, i - 4, j + 1, k + 1, CandyCraft.CaramelGlass1, 0, 2);
         CandyGenUtil.setBlock(world, i - 4, j + 1, k - 1, CandyCraft.CaramelGlass1, 0, 2);
         CandyGenUtil.setBlock(world, i + 2, j, k + 3, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 2, j, k + 3, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 2, j, k - 3, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 2, j, k - 3, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 3, j, k - 2, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 3, j, k + 2, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 3, j, k - 2, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 3, j, k + 2, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 2, j + 3, k + 3, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 2, j + 3, k + 3, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 2, j + 3, k - 3, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 2, j + 3, k - 3, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 3, j + 3, k - 2, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i + 3, j + 3, k + 2, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 3, j + 3, k - 2, CandyCraft.ChocolateCobbleStone, 0, 2);
         CandyGenUtil.setBlock(world, i - 3, j + 3, k + 2, CandyCraft.ChocolateCobbleStone, 0, 2);

         for(int z = 0; z <= 1; ++z) {
            CandyGenUtil.setBlock(world, i + 2, j + z + 1, k + 3, CandyCraft.CaramelGlass1, 0, 2);
            CandyGenUtil.setBlock(world, i - 2, j + z + 1, k + 3, CandyCraft.CaramelGlass1, 0, 2);
            CandyGenUtil.setBlock(world, i + 2, j + z + 1, k - 3, CandyCraft.CaramelGlass1, 0, 2);
            CandyGenUtil.setBlock(world, i - 2, j + z + 1, k - 3, CandyCraft.CaramelGlass1, 0, 2);
            CandyGenUtil.setBlock(world, i + 3, j + z + 1, k - 2, CandyCraft.CaramelGlass1, 0, 2);
            CandyGenUtil.setBlock(world, i + 3, j + z + 1, k + 2, CandyCraft.CaramelGlass1, 0, 2);
            CandyGenUtil.setBlock(world, i - 3, j + z + 1, k - 2, CandyCraft.CaramelGlass1, 0, 2);
            CandyGenUtil.setBlock(world, i - 3, j + z + 1, k + 2, CandyCraft.CaramelGlass1, 0, 2);
         }

         CandyGenUtil.setBlock(world, i + 3, j + 3, k, CandyCraft.HoneyLamp, 0, 3);
         CandyGenUtil.setBlock(world, i - 3, j + 3, k, CandyCraft.HoneyLamp, 0, 3);
         CandyGenUtil.setBlock(world, i, j + 3, k + 3, CandyCraft.HoneyLamp, 0, 3);
         CandyGenUtil.setBlock(world, i, j + 3, k - 3, CandyCraft.HoneyLamp, 0, 3);
         CandyGenUtil.setBlock(world, i + 3, j + 3, k + 1, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i + 3, j + 3, k - 1, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i - 3, j + 3, k + 1, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i - 3, j + 3, k - 1, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i - 1, j + 3, k + 3, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i + 1, j + 3, k + 3, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i - 1, j + 3, k - 3, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i + 1, j + 3, k - 3, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i + 2, j + 3, k - 2, CandyCraft.ChocolateCobbleStone, 0, 3);
         CandyGenUtil.setBlock(world, i + 2, j + 3, k + 2, CandyCraft.ChocolateCobbleStone, 0, 3);
         CandyGenUtil.setBlock(world, i - 2, j + 3, k - 2, CandyCraft.ChocolateCobbleStone, 0, 3);
         CandyGenUtil.setBlock(world, i - 2, j + 3, k + 2, CandyCraft.ChocolateCobbleStone, 0, 3);
         CandyGenUtil.setBlock(world, i, j + 4, k, CandyCraft.CaramelGlass2, 0, 3);
         CandyGenUtil.setBlock(world, i, j + 4, k + 1, CandyCraft.CaramelGlass2, 0, 3);
         CandyGenUtil.setBlock(world, i, j + 4, k - 1, CandyCraft.CaramelGlass2, 0, 3);
         CandyGenUtil.setBlock(world, i - 1, j + 4, k, CandyCraft.CaramelGlass2, 0, 3);
         CandyGenUtil.setBlock(world, i + 1, j + 4, k, CandyCraft.CaramelGlass2, 0, 3);
         CandyGenUtil.setBlock(world, i, j + 4, k + 2, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i, j + 4, k - 2, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i - 2, j + 4, k, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i + 2, j + 4, k, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i + 1, j + 4, k + 2, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i - 1, j + 4, k + 2, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i + 1, j + 4, k - 2, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i - 1, j + 4, k - 2, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i + 2, j + 4, k - 1, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i + 2, j + 4, k + 1, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i - 2, j + 4, k - 1, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i - 2, j + 4, k + 1, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i + 1, j + 4, k + 1, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i - 1, j + 4, k + 1, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i + 1, j + 4, k - 1, CandyCraft.ChocolateStone, 0, 3);
         CandyGenUtil.setBlock(world, i - 1, j + 4, k - 1, CandyCraft.ChocolateStone, 0, 3);
         TileEntityChest chest = (TileEntityChest)CandyGenUtil.getTile(world, i, j, k);
         int time = random.nextInt(7) + 5;

         for(int chestTime = 0; chestTime <= time; ++chestTime) {
            Item it = WorldGenCandyHouse.itemToStock[random.nextInt(WorldGenCandyHouse.itemToStock.length)];
            int stack = random.nextInt(7) + 4;
            ItemStack st = new ItemStack(it);
            st.setCount(stack > st.getMaxStackSize() ? st.getMaxStackSize() : stack);
            chest.setInventorySlotContents(random.nextInt(27), new ItemStack(st.getItem(), st.getCount()));
         }

         return true;
      }
   }
}
