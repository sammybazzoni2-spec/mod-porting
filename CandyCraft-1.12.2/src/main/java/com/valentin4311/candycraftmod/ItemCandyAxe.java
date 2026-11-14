package com.valentin4311.candycraftmod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class ItemCandyAxe extends ItemAxe {
   protected ItemCandyAxe(Item.ToolMaterial material) {
      super(material, 6.0F + material.getAttackDamage(), -3.1F);
   }
}
