package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemCandySlab extends ItemSlab {
   String genericName;
   static BlockSlab[] slab;
   static BlockSlab[] doubleSlab;

   private static void init() {
      if (slab == null) {
         slab = new BlockSlab[]{CandyCraft.CandyHalfStep, CandyCraft.LicoriceHalfStep, CandyCraft.BarleyHalfStep, CandyCraft.CottonCandyHalfStep, CandyCraft.CandyHalfStep2, CandyCraft.CandyHalfStep3, CandyCraft.IceCreamHalfStep0, CandyCraft.IceCreamHalfStep1, CandyCraft.IceCreamHalfStep2, CandyCraft.IceCreamHalfStep3};
         doubleSlab = new BlockSlab[]{CandyCraft.CandyStep, CandyCraft.LicoriceStep, CandyCraft.BarleyStep, CandyCraft.CottonCandyStep, CandyCraft.CandyStep2, CandyCraft.CandyStep3, CandyCraft.IceCreamStep0, CandyCraft.IceCreamStep1, CandyCraft.IceCreamStep2, CandyCraft.IceCreamStep3};
      }
   }

   private static BlockSlab single(Block b) {
      init();
      return slab[getBlockId(b)];
   }

   private static BlockSlab dbl(Block b) {
      init();
      return doubleSlab[getBlockId(b)];
   }

   public ItemCandySlab(Block block) {
      super(block, single(block), dbl(block));
   }

   public static int getBlockId(Block b) {
      init();
      for (int i = 0; i < slab.length; ++i) {
         if (b == slab[i] || b == doubleSlab[i]) {
            return i;
         }
      }
      return -1;
   }

   public static boolean getComplete(Block b) {
      init();
      for (int i = 0; i < doubleSlab.length; ++i) {
         if (b == doubleSlab[i]) {
            return true;
         }
      }
      return false;
   }

   @Override
   public String getTranslationKey(ItemStack stack) {
      Block b = Block.getBlockFromItem(stack.getItem());
      return "block." + getComplete(b) + "," + getBlockId(b);
   }
}
