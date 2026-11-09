package com.thecramp.tab;

import com.thecramp.Main.MainRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabsBoss extends CreativeTabs {
   public CreativeTabsBoss(String tabLabel) {
      super(tabLabel);
   }

   @Override
   public ItemStack createIcon() {
      Item icon = MainRegistry.enchiridion;
      return new ItemStack(icon != null ? icon : Items.STICK);
   }
}
