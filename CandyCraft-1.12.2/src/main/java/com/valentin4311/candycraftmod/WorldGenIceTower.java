package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenIceTower extends WorldGenerator {
   @Override
   public boolean generate(World par1, Random par2, BlockPos pos) {
      int par3 = pos.getX();
      int par4 = pos.getY();
      int par5 = pos.getZ();
      for(par4 = 100; par4 > 50 && CandyGenUtil.getBlock(par1, par3, par4, par5) != CandyCraft.PuddingBlock; --par4) {
      }

      for(int x = 0; x <= 6; ++x) {
         for(int z = 0; z <= 6; ++z) {
            if (CandyGenUtil.getBlock(par1, par3 + x, par4, par5 + z) != CandyCraft.PuddingBlock) {
               return false;
            }
         }
      }

      ++par4;

      for(int y = 0; y < 2; ++y) {
         CandyGenUtil.setBlock(par1, par3 + 2, par4 + y, par5, CandyCraft.IceCream, 3, 3);
         CandyGenUtil.setBlock(par1, par3 + 4, par4 + y, par5, CandyCraft.IceCream, 3, 3);
         CandyGenUtil.setBlock(par1, par3 + 1, par4 + y, par5 + 1, CandyCraft.IceCream, 3, 3);
         CandyGenUtil.setBlock(par1, par3 + 5, par4 + y, par5 + 1, CandyCraft.IceCream, 3, 3);
         CandyGenUtil.setBlock(par1, par3, par4 + y, par5 + 2, CandyCraft.IceCream, 3, 3);
         CandyGenUtil.setBlock(par1, par3 + 6, par4 + y, par5 + 2, CandyCraft.IceCream, 3, 3);
         CandyGenUtil.setBlock(par1, par3, par4 + y, par5 + 4, CandyCraft.IceCream, 3, 3);
         CandyGenUtil.setBlock(par1, par3 + 6, par4 + y, par5 + 4, CandyCraft.IceCream, 3, 3);
         CandyGenUtil.setBlock(par1, par3 + 1, par4 + y, par5 + 5, CandyCraft.IceCream, 3, 3);
         CandyGenUtil.setBlock(par1, par3 + 5, par4 + y, par5 + 5, CandyCraft.IceCream, 3, 3);
         CandyGenUtil.setBlock(par1, par3 + 2, par4 + y, par5 + 6, CandyCraft.IceCream, 3, 3);
         CandyGenUtil.setBlock(par1, par3 + 4, par4 + y, par5 + 6, CandyCraft.IceCream, 3, 3);
      }

      CandyGenUtil.setBlock(par1, par3 + 1, par4, par5, CandyCraft.IceCream, 3, 3);
      CandyGenUtil.setBlock(par1, par3, par4, par5 + 1, CandyCraft.IceCream, 3, 3);
      CandyGenUtil.setBlock(par1, par3 + 5, par4, par5, CandyCraft.IceCream, 3, 3);
      CandyGenUtil.setBlock(par1, par3 + 6, par4, par5 + 1, CandyCraft.IceCream, 3, 3);
      CandyGenUtil.setBlock(par1, par3, par4, par5 + 5, CandyCraft.IceCream, 3, 3);
      CandyGenUtil.setBlock(par1, par3 + 1, par4, par5 + 6, CandyCraft.IceCream, 3, 3);
      CandyGenUtil.setBlock(par1, par3 + 5, par4, par5 + 6, CandyCraft.IceCream, 3, 3);
      CandyGenUtil.setBlock(par1, par3 + 6, par4, par5 + 5, CandyCraft.IceCream, 3, 3);
      CandyGenUtil.setBlock(par1, par3 + 3, par4 + 2, par5, CandyCraft.IceCream, 3, 3);
      CandyGenUtil.setBlock(par1, par3 + 3, par4 + 2, par5 + 6, CandyCraft.IceCream, 3, 3);
      CandyGenUtil.setBlock(par1, par3, par4 + 2, par5 + 3, CandyCraft.IceCream, 3, 3);
      CandyGenUtil.setBlock(par1, par3 + 6, par4 + 2, par5 + 3, CandyCraft.IceCream, 3, 3);

      for(int y = 0; y < 4; ++y) {
         int meta = y != 0 && y != 2 ? (y == 3 ? 0 : 2) : 1;
         CandyGenUtil.setBlock(par1, par3 + 2, par4 + 2 + y, par5 + 1, CandyCraft.IceCream, meta, 3);
         CandyGenUtil.setBlock(par1, par3 + 1, par4 + 2 + y, par5 + 2, CandyCraft.IceCream, meta, 3);
         CandyGenUtil.setBlock(par1, par3 + 4, par4 + 2 + y, par5 + 1, CandyCraft.IceCream, meta, 3);
         CandyGenUtil.setBlock(par1, par3 + 5, par4 + 2 + y, par5 + 2, CandyCraft.IceCream, meta, 3);
         CandyGenUtil.setBlock(par1, par3 + 2, par4 + 2 + y, par5 + 5, CandyCraft.IceCream, meta, 3);
         CandyGenUtil.setBlock(par1, par3 + 1, par4 + 2 + y, par5 + 4, CandyCraft.IceCream, meta, 3);
         CandyGenUtil.setBlock(par1, par3 + 5, par4 + 2 + y, par5 + 4, CandyCraft.IceCream, meta, 3);
         CandyGenUtil.setBlock(par1, par3 + 4, par4 + 2 + y, par5 + 5, CandyCraft.IceCream, meta, 3);
         if (y != 0 && y != 3) {
            CandyGenUtil.setBlock(par1, par3 + 3, par4 + 2 + y, par5 + 1, CandyCraft.IceCream, y - 1, 3);
            CandyGenUtil.setBlock(par1, par3 + 3, par4 + 2 + y, par5 + 5, CandyCraft.IceCream, y - 1, 3);
            CandyGenUtil.setBlock(par1, par3 + 1, par4 + 2 + y, par5 + 3, CandyCraft.IceCream, y - 1, 3);
            CandyGenUtil.setBlock(par1, par3 + 5, par4 + 2 + y, par5 + 3, CandyCraft.IceCream, y - 1, 3);
         }
      }

      for(int x = 0; x < 3; ++x) {
         for(int z = 0; z < 3; ++z) {
            CandyGenUtil.setBlock(par1, par3 + 2 + x, par4 + 3, par5 + 2 + z, CandyCraft.IceCream, 3, 3);
         }
      }

      CandyGenUtil.setBlock(par1, par3 + 3, par4 + 3, par5 + 3, CandyCraft.IceCream, 0, 3);
      CandyGenUtil.setBlock(par1, par3 + 3, par4 + 4, par5 + 3, CandyCraft.CandyChest, par2.nextInt(4), 3);
      TileEntityCandyChest chest = (TileEntityCandyChest)CandyGenUtil.getTile(par1, par3 + 3, par4 + 4, par5 + 3);
      int time = par2.nextInt(7) + 5;

      for(int chestTime = 0; chestTime <= time; ++chestTime) {
         Item it = WorldGenCandyHouse.itemToStock[par2.nextInt(WorldGenCandyHouse.itemToStock.length)];
         int stack = par2.nextInt(7) + 4;
         ItemStack st = new ItemStack(it);
         st.setCount(stack > st.getMaxStackSize() ? st.getMaxStackSize() : stack);
         chest.setInventorySlotContents(par2.nextInt(27), new ItemStack(st.getItem(), st.getCount()));
      }

      chest.setInventorySlotContents(0, new ItemStack(Items.LAVA_BUCKET));
      chest.setInventorySlotContents(1, new ItemStack(CandyCraft.SugarBlock, 12, 0));
      WorldProviderCandy.canGenIceTower = 600;
      return true;
   }
}
