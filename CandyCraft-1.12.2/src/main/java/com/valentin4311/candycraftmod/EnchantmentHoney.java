package com.valentin4311.candycraftmod;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentHoney extends Enchantment {
   protected EnchantmentHoney(Enchantment.Rarity rarity, EnumEnchantmentType type) {
      super(rarity, type, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
   }

   @Override
   public int getMinEnchantability(int par1) {
      return 15;
   }

   @Override
   public boolean canApply(ItemStack par1ItemStack) {
      return par1ItemStack.getItem() == CandyCraft.CaramelBow;
   }

   @Override
   public int getMaxEnchantability(int par1) {
      return 25;
   }

   @Override
   public int getMaxLevel() {
      return 2;
   }
}
