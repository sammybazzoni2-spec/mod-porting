package com.valentin4311.candycraftmod;

import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityAlchemy extends TileEntity {
   private int liquidQuantity = 0;
   private boolean topFilled = false;
   private ArrayList<ItemStack> recipes = new ArrayList<ItemStack>();
   private ArrayList<Item> compatibleItems;

   public TileEntityAlchemy() {
      this.compatibleItems = new ArrayList<Item>(Arrays.asList(Items.COOKIE, Items.SUGAR, CandyCraft.CaramelBucket, CandyCraft.GummyBall, CandyCraft.MarshmallowFlower, Item.getItemFromBlock(CandyCraft.CandyFlower), Item.getItemFromBlock(CandyCraft.BananaBlock), Item.getItemFromBlock(CandyCraft.MintBlock), Item.getItemFromBlock(CandyCraft.RaspberryBlock), Item.getItemFromBlock(CandyCraft.PoisonousFlower), CandyCraft.Lollipop, CandyCraft.BarleySugar, CandyCraft.Dragibus, CandyCraft.LollipopSeeds, CandyCraft.CranberryFish, CandyCraft.Gummy, CandyCraft.Licorice, CandyCraft.HoneyShard, CandyCraft.HoneyComb, CandyCraft.PEZ, CandyCraft.PEZDust, CandyCraft.SugarCrystal, CandyCraft.ChewingGum, CandyCraft.CandiedCherry, CandyCraft.NougatPowder, CandyCraft.CottonCandy, CandyCraft.Waffle, CandyCraft.WaffleNugget));
   }

   public boolean isTopFilled() {
      return this.topFilled;
   }

   public void setTopFilled(boolean i) {
      this.topFilled = i;
   }

   public int getLiquid() {
      return this.liquidQuantity;
   }

   public void setLiquid(int i) {
      this.liquidQuantity = i;
   }

   public boolean addPotionToRecipes(ItemStack stack) {
      if (!stack.isEmpty() && !this.world.isRemote) {
         if (!this.compatibleItems.contains(stack.getItem())) {
            return false;
         } else {
            this.recipes.add(stack.copy());
            if (this.recipes.size() >= 4) {
               float f = 0.7F;
               double d0 = (double)(this.world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
               double d1 = (double)(this.world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.2D + 0.6D;
               double d2 = (double)(this.world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
               ItemStack itemstack1 = new ItemStack(CandyCraft.GrenadineCandy);
               String meta = "";
               for (ItemStack st : this.recipes) {
                  if (!st.isEmpty()) {
                     String id = String.valueOf(net.minecraft.potion.Potion.getIdFromPotion(AlchemyRecipes.getPotionIdForItem(st)));
                     if (id.length() < 2) {
                        id = "0" + id;
                     }

                     meta = meta + id;
                  }
               }

               int i = Integer.valueOf(meta);
               itemstack1.setItemDamage(i);
               itemstack1.setTagCompound(new NBTTagCompound());
               itemstack1.getTagCompound().setInteger("MetaSystem", i);
               EntityItem entityitem = new EntityItem(this.world, (double)this.pos.getX() + d0, (double)this.pos.getY() + d1, (double)this.pos.getZ() + d2, itemstack1);
               entityitem.setPickupDelay(10);
               this.world.spawnEntity(entityitem);
               this.setTopFilled(false);
               if (this.getLiquid() > 0) {
                  this.setLiquid(this.getLiquid() - 1);
                  this.setTopFilled(true);
               }

               this.recipes.clear();
            }

            this.refreshPackets();
            return true;
         }
      } else {
         return false;
      }
   }

   /** Marks the tile dirty and sends it to the clients. */
   public void refreshPackets() {
      this.markDirty();
      if (this.world != null) {
         IBlockState state = this.world.getBlockState(this.pos);
         this.world.notifyBlockUpdate(this.pos, state, state, 3);
      }
   }

   public int getIngredientsCount() {
      return this.recipes.size();
   }

   @SideOnly(Side.CLIENT)
   @Override
   public double getMaxRenderDistanceSquared() {
      return 8192.0D;
   }

   @Override
   public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
      this.readFromNBT(pkt.getNbtCompound());
   }

   @Override
   public NBTTagCompound writeToNBT(NBTTagCompound compound) {
      super.writeToNBT(compound);
      compound.setInteger("liquidAmount", this.getLiquid());
      compound.setBoolean("isTopFilled", this.isTopFilled());
      for (int i = 0; i < 4 && i < this.recipes.size(); ++i) {
         if (!this.recipes.get(i).isEmpty()) {
            compound.setTag("StackContent" + i, this.recipes.get(i).writeToNBT(new NBTTagCompound()));
         }
      }

      return compound;
   }

   @Override
   public void readFromNBT(NBTTagCompound compound) {
      super.readFromNBT(compound);
      this.setLiquid(compound.getInteger("liquidAmount"));
      this.setTopFilled(compound.getBoolean("isTopFilled"));
      this.recipes.clear();
      for (int i = 0; i < 4; ++i) {
         if (compound.hasKey("StackContent" + i)) {
            this.recipes.add(new ItemStack(compound.getCompoundTag("StackContent" + i)));
         }
      }
   }

   @Override
   public NBTTagCompound getUpdateTag() {
      return this.writeToNBT(new NBTTagCompound());
   }

   @Override
   public SPacketUpdateTileEntity getUpdatePacket() {
      return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
   }
}
