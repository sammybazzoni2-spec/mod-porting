package com.thecramp.tab;

import com.thecramp.Main.MainRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabsLumpy extends CreativeTabs {
   public CreativeTabsLumpy(String tabLabel) {
      super(tabLabel);
   }

   @Override
   public ItemStack getTabIconItem() {
      Item icon = MainRegistry.bananaSerum;
      return new ItemStack(icon != null ? icon : Items.STICK);
   }
}
