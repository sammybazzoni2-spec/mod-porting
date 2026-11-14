package com.valentin4311.candycraftmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerCandyWorkBench extends Container {
   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
   public IInventory craftResult = new InventoryCraftResult();
   private World worldObj;
   private BlockPos pos;

   public ContainerCandyWorkBench(InventoryPlayer playerInv, World world, BlockPos pos) {
      this.worldObj = world;
      this.pos = pos;
      this.addSlotToContainer(new SlotCrafting(playerInv.player, this.craftMatrix, this.craftResult, 0, 124, 35));

      for (int l = 0; l < 3; ++l) {
         for (int i1 = 0; i1 < 3; ++i1) {
            this.addSlotToContainer(new Slot(this.craftMatrix, i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
         }
      }

      for (int y = 0; y < 3; ++y) {
         for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
         }
      }

      for (int x = 0; x < 9; ++x) {
         this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
      }

      this.onCraftMatrixChanged(this.craftMatrix);
   }

   @Override
   public void onCraftMatrixChanged(IInventory inventory) {
      ItemStack result = ItemStack.EMPTY;
      IRecipe recipe = CraftingManager.findMatchingRecipe(this.craftMatrix, this.worldObj);
      if (recipe != null) {
         result = recipe.getCraftingResult(this.craftMatrix);
      }
      this.craftResult.setInventorySlotContents(0, result);
   }

   @Override
   public void onContainerClosed(EntityPlayer player) {
      super.onContainerClosed(player);
      if (!this.worldObj.isRemote) {
         for (int i = 0; i < 9; ++i) {
            ItemStack stack = this.craftMatrix.removeStackFromSlot(i);
            if (!stack.isEmpty()) {
               player.dropItem(stack, false);
            }
         }
      }
   }

   @Override
   public boolean canInteractWith(EntityPlayer player) {
      if (this.worldObj.getBlockState(this.pos).getBlock() != CandyCraft.CandyWorkbench) {
         return false;
      }
      return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int index) {
      ItemStack itemstack = ItemStack.EMPTY;
      Slot slot = this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index == 0) {
            if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
               return ItemStack.EMPTY;
            }
            slot.onSlotChange(itemstack1, itemstack);
         } else if (index >= 10 && index < 37) {
            if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
               return ItemStack.EMPTY;
            }
         } else if (index >= 37 && index < 46) {
            if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
               return ItemStack.EMPTY;
            }
         } else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
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

   @Override
   public boolean canMergeSlot(ItemStack stack, Slot slot) {
      return slot.inventory != this.craftResult && super.canMergeSlot(stack, slot);
   }
}
