package com.valentin4311.candycraftmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntitySugarFurnace extends TileEntity implements ISidedInventory, ITickable {
   private NonNullList<ItemStack> furnaceItemStacks = NonNullList.withSize(3, ItemStack.EMPTY);
   public int furnaceBurnTime;
   public int currentItemBurnTime;
   public int furnaceCookTime;
   String customName;

   @Override
   public int getSizeInventory() {
      return this.furnaceItemStacks.size();
   }

   @Override
   public boolean isEmpty() {
      for (ItemStack stack : this.furnaceItemStacks) {
         if (!stack.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public ItemStack getStackInSlot(int index) {
      return this.furnaceItemStacks.get(index);
   }

   @Override
   public ItemStack decrStackSize(int index, int count) {
      return ItemStackHelper.getAndSplit(this.furnaceItemStacks, index, count);
   }

   @Override
   public ItemStack removeStackFromSlot(int index) {
      return ItemStackHelper.getAndRemove(this.furnaceItemStacks, index);
   }

   @Override
   public void setInventorySlotContents(int index, ItemStack stack) {
      this.furnaceItemStacks.set(index, stack);
      if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit()) {
         stack.setCount(this.getInventoryStackLimit());
      }
   }

   public void setCustomName(String name) {
      this.customName = name;
   }

   @Override
   public void readFromNBT(NBTTagCompound compound) {
      super.readFromNBT(compound);
      this.furnaceItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
      ItemStackHelper.loadAllItems(compound, this.furnaceItemStacks);
      this.furnaceBurnTime = compound.getShort("BurnTime");
      this.furnaceCookTime = compound.getShort("CookTime");
      this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks.get(1));
      if (compound.hasKey("CustomName", 8)) {
         this.customName = compound.getString("CustomName");
      }
   }

   @Override
   public NBTTagCompound writeToNBT(NBTTagCompound compound) {
      super.writeToNBT(compound);
      compound.setShort("BurnTime", (short)this.furnaceBurnTime);
      compound.setShort("CookTime", (short)this.furnaceCookTime);
      ItemStackHelper.saveAllItems(compound, this.furnaceItemStacks);
      if (this.hasCustomName()) {
         compound.setString("CustomName", this.customName);
      }

      return compound;
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @SideOnly(Side.CLIENT)
   public int getCookProgressScaled(int scale) {
      return this.furnaceCookTime * scale / 200;
   }

   @SideOnly(Side.CLIENT)
   public int getBurnTimeRemainingScaled(int scale) {
      if (this.currentItemBurnTime == 0) {
         this.currentItemBurnTime = 200;
      }

      return this.furnaceBurnTime * scale / this.currentItemBurnTime;
   }

   public boolean isBurning() {
      return this.furnaceBurnTime > 0;
   }

   @Override
   public void update() {
      boolean flag = this.furnaceBurnTime > 0;
      boolean flag1 = false;
      if (this.furnaceBurnTime > 0) {
         --this.furnaceBurnTime;
      }

      if (!this.world.isRemote) {
         if (this.furnaceBurnTime == 0 && this.canSmelt()) {
            this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks.get(1));
            if (this.furnaceBurnTime > 0) {
               flag1 = true;
               ItemStack fuel = this.furnaceItemStacks.get(1);
               if (!fuel.isEmpty()) {
                  Item container = fuel.getItem().getContainerItem();
                  fuel.shrink(1);
                  if (fuel.isEmpty()) {
                     this.furnaceItemStacks.set(1, container == null ? ItemStack.EMPTY : new ItemStack(container));
                  }
               }
            }
         }

         if (this.isBurning() && this.canSmelt()) {
            ++this.furnaceCookTime;
            if (this.furnaceCookTime == 200) {
               this.furnaceCookTime = 0;
               this.smeltItem();
               flag1 = true;
            }
         } else {
            this.furnaceCookTime = 0;
         }

         if (flag != this.furnaceBurnTime > 0) {
            flag1 = true;
            BlockCandyFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.world, this.pos);
         }
      }

      if (flag1) {
         this.markDirty();
      }
   }

   private boolean canSmelt() {
      ItemStack input = this.furnaceItemStacks.get(0);
      if (input.isEmpty()) {
         return false;
      } else {
         ItemStack itemstack = CandyFurnaceRecipe.smelting().getSmeltingResult(input);
         if (itemstack == null || itemstack.isEmpty()) {
            return false;
         } else {
            ItemStack output = this.furnaceItemStacks.get(2);
            if (output.isEmpty()) {
               return true;
            } else if (!output.isItemEqual(itemstack)) {
               return false;
            } else {
               int result = output.getCount() + itemstack.getCount();
               return result <= this.getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
            }
         }
      }
   }

   public void smeltItem() {
      if (this.canSmelt()) {
         ItemStack input = this.furnaceItemStacks.get(0);
         ItemStack itemstack = CandyFurnaceRecipe.smelting().getSmeltingResult(input);
         ItemStack output = this.furnaceItemStacks.get(2);
         if (output.isEmpty()) {
            this.furnaceItemStacks.set(2, itemstack.copy());
            output = this.furnaceItemStacks.get(2);
         } else if (output.isItemEqual(itemstack)) {
            output.grow(itemstack.getCount());
         }

         boolean shouldRemove = true;
         if (output.getItem() == Item.getItemFromBlock(CandyCraft.CaramelBlock) && input.getItem() == CandyCraft.CaramelBucket) {
            this.furnaceItemStacks.set(0, new ItemStack(Items.BUCKET));
            shouldRemove = false;
         }

         if (shouldRemove) {
            input.shrink(1);
            if (input.getCount() <= 0) {
               this.furnaceItemStacks.set(0, ItemStack.EMPTY);
            }
         }
      }
   }

   public static int getItemBurnTime(ItemStack stack) {
      if (stack.isEmpty()) {
         return 0;
      } else {
         Item item = stack.getItem();
         if (item == Items.SUGAR) {
            return 300;
         } else {
            return item == Item.getItemFromBlock(CandyCraft.SugarBlock) ? 1200 : TileEntityFurnace.getItemBurnTime(stack);
         }
      }
   }

   public static boolean isItemFuel(ItemStack stack) {
      return getItemBurnTime(stack) > 0;
   }

   @Override
   public boolean isUsableByPlayer(EntityPlayer player) {
      return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
   }

   @Override
   public boolean isItemValidForSlot(int index, ItemStack stack) {
      return index == 2 ? false : (index == 1 ? isItemFuel(stack) : true);
   }

   @Override
   public String getName() {
      return this.hasCustomName() ? this.customName : "container.SugarFurnace";
   }

   @Override
   public boolean hasCustomName() {
      return this.customName != null && this.customName.length() > 0;
   }

   @Override
   public ITextComponent getDisplayName() {
      return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation("Block.SugarFurnace");
   }

   @Override
   public void openInventory(EntityPlayer player) {
   }

   @Override
   public void closeInventory(EntityPlayer player) {
   }

   @Override
   public int[] getSlotsForFace(EnumFacing side) {
      return new int[]{0, 1, 2};
   }

   @Override
   public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
      return index != 2 && isItemFuel(stack) && index == 1 || index == 0 && !isItemFuel(stack);
   }

   @Override
   public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
      return index == 2;
   }

   @Override
   public int getField(int id) {
      switch (id) {
         case 0:
            return this.furnaceBurnTime;
         case 1:
            return this.currentItemBurnTime;
         case 2:
            return this.furnaceCookTime;
         default:
            return 0;
      }
   }

   @Override
   public void setField(int id, int value) {
      switch (id) {
         case 0:
            this.furnaceBurnTime = value;
            break;
         case 1:
            this.currentItemBurnTime = value;
            break;
         case 2:
            this.furnaceCookTime = value;
            break;
         default:
            break;
      }
   }

   @Override
   public int getFieldCount() {
      return 3;
   }

   @Override
   public void clear() {
      this.furnaceItemStacks.clear();
   }
}
