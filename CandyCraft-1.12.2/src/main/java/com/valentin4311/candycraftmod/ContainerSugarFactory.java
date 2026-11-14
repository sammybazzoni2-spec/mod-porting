package com.valentin4311.candycraftmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerSugarFactory extends Container {
   private TileEntitySugarFactory furnace;
   private InventoryPlayer playerInventory;
   private int lastCookTime = 0;

   public ContainerSugarFactory(InventoryPlayer playerInv, TileEntitySugarFactory tile) {
      this.playerInventory = playerInv;
      this.furnace = tile;
      this.addSlotToContainer(new Slot(tile, 0, 8, 33));
      this.addSlotToContainer(new SlotSugarFactory(playerInv.player, tile, 1, 152, 33));

      for (int i = 0; i < 3; ++i) {
         for (int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 59 + i * 18));
         }
      }

      for (int k = 0; k < 9; ++k) {
         this.addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 117));
      }
   }

   @Override
   public void addListener(IContainerListener listener) {
      super.addListener(listener);
      listener.sendWindowProperty(this, 0, this.furnace.currentTime);
   }

   @Override
   public void detectAndSendChanges() {
      super.detectAndSendChanges();
      for (int i = 0; i < this.listeners.size(); ++i) {
         IContainerListener listener = this.listeners.get(i);
         if (this.lastCookTime != this.furnace.currentTime) {
            listener.sendWindowProperty(this, 0, this.furnace.currentTime);
         }
      }
      this.lastCookTime = this.furnace.currentTime;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int id, int data) {
      if (id == 0) {
         this.furnace.currentTime = data;
      }
   }

   @Override
   public boolean canInteractWith(EntityPlayer player) {
      return this.furnace.isUsableByPlayer(player);
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int index) {
      ItemStack itemstack = ItemStack.EMPTY;
      Slot slot = this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index == 0 || index == 1) {
            if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
               return ItemStack.EMPTY;
            }
         } else if (TileEntitySugarFactory.isItemValid(itemstack1)) {
            if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
               return ItemStack.EMPTY;
            }
         } else if (index >= 2 && index < 29) {
            if (!this.mergeItemStack(itemstack1, 29, 38, false)) {
               return ItemStack.EMPTY;
            }
         } else if (index >= 29 && index < 38 && !this.mergeItemStack(itemstack1, 2, 29, false)) {
            return ItemStack.EMPTY;
         }

         if (itemstack1.isEmpty()) {
            slot.putStack(ItemStack.EMPTY);
         } else {
            slot.onSlotChanged();
         }

         if (itemstack1.getCount() == itemstack.getCount()) {
            return ItemStack.EMPTY;
         }

         slot.onTake(player, itemstack1);
      }
      return itemstack;
   }
}
