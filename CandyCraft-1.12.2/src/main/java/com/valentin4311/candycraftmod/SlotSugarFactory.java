package com.valentin4311.candycraftmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSugarFactory extends Slot {
   private EntityPlayer thePlayer;
   private int removeCount;

   public SlotSugarFactory(EntityPlayer player, IInventory inventory, int index, int x, int y) {
      super(inventory, index, x, y);
      this.thePlayer = player;
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return false;
   }

   @Override
   public ItemStack decrStackSize(int amount) {
      if (this.getHasStack()) {
         this.removeCount += Math.min(amount, this.getStack().getCount());
      }
      return super.decrStackSize(amount);
   }

   @Override
   public ItemStack onTake(EntityPlayer player, ItemStack stack) {
      this.onCrafting(stack);
      return super.onTake(player, stack);
   }

   @Override
   protected void onCrafting(ItemStack stack, int amount) {
      this.removeCount += amount;
      this.onCrafting(stack);
   }
}
