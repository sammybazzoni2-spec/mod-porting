package com.thecramp.item;

import com.thecramp.Main.MainRegistry;
import net.minecraft.item.Item;

public class DemonBlood {
   public static Item dBlood;

   public static void mainRegistry() {
      initializeItem();
   }

   public static void initializeItem() {
      dBlood = (new Item()).setUnlocalizedName("dBlood").setCreativeTab(MainRegistry.tabCrafting);
   }
}
