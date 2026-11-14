package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityEgg extends TileEntity implements ITickable {
   Random rand = new Random();
   public int timeLeft = 300;
   public int hatchDelay = 50;

   public TileEntityEgg() {
      this.timeLeft = this.rand.nextInt(24000) + 48000;
   }

   @Override
   public void update() {
      boolean readyToHatch = true;
      for (int x = 0; x < 5; ++x) {
         for (int z = 0; z < 5; ++z) {
            if ((x == 0 || x == 4 || z == 0 || z == 4) && this.world.getBlockState(this.pos.add(-2 + x, 0, -2 + z)).getBlock() != CandyCraft.GoldenFlower) {
               readyToHatch = false;
            }
         }
      }

      if (readyToHatch || this.timeLeft <= this.hatchDelay) {
         --this.timeLeft;
         if (!this.world.isRemote) {
            if (this.timeLeft == this.hatchDelay) {
               if (this.world.getBlockState(this.pos).getBlock() == CandyCraft.DragonEgg && CandyCraftConfig.allowDragons) {
                  EntityDragon dragon = new EntityDragon(this.world);
                  dragon.setPosition((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 1.0D, (double)this.pos.getZ());
                  this.world.spawnEntity(dragon);
               } else if (CandyCraftConfig.allowBeetleKings) {
                  EntityKingBeetle beetle = new EntityKingBeetle(this.world);
                  beetle.setPosition((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 1.0D, (double)this.pos.getZ());
                  this.world.spawnEntity(beetle);
                  beetle.entityDropItem(new ItemStack(CandyCraft.ChewingGumEmblem), 0.5F);
               }
            }

            if (this.timeLeft <= 0) {
               this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
               for (int x = 0; x < 5; ++x) {
                  for (int z = 0; z < 5; ++z) {
                     BlockPos p = this.pos.add(-2 + x, 0, -2 + z);
                     if ((x == 0 || x == 4 || z == 0 || z == 4) && this.world.getBlockState(p).getBlock() == CandyCraft.GoldenFlower) {
                        this.world.setBlockState(p, CandyCraft.CandyFlower.getDefaultState());
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
      this.readFromNBT(pkt.getNbtCompound());
   }

   @Override
   public NBTTagCompound getUpdateTag() {
      return this.writeToNBT(new NBTTagCompound());
   }

   @Override
   public SPacketUpdateTileEntity getUpdatePacket() {
      return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
   }

   @Override
   public void readFromNBT(NBTTagCompound compound) {
      super.readFromNBT(compound);
      this.timeLeft = compound.getInteger("hatch");
   }

   @Override
   public NBTTagCompound writeToNBT(NBTTagCompound compound) {
      super.writeToNBT(compound);
      compound.setInteger("hatch", this.timeLeft);
      return compound;
   }
}
