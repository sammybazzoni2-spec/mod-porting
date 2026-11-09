package com.thecramp.tab;

import com.thecramp.Main.MainRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabsGrass extends CreativeTabs {
   public CreativeTabsGrass(String tabLabel) {
      super(tabLabel);
   }

   @Override
   public ItemStack getTabIconItem() {
      Item icon = MainRegistry.grassSword;
      return new ItemStack(icon != null ? icon : Items.STICK);
   }
}
