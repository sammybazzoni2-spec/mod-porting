package com.valentin4311.candycraftmod;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntitySugarFactory extends TileEntity implements ISidedInventory, ITickable {
   public boolean advancedMode = false;
   public boolean checked = false;
   private NonNullList<ItemStack> FactoryItemStacks = NonNullList.withSize(2, ItemStack.EMPTY);
   public int currentTime = 0;
   private String customName;
   public static Map<ItemStack, ItemStack> recipeList = new HashMap<ItemStack, ItemStack>();
   public static Map<ItemStack, ItemStack> advancedRecipeList = new HashMap<ItemStack, ItemStack>();
   public static Map<ItemStack, ItemStack> recipes = new HashMap<ItemStack, ItemStack>();

   public static boolean isItemValid(ItemStack i) {
      return !i.isEmpty() && CandyCraft.itemList.contains(i.getItem());
   }

   @Override
   public int getSizeInventory() {
      return this.FactoryItemStacks.size();
   }

   @Override
   public boolean isEmpty() {
      for (ItemStack stack : this.FactoryItemStacks) {
         if (!stack.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public void readFromNBT(NBTTagCompound compound) {
      super.readFromNBT(compound);
      this.FactoryItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
      ItemStackHelper.loadAllItems(compound, this.FactoryItemStacks);
      this.currentTime = compound.getShort("BurnTime");
      if (compound.hasKey("CustomName")) {
         this.customName = compound.getString("CustomName");
      }
   }

   @Override
   public NBTTagCompound writeToNBT(NBTTagCompound compound) {
      super.writeToNBT(compound);
      compound.setShort("BurnTime", (short)this.currentTime);
      ItemStackHelper.saveAllItems(compound, this.FactoryItemStacks);
      if (this.hasCustomName()) {
         compound.setString("CustomName", this.customName);
      }

      return compound;
   }

   @Override
   public ItemStack getStackInSlot(int i) {
      return this.FactoryItemStacks.get(i);
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public ItemStack decrStackSize(int index, int count) {
      ItemStack stack = ItemStackHelper.getAndSplit(this.FactoryItemStacks, index, count);
      if (!stack.isEmpty()) {
         this.markDirty();
      }

      return stack;
   }

   @Override
   public ItemStack removeStackFromSlot(int index) {
      return ItemStackHelper.getAndRemove(this.FactoryItemStacks, index);
   }

   @Override
   public void setInventorySlotContents(int i, ItemStack itemstack) {
      this.FactoryItemStacks.set(i, itemstack);
      if (!itemstack.isEmpty() && itemstack.getCount() > this.getInventoryStackLimit()) {
         itemstack.setCount(this.getInventoryStackLimit());
      }

      this.markDirty();
   }

   @Override
   public boolean isUsableByPlayer(EntityPlayer player) {
      return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
   }

   @Override
   public void update() {
      if (!this.checked) {
         this.advancedMode = this.world.getBlockState(this.pos).getBlock() == CandyCraft.AdvancedSugarFactory;
         this.checked = true;
      }

      ItemStack in = this.FactoryItemStacks.get(0);
      ItemStack out = this.FactoryItemStacks.get(1);
      if (!in.isEmpty() && in.getItem() != Items.SUGAR) {
         ItemStack result = new ItemStack(Items.SUGAR, 1);
         ItemStack base = ItemStack.EMPTY;
         Item item = in.getItem();
         if (item == Items.STICK) {
            result = new ItemStack(CandyCraft.CandyStick);
         }

         if (item == Item.getItemFromBlock(CandyCraft.CandyFlower)) {
            result = new ItemStack(CandyCraft.HoneyShard);
         }

         if (item == Item.getItemFromBlock(CandyCraft.ChocolateStone)) {
            result = new ItemStack(CandyCraft.ChocolateCoins);
         }

         if (item == Item.getItemFromBlock(CandyCraft.HoneyBlock)) {
            result = new ItemStack(CandyCraft.HoneyComb);
         }

         if (item == Item.getItemFromBlock(CandyCraft.NougatBlock) && this.advancedMode) {
            result = new ItemStack(CandyCraft.NougatHead, 1, 1);
         }

         if (item == Item.getItemFromBlock(CandyCraft.GoldenFlower) && this.advancedMode) {
            result = new ItemStack(Items.GOLD_NUGGET);
         }

         if (item == CandyCraft.GrenadineBucket) {
            base = new ItemStack(Items.BUCKET, 1);
         }

         if (item == CandyCraft.CaramelBucket) {
            base = new ItemStack(Items.BUCKET, 1);
         }

         if (isItemValid(in) && (out.isEmpty() || out.getCount() < 64 && out.getItem() == result.getItem())) {
            this.currentTime += this.advancedMode ? 2 : 1;
         } else {
            this.currentTime = 0;
         }

         if (this.currentTime >= 240 && !this.world.isRemote) {
            if (out.isEmpty()) {
               this.FactoryItemStacks.set(1, result);
               this.currentTime = 0;
            } else if (out.getCount() < 64 && result.getItem() == out.getItem()) {
               out.grow(1);
               this.currentTime = 0;
            }

            if (base.isEmpty()) {
               in.shrink(1);
               if (in.getCount() <= 0) {
                  this.FactoryItemStacks.set(0, ItemStack.EMPTY);
               }
            } else {
               this.FactoryItemStacks.set(0, base);
            }

            this.markDirty();
         }
      } else {
         this.currentTime = 0;
      }
   }

   @Override
   public boolean isItemValidForSlot(int i, ItemStack itemstack) {
      return i != 1;
   }

   @Override
   public int[] getSlotsForFace(EnumFacing side) {
      return new int[]{0, 1};
   }

   @Override
   public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
      return index != 1;
   }

   @Override
   public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
      return index != 0;
   }

   @Override
   public String getName() {
      return this.hasCustomName() ? this.customName : "container.SugarFactory";
   }

   @Override
   public boolean hasCustomName() {
      return this.customName != null && this.customName.length() > 0;
   }

   public void setCustomName(String name) {
      this.customName = name;
   }

   @Override
   public ITextComponent getDisplayName() {
      return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
   }

   @Override
   public void openInventory(EntityPlayer player) {
   }

   @Override
   public void closeInventory(EntityPlayer player) {
   }

   @Override
   public int getField(int id) {
      return id == 0 ? this.currentTime : 0;
   }

   @Override
   public void setField(int id, int value) {
      if (id == 0) {
         this.currentTime = value;
      }
   }

   @Override
   public int getFieldCount() {
      return 1;
   }

   @Override
   public void clear() {
      this.FactoryItemStacks.clear();
   }

   static {
      recipeList.put(new ItemStack(Items.STICK), new ItemStack(Items.STICK));
   }
}
