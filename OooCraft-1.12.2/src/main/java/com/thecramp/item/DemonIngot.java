package com.thecramp.item;

import com.thecramp.Main.MainRegistry;
import net.minecraft.item.Item;

public class DemonIngot {
   public static Item dIngot;

   public static void mainRegistry() {
      initializeItem();
   }

   public static void initializeItem() {
      dIngot = (new Item()).setUnlocalizedName("dIngot").setCreativeTab(MainRegistry.tabCrafting);
   }
}
