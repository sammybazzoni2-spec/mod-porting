package com.valentin4311.candycraftmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class TileEntityTeleporter extends TileEntity implements ITickable {
   public int dungeonID = -1;
   public boolean generated = false;
   public EntityPlayer player = null;
   public int x = 0;
   public int y = 64;
   public int z = 0;
   public int oX;
   public int oY;
   public int oZ;
   public int dim = 0;
   public int tickExisted = 0;

   @Override
   public void readFromNBT(NBTTagCompound compound) {
      super.readFromNBT(compound);
      this.generated = compound.getBoolean("generated");
      this.x = compound.getInteger("xPort");
      this.y = compound.getInteger("yPort");
      this.z = compound.getInteger("zPort");
      this.dim = compound.getInteger("dim");
      this.tickExisted = compound.getInteger("tickExisted");
   }

   @Override
   public void update() {
      if (this.dungeonID != -1 && !this.generated) {
         this.generated = true;
         this.genDungeon(this.world);
      }

      if (this.tickExisted < 200) {
         ++this.tickExisted;
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

   @Override
   public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
      this.readFromNBT(pkt.getNbtCompound());
   }

   public void genDungeon(World world) {
      (new WorldGenSlimeDungeon(this.oX, this.oY, this.oZ, this.dim)).generate(FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(CandyCraft.idDimension2), world.rand, new BlockPos(this.dungeonID * 1000, 64, 0));
      if (this.player != null) {
         this.player.sendMessage(new TextComponentString("§e" + (new TextComponentTranslation("chat.generated")).getUnformattedComponentText()));
      }
   }

   @Override
   public NBTTagCompound writeToNBT(NBTTagCompound compound) {
      super.writeToNBT(compound);
      compound.setBoolean("generated", this.generated);
      compound.setInteger("xPort", this.x);
      compound.setInteger("yPort", this.y);
      compound.setInteger("zPort", this.z);
      compound.setInteger("dim", this.dim);
      compound.setInteger("tickExisted", this.tickExisted);
      return compound;
   }
}
