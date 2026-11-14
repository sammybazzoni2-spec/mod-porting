package com.valentin4311.candycraftmod;

import java.util.UUID;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityCandyWolf extends EntityWolf {
   private static final DataParameter<Integer> DRINK = EntityDataManager.<Integer>createKey(EntityCandyWolf.class, DataSerializers.VARINT);

   public EntityCandyWolf(World world) {
      super(world);
   }

   @Override
   public boolean isBreedingItem(ItemStack stack) {
      return !stack.isEmpty() && stack.getItem() == CandyCraft.BarleySugar;
   }

   @Override
   public EntityCandyWolf createChild(EntityAgeable ageable) {
      EntityCandyWolf child = new EntityCandyWolf(this.world);
      UUID owner = this.getOwnerId();
      if (owner != null) {
         child.setTimeUntilDrink(this.world.rand.nextInt(12000) + 5000);
         child.setOwnerId(owner);
         child.setTamed(true);
      }
      return child;
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (!this.world.isRemote && this.isTamed() && this.getTimeUntilDrink() > 0) {
         boolean tree = false;
         for (int i = 0; i < 4; ++i) {
            BlockPos pos = new BlockPos((int)Math.floor(this.posX), (int)Math.floor(this.posY) + i, (int)Math.floor(this.posZ));
            net.minecraft.block.state.IBlockState state = this.world.getBlockState(pos);
            if (state.getBlock() == CandyCraft.CandyLeave && state.getValue(BlockCandyLeave.VARIANT) == 1) {
               tree = true;
            }
         }
         this.setTimeUntilDrink(this.getTimeUntilDrink() - (tree ? 2 : 1));
      }
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(DRINK, 0);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("Caramel", this.getTimeUntilDrink());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setTimeUntilDrink(compound.getInteger("Caramel"));
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (this.isTamed()) {
         if (!stack.isEmpty() && stack.getItem() == Items.BUCKET && this.getTimeUntilDrink() < 1) {
            if (!this.world.isRemote) {
               ItemStack bucket = new ItemStack(CandyCraft.CaramelBucket);
               if (!player.capabilities.isCreativeMode) {
                  stack.shrink(1);
               }
               if (stack.isEmpty()) {
                  player.setHeldItem(hand, bucket);
               } else if (!player.inventory.addItemStackToInventory(bucket)) {
                  player.dropItem(bucket, false);
               }
               this.setTimeUntilDrink(this.world.rand.nextInt(12000) + 5000);
            }
            return true;
         }
      } else if (!stack.isEmpty() && stack.getItem() == CandyCraft.BarleySugar && !this.isAngry()) {
         if (!player.capabilities.isCreativeMode) {
            stack.shrink(1);
         }
         if (!this.world.isRemote) {
            if (this.rand.nextInt(3) == 0) {
               this.setTamed(true);
               this.setTimeUntilDrink(this.world.rand.nextInt(12000) + 10000);
               this.navigator.clearPath();
               this.setAttackTarget(null);
               this.aiSit.setSitting(true);
               this.setHealth(20.0F);
               this.setOwnerId(player.getUniqueID());
               this.playTameEffect(true);
               this.world.setEntityState(this, (byte)7);
            } else {
               this.playTameEffect(false);
               this.world.setEntityState(this, (byte)6);
            }
         }
         return true;
      }
      return super.processInteract(player, hand);
   }

   public void setTimeUntilDrink(int ticks) {
      this.dataManager.set(DRINK, ticks);
   }

   public int getTimeUntilDrink() {
      return this.dataManager.get(DRINK);
   }
}
