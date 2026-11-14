package com.valentin4311.candycraftmod;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentEat extends Enchantment {
   protected EnchantmentEat(Enchantment.Rarity rarity, EnumEnchantmentType type) {
      super(rarity, type, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
   }

   @Override
   public int getMinEnchantability(int par1) {
      return 0;
   }

   @Override
   public boolean canApply(ItemStack par1ItemStack) {
      return par1ItemStack.getItem() == CandyCraft.Fork;
   }

   @Override
   public int getMaxEnchantability(int par1) {
      return 20;
   }

   @Override
   public int getMaxLevel() {
      return 1;
   }
}
