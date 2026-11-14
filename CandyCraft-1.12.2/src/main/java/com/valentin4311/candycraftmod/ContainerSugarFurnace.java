package com.valentin4311.candycraftmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerSugarFurnace extends Container {
   private TileEntitySugarFurnace furnace;
   private int lastCookTime = 0;
   private int lastBurnTime = 0;
   private int lastItemBurnTime = 0;

   public ContainerSugarFurnace(InventoryPlayer playerInv, TileEntitySugarFurnace tile) {
      this.furnace = tile;
      this.addSlotToContainer(new Slot(tile, 0, 56, 17));
      this.addSlotToContainer(new Slot(tile, 1, 56, 53));
      this.addSlotToContainer(new SlotFurnaceOutput(playerInv.player, tile, 2, 116, 35));

      for (int i = 0; i < 3; ++i) {
         for (int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for (int k = 0; k < 9; ++k) {
         this.addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
      }
   }

   @Override
   public void addListener(IContainerListener listener) {
      super.addListener(listener);
      listener.sendWindowProperty(this, 0, this.furnace.furnaceCookTime);
      listener.sendWindowProperty(this, 1, this.furnace.furnaceBurnTime);
      listener.sendWindowProperty(this, 2, this.furnace.currentItemBurnTime);
   }

   @Override
   public void detectAndSendChanges() {
      super.detectAndSendChanges();
      for (int i = 0; i < this.listeners.size(); ++i) {
         IContainerListener listener = this.listeners.get(i);
         if (this.lastCookTime != this.furnace.furnaceCookTime) {
            listener.sendWindowProperty(this, 0, this.furnace.furnaceCookTime);
         }
         if (this.lastBurnTime != this.furnace.furnaceBurnTime) {
            listener.sendWindowProperty(this, 1, this.furnace.furnaceBurnTime);
         }
         if (this.lastItemBurnTime != this.furnace.currentItemBurnTime) {
            listener.sendWindowProperty(this, 2, this.furnace.currentItemBurnTime);
         }
      }
      this.lastCookTime = this.furnace.furnaceCookTime;
      this.lastBurnTime = this.furnace.furnaceBurnTime;
      this.lastItemBurnTime = this.furnace.currentItemBurnTime;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int id, int data) {
      if (id == 0) {
         this.furnace.furnaceCookTime = data;
      }
      if (id == 1) {
         this.furnace.furnaceBurnTime = data;
      }
      if (id == 2) {
         this.furnace.currentItemBurnTime = data;
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
         if (index == 2) {
            if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
               return ItemStack.EMPTY;
            }
            slot.onSlotChange(itemstack1, itemstack);
         } else if (index != 1 && index != 0) {
            if (!CandyFurnaceRecipe.smelting().getSmeltingResult(itemstack1).isEmpty()) {
               if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                  return ItemStack.EMPTY;
               }
            } else if (TileEntitySugarFurnace.isItemFuel(itemstack1)) {
               if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                  return ItemStack.EMPTY;
               }
            } else if (index >= 3 && index < 30) {
               if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                  return ItemStack.EMPTY;
               }
            } else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
               return ItemStack.EMPTY;
            }
         } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
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
