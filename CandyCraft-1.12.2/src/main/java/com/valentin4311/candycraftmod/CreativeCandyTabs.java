package com.valentin4311.candycraftmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeCandyTabs extends CreativeTabs {
   public CreativeCandyTabs(String label) {
      super(label);
   }

   @Override
   public ItemStack createIcon() {
      return new ItemStack(Item.getItemFromBlock(CandyCraft.CandyWorkbench));
   }
}
